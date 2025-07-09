import re
from abc import ABC, abstractmethod, ABCMeta
import asyncio
from datetime import datetime
from SmartHomeAutomationSystem.core.exceptions import InvalidParameterError, ActionNotSupportedError
from SmartHomeAutomationSystem.utils.decorators import log_device_state_change

class DeviceRegisterMeta(ABCMeta):
    registry = {}

    def __new__(mcs, name, bases, namespace):
        cls = super().__new__(mcs, name, bases, namespace)
        if not name.startswith('Abstract') and name != "SmartDevice" and not name.startswith("ABC"):
            DeviceRegisterMeta.registry[name] = cls
        return cls

class SmartDevice(ABC, metaclass=DeviceRegisterMeta):
    _total_devices_created = 0

    def __init__(self, device_id):
        self._validate_device_id(device_id)
        self._device_id = device_id
        self.__is_on = False
        SmartDevice._total_devices_created += 1

    @property
    def device_id(self):
        return self._device_id

    @property
    def is_on(self):
        return self.__is_on

    def _set_on(self, value):
        self.__is_on = value

    @staticmethod
    def _validate_device_id(device_id):
        if not re.fullmatch(r'[A-Z]\d{3}', device_id):
            raise InvalidParameterError(f"Device ID {device_id} is invalid (must match pattern [A-Z]ddd).")

    @classmethod
    def get_total_devices_created(cls):
        return cls._total_devices_created

    @staticmethod
    def get_system_time():
        return datetime.now().strftime("%Y-%m-%d %H:%M:%S")

    @abstractmethod
    async def turn_on(self):
        pass

    @abstractmethod
    async def turn_off(self):
        pass

    @abstractmethod
    def get_status_report(self):
        pass

    @abstractmethod
    async def perform_action(self, action, value=None):
        pass

    @abstractmethod
    def get_supported_actions(self):
        pass

class SmartLight(SmartDevice):
    def __init__(self, device_id, brightness=100):
        super().__init__(device_id)
        self._brightness = brightness

    @property
    def brightness(self):
        return self._brightness

    @brightness.setter
    def brightness(self, value):
        if not (0 <= value <= 100):
            raise InvalidParameterError("Brightness must be 0-100.")
        self._brightness = value

    @log_device_state_change
    async def turn_on(self):
        await asyncio.sleep(1)
        self._set_on(True)

    @log_device_state_change
    async def turn_off(self):
        await asyncio.sleep(1)
        self._set_on(False)

    def get_status_report(self):
        return {
            "type": "SmartLight",
            "id": self.device_id,
            "on": self.is_on,
            "brightness": self.brightness,
        }

    async def perform_action(self, action, value=None):
        if action == "set_brightness":
            self.brightness = value
            return f"Brightness set to {value}."
        raise ActionNotSupportedError("Action not supported.")

    def get_supported_actions(self):
        return ["turn_on", "turn_off", "set_brightness"]

class SmartThermostat(SmartDevice):
    def __init__(self, device_id, temperature=24):
        super().__init__(device_id)
        self._temperature = temperature

    @property
    def temperature(self):
        return self._temperature

    @temperature.setter
    def temperature(self, value):
        if not (10 <= value <= 35):
            raise InvalidParameterError("Temperature must be between 10°C and 35°C.")
        self._temperature = value

    @log_device_state_change
    async def turn_on(self):
        await asyncio.sleep(1)
        self._set_on(True)

    @log_device_state_change
    async def turn_off(self):
        await asyncio.sleep(1)
        self._set_on(False)

    def get_status_report(self):
        return {
            "type": "SmartThermostat",
            "id": self.device_id,
            "on": self.is_on,
            "temperature": self.temperature,
        }

    async def perform_action(self, action, value=None):
        if action == "set_temperature":
            self.temperature = value
            return f"Temperature set to {value}°C."
        raise ActionNotSupportedError("Action not supported.")

    def get_supported_actions(self):
        return ["turn_on", "turn_off", "set_temperature"]

class SmartDoorLock(SmartDevice):
    def __init__(self, device_id, passcode="Default@123"):
        super().__init__(device_id)
        self._locked = True
        self._passcode = passcode

    @property
    def locked(self):
        return self._locked

    @property
    def passcode(self):
        return "****"

    def set_passcode(self, new_passcode):
        if not re.match(r'^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^a-zA-Z0-9]).{8,}$', new_passcode):
            raise InvalidParameterError("Passcode not strong enough.")
        self._passcode = new_passcode

    @log_device_state_change
    async def turn_on(self):
        await asyncio.sleep(1)
        self._set_on(True)

    @log_device_state_change
    async def turn_off(self):
        await asyncio.sleep(1)
        self._set_on(False)

    def get_status_report(self):
        return {
            "type": "SmartDoorLock",
            "id": self.device_id,
            "on": self.is_on,
            "locked": self.locked,
        }

    async def perform_action(self, action, value=None):
        if action == "unlock":
            if value == self._passcode:
                self._locked = False
                return "Door unlocked!"
            else:
                raise ActionNotSupportedError("Incorrect passcode.")
        elif action == "lock":
            self._locked = True
            return "Door locked."
        elif action == "set_passcode":
            self.set_passcode(value)
            return "Passcode changed."
        raise ActionNotSupportedError("Action not supported.")

    def get_supported_actions(self):
        return ["turn_on", "turn_off", "lock", "unlock", "set_passcode"]

class SmartCamera(SmartDevice):
    def __init__(self, device_id, resolution="1080p"):
        super().__init__(device_id)
        self._recording = False
        self._resolution = resolution

    @property
    def recording(self):
        return self._recording

    @property
    def resolution(self):
        return self._resolution

    @resolution.setter
    def resolution(self, value):
        if value not in ["720p", "1080p", "4K"]:
            raise InvalidParameterError("Resolution must be one of: 720p, 1080p, 4K.")
        self._resolution = value

    @log_device_state_change
    async def turn_on(self):
        await asyncio.sleep(1)
        self._set_on(True)

    @log_device_state_change
    async def turn_off(self):
        await asyncio.sleep(1)
        self._set_on(False)
        self._recording = False

    def get_status_report(self):
        return {
            "type": "SmartCamera",
            "id": self.device_id,
            "on": self.is_on,
            "recording": self.recording,
            "resolution": self.resolution,
        }

    async def perform_action(self, action, value=None):
        if action == "start_recording":
            self._recording = True
            return "Recording started."
        elif action == "stop_recording":
            self._recording = False
            return "Recording stopped."
        elif action == "set_resolution":
            self.resolution = value
            return f"Resolution set to {value}."
        raise ActionNotSupportedError("Action not supported.")

    def get_supported_actions(self):
        return ["turn_on", "turn_off", "start_recording", "stop_recording", "set_resolution"]

class SmartSpeaker(SmartDevice):
    def __init__(self, device_id, volume=50):
        super().__init__(device_id)
        self.__volume = volume
        self.__current_track = None

    @property
    def volume(self):
        return self.__volume

    @volume.setter
    def volume(self, value):
        if not (0 <= value <= 100):
            raise InvalidParameterError("Volume must be 0-100.")
        self.__volume = value

    @property
    def current_track(self):
        return self.__current_track

    @log_device_state_change
    async def turn_on(self):
        await asyncio.sleep(1)
        self._set_on(True)

    @log_device_state_change
    async def turn_off(self):
        await asyncio.sleep(1)
        self._set_on(False)
        self.__current_track = None

    def get_status_report(self):
        return {
            "type": "SmartSpeaker",
            "id": self.device_id,
            "on": self.is_on,
            "volume": self.volume,
            "track": self.current_track
        }

    async def perform_action(self, action, value=None):
        if action == "set_volume":
            self.volume = value
            return f"Volume set to {value}."
        elif action == "play_track":
            self.__current_track = value
            return f"Playing track: {value}"
        elif action == "stop_track":
            self.__current_track = None
            return "Stopped playing."
        raise ActionNotSupportedError("Action not supported.")

    def get_supported_actions(self):
        return ["turn_on", "turn_off", "set_volume", "play_track", "stop_track"]
