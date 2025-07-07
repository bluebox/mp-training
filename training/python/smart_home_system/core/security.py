from devices import SmartDevice
from core.exceptions import ActionNotSupportedError


class SecuritySensor(SmartDevice):
    __pattern='^SS'
    __is_arm=False
    __supported_actions=['arm','disarm']

    def __init__(self,device_id):
        super().__init__(device_id,False,SecuritySensor.__pattern)
        self.__is_arm=False

    def get_supported_actions(self):
        print(f"{SecuritySensor.__supported_actions}")

    def perform_action(self,action_type=None):
        if action_type=='arm':
            if self.__is_arm:
                print("The device is already armed")
            else:
                self.__is_arm=True
                print("The device is armed")
        elif action_type=='disarm':
            if not self.__is_arm:
                print("The device is already disarmed")
            else:
                self.__is_arm=False
                print("The device is now disarmed")
        else:
            raise ActionNotSupportedError("This action is invalid",367)

    def get_status_report(self):
        print(f"device_id: {self._device_id} turned on: {self._is_on}  armed: {self.__is_arm}")

from datetime import datetime
from core.devices.SmartDevice import SmartDevice
from abc import ABC, abstractmethod


class Programmable(ABC):
    @abstractmethod
    def add_schedule(self, time_str, action, value):
        pass

    @abstractmethod
    async def run_scheduled_actions(self):
        pass


class SmartAlarmSystem(SmartDevice, Programmable):
    def __init__(self, device_id, volume=5):
        super().__init__(device_id)
        self._armed = False
        self._volume = volume
        self._schedule = []
    async def perform_action(self, action, value=None):
        if action == "arm":
            self._armed = True
            print(f"{self._device_id} is armed.")
        elif action == "disarm":
            self._armed = False
            print(f"{self._device_id} is disarmed.")
        elif action == "set_volume":
            self._volume = value
            print(f"{self._device_id} volume set to {value}.")
        else:
            print(f"Invalid action: {action}")

    def get_supported_actions(self):
        return ["arm", "disarm", "set_volume"]

    def add_schedule(self, time_str, action, value=None):
        self._schedule.append({
            "time": time_str,
            "action": action,
            "value": value
        })

    async def run_scheduled_actions(self):
        now = datetime.now().strftime("%H:%M")
        for task in list(self._schedule):
            if task["time"] == now:
                await self.perform_action(task["action"], task["value"])
                self._schedule.remove(task)

    def get_status_report(self):
        print(f"Alarm ID: {self._device_id}")
        print("Status:", "Armed" if self._armed else "Disarmed")
        print("Volume:", self._volume)

        if not self._schedule:
            print("No scheduled tasks.")
        else:
            print("Scheduled tasks:")
            for task in self._schedule:
                print(f" - At {task['time']}: {task['action']} {task['value'] if task['value'] is not None else ''}")

if __name__ == "__main__":

    a=SecuritySensor('SS3923')
    a.turn_on()
    a.get_supported_actions()


