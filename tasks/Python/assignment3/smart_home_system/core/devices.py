import asyncio
from datetime import datetime
from abc import ABC,abstractmethod
import re
from smart_home_system.core.exceptions import InvalidParameterError, PermissionDeniedError
from smart_home_system.utils.helpers import validate_id

# class DeviceRegistrarMeta(type):
#     def __new__(cls, *args, **kwargs):
#         super().__new__(cls,SmartDevice)
#         pass ,metaclass=DeviceRegistrarMeta

class SmartDevice(ABC):
    _total_devices_created = 0
    device_ids = []

    # def __init_subclass__(cls, **kwargs):
    #     super().__init_subclass__(**kwargs)
    #     cls.subclasses.append(cls)

    def __init__(self, device_id):
        if not validate_id(device_id):
            raise InvalidParameterError()
        self._device_id = device_id
        self.__is_on = False
        SmartDevice.device_ids.append(self._device_id)
        SmartDevice._total_devices_created += 1

    async def turn_on(self):
        if self.__is_on:
            print(f"Device {self._device_id} already ON.")
        else:
            self.__is_on = True
            await asyncio.sleep(1)
            print(f"Device {self._device_id} turned ON.")

    async def turn_off(self):
        if not self.__is_on:
            print(f"Device {self._device_id} already OFF.")
        else:
            self.__is_on = False
            await asyncio.sleep(1)
            print(f"Device {self._device_id} turned OFF.")

    @property
    def is_on(self):
        return self.__is_on

    @classmethod
    def get_device_count(cls):
        return cls._total_devices_created

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
        return datetime.now()

class SmartLight(SmartDevice):

    def __init__(self, device_id):
        super().__init__(device_id)
        self.__brightness = 0

    def set_brightness(self, level):
        if not self.is_on:
            print("Light must be on to set brightness.") #custom exception DeviceOfflineError
            return
        if 0 <= level <= 100:
            self.__brightness = level
            print(f"Brightness set to {level}%.")
        else:
            print("Invalid brightness level. Must be 0-100.") #InvalidParameterError

    @property
    def brightness(self):
        return self.__brightness

    @brightness.setter
    def brightness(self, level):
        self.set_brightness(level)

    def get_status_report(self):
        return f"Light {self._device_id} , ON: {self.is_on} , Brightness: {self.__brightness}"

    async def perform_action(self, action_type, value=None):
        if action_type == "set_brightness":
            self.set_brightness(value)
        elif action_type == "get_brightness":
            print(f"The brightness is at {self.brightness}")
        elif action_type == "turn_on":
            await self.turn_on()
        elif action_type == "turn_off":
            await self.turn_off()
        elif action_type == "get_status_report":
            print(self.get_status_report())
        else:
            print("Unsupported action for SmartLight.") #ActionNotSupportedError
        await asyncio.sleep(3)

    def get_supported_actions(self):
        print("Supported actions for SmartLight: ","set_brightness, get_brightness, turn_on, turn_off, get_status_report")

class SmartThermostat(SmartDevice):

    def __init__(self, device_id):
        super().__init__(device_id)
        self.__temperature = 20.0

    def set_temperature(self, temp):
        if not self.is_on:
            print("Thermostat must be ON to change temperature.")
            return
        if 18.0 <= temp <= 30.0:
            self.__temperature = temp
            print(f"Temperature set to {temp} °C.")
        else:
            print("Invalid temperature. Must be between 18-30 °C.")

    @property
    def temperature(self):
        return self.__temperature

    @temperature.setter
    def temperature(self, temp):
        self.set_temperature(temp)

    def get_status_report(self):
        return f"Thermostat {self._device_id} , ON: {self.is_on} , Temp: {self.__temperature} °C"

    async def perform_action(self, action_type, value=None):
        if action_type == "set_temperature":
            self.set_temperature(value)
        elif action_type == "get_temperature":
            print(f"The temperature is at {self.__temperature} °C")
        elif action_type == "turn_on":
            await self.turn_on()
        elif action_type == "turn_off":
            await self.turn_off()
        elif action_type == "get_status_report":
            print(self.get_status_report())
        else:
            print("Unsupported action for SmartThermostat.")
        await asyncio.sleep(3)

    def get_supported_actions(self):
        print("Supported actions for SmartThermostat: ","set_temperature, get_temperature, turn_on, turn_off, get_status_report")

class SmartDoorLock(SmartDevice):

    def __init__(self, device_id):
        super().__init__(device_id)
        self.__passcode = "Abcdef@123"
        self.__lock = True

    def set_passcode(self, passcode): #PermissionDeniedError
        if not self.is_on:
            print("SmartDoor must be ON to change passcode.")
            return
        elif self.validate_passcode(passcode):
            self.__passcode = passcode
        else:
            print("Invalid passcode")

    # @property
    # def passcode(self):
    #     return self.__passcode
    #
    # @passcode.setter
    # def passcode(self, passcode):
    #     self.set_passcode(passcode)

    def open_door(self,passcode):
        if not self.is_on:
            print("SmartDoorLock is in off state, we can open the door")
        else:
            if self.__passcode == passcode:
                print("unlocked, you can open the door")
            else:
                print("error, incorrect passcode")

    async def perform_action(self, action_type, value=None):
        if action_type == "set_passcode":
            self.set_passcode(value)
        elif action_type == "turn_on":
            await self.turn_on()
        elif action_type == "turn_off":
            await self.turn_off()
        elif action_type == "get_status_report":
            print(self.get_status_report())
        else:
            print("Unsupported action for SmartDoor.")
        await asyncio.sleep(3)

    def get_status_report(self):
        return f"SmartDoorLock {self._device_id} , ON: {self.is_on}"

    def get_supported_actions(self):
        print("Supported actions for SmartDoor: ","set_brightness, turn_on, turn_off, get_status_report")

    async def validate_passcode(self, passcode):
        old = input("Enter your old passcode: ")
        if old == self.__passcode:
            if re.match(r"^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[_@.$])[A-Za-z\d_@.$]{10,}$",passcode):
                return True
            print("The passcode should have at least one uppercase,at least one lowercase,\nat least one Special character,at least one digit and must have length at least 10")
            return False
        print("passcode mismatch")
        await asyncio.sleep(2)
        return False

class SmartCamera(SmartDevice):

    def __init__(self, device_id):
        super().__init__(device_id)
        self.__is_recording = False
        self.__resolution = "720p"

    def set_recording(self, value):
        if not self.is_on:
            print("SmartCamera must be ON to change status.")
        elif value and isinstance(value,bool):
            if self.__is_recording:
                print("SmartCamera is already in recording state")
            else:
                self.__is_recording = True
                print("SmartCamera started recording")

        elif not value and isinstance(value, bool):
            if self.__is_recording:
                self.__is_recording = False
                print("SmartCamera stopped recording")
            else:
                print("SmartCamera is already in idle/off state")
        else:
            print("Invalid value to change state of SmartCamera")

    def set_resolution(self,value):
        #720p HD,1080p Full HD, 2k QHD ,4k ultra hd
        if not self.is_on:
            print("SmartCamera must be ON to set resolution")
        elif value in ("720p","1080p",'2k','4k'):
            self.__resolution = value
            print(f"SmartCamera resolution is set to {self.__resolution}")
        else:
            print("Invalid resolution for this SmartCamera")

    @property
    def is_recording(self):
        return self.__is_recording

    @is_recording.setter
    def is_recording(self,value):
        self.set_recording(value)

    def get_status_report(self):
        return f"SmartCamera {self._device_id} , ON: {self.is_on} , Resolution: {self.__resolution} , Is in recording: {self.__is_recording}"

    async def perform_action(self, action_type, value=None):
        if action_type == "set_resolution":
            self.set_resolution(value)
        elif action_type == "turn_on":
            await self.turn_on()
        elif action_type == "turn_off":
            await self.turn_off()
        elif action_type == "get_status_report":
            print(self.get_status_report())
        elif action_type == "set_recording":
            self.set_resolution(value)
        else:
            print("Unsupported action for SmartCamera.")
        await asyncio.sleep(3)

    def get_supported_actions(self):
        print("set_resolution, turn_on, turn_off, get_status_report, set_recording")

class SmartSpeaker(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__volume = 50
        self.__track = None

    @property
    def volume(self):
        return self.__volume

    @volume.setter
    def volume(self, value):
        if not self.is_on:
            print("Speaker must be ON to set volume.")
            return
        elif 0 <= value <= 100:
            self.__volume = value
            print(f"Volume set to {value}%.")
        else:
            print("Volume must be between 0 and 100.")

    def play_track(self, track_name):
        if not self.is_on:
            print("Speaker must be ON to play track.")
            return
        self.__track = track_name
        print(f"Playing track: {track_name}")

    def get_status_report(self):
        return f"SmartSpeaker {self._device_id}, ON: {self.is_on}, Volume: {self.__volume}, Now Playing: {self.__track or 'Nothing'}"

    async def perform_action(self, action_type, value=None):
        if action_type == "set_volume":
            self.volume = value
        elif action_type == "play_track":
            self.play_track(value)
        elif action_type == "turn_on":
            await self.turn_on()
        elif action_type == "turn_off":
            await self.turn_off()
        elif action_type == "get_status_report":
            print(self.get_status_report())
        else:
            print("Unsupported action for SmartSpeaker.")
        await asyncio.sleep(3)

    def get_supported_actions(self):
        print("Supported actions for SmartSpeaker: set_volume, play_track, turn_on, turn_off, get_status_report")
