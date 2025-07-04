import asyncio
import random
from abc import ABC,abstractmethod
from datetime import datetime
from utils import helpers
from utils.decorators import log_device_state_change


class SmartDevice(ABC):
    _device_count = 0
    _devices = set()
    ref = None
    def __init__(self, device_id):
        if device_id in SmartDevice._devices:
            print(f"Device with id: {device_id} already exist")
            return
        self._device_id = device_id
        self.__is_on = False
        SmartDevice._device_count += 1
        SmartDevice._devices.add(device_id)
        ref = self
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
            print("device already exist")#error
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
            print("light is not on. Brightness level cannot be changed")
        elif 0 <= level <= 100:
            self.__brightness = level
            print(f"Brightness set to {level}.")
        else:
            print("Brightness level must be between 0 and 100.")

    def get_status_report(self):
        return f"[SmartLight] ID: {self._device_id}, ON: {self.is_on}, Brightness: {self.__brightness}"

    def get_supported_actions(self):
        actions = ["set_brightness"]
        for i in actions:
            yield i
    def to_dict(self):
        return {"is_on": self.is_on, "brightness": self.brightness, "type":self.__class__.__name__}

    async def perform_action(self, action_type, value=None):
        if not self.is_on:
            await asyncio.sleep(1)
            await self.turn_on()
        if action_type.lower() == "set_brightness":
            await asyncio.sleep(1)
            self.brightness = value
        else:
            print(f"Unknown action '{action_type}' for SmartLight.")
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
            print("Thermostat is not on. Temperature cannot be changed.")
        elif 18.0 <= temp <= 30.0:
            self.__temperature = temp
            print(f"Temperature set to {temp}C.")
        else:
            print("Temperature must be between 18.0C and 30.0C.")

    def to_dict(self):
        return {"is_on": self.is_on, "temperature": self.temperature}

    def get_status_report(self):
        return f"[SmartThermostat] ID: {self._device_id}, ON: {self.is_on}, Temperature: {self.__temperature}C"

    def get_supported_actions(self):
        actions = ["set_temperature"]
        for i in actions:
            yield i

    async def perform_action(self, action_type, value=None):
        if not self.is_on:
            await self.turn_on()
        if action_type.lower() == "set_temperature":
            await asyncio.sleep(1)
            self.temperature = value
        else:
            print(f"Unknown action '{action_type}' for SmartThermostat.")
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
        self.__resolution = self.resolution_levels[resolution]

    def get_supported_actions(self):
        actions = ["start_recording", "stop_recording","set_resolution"]
        for i in actions:
            yield i

    def to_dict(self):
        return {"is_on": self.is_on, "recording": self.recording, "resolution": next(k for k,v in self.resolution_levels.items() if v == tuple(self.resolution)), "type":self.__class__.__name__}
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
            print(f"Unknown action '{action_type}' for SmartCamera.")

    async def load_state(self,state):
        if state["is_on"]:
            await self.turn_on()
        else:
            await self.turn_off()
        self.recording = state["recording"]
        self.resolution = state["resolution"]

class SmartSpeaker(SmartDevice):
    songs = [i for i in range(1,11)]
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__playing = False
        self.__volume = 20
        self.__track_id = None

    def get_status_report(self):
        return f"[SmartSpeaker] ID: {self._device_id}, ON:{self.is_on}, Playing: {self.__playing}, Volume: {self.__volume}, Track ID: {self.__track_id}]"

    @property
    def playing(self):
        return self.__playing
    @playing.setter#can add a real player flac
    def playing(self, playing):
        if playing:
            if self.__track_id is None:
                self.__track_id = random.choice(SmartSpeaker.songs)
                self.__playing = True
        else:
            self.__playing = False

    @property
    def volume(self):
        return self.__volume
    @volume.setter
    def volume(self, volume):
        if volume < 0 or volume > 100:
            print("Volume must be between 0 and 100.")#exception custom
        else:
            self.__volume = volume

    def to_dict(self):
        return {"is_on": self.is_on, "playing": self.playing, "volume": self.volume, "track_id": self.__track_id,"type":self.__class__.__name__}

    @property
    def track_id(self):
        return self.__track_id
    @track_id.setter
    def track_id(self, track_id):
        if track_id in SmartSpeaker.songs:
            self.__track_id = track_id
        else:
            print(f"Unknown track ID '{track_id}' for SmartSpeaker.")#exception custom

    def get_supported_actions(self):
        actions = ["play", "stop","set_volume","shift_track","shuffle"]
        for i in actions:
            yield i

    def perform_action(self, action_type, value=None):
        if(not self.is_on):
            self.turn_on()
        if action_type.lower() == "play":
            self.__playing = True
        elif action_type.lower() == "stop":
            self.__playing = False
        elif action_type.lower() == "set_volume":
            self.__volume = value
        elif action_type.lower() == "shift_track":
            self.__track_id = SmartSpeaker.songs[value]
        elif action_type.lower() == "shuffle":
            self.__track_id = random.choice(SmartSpeaker.songs)
    def load_state(self,state):
        if state["is_on"]:
            self.turn_on()
        else:
            self.turn_off()
        self.playing = state["playing"]
        self.volume = state["volume"]
        self.track_id = state["track_id"]

class SmartDoor(SmartDevice):
    def __init__(self, device_id,passcode=None):
        super().__init__(device_id)
        self.__passcode = passcode
        self.__lock = False
    @property
    def lock(self):
        return self.__lock
    @lock.setter
    def lock(self, lock):
        if lock:
            self.__lock = lock
        #alreadylocked exception
    @property
    def passcode(self):
        return self.__passcode
    @passcode.setter
    def passcode(self, passcode):
        if helpers.is_strong(passcode):
            self.__passcode = passcode

    def get_status_report(self):
        return f"[SmartDoot] ID: {self._device_id}, ON: {self.is_on}, Lock: {self.__lock}"
    def get_supported_actions(self):
        actions = ["lock", "unlock","change_passcode"]
        for i in actions:
            yield i

    def to_dict(self):
        return {"is_on":self.is_on, "lock":self.lock, "passcode":self.passcode, "type":self.__class__.__name__}

    def perform_action(self, action_type, value=None):
        if action_type.lower() == "lock":
            if self.__passcode == value:
                self.__lock = True
            else:
                pass #raise error
        elif action_type.lower() == "unlock":
            if self.__passcode == value:
                self.__lock = False
            else:
                pass #raise error
        else:
            print(f"Unknown action '{action_type}' for SmartDoor.")#error raise

    def load_state(self,state):
        if state["is_on"]:
            self.turn_on()
        else:
            self.turn_on()
        self.lock = state["lock"]
        self.passcode = state["passcode"]

if __name__ == "__main__":
    smart = SmartCamera("sd001")
    asyncio.run(smart.turn_on())
    print("end")