import asyncio
from abc import ABC, abstractmethod

from core.devices import SmartDevice
from utils.decorators import log_device_state_change
from core.exceptions import UnsupportedActionError,DeviceStateError,InvalidParameterError



class Programmable(ABC):
    @abstractmethod
    async def schedule_task(self):
        pass


class SecuritySensor(SmartDevice):
    def __init__(self, device_id: str):
        super().__init__(device_id)
        self.__is_armed = False

    @property
    def is_armed(self) -> bool:
        return self.__is_armed

    def arm_sensor(self):
        self.__is_armed = True
        print(f"Sensor {self.device_id} armed.")

    def disarm_sensor(self):
        self.__is_armed = False
        print(f"Sensor {self.device_id} disarmed.")

    def get_status_report(self):
        return (
            f"[SecuritySensor] ID:{self.device_id}, ON:{self.is_on}, "
            f"Armed:{self.__is_armed}"
        )

    def get_supported_actions(self):
        return ["arm", "disarm"]

    @log_device_state_change
    async def perform_action(self, action_type, value=None):
        await asyncio.sleep(1)
        if action_type.lower() == "arm":
            if self.__is_armed:
                raise DeviceStateError("Sensor already armed.")
            self.arm_sensor()
        elif action_type.lower() == "disarm":
            if not self.__is_armed:
                raise DeviceStateError("Sensor already disarmed.")
            self.disarm_sensor()
        else:
            raise UnsupportedActionError("Unsupported action for SecuritySensor.")

    @log_device_state_change
    async def turn_off(self):
        self.__is_armed = False
        await super().turn_off()

    async def load_state(self, state: dict):
        if state["is_on"]:
            await self.turn_on()
        else:
            await self.turn_off()
        await self.perform_action("arm" if state["is_armed"] else "disarm")

    def to_dict(self):
        return {"type": self.__class__.__name__,"is_on": self.is_on,"is_armed": self.is_armed}


class SmartAlarmSystem(SmartDevice, Programmable):
    def __init__(self, device_id: str):
        super().__init__(device_id)
        self.__sound_volume = 0
        self.__sensors: list[SecuritySensor] = []
        self.add_sensor()

    @log_device_state_change
    def add_sensor(self):
        new_id = f"{self.device_id}.{len(self.__sensors) + 1}"
        self.__sensors.append(SecuritySensor(new_id))
        print(f"Sensor {new_id} added to {self.device_id}.")

    @log_device_state_change
    def remove_sensor(self, sensor_id: str | None = None):
        if any(s.is_armed for s in self.__sensors):
            raise DeviceStateError("Cannot remove sensors while system is armed.")
        if not self.__sensors:
            raise DeviceStateError("No sensors to remove.")
        if sensor_id is None:
            removed = self.__sensors.pop()
        else:
            self.__sensors = [s for s in self.__sensors if s.device_id != sensor_id]
            removed = sensor_id
        print(f"Sensor {removed} removed from {self.device_id}.")

    def get_status_report(self):
        status_lines = [s.get_status_report() for s in self.__sensors]
        return (
            f"[SmartAlarmSystem] ID:{self.device_id}, ON:{self.is_on}, "
            f"Sensors:{len(self.__sensors)}\n" + "\n".join(status_lines)
        )

    def get_supported_actions(self):
        return ["arm", "disarm", "set_siren_volume"]

    @log_device_state_change
    async def perform_action(self, action_type, value=None):
        await asyncio.sleep(1)
        action = action_type.lower()

        if action == "arm":
            if all(s.is_armed for s in self.__sensors):
                raise DeviceStateError("Alarm system already armed.")
            for s in self.__sensors:
                await s.turn_on()
                await s.perform_action("arm")
            print(f"Alarm system {self.device_id} armed.")

        elif action == "disarm":
            if all(not s.is_armed for s in self.__sensors):
                raise DeviceStateError("Alarm system already disarmed.")
            for s in self.__sensors:
                await s.perform_action("disarm")
                await s.turn_off()
            print(f"Alarm system {self.device_id} disarmed.")

        elif action == "set_siren_volume":
            if not isinstance(value, (int, float)) or not 0 <= value <= 100:
                raise InvalidParameterError("Volume must be a number 0‑100.")
            self.__sound_volume = value
            print(f"{self.device_id} siren volume set to {value}.")

        else:
            raise UnsupportedActionError("Unsupported action for SmartAlarmSystem.")

    async def turn_off(self):
        for s in self.__sensors:
            await s.turn_off()
        await super().turn_off()

    async def schedule_task(self):
        now = SmartDevice.get_system_time()
        arm_time = now.replace(hour=18, minute=0, second=0, microsecond=0)
        disarm_time = now.replace(hour=7, minute=0, second=0, microsecond=0)

        if now >= arm_time:
            await self.perform_action("arm")
        elif now >= disarm_time:
            await self.perform_action("disarm")

    async def load_state(self, state: dict):
        if state["is_on"]:
            await self.turn_on()
        else:
            await self.turn_off()

        id_map = {s.device_id: s for s in self.__sensors}
        for sid, blob in state["sensors"].items():
            if sid in id_map:
                await id_map[sid].load_state(blob)

    def to_dict(self):
        return {"type": self.__class__.__name__,"is_on": self.is_on,"sound_volume": self.__sound_volume,"sensors": {s.device_id: s.to_dict() for s in self.__sensors}}
