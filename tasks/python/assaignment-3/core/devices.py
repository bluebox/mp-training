import asyncio
import random
from abc import ABC,abstractmethod,ABCMeta
from datetime import datetime
from utils import helpers
from utils.decorators import log_device_state_change
from core.meta_class import DeviceRegisterMeta
import re
from core.exceptions import *

# class DeviceRegisterMeta(ABCMeta):
#     device_register = set()
#     def __new__(cls, name, bases, dct):
#         new_cls = super().__new__(cls, name, bases, dct)
#         cls.device_register.add(name)
#         return new_cls

class SmartDevice(ABC,metaclass=DeviceRegisterMeta):
    _device_count = 0
    _devices = set()
    _device_id_pattern = re.compile(r"^[A-Z]\d{3}(?:\.\d+)?$")
    ref = None
    def __init__(self, device_id):
        if not isinstance(device_id, str) or not SmartDevice._device_id_pattern.match(device_id):
            raise InvalidParameterError(
                f"Invalid device ID '{device_id}'. Must match pattern: [A-Z]<3-digit number> (e.g., L001, T123)"
            )
        if device_id in SmartDevice._devices:
            raise DuplicateDeviceError(f"Device ID '{device_id}' already exists.")
        self._device_id = device_id
        self.__is_on = False
        SmartDevice._device_count += 1
        SmartDevice._devices.add(device_id)
        SmartDevice.ref = self
    @log_device_state_change
    async def turn_on(self):
        if not self.__is_on:
            self.__is_on = True
            await asyncio.sleep(1)
            print(f"Device {self._device_id} turned on.")
        else:
            print(f"Device {self._device_id} is already on.")
            await asyncio.sleep(1)
    @log_device_state_change
    async def turn_off(self):
        if self.__is_on:
            self.__is_on = False
            await asyncio.sleep(1)
            print(f"Device {self._device_id} turned off.")
        else:
            print(f"Device {self._device_id} is already off.")
            await asyncio.sleep(1)
    @property
    def is_on(self):
        return self.__is_on

    @property
    def device_id(self):
        return self._device_id

    @device_id.setter
    def device_id(self,device_id):
        if device_id in self._devices:
            raise DuplicateDeviceError(f"Device ID '{device_id}' already exists.")
        else:
            self._devices.add(device_id)
            self._device_count +=1

    @classmethod
    def get_device_count(cls):
        return cls._device_count

    @staticmethod
    def get_system_time():
        return datetime.now()

    @abstractmethod
    def get_status_report(self):
        pass

    @abstractmethod
    def load_state(self,state):
        pass

    @abstractmethod
    def perform_action(self, action_type, value=None):
        pass

    @abstractmethod
    def get_supported_actions(self):
        pass

    @abstractmethod
    def to_dict(self):
        pass

class SmartLight(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__brightness = 0

    @property
    def brightness(self):
        return self.__brightness

    @brightness.setter
    def brightness(self, level):
        if not self.is_on:
            raise DeviceStateError("Cannot change brightness while the light is OFF.")
        elif 0 <= level <= 100:
            self.__brightness = level
            print(f"Brightness set to {level}.")
        else:
            raise InvalidParameterError("Brightness must be 0‑100.")

    def get_status_report(self):
        return f"[SmartLight] ID: {self._device_id}, ON: {self.is_on}, Brightness: {self.__brightness}"

    def get_supported_actions(self):
        actions = ["set_brightness"]
        for i in actions:
            yield i
    def to_dict(self):
        return {"is_on": self.is_on, "brightness": self.brightness, "type":self.__class__.__name__}

    @log_device_state_change
    async def perform_action(self, action_type, value=None):
        if not self.is_on:
            await asyncio.sleep(1)
            await self.turn_on()
        if action_type.lower() == "set_brightness":
            await asyncio.sleep(1)
            self.brightness = value
        else:
            raise UnsupportedActionError(f"{action_type} not supported by SmartThermostat")
    async def load_state(self,state):
        if state["is_on"]:
            await self.turn_on()
        else:
            await self.turn_off()
        self.__brightness = state["brightness"]


class SmartThermostat(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__temperature = 20.0

    @property
    def temperature(self):
        return self.__temperature

    @temperature.setter
    def temperature(self, temp):
        if not self.is_on:
            raise DeviceStateError("Thermostat is OFF.")
        elif 18.0 <= temp <= 30.0:
            self.__temperature = temp
            print(f"Temperature set to {temp}C.")
        else:
            raise InvalidParameterError("Temperature must be 18–30C.")

    def to_dict(self):
        return {"is_on": self.is_on, "temperature": self.temperature,"type":self.__class__.__name__}

    def get_status_report(self):
        return f"[SmartThermostat] ID: {self._device_id}, ON: {self.is_on}, Temperature: {self.__temperature}C"

    def get_supported_actions(self):
        actions = ["set_temperature"]
        for i in actions:
            yield i

    @log_device_state_change
    async def perform_action(self, action_type, value=None):
        if not self.is_on:
            await self.turn_on()
        if action_type.lower() == "set_temperature":
            await asyncio.sleep(1)
            self.temperature = value
        else:
            raise UnsupportedActionError(f"{action_type} not supported by SmartThermostat")
            await asyncio.sleep(1)

    async def load_state(self,state):
        if state["is_on"]:
            await self.turn_on()
        else:
            await self.turn_off()
        self.temperature = state["temperature"]


class SmartCamera(SmartDevice):
    resolution_levels = {'FHD':(1920,1080),'HD':(1280,720),'SD':(700,600)}
    def __init__(self,device_id):
        super().__init__(device_id)
        self.__recording = False
        self.__resolution = self.resolution_levels['HD']

    def get_status_report(self):
        return f"[SmartCamera] ID: {self._device_id}, ON:{self.is_on}, Recording:{self.__recording} ,Resolution: {self.__resolution}]"

    @property
    def recording(self):
        return self.__recording

    @recording.setter
    def recording(self, is_recording):
        self.__recording = is_recording

    @property
    def resolution(self):
        return self.__resolution

    @resolution.setter
    def resolution(self, resolution):
        if resolution not in self.resolution_levels:
            raise InvalidParameterError("Unknown resolution key.")
        self.__resolution = self.resolution_levels[resolution]

    def get_supported_actions(self):
        actions = ["start_recording", "stop_recording","set_resolution"]
        for i in actions:
            yield i

    def to_dict(self):
        return {"is_on": self.is_on, "recording": self.recording, "resolution": next(k for k,v in self.resolution_levels.items() if v == tuple(self.resolution)), "type":self.__class__.__name__}
    @log_device_state_change
    async def perform_action(self, action_type, value=None):
        if action_type.lower() == "start_recording":
            if self.is_on:
                await asyncio.sleep(1)
                self.__recording = True
            else:
                await self.turn_on()
                self.__recording = True
        elif action_type.lower() == "stop_recording":
            await asyncio.sleep(1)
            self.__recording = False
        elif action_type.lower() == "set_resolution":
            await asyncio.sleep(1)
            self.__resolution = self.resolution_levels[value]
        else:
            await asyncio.sleep(1)
            raise UnsupportedActionError("Unknown action for SmartCamera")

    async def load_state(self,state):
        if state["is_on"]:
            await self.turn_on()
        else:
            await self.turn_off()
        self.recording = state["recording"]
        self.resolution = state["resolution"]

class SmartSpeaker(SmartDevice):
    songs = list(range(1, 11))

    def __init__(self, device_id):
        super().__init__(device_id)
        self.__playing = False
        self.__volume = 20
        self.__track_id = None

    def get_status_report(self):
        return f"[SmartSpeaker] ID:{self._device_id}, ON:{self.is_on}, Playing:{self.__playing}, Volume:{self.__volume}, Track:{self.__track_id}"

    @property
    def playing(self):
        return self.__playing

    @playing.setter
    def playing(self, state):
        self.__playing = bool(state)
        if self.__playing and self.__track_id is None:
            self.__track_id = random.choice(self.songs)

    @property
    def volume(self):
        return self.__volume

    @volume.setter
    def volume(self, value):
        if not 0 <= value <= 100:
            raise InvalidParameterError("Volume must be 0‑100.")
        self.__volume = value

    @property
    def track_id(self):
        return self.__track_id

    @track_id.setter
    def track_id(self, tid):
        if tid not in self.songs:
            raise InvalidParameterError(f"Track {tid} not found.")
        self.__track_id = tid

    def get_supported_actions(self):
        return ["play", "stop", "set_volume", "shift_track", "shuffle", "next", "previous"]

    def to_dict(self):
        return {"is_on": self.is_on, "playing": self.playing, "volume": self.volume,
                "track_id": self.__track_id, "type": self.__class__.__name__}

    @log_device_state_change
    async def perform_action(self, action_type, value=None):
        if not self.is_on:
            await self.turn_on()

        action = action_type.lower()
        if action == "play":
            self.playing = True
            print(f"Playing track {self.__track_id}")
            await asyncio.sleep(4)
        elif action == "stop":
            self.playing = False
            print("Playback stopped")
        elif action == "set_volume":
            self.volume = value
            print(f"Volume set to {self.__volume}")
        elif action == "shift_track":
            self.track_id = value
            print(f"Playing track {self.__track_id}")
            self.playing = True
            await asyncio.sleep(4)
        elif action == "shuffle":
            self.track_id = random.choice(self.songs)
            print(f"Playing track {self.__track_id}")
            self.playing = True
            await asyncio.sleep(4)
        elif action == "next":
            idx = (self.songs.index(self.__track_id) + 1) % len(self.songs)
            self.track_id = self.songs[idx]
            print(f"Playing track {self.__track_id}")
            self.playing = True
            await asyncio.sleep(4)
        elif action == "previous":
            idx = (self.songs.index(self.__track_id) - 1) % len(self.songs)
            self.track_id = self.songs[idx]
            print(f"Playing track {self.__track_id}")
            self.playing = True
            await asyncio.sleep(4)
        else:
            raise UnsupportedActionError("Unsupported action for SmartSpeaker")

    async def load_state(self, state):
        if state["is_on"]:
            await self.turn_on()
        else:
            await self.turn_off()
        self.playing = state["playing"]
        self.volume = state["volume"]
        self.track_id = state["track_id"]

import asyncio
from core.devices import SmartDevice
from core.exceptions import InvalidParameterError, DeviceStateError, PermissionDeniedError, UnsupportedActionError
from utils import helpers
from utils.decorators import log_device_state_change

class SmartDoor(SmartDevice):
    def __init__(self, device_id, passcode=None):
        super().__init__(device_id)
        self.__passcode = passcode
        self.__lock = False

    @property
    def lock(self):
        return self.__lock

    @lock.setter
    def lock(self, lock):
        self.__lock = lock

    @property
    def passcode(self):
        return self.__passcode

    @passcode.setter
    def passcode(self, passcode):
        if not helpers.is_strong(passcode):
            raise InvalidParameterError("Passcode does not meet strength policy.")
        self.__passcode = passcode

    def get_status_report(self):
        return f"[SmartDoor] ID: {self._device_id}, ON: {self.is_on}, Locked: {self.__lock}"

    def get_supported_actions(self):
        return iter(["lock", "unlock", "password_reset"])

    def to_dict(self):
        return {
            "is_on": self.is_on,
            "lock": self.lock,
            "passcode": self.passcode,
            "type": self.__class__.__name__
        }

    @log_device_state_change
    async def perform_action(self, action_type, value=None):
        await self.turn_on()
        await asyncio.sleep(1)

        if action_type.lower() == "lock":
            if self.__lock:
                raise DeviceStateError("Door already locked.")
            if value != self.__passcode:
                raise PermissionDeniedError("Incorrect passcode.")
            self.__lock = True
            print("Door locked.")
        elif action_type.lower() == "unlock":
            if not self.__lock:
                raise DeviceStateError("Door already unlocked.")
            if value != self.__passcode:
                raise PermissionDeniedError("Incorrect passcode.")
            self.__lock = False
            print("Door unlocked.")
        elif action_type.lower() == "password_reset":
            self.passcode = value
            print("Passcode updated.")
        else:
            raise UnsupportedActionError("Unknown action for SmartDoor")

    async def load_state(self, state):
        if state["is_on"]:
            await self.turn_on()
        else:
            await self.turn_off()
        self.lock = state["lock"]
        self.passcode = state["passcode"]

if __name__ == "__main__":
    smart = SmartCamera("sd001")
    asyncio.run(smart.turn_on())
    print("end")