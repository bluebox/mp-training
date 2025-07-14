from datetime import datetime

from core.exceptions import InvalidParameterError
from core.devicess import SmartDevice
from manager.scheduler import scheduler_instance
from abc import ABC, abstractmethod
import asyncio


class Programmable(ABC):
    @abstractmethod
    def schedule_task(self):
        pass


class SecuritySensor(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__is_armed = False

    def arm_sensor(self):
        self.__is_armed = True
        print(f"sensor {self._device_id} isarmed")
    def disarm_sensor(self):
        self.__is_armed = False
        print(f"sensor {self._device_id} is disarmed")
    def get_status_report(self):
        return f"[Sensor] {self._device_id}       On: {self.is_on}      Armed: {self.__is_armed}"

    async def perform_action(self, action_type, value=None):
        try:
            if action_type == "arm":
                self.__is_armed = True
                print("sensir armed")
            elif action_type == "disarm":
                self.__is_armed = False
                print("sensor disarmed")
            else:
                print("invalid action for securitysensor")
                raise InvalidParameterError("invalid action")
        except:
            raise


class SmartAlarmSystem(SmartDevice, Programmable):
    def __init__(self, device_id, sensor=None):
        super().__init__(device_id)
        self.__is_armed = False
        self._sensor = sensor
        self.__siren_volume = 10

    @property
    def siren_volume(self):
        return self.__siren_volume

    @siren_volume.setter
    def siren_volume(self, value):
        try:
            if not isinstance(value,int):
                raise InvalidParameterError("volume soulg be integer")
            if 0 <= value <= 10:
                self.__siren_volume = value
                print(f"alarm siren volume setting {value}")
            else:
                print("siren volume should be between 0 and 10")
                raise InvalidParameterError("Volume range should be 0-10")
        except (InvalidParameterError) as e:
            print(f"Error:{e}")


    async def perform_action(self, action_type, value=None):
        timestamp = SmartDevice.get_system_time()
        try:

            if action_type == "arm":
                self.__is_armed = True
                print(f"alarm {self._device_id} armed")
                if self._sensor:
                    self._sensor.arm_sensor()
                log_data = f"{self.__class__.__name__},{self._device_id},Armed,{timestamp}\n"
                self.update_actions(log_data)

            elif action_type == "disarm":
                self.__is_armed = False
                print(f"alarm {self._device_id} disarmed")
                if self._sensor:
                    self._sensor.disarm_sensor()
                log_data = f"{self.__class__.__name__},{self._device_id},Disarmed,{timestamp}\n"
                self.update_actions(log_data)

            elif action_type == "set_volume":
                try:
                    self.siren_volume = int(value)
                except:
                    print("invalid siren volume value")

            else:
                print("invalid action for SmartAlarmSystem")
                raise InvalidParameterError("invalid action on alarm")
        except (InvalidParameterError):
            raise

    def get_status_report(self):
        report = f"[Alarm] {self._device_id}     On: {self.is_on}      Armed: {self.__is_armed}"
        if self._sensor:
            report += f"\n   {self._sensor._device_id} sensor connected"
        return report

    async def schedule_task(self,schedule):
        print("Alarm scheduling interface")
        try:
            time_input = input("Enter time to arm alarm (HH:MM): ")
            time_obj = datetime.strptime(time_input, "%H:%M").strftime("%H:%M")

            
            schedule.add_scheduled_task(
                time_str=time_obj,
                device_id=self._device_id,
                action_type="arm",
                value=None,
                user_role="Admin"
            )
            print(f"Alarm scheduled at {time_obj}")
        except Exception as e:
            print("Invalid time format:", e)

