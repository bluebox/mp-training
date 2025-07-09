from abc import ABC, abstractmethod
from .devices import SmartDevice
from SmartHomeAutomationSystem.utils.decorators import log_device_state_change
from SmartHomeAutomationSystem.core.exceptions import ActionNotSupportedError, InvalidParameterError
import asyncio

class Programmable(ABC):
    @abstractmethod
    async def run_scheduled_task(self, action, value=None):
        pass

class SecuritySensor(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__armed = False

    @property
    def armed(self):
        return self.__armed

    @log_device_state_change
    async def turn_on(self):
        await asyncio.sleep(0.1)
        self._set_on(True)
        self.__armed = True

    @log_device_state_change
    async def turn_off(self):
        await asyncio.sleep(0.1)
        self._set_on(False)
        self.__armed = False

    def get_status_report(self):
        return {
            "type": "SecuritySensor",
            "id": self.device_id,
            "on": self.is_on,
            "armed": self.armed
        }

    async def perform_action(self, action, value=None):
        if action == "arm":
            self.__armed = True
            return "Sensor armed."
        elif action == "disarm":
            self.__armed = False
            return "Sensor disarmed."
        raise ActionNotSupportedError("Action not supported.")

    def get_supported_actions(self):
        return ["turn_on", "turn_off", "arm", "disarm"]

class SmartAlarmSystem(SmartDevice, Programmable):
    def __init__(self, device_id, siren_volume=70):
        super().__init__(device_id)
        self.__armed = False
        self.__siren_volume = siren_volume

    @property
    def armed(self):
        return self.__armed

    @property
    def siren_volume(self):
        return self.__siren_volume

    @siren_volume.setter
    def siren_volume(self, value):
        if not (0 <= value <= 100):
            raise InvalidParameterError("Siren volume must be 0-100.")
        self.__siren_volume = value

    @log_device_state_change
    async def turn_on(self):
        await asyncio.sleep(0.1)
        self._set_on(True)
        self.__armed = True

    @log_device_state_change
    async def turn_off(self):
        await asyncio.sleep(0.1)
        self._set_on(False)
        self.__armed = False

    def get_status_report(self):
        return {
            "type": "SmartAlarmSystem",
            "id": self.device_id,
            "on": self.is_on,
            "armed": self.armed,
            "siren_volume": self.siren_volume
        }

    async def perform_action(self, action, value=None):
        if action == "arm":
            self.__armed = True
            return "Alarm system armed."
        elif action == "disarm":
            self.__armed = False
            return "Alarm system disarmed."
        elif action == "set_siren_volume":
            self.siren_volume = value
            return f"Siren volume set to {value}."
        raise ActionNotSupportedError("Action not supported.")

    def get_supported_actions(self):
        return ["turn_on", "turn_off", "arm", "disarm", "set_siren_volume"]

    async def run_scheduled_task(self, action, value=None):
        return await self.perform_action(action, value)
