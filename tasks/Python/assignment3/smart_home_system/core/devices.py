import asyncio
from datetime import datetime
from abc import ABC, abstractmethod
import re
from smart_home_system.core.exceptions import (
    InvalidParameterError, DeviceOfflineError, ActionNotSupportedError,
    PermissionDeniedError, AuthenticationError,
)
from smart_home_system.core.metaclass import DeviceRegistrarMeta
from smart_home_system.utils.helpers import validate_id
from smart_home_system.utils.decorators import log_device_state_change

class SmartDevice(ABC, metaclass=DeviceRegistrarMeta):
    _total_devices_created = 0
    device_ids = []

    def __init__(self, device_id):
        if not validate_id(device_id):
            raise InvalidParameterError(f"Device ID '{device_id}' is invalid.")
        if device_id in SmartDevice.device_ids:
            raise PermissionDeniedError(f"Already existing Id, this device cannot have same ID {device_id}")
        self._device_id = device_id
        self.__is_on = False
        SmartDevice.device_ids.append(self._device_id)
        SmartDevice._total_devices_created += 1

    @log_device_state_change
    async def turn_on(self):
        self.__is_on = True
        await asyncio.sleep(1)
        return f"Device {self._device_id} turned ON."

    @log_device_state_change
    async def turn_off(self):
        self.__is_on = False
        await asyncio.sleep(1)
        return f"Device {self._device_id} turned OFF."

    @property
    def is_on(self):
        return self.__is_on

    @property
    def device_id(self):
        return self._device_id

    @classmethod
    def get_device_count(cls):
        return cls._total_devices_created

    @staticmethod
    def get_system_time():
        return datetime.now()

    @abstractmethod
    def get_status_report(self):
        pass

    @abstractmethod
    async def perform_action(self, action_type, value=None):
        pass

    @abstractmethod
    def get_supported_actions(self):
        pass


class SmartLight(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__brightness = 50

    def set_brightness(self, level):
        if not self.is_on:
            raise DeviceOfflineError("SmartLight must be ON to set brightness.")
        if 0 <= level <= 100:
            self.__brightness = level
            print(f"Brightness is set to {self.__brightness} %")
        else:
            raise InvalidParameterError("Brightness must be between 0 and 100.")

    @property
    def brightness(self):
        return self.__brightness

    @brightness.setter
    def brightness(self, level):
        self.set_brightness(level)

    def get_status_report(self):
        if self.is_on:
            return f"SmartLight {self._device_id} - ON: {self.is_on}, Brightness: {self.__brightness}%"
        return f"SmartLight {self._device_id} - ON: {self.is_on}"

    async def perform_action(self, action_type, value=None):
        if action_type == "set_brightness":
            self.set_brightness(value)
        elif action_type == "get_brightness":
            print(f"{self._device_id} Brightness: {self.__brightness}%")
        elif action_type == "turn_on":
            await self.turn_on()
        elif action_type == "turn_off":
            await self.turn_off()
        elif action_type == "get_status_report":
            print(self.get_status_report())
        else:
            raise ActionNotSupportedError(f"{action_type} not supported for SmartLight.")
        await asyncio.sleep(0.5)

    def get_supported_actions(self):
        return ["set_brightness", "get_brightness", "turn_on", "turn_off", "get_status_report"]


class SmartThermostat(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__temperature = 22.0

    def set_temperature(self, temp):
        if not self.is_on:
            raise DeviceOfflineError("SmartThermostat must be ON to set temperature.")
        if 18 <= temp <= 30:
            self.__temperature = temp
            print(f"Temperature is set to {self.__temperature} °C")
        else:
            raise InvalidParameterError("Temperature must be between 18°C and 30°C.")

    @property
    def temperature(self):
        return self.__temperature

    @temperature.setter
    def temperature(self, temp):
        self.set_temperature(temp)

    def get_status_report(self):
        if self.is_on:
            return f"SmartThermostat {self._device_id} - ON: {self.is_on}, Temp: {self.__temperature}°C"
        return f"SmartThermostat {self._device_id} - ON: {self.is_on}"

    async def perform_action(self, action_type, value=None):
        if action_type == "set_temperature":
            self.set_temperature(value)
        elif action_type == "get_temperature":
            print(f"{self._device_id} Temperature: {self.__temperature}°C")
        elif action_type == "turn_on":
            await self.turn_on()
        elif action_type == "turn_off":
            await self.turn_off()
        elif action_type == "get_status_report":
            print(self.get_status_report())
        else:
            raise ActionNotSupportedError(f"{action_type} not supported for SmartThermostat.")
        await asyncio.sleep(0.5)

    def get_supported_actions(self):
        return ["set_temperature", "get_temperature", "turn_on", "turn_off", "get_status_report"]


class SmartDoorLock(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__locked = True
        self.__passcode = "Admin@123"

    def change_passcode(self, old, new):
        if old != self.__passcode:
            raise AuthenticationError("Incorrect current passcode.")
        pattern = r"^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[_@.$])[A-Za-z\d_@.$]{9,}$"
        if not re.fullmatch(pattern, new):
            raise InvalidParameterError("New passcode is not strong enough.")
        self.__passcode = new
        print(f"{self._device_id} Passcode updated.")

    def unlock(self, code):
        if self.is_on and code == self.__passcode:
            self.__locked = False
            print(f"{self._device_id} unlocked.")
        elif not self.is_on:
            raise DeviceOfflineError("device is offline")
        else:
            raise PermissionDeniedError("Invalid passcode.")

    @property
    def is_lock(self):
        return self.__locked

    def get_status_report(self):
        if self.is_on:
            return f"SmartDoorLock {self._device_id} - ON: {self.is_on}, Locked: {self.__locked}"
        return f"SmartDoorLock {self._device_id} - ON: {self.is_on}"

    async def perform_action(self, action_type, value=None):
        if action_type == "change_passcode":
            old, new = value
            self.change_passcode(old, new)
        elif action_type == "unlock":
            self.unlock(value)
        elif action_type == "turn_on":
            await self.turn_on()
        elif action_type == "turn_off":
            await self.turn_off()
        elif action_type == "get_status_report":
            print(self.get_status_report())
        else:
            raise ActionNotSupportedError(f"{action_type} not supported for SmartDoorLock.")
        await asyncio.sleep(0.5)

    def get_supported_actions(self):
        return ["change_passcode", "unlock", "turn_on", "turn_off", "get_status_report"]


class SmartCamera(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__is_recording = False
        self.__resolution = "720p"

    @property
    def resolution(self):
        return self.__resolution

    def set_recording(self, on: bool):
        if not self.is_on:
            raise DeviceOfflineError("SmartCamera must be ON to change recording status.")
        self.__is_recording = on
        print(f"{self._device_id} is Recording: {on}")

    def set_resolution(self, resolution):
        if not self.is_on:
            raise DeviceOfflineError("SmartCamera must be on.")
        if resolution not in ("720p", "1080p", "2k", "4k"):
            raise InvalidParameterError("Unsupported resolution.")
        self.__resolution = resolution
        print(f"{self._device_id} resolution is set to {self.__resolution}")

    def get_status_report(self):
        if self.is_on:
            return f"SmartCamera {self._device_id} - ON: {self.is_on}, Recording: {self.__is_recording}, Resolution: {self.__resolution}"
        return f"SmartCamera {self._device_id} - ON: {self.is_on},"

    async def perform_action(self, action_type, value=None):
        if action_type == "set_recording":
            self.set_recording(value)
        elif action_type == "set_resolution":
            self.set_resolution(value)
        elif action_type == "turn_on":
            await self.turn_on()
        elif action_type == "turn_off":
            await self.turn_off()
        elif action_type == "get_status_report":
            print(self.get_status_report())
        else:
            raise ActionNotSupportedError(f"{action_type} not supported for SmartCamera.")
        await asyncio.sleep(0.5)

    def get_supported_actions(self):
        return ["set_recording", "set_resolution", "turn_on", "turn_off", "get_status_report"]


class SmartSpeaker(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__volume = 50
        self.__track = None

    @property
    def volume(self):
        return self.__volume

    @volume.setter
    def volume(self,value):
        self.set_volume(value)

    def get_track(self):
        return self.__track

    def set_volume(self, vol):
        if not self.is_on:
            raise DeviceOfflineError("SmartSpeaker must be ON.")
        elif 0 <= vol <= 100:
            self.__volume = vol
            print(f"Volume is set to {self.__volume}%")
        else:
            raise InvalidParameterError("Volume must be between 0 and 100.")

    def play_track(self, track):
        self.__track = track
        print(f"{self._device_id} playing: {track}")

    def get_status_report(self):
        if self.is_on:
            return f"SmartSpeaker {self._device_id} - ON: {self.is_on}, Volume: {self.__volume}, Track: {self.__track or 'None'}"
        return f"SmartSpeaker {self._device_id} - ON: {self.is_on},"

    async def perform_action(self, action_type, value=None):
        if action_type == "set_volume":
            self.set_volume(value)
        elif action_type == "play_track":
            self.play_track(value)
        elif action_type == "turn_on":
            await self.turn_on()
        elif action_type == "turn_off":
            await self.turn_off()
        elif action_type == "get_status_report":
            print(self.get_status_report())
        else:
            raise ActionNotSupportedError(f"{action_type} not supported for SmartSpeaker.")
        await asyncio.sleep(0.5)

    def get_supported_actions(self):
        return ["set_volume", "play_track", "turn_on", "turn_off", "get_status_report"]
