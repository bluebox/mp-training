
# Include a protected _device_id attribute that must be validated using a regular expression (e.g., L001, T015). Invalid IDs should raise an InvalidParameterError.

# Concrete Device Implementations: Implement at least five (5) distinct smart device types inheriting from SmartDevice. Each device must have:
# Unique, private attributes representing its specific state (e.g., brightness for a light, temperature for a thermostat).
# Properties (@property and @setter) for controlled access to these attributes, with appropriate validation (e.g., brightness 0-100, temperature within a realistic range).
# Device-specific implementations for get_status_report(), perform_action(), and get_supported_actions().
# Asynchronous methods where appropriate (e.g., turn_on(), turn_off(), perform_action() should use asyncio.sleep() to simulate delays).

# Examples of devices (minimum 5 required):
# SmartLight: Controls on/off state and brightness.
# SmartThermostat: Controls on/off state and temperature settings.
# SmartDoorLock: Controls lock/unlock state using a passcode; can also set/change the passcode. Passcode changes must validate the new passcode for strength (e.g., minimum length, uppercase, lowercase, digit, special character).
# SmartCamera: Controls on/off state, recording, and resolution settings.
# SmartSpeaker: Controls on/off state, volume, and playing specific tracks.
from datetime import datetime
from abc import ABC,abstractmethod
import re

class SmartDevice(ABC):
    _total_devices_created = 0

    def __init__(self, device_id):
        self._device_id = device_id
        self.__is_on = False
        SmartDevice._total_devices_created += 1

    def turn_on(self):
        self.__is_on = True
        print(f"Device {self._device_id} turned ON.")

    def turn_off(self):
        self.__is_on = False
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
    def perform_action(self, action_type, value=None):
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
            print("Light must be on to set brightness.")
            return
        if 0 <= level <= 100:
            self.__brightness = level
            print(f"Brightness set to {level}%.")
        else:
            print("Invalid brightness level. Must be 0-100.")

    @property
    def brightness(self):
        return self.__brightness

    @brightness.setter
    def brightness(self, level):
        self.set_brightness(level)

    def get_status_report(self):
        return f"Light {self._device_id} , ON: {self.is_on} , Brightness: {self.__brightness}"

    def perform_action(self, action_type, value=None):
        if action_type == "set_brightness":
            self.set_brightness(value)
        elif action_type == "get_brightness":
            print(f"The brightness is at {self.brightness}")
        elif action_type == "turn_on":
            self.turn_on()
        elif action_type == "turn_off":
            self.turn_off()
        elif action_type == "get_status_report":
            print(self.get_status_report())
        else:
            print("Unsupported action for SmartLight.")

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

    def perform_action(self, action_type, value=None):
        if action_type == "set_temperature":
            self.set_temperature(value)
        elif action_type == "get_temperature":
            print(f"The temperature is at {self.__temperature} °C")
        elif action_type == "turn_on":
            self.turn_on()
        elif action_type == "turn_off":
            self.turn_off()
        elif action_type == "get_status_report":
            print(self.get_status_report())
        else:
            print("Unsupported action for SmartThermostat.")

    def get_supported_actions(self):
        print("Supported actions for SmartThermostat: ","set_temperature, get_temperature, turn_on, turn_off, get_status_report")

class SmartDoorLock(SmartDevice):

    def __init__(self, device_id):
        super().__init__(device_id)
        self.__passcode = "Medplus@123"

    def set_passcode(self, passcode):
        if not self.is_on:
            print("SmartDoor must be ON to change passcode.")
            return
        elif passcode:
            #([A-Z]+[a-z]+[_@#$\.]+){10,}
            self.__passcode = passcode
        else:
            print("Invalid passcode")

    @property
    def passcode(self):
        return self.__passcode

    @passcode.setter
    def passcode(self, passcode):
        self.set_passcode(passcode)

    def perform_action(self, action_type, value=None):
        if action_type == "set_passcode":
            self.set_passcode(value)
        elif action_type == "turn_on":
            self.turn_on()
        elif action_type == "turn_off":
            self.turn_off()
        elif action_type == "get_status_report":
            print(self.get_status_report())
        else:
            print("Unsupported action for SmartDoor.")

    def get_status_report(self):
        return f"SmartDoor {self._device_id} , ON: {self.is_on}"

    def get_supported_actions(self):
        print("Supported actions for SmartDoor: ","set_brightness, turn_on, turn_off, get_status_report")

class SmartCamera(SmartDevice):

    def __init__(self, device_id):
        super().__init__(device_id)
        self.__is_recording = False
        self.resolution = "720p"

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
            self.resolution = value
            print(f"SmartCamera resolution is set to {self.resolution}")
        else:
            print("Invalid resolution for this SmartCamera")

    @property
    def is_recording(self):
        return self.__is_recording

    @is_recording.setter
    def is_recording(self,value):
        self.set_recording(value)

    def get_status_report(self):
        return f"SmartCamera {self._device_id} , ON: {self.is_on} , Resolution: {self.resolution} , Is in recording: {self.__is_recording}"

    def perform_action(self, action_type, value=None):
        if action_type == "set_resolution":
            self.set_resolution(value)
        elif action_type == "turn_on":
            self.turn_on()
        elif action_type == "turn_off":
            self.turn_off()
        elif action_type == "get_status_report":
            print(self.get_status_report())
        elif action_type == "set_recording":
            self.set_resolution(value)
        else:
            print("Unsupported action for SmartCamera.")

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
        if 0 <= value <= 100:
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

    def perform_action(self, action_type, value=None):
        if action_type == "set_volume":
            self.volume = value
        elif action_type == "play_track":
            self.play_track(value)
        elif action_type == "turn_on":
            self.turn_on()
        elif action_type == "turn_off":
            self.turn_off()
        elif action_type == "get_status_report":
            print(self.get_status_report())
        else:
            print("Unsupported action for SmartSpeaker.")

    def get_supported_actions(self):
        print("Supported actions for SmartSpeaker: set_volume, play_track, turn_on, turn_off, get_status_report")

