from abc import ABC, abstractmethod
from datetime import datetime
from core.exceptions import InvalidParameterError
from utils.helper import device_id_validate
from utils.decorators import log_device_state_change
import asyncio

from abc import ABCMeta

class DeviceRegistrarMeta(ABCMeta):
    registry = {}

    def __new__(mcs, name, bases, class_dict):
        cls = super().__new__(mcs, name, bases, class_dict)
        if not class_dict.get('__abstract__', False):
            DeviceRegistrarMeta.registry[name] = cls
        return cls


class SmartDevice(ABC,metaclass=DeviceRegistrarMeta):
    _device_count = 0

    def __init__(self, device_id):
        if not device_id_validate(device_id):
            raise InvalidParameterError()
        self._device_id = device_id
        self.__is_on = False
        SmartDevice._device_count += 1
    @log_device_state_change
    async def turn_on(self):
        await asyncio.sleep(1)
        self.__is_on = True
        print(f"Device {self._device_id} turned on.")
    @log_device_state_change
    async def turn_off(self):
        await asyncio.sleep(1)
        self.__is_on = False
        print(f"Device {self._device_id} turned off.")

    def is_on(self):
        return self.__is_on

    @classmethod
    def get_device_count(cls):
        return cls._device_count

    @abstractmethod
    def get_status_report(self):
        pass

    @abstractmethod
    async def perform_action(self, action_type, value=None):
        pass
    @abstractmethod
    def get_supported_actions(self):
        pass
    @staticmethod
    def get_system_time():
        return datetime.now().time()

if __name__=='__main__':
    print(DeviceRegistrarMeta.registry)
    # a=SmartDevice('a@1')