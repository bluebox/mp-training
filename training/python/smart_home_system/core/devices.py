from abc import ABC, abstractmethod
from utils.helpers import pattern_match
from exceptions import InvalidParameterError,WrongPasscodeError,ActionNotSupportedError,DeviceOfflineError
import time
from utils.decorators import log_status_change,require_device_on
import asyncio

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
        self._is_on = is_on
    @log_status_change
    async def turn_on(self):
        print("Turning on the device")
        await asyncio.sleep(1)
        if self._is_on:
            print("The device is already turned on")
        else:
            self._is_on = True
            print("The device has been turned on")
    @log_status_change
    async def turn_off(self):
        print("Turning off the device")
        await asyncio.sleep(1)
        if self._is_on:
            self._is_on = False
            print(" The device has beeen turned off")
        else:
            print("The device is already turned off")

    async def perform_action(self):
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

    @classmethod
    def get_device_count(cls):
        return cls._total_devices_created

class SmartLight(SmartDevice):
    __pattern_l = '^SL'
    supported_actions=["set_brightness"]

    def __init__(self, device_id):
        super().__init__(device_id, False, SmartLight.__pattern_l)
        self._brightness = 0

    @property
    def brightness(self):
        return self._brightness

    @brightness.setter
    def brightness(self, level):
        if not self._is_on():
            raise DeviceOfflineError("Device is offline",226)
        if 0 <= level <= 100:
            self._brightness = level
            print(f"Brightness of {self._device_id} set to {level}")
        else:
            raise ValueError("Enter a valid brightness value (0-100)",226)

    def perform_action(self,action_type,value=None):
        if action_type == "set_brightness":
            self.brightness = value
        else:
            raise ActionNotSupportedError(f"Action '{action_type}' is not supported for SmartLight",367)

    def get_status_report(self):
        return f"SmartLight {self._device_id}: ON={self._is_on()}, Brightness={self._brightness}"

    def get_supported_actions(self):
        return SmartLight.supported_actions


class SmartSpeaker(SmartDevice):
    __pattern_fr = '^SS'
    supported_actions=['set_volume', 'change_track']

    def __init__(self, device_id):
        super().__init__(device_id, False, SmartSpeaker.__pattern_fr)
        self.__volume = 0
        self.__track = None

    @property
    def volume(self):
        return self.__volume

    @volume.setter
    @require_device_on
    def volume(self, value):
        if value >= 0 and value <= 100:
            self.__volume = value
            print(f"Volume of {self._device_id} set to {value}")
        else:
            print("enter a valid range of volume in 1-100")

    @property
    def track(self):
        return self.__track

    @track.setter
    @require_device_on
    def track(self, track):
        self.track = track

    async def perform_action(self,action_type,value=None):
        try:
            if action_type == 'change_track':
                self.track = value
            elif action_type == 'set_volume':
                self.volume = value
            else:
                raise ActionNotSupportedError("This action is not supported",367)
        except ActionNotSupportedError as e:
            print("ERROR", e)

    def get_status_report(self):
        return f"SmartSpeaker {self._device_id}: ON={self._is_on()}, Volume={self.__volume}"

    def get_supported_actions(self):
        return SmartSpeaker.supported_actions


class SmartDoorLock(SmartDevice):
    __pattern_dl = '^SDL'
    _is_lock = True
    __passcode = 'Admin'
    supported_actions=['unlock','lock']

    def __init__(self, device_id):
        super().__init__(device_id, False, SmartDoorLock.__pattern_dl)
        self._is_lock = SmartDoorLock._is_lock
        self.__passcode = SmartDoorLock.__passcode

    async def perform_action(self, action_type, passcode=None):
        if self._is_on:
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
                    raise ActionNotSupportedError("This action is invalid",367)
            else:
                raise WrongPasscodeError("Please enter a correct passcode",333)
        else:
            print("The device is not turned on")


    async def turn_on(self,passcode=None):
        print("Turning on the device")
        await asyncio.sleep(1)
        if passcode==self.__passcode:
            if self._is_on:
                print("The device is already turned on")
            else:
                self._is_on = True
                print("The device has been turned on")
        else:
            raise WrongPasscodeError("Please enter a correct passcode",333)

    async def turn_off(self,passcode=None):
        print("Turning on the device")
        await asyncio.sleep(1)
        if passcode==self.__passcode:
            if not self._is_on:
                print("The device is already turned off")
            else:
                self._is_on = False
                print("The device has been turned off")
        else:
            raise WrongPasscodeError("Please enter a correct passcode",333)
    @require_device_on
    def set_passcode(self,new_passcode,old_passcode=None):
        if self._is_on:
            if self.__passcode==old_passcode:
                self.__passcode=new_passcode
                print("The password has been changed")
            else:
                raise WrongPasscodeError("Please enter a correct passcode",333)
        else:
            print("Please turn on the device first")

    def get_supported_actions(self):
        return SmartDoorLock.supported_actions

    def get_status_report(self):
        print(f"device_id: {self._device_id} turned on: {self._is_on} is_locked: {self._is_lock} ")


class SmartThermoStat(SmartDevice):
    __pattern_sc = '^SC'


    def __init__(self, device_id):
        super().__init__(device_id, False, SmartThermoStat.__pattern_sc)
        self.__temperature = 'Not set'

    @property
    def temperature(self):
        return self.__temperature

    @temperature.setter
    @require_device_on
    def temperature(self, temp):
        if 18.0 <= temp <= 30.0:
            self.__temperature = temp
            print(f"Temperature of {self._device_id} set to {temp}")
        else:
            print("Error: Temperature must be between 18 and 30 Celsius.")

    def get_status_report(self):
        return f"SmartThermostat {self._device_id}: ON={self._is_on()}, Temperature={self.__temperature}"

    async def perform_action(self, action_type, value=None):
        try:
            if action_type == "set_temperature":
                self.temperature = value
            else:
                raise ActionNotSupportedError("This action is not supported",367)
        except ActionNotSupportedError as e:
            print("ERROR", e)

    def get_supported_actions(self):
        return "set_temperature"




if __name__ == "__main__":

    # device_1 = SmartAC('SAC1601')
    # device_2 = SmartPrinter('SPR1601')

    device_3= SmartDoorLock('SDL1601')
    # device_3.turn_on('Admin')
    device_3.set_passcode('Kanishka123#','Admin')
    device_3.turn_on('Admin')
    device_3.set_passcode('Kanishka123#','Admin')
    asyncio.run(device_3.turn_off('Kanishka123#'))
    device_3.perform_action('lock','Kanishka123#')
    device_3.perform_action('unlock','Kanishka123#')
    device_3.get_status_report()
    print(device_3.get_supported_actions())


    # print(SmartDevice.get_system_time())

# a=SmartDevice('SN0605',True)
