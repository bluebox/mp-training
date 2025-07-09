from core.devices import SmartDevice
from core.exceptions import ActionNotSupportedError, InvalidParameterError, DeviceOfflineError
from datetime import datetime
from abc import ABC, abstractmethod
import asyncio

# from utils.decorators import require_device_on


class SecuritySensor(SmartDevice):
    __pattern='^SS'
    __is_arm=False
    supported_actions=('arm','disarm')

    def __init__(self,device_id):
        super().__init__(device_id,False,SecuritySensor.__pattern)
        self.__is_arm=False

    def get_supported_actions(self):
        print(f"{SecuritySensor.supported_actions}")
    async def perform_action(self,action_type,value=None,passcode=None):
        if self._is_on:
            print(f"performing {action_type} on {self._device_id}")
            await asyncio.sleep(1)
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
        else:
            raise DeviceOfflineError("This device is offline",367)

    def get_status_report(self):
        print(f"device_id: {self._device_id} turned on: {self._is_on}  armed: {self.__is_arm}")


class Programmable(ABC):
    @abstractmethod
    def add_schedule(self, time_str, action, value):
        pass

    @abstractmethod
    async def run_scheduled_actions(self):
        pass


class SmartAlarmSystem(SmartDevice, Programmable):
    supported_actions=('arm','disarm',"set volume")
    __pattern='^SAS'
    def __init__(self, device_id, volume=5):
        super().__init__(device_id,False,SmartAlarmSystem.__pattern)
        self._armed = False
        self._volume = volume
        self._schedule = []

    async def perform_action(self, action_type, value=None,passcode=None):
        if self._is_on:
            print(f"performing {action_type} on {self._device_id}")
            await asyncio.sleep(1)
            if action_type == "arm":
                self._armed = True
                print(f"{self._device_id} is armed.")
            elif action_type == "disarm":
                self._armed = False
                print(f"{self._device_id} is disarmed.")
            elif action_type == "set_volume":
                if 0<=value<=100:
                    self._volume = value
                    print(f"{self._device_id} volume set to {value}.")
                else:
                    raise InvalidParameterError("Please enter a valid volume value between 0 and 100",333)
            else:
                raise ActionNotSupportedError("This action is invalid",367)
        else:
            raise DeviceOfflineError("This device is offline",367)

    def get_supported_actions(self):
        return SmartAlarmSystem.supported_actions

    def add_schedule(self, time_str, action_type, value=None):
            self._schedule.append({
                "time": time_str,
                "action_type": action_type,
                "value": value
            })

    async def run_scheduled_actions(self):
        now = datetime.now().strftime("%H:%M")
        for task in list(self._schedule):
            if task["time"] == now:
                await self.perform_action(task["action_type"], task["value"])
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
                print(f" - At {task['time']}: {task['action_type']} {task['value'] if task['value'] is not None else ''}")

if __name__ == "__main__":

    a=SmartAlarmSystem('SAS3923')
    asyncio.run(a.turn_on())
    print(a.get_supported_actions())
    asyncio.run(a.perform_action('disarm'))
    a.add_schedule('12:24', 'arm')
    a.add_schedule('12:24', 'disarm')
    a.add_schedule('12:23', 'set_volume',value=10)
    asyncio.run(a.run_scheduled_actions())
    print(a._schedule)



