from abc import ABC, abstractmethod
from utils.helpers import pattern_match
from exceptions import InvalidParameterError,WrongPasscodeError
import time


# def pattern_match(a,b):
#     return  False
class SmartDevice(ABC):
    _total_devices_created = 0

    def __init__(self, device_id, is_on, device_pattern):
        self.__pattern = device_pattern
        if pattern_match(self.__pattern, device_id):
            self._device_id = device_id
            SmartDevice._total_devices_created += 1
        else:
            raise InvalidParameterError(f"The ID you entered is invalid", 304)
        self.__is_on = is_on

    def turn_on(self):
        if self.__is_on:
            print("The device is already turned on")
        else:
            self.__is_on = True
            print("The device has been turned on")

    def turn_off(self):
        if self.__is_on:
            self.__is_on = False
            print(" The device has beeen turned off")
        else:
            print("The device is already turned off")

    def perform_action(self):
        pass

    @abstractmethod
    def get_status_report(self):
        pass

    @abstractmethod
    def get_supported_actions(self):
        pass

    @staticmethod
    def get_system_time():
        return time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))


class SmartAC(SmartDevice):
    __pattern_l = '^SAC'

    def __init__(self, device_id):
        super().__init__(device_id, False, SmartAC.__pattern_l)

    def perform_action(self):
        pass

    def get_status_report(self):
        pass

    def get_supported_actions(self):
        pass


class SmartPrinter(SmartDevice):
    __pattern_fr = '^SPR'

    def __init__(self, device_id):
        super().__init__(device_id, False, SmartPrinter.__pattern_fr)

    def perform_action(self):
        pass

    def get_status_report(self):
        pass

    def get_supported_actions(self):
        pass


class SmartDoorLock(SmartDevice):
    __pattern_dl = '^SDL'
    _is_lock = True
    __passcode = 'Admin'

    def __init__(self, device_id):
        super().__init__(device_id, False, SmartDoorLock.__pattern_dl)
        self._is_lock = SmartDoorLock._is_lock
        self.__passcode = SmartDoorLock.__passcode

    def perform_action(self, action_type, passcode=None):
        if passcode==self.__passcode:
            if action_type=='lock':
                if self._is_lock:
                    print("The device is already locked")
                else:
                    self._is_lock = True
                    print("The device has been locked")
            elif action_type=='unlock':
                if self._is_lock:
                    self._is_lock=False
                    print("The device is unlocked")
                else:
                    print("The device is already unlocked")
        else:
            raise WrongPasscodeError("Please enter a correct passcode",333)


    def get_status_report(self):
        pass

    def get_supported_actions(self):
        pass


class SmartCamera(SmartDevice):
    __pattern_sc = '^SC'

    def __init__(self, device_id):
        super().__init__(device_id, False, SmartCamera.__pattern_sc)

    def perform_action(self):
        pass

    def get_status_report(self):
        pass

    def get_supported_actions(self):
        pass


class SmartSpeaker(SmartDevice):
    __pattern_ss = '^SSP'

    def __init__(self, device_id):
        super().__init__(device_id, False, SmartSpeaker.__pattern_ss)

    def perform_action(self):
        pass

    def get_status_report(self):
        pass

    def get_supported_actions(self):
        pass

device_1 = SmartAC('SAC1601')
device_2 = SmartPrinter('SPR1601')
print(SmartDevice.get_system_time())

# a=SmartDevice('SN0605',True)
