from abc import ABC, abstractmethod
import asyncio
from smart_home_system.core.devices  import SmartDevice
from smart_home_system.core.exceptions import InvalidParameterError, DeviceOfflineError, ActionNotSupportedError
from datetime import datetime


class Programmable(ABC):
    @abstractmethod
    def schedule_task(self, time_str: str):
        pass


class SecuritySensor(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__is_armed = False

    def arm_sensor(self, value: bool):
        if not self.is_on:
            raise DeviceOfflineError(f"Device {self._device_id} must be ON to change arm status.")
        if not isinstance(value, bool):
            raise InvalidParameterError("Arm value must be a boolean.")

        if self.__is_armed == value:
            status = "armed" if value else "disarmed"
            print(f"Sensor {self._device_id} is already {status}.")
        else:
            self.__is_armed = value
            status = "armed" if value else "disarmed"
            print(f"Sensor {self._device_id} is now {status}.")

    @property
    def is_armed(self):
        return self.__is_armed

    @is_armed.setter
    def is_armed(self, value: bool):
        self.arm_sensor(value)

    def get_status_report(self):
        if self.is_on:
            return f"SecuritySensor {self._device_id} ON: {self.is_on} Armed: {self.__is_armed}"
        return f"SecuritySensor {self._device_id} ON: {self.is_on} "
    async def perform_action(self, action_type, value=None):
        await asyncio.sleep(1)
        if action_type == "arm":
            self.arm_sensor(True)
        elif action_type == "disarm":
            self.arm_sensor(False)
        elif action_type == "get_status_report":
            print(self.get_status_report())
        elif action_type == "turn_on":
            await self.turn_on()
        elif action_type == "turn_off":
            await self.turn_off()
        else:
            raise ActionNotSupportedError(f"{action_type} not supported by SecuritySensor.")

    def get_supported_actions(self):
        print("Supported actions: arm, disarm, turn_on, turn_off, get_status_report")


class SmartAlarmSystem(SmartDevice, Programmable):
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__armed = False
        self.__siren_volume = 5

    @property
    def armed(self):
        return self.__armed

    @armed.setter
    def armed(self, value: bool):
        if not isinstance(value, bool):
            raise InvalidParameterError("Armed value must be boolean.")
        self.__armed = value
        print(f"Alarm system {self._device_id} {'armed' if value else 'disarmed'}.")

    def get_status_report(self):
        if self.is_on:
            return f"SmartAlarmSystem {self._device_id} ON: {self.is_on} Armed: {self.__armed} Volume: {self.__siren_volume}"
        return f"SmartAlarmSystem {self._device_id} ON: {self.is_on}"

    async def perform_action(self, action_type, value=None):
        await asyncio.sleep(1)
        if action_type == "arm":
            self.armed = True
        elif action_type == "disarm":
            self.armed = False
        elif action_type == "turn_on":
            await self.turn_on()
        elif action_type == "turn_off":
            await self.turn_off()
        elif action_type == "set_volume":
            self.set_volume(value)
        elif action_type == "get_status_report":
            print(self.get_status_report())
        else:
            raise ActionNotSupportedError(f"{action_type} not supported by SmartAlarmSystem.")

    def get_supported_actions(self):
        print("Supported actions: arm, disarm, set_volume, turn_on, turn_off, get_status_report")

    def schedule_task(self, time_str: str):
        try:
            schedule_time = datetime.strptime(time_str, "%H:%M")
            print(f"Alarm system {self._device_id} scheduled for {schedule_time.strftime('%H:%M')}.")
        except ValueError:
            raise InvalidParameterError("Time format must be HH:MM")

    def set_volume(self,vol):
        if not self.is_on:
            raise DeviceOfflineError(f"Device {self._device_id} must be ON to set volume.")
        if isinstance(vol, int) and 1 <= vol <= 10:
            self.__siren_volume = vol
            print(f"Siren volume set to {vol}.")
        else:
            raise InvalidParameterError("Volume must be an integer between 1 and 10.")


