from abc import ABC, abstractmethod
from utils.helpers import pattern_match,passcode_validate
from core import exceptions
import time
from utils.decorators import log_status_change,require_device_on
import asyncio
from abc import ABCMeta

class DeviceRegistrarMeta(ABCMeta):
    registry = {}

    def __new__(mcs, name, bases, class_dict):
        cls = super().__new__(mcs, name, bases, class_dict)
        if not class_dict.get('__abstract__', False):
            DeviceRegistrarMeta.registry[name] = cls
        return cls

# def pattern_match(a,b):
#     return  False
class SmartDevice(ABC,metaclass=DeviceRegistrarMeta):
    _total_devices_created = 0
    _devices={}


    def __init__(self, device_id, is_on, device_pattern):
        self.__pattern = device_pattern
        if device_id not in self._devices:
            if pattern_match(self.__pattern, device_id):
                self._device_id = device_id
                SmartDevice._devices[device_id] = self
                SmartDevice._total_devices_created += 1
            else:
                raise exceptions.InvalidParameterError(f"The ID you entered is invalid", 304)
            self._is_on = is_on
        else:
            raise exceptions.DuplicateDeviceError("Sorry! This device cannot be added",3335)

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
            print(" The device has been turned off")
        else:
            print("The device is already turned off")

    async def perform_action(self,action_type,value=None,passcode=None):
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
        if not self._is_on:
            raise exceptions.DeviceOfflineError("Device is offline", 226)
        elif 0 <= level <= 100:
            self._brightness = level
            print(f"Brightness of {self._device_id} set to {level}")
        else:
            raise ValueError("Enter a valid brightness value (0-100)",226)

    async def perform_action(self,action_type,value=None):
        await asyncio.sleep(1)
        if action_type == "set_brightness":
            self.brightness = value
        else:
            raise exceptions.ActionNotSupportedError(f"Action '{action_type}' is not supported for SmartLight", 367)

    def get_status_report(self):
        return f"SmartLight {self._device_id}: ON={self._is_on}, Brightness={self._brightness}"

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
        if 0<= value <= 100:
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
        self.__track = track

    async def perform_action(self,action_type,value=None):
        try:
            if action_type == 'change_track':
                self.track = value
            elif action_type == 'set_volume':
                self.volume = value
            else:
                raise exceptions.ActionNotSupportedError("This action is not supported", 367)
        except exceptions.ActionNotSupportedError as e:
            print("ERROR", e)

    def get_status_report(self):
        return f"SmartSpeaker {self._device_id}: ON={self._is_on}, Volume={self.__volume}"

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

    async def perform_action(self, action_type, value=None,passcode=None):
        if self._is_on:
            if passcode==self.__passcode:
                if action_type=='lock':
                        if self._is_lock:
                            print("The device is already locked")
                        else:
                            self._is_lock = True
                            print("The device has been locked")
                        return True
                elif action_type=='unlock':
                        if self._is_lock:
                            self._is_lock=False
                            print("The device is unlocked")
                        else:
                            print("The device is already unlocked")
                        return True
                else:
                    raise exceptions.ActionNotSupportedError("This action is invalid", 367)
            else:
                raise exceptions.WrongPasscodeError("Please enter a correct passcode", 333)
        else:
            print("The device is not turned on")

    @log_status_change
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
            raise exceptions.WrongPasscodeError("Please enter a correct passcode", 333)
    @log_status_change
    async def turn_off(self,passcode=None):
        print("Turning off the device")
        await asyncio.sleep(1)
        if passcode==self.__passcode:
            if not self._is_on:
                print("The device is already turned off")
            else:
                self._is_on = False
                print("The device has been turned off")
        else:
            raise exceptions.WrongPasscodeError("Please enter a correct passcode", 333)
    @require_device_on
    def set_passcode(self,new_passcode,old_passcode=None):
        if self._is_on:
            if self.__passcode==old_passcode:
                if passcode_validate(new_passcode):
                    self.__passcode=new_passcode
                    print("The password has been changed")
                else:
                    raise exceptions.WrongPasscodeError("This is not a valid new passcode", 333)
            else:
                raise exceptions.WrongPasscodeError("wrong old passcode", 333)

        else:
            print("Please turn on the device first")

    def get_supported_actions(self):
        return SmartDoorLock.supported_actions

    def get_status_report(self):
        print(f"device_id: {self._device_id} turned on: {self._is_on} is_locked: {self._is_lock} ")


class SmartThermoStat(SmartDevice):
    __pattern_sc = '^ST'


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
        return f"SmartThermostat {self._device_id}: ON={self._is_on}, Temperature={self.__temperature}"

    async def perform_action(self, action_type, value=None):
        try:
            if action_type == "set_temperature":
                self.temperature = value
            else:
                raise exceptions.ActionNotSupportedError("This action is not supported", 367)
        except exceptions.ActionNotSupportedError as e:
            print("ERROR", e)

    def get_supported_actions(self):
        return "set_temperature"




if __name__ == "__main__":
    print(DeviceRegistrarMeta.registry)

    device_3= SmartDoorLock('SDL1601')
    asyncio.run(device_3.turn_on('Admin'))
    device_3.set_passcode('Kanishka123#','Admin')
    asyncio.run(device_3.turn_off('Kanishka123#'))
    asyncio.run(device_3.perform_action('lock','Kanishka123#'))
    asyncio.run(device_3.perform_action('unlock','Kanishka123#'))
    asyncio.run(device_3.turn_off('Kanishka123#'))

    device_3.get_status_report()
    print(device_3.get_supported_actions())
    print(device_3._SmartDoorLock__passcode)


    # device_3= SmartSpeaker('SS1601')
    # asyncio.run(device_3.turn_on())
    # print(device_3.volume)
    # # asyncio.run(device_3.perform_action('set_temperature',25))
    # print(device_3.track)
    # device_3.volume=70
    # device_3.track=100
    # print(device_3.volume)
    # print(device_3.track)
    #
    # asyncio.run(device_3.turn_off())
    # print(device_3.get_status_report())
    # print(device_3.get_supported_actions())

    # print(SmartDevice.get_system_time())

# a=SmartDevice('SN0605',True)
