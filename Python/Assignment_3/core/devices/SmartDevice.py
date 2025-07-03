from abc import ABC, abstractmethod
from datetime import datetime
from core.exceptions import InvalidParameterError
from utils.helper import device_id_validate
class SmartDevice(ABC):
    _device_count = 0

    def __init__(self, device_id):
        if not device_id_validate(device_id):
            raise InvalidParameterError()
        self._device_id = device_id
        self.__is_on = False
        SmartDevice._device_count += 1

    def turn_on(self):
        self.__is_on = True
        print(f"Device {self._device_id} turned on.")

    def turn_off(self):
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
    def perform_action(self, action_type, value=None):
        pass
    @abstractmethod
    def get_supported_actions(self):
        pass
    @staticmethod
    def get_system_time():
        return datetime.now().time()

if __name__=='__main__':
    a=SmartDevice('a@1')