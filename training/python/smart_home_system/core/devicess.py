from abc import ABC, abstractmethod, ABCMeta
from datetime import datetime
import re
import asyncio
from time import sleep

from core.exceptions import DeviceOfflineError, InvalidParameterError, SmartHomeError, AuthenticationError
from utils.decorators import log_device_action, validate_password


class DeviceRegistrarMeta(ABCMeta):
    registry = {}

    def __new__(mcls, name, bases, namespace):
        cls = super().__new__(mcls, name, bases, namespace)

        if not getattr(cls, "__abstractmethods__", False):
            DeviceRegistrarMeta.registry[name] = cls
        return cls

class SmartDevice(metaclass=DeviceRegistrarMeta):
    _device_count = 0
    devices = []
    states = []
    pat = r"^SD\d$"
    def __init__(self, device_id = None):

        if isinstance(device_id, str) and re.match(SmartDevice.pat, device_id):
                is_there = True
                for name, id in SmartDevice.devices:
                    if id == device_id:
                        print(f"Duplicate ID '{device_id}' detected, generating new ID.")
                        is_there = False
                if is_there:
                    self._device_id = device_id
                    SmartDevice.devices.append((self.__class__.__name__, self._device_id))
                    SmartDevice._device_count += 1
                    return

        while True:
            SmartDevice._device_count += 1
            new_id = f"SD{SmartDevice._device_count}"
            flag = 0
            for name, id in SmartDevice.devices:
                if id == new_id:
                    flag=1
                    break
            if flag == 0 :
                self._device_id = new_id
                SmartDevice.devices.append((self.__class__.__name__, self._device_id))
                print(f"Generated new Device ID: {new_id}")
                break

        self.__is_on = False

    # @update_states(val,data2)
    def update_states(self,val,data2):
        with open("smarthome_logs","a+") as f2:
            f2.write(data2)

        flag=0
        for i in range(len(SmartDevice.states)):
            rec = SmartDevice.states[i]
            if rec[1] == self._device_id :
                rec[2] = val
                flag = 1
                break
        if flag == 0 :
            SmartDevice.states.append([self.__class__.__name__, self._device_id, val])
    def update_actions(self,data):
        with open("actions","a+") as f3:
            f3.write(data)
    async def turn_on(self):
          await asyncio.sleep(1)

          self.__is_on = True
          print(f"{self.__class__.__name__} Device {self._device_id} turned on")
          data = f"{self.__class__.__name__}     |      {self._device_id}    turned On.  |    {SmartDevice.get_system_time()}\n"
          print(data)
          with open("logs","a+") as f:
              print("hlooo")
              f.write(data)

          data2 = f"{self.__class__.__name__},{self._device_id},Turn ON,{SmartDevice.get_system_time()}\n"
          task = "ON"
          self.update_states(task,data2)

    async def turn_off(self):
        await asyncio.sleep(1)
        self.__is_on = False
        print(f"{self.__class__.__name__} Device {self._device_id} turned off.")
        with open("logs","a") as f:
            data = f"{self.__class__.__name__}     |      {self._device_id}    turned Off.  |    {SmartDevice.get_system_time()}\n"
            f.write(data)

        data2 = f"{self.__class__.__name__},{self._device_id},Turn OFF,{SmartDevice.get_system_time()}\n"
        task = "OFF"
        self.update_states(task,data2)

    @property
    def is_on(self):
        return self.__is_on

    @classmethod
    def get_device_count(cls):
        return cls._device_count

    @staticmethod
    def get_system_time():
        return datetime.now().strftime("%Y-%m-%d %H:%M:%S")

    @abstractmethod
    def get_status_report(self):
        pass

    @abstractmethod
    def perform_action(self, action_type, value=None):
        pass
class SmartLight(SmartDevice):
    def __init__(self, device_id = None):
        super().__init__(device_id)
        self.__brightness = 0

    @property
    def brightness(self):
        return self.__brightness

    @brightness.setter
    def brightness(self, value):
        print("setter")
        try:
            if not self.is_on:
                print("Light must be ON to set brightness")
                raise DeviceOfflineError(self._device_id)
            elif 0 <= value <= 100:
                if self.__brightness == value:
                    print(f"Given same value,Brightness already set to {value}")
                else:
                    self.__brightness = value
                    print(f"Brightness set to {value}.")
            else:
                print("Brightness must be between 0 and 100")
                raise InvalidParameterError(f"Brightness must be between 0 and 100, got {value}")
        except (DeviceOfflineError,InvalidParameterError):
            print("failed setting")
            raise

    def get_status_report(self):
        return f"[Light] {self._device_id} | On: {self.is_on} | Brightness: {self.__brightness}"

    @log_device_action()
    async def perform_action(self, action_type, value=None):
        await asyncio.sleep(0.5)
        try:
            if not self.is_on:
                raise DeviceOfflineError(self._device_id)
            if action_type == "set_brightness":
                if isinstance(value, int):
                    self.brightness = value
                    val = "set-britness"
                    data = f"{self.__class__.__name__},{self._device_id},set_brightness,{SmartDevice.get_system_time()}\n"
                    self.update_actions(data)
                else:
                    print("Brightness value must be an integer")
                    raise InvalidParameterError("Brightness value must be an integer")
            else:
                print("Invalid action for SmartLight")
                raise InvalidParameterError("Invalid action")
        except InvalidParameterError as err:
            print("failed action {} ".format(err))
    def get_supported_actions(self):
        return ["set_brightness"]

#thermo
class SmartThermostat(SmartDevice):
    def __init__(self, device_id=None):
        super().__init__(device_id)
        self.__temperature = 22  

    @property
    def temperature(self):
        return self.__temperature

    @temperature.setter
    def temperature(self, value):
        try:

            if not self.is_on:
                raise DeviceOfflineError(self._device_id)
            if not isinstance(value, int):
                print("Temperature must be an integer")
                raise InvalidParameterError("must be integer")
            elif 18 <= value <= 30:
                if self.__temperature == value:
                    print("Tempearature already set to provided value")
                else:
                    self.__temperature = value
                    print(f"Temperature set to {value}°C")
                    data = f"{self.__class__.__name__},{self._device_id},Set Temperature: {value},{SmartDevice.get_system_time()}\n"
                    self.update_actions(data)
            else:
                raise InvalidParameterError(f"Temperature must be between 18 and 30,but is{value}")
        except (DeviceOfflineError,InvalidParameterError) as e:
            print(f"error:{e}")

    def get_status_report(self):
        return f"[Thermostat] {self._device_id} | On: {self.is_on} | Temperature: {self.__temperature}°C"

    @log_device_action()
    async def perform_action(self, action_type, value=None):
        await asyncio.sleep(0.5)
        try:
            if action_type == "set_temperature":
                if not isinstance(value, int):
                    raise InvalidParameterError("Temperature not an integer")
                self.temperature = value
            else:
                print("Invalid action for SmartThermostat")
                raise InvalidParameterError(f"Action '{action_type}' not supported")
        except (InvalidParameterError) as e:
            print("perform action failed:",e)


    def get_supported_actions(self):
        return ["set_temperature"]


class SmartDoorLock(SmartDevice):
    def __init__(self, device_id, code = None):
        super().__init__(device_id)
        self.__password = code

        if code is None:
            self.__password = "Abcdefgh1"
            return

        pattern = r"^[a-zA-Z0-9]+$"
        if bool(re.match(pattern, self.__password)):

           print("valid password")
        else:
           i=0
           while i<3 :
               print("choose a valid password.Max 3 attempts")
               pas=input()
               if re.match(pattern,pas):
                   print("password updated")
                   self.__password = pas
                   break
               i=i+1

           if not re.match(pattern,self.__password):
               print("password is invalid\nMax attempts exceeded;Ending process Try again")
               raise InvalidParameterError("invalid password format")
        self.__locked = True

    def _validate_password(self,password):
        return password == self.__password

    # @validate_password()
    async def lock(self):
        if not self.__locked:
            self.__locked = True
            print("Door locked.")
            val = "Locked"
            data = f"{self.__class__.__name__},{self._device_id},Locked,{SmartDevice.get_system_time()}\n"
            self.update_states(val,data)
            self.update_actions(data) 
        else:
            print("Door is already locked.")

    @validate_password()
    async def unlock(self):
        if self.__locked:
            self.__locked = False
            print("Door unlocked.")

            val = "UnLocked"
            data = f"{self.__class__.__name__},{self._device_id},UnLock,{SmartDevice.get_system_time()}\n"
            self.update_states(val,data)
            self.update_actions(data) 
        else:
            print("door is already unlocked.")

    def get_status_report(self):
        return f"[DoorLock] {self._device_id} | On: {self.is_on} | Locked: {self.__locked}"

    @log_device_action()
    async def perform_action(self, action_type, value=None):
        await asyncio.sleep(0.5)
        try:
            if action_type == "lock":
                await self.lock()
            elif action_type == "unlock":
                if not self.is_on:
                    raise DeviceOfflineError(f"Device {self._device_id} is offline")
                await self.unlock()
            else:
                print("invalid action for SmartDoorLock")
                raise InvalidParameterError("Invalid action on door")
        except(DeviceOfflineError,InvalidParameterError,AuthenticationError) as e:
            print("door cannot  perform ",e)

    def get_supported_actions(self):
        return ["lock", "unlock"]


#speakerr
class SmartSpeaker(SmartDevice):
    def __init__(self, device_id=None):
        super().__init__(device_id)
        self.__volume = 50
        self.__track = None

    @property
    def volume(self):
        return self.__volume

    @volume.setter
    def volume(self, value):
        try:
            if not self.is_on:
                print("Speaker must be ON to set volume")
                raise DeviceOfflineError("Device is off please on to use")
            elif 0 <= value <= 100:
                if self.volume==value:
                    print("Volume already set to provided value")
                else:
                    self.__volume = value
                    print(f"Volume set to {value}")
                    data = f"{self.__class__.__name__},{self._device_id},Set Volume: {value},{SmartDevice.get_system_time()}\n"
                    self.update_actions(data)
            else:
                print("Volume must be between 0 and 100")
                raise InvalidParameterError("Invalid action on speaker")
        except (DeviceOfflineError,InvalidParameterError) as e:
            print("error in speaker setter",e)

    VALID_TRACKS = ["normal","rainy","magic","sunny"]
    def play_track(self, track_name):
        try:

            if not self.is_on:
                print("Speaker must be ON to play a track")
                raise DeviceOfflineError("Device is off please on to use")
            else:
                self.__track = track_name
                print(f"Now playing: {track_name}")
                data = f"{self.__class__.__name__},{self._device_id},Play Track: {track_name},{SmartDevice.get_system_time()}\n"
                self.update_actions(data)
        except (DeviceOfflineError):
            raise

    def get_status_report(self):
        return f"[Speaker] {self._device_id} | On: {self.is_on} | Volume: {self.__volume} | Track: {self.__track or 'None'}"

    # //matching nearest track my matching
    @staticmethod
    def matched(s,value):
        mx=min(len(s),len(value))
        req=3
        mtc=0
        for i in range(mx):
            if s[i] == value[i]:
                mtc+=1
            else:
                break
        return mtc>=req


    @log_device_action()
    async def perform_action(self, action_type, value=None):
        await asyncio.sleep(0.5)
        try:
            if not self.is_on:
                raise DeviceOfflineError("speaker id off")
            if action_type == "set_volume":

                try:
                    value = int(value)
                except (ValueError, TypeError):
                    raise InvalidParameterError("Volume should be an integer")
                self.volume = value

            elif action_type == "play_track":
                if isinstance(value, str) and value in self.VALID_TRACKS:
                    self.play_track(value)

                elif isinstance(value, str) and value not in self.VALID_TRACKS:
                    matches = [s for s in self.VALID_TRACKS if self.matched(s, value)]
                    if len(matches)>0:
                        print(f"Playing track: {matches[0]}")
                        self.play_track(matches[0])

                else:
                    if not isinstance(value,str):
                        print("Track name must be a string")
                    else:
                        print("specified track not present")
                    raise InvalidParameterError("track should be string")
            else:
                print("Invalid action for SmartSpeaker")
                raise InvalidParameterError("invalid action for speaker")
        except (DeviceOfflineError,InvalidParameterError):
            raise

    def get_supported_actions(self):
        return ["set_volume", "play_track"]

#smart cam
class SmartCamera(SmartDevice):
    VALID_RESOLUTIONS = ["360p", "720p", "1080p", "4K", "8K"]

    def __init__(self, device_id=None, resolution="1080p"):
        super().__init__(device_id)
        self.__resolution = resolution
        self.__recording = False

    def start_recording(self):
        try:

            if self.is_on:
                if not self.__recording:
                    self.__recording = True
                    print("Camera started recording.")
                    data = f"{self.__class__.__name__},{self._device_id},Start Recording,{SmartDevice.get_system_time()}\n"
                    self.update_actions(data)
                else:
                    print("Camera is already recording")
            else:
                print("Camera must be ON to record")
                raise DeviceOfflineError("Camera is of please turn it on")
        except (DeviceOfflineError) as e:
            print(f"Error:{e}")

    def stop_recording(self):
        if self.__recording:
            self.__recording = False
            print("Camera stopped recording")
            data = f"{self.__class__.__name__},{self._device_id},Stop Recording,{SmartDevice.get_system_time()}\n"
            self.update_actions(data)
        else:
            print("Camera is not recording")

    def get_status_report(self):
        return f"[Camera] {self._device_id} | On: {self.is_on} | Resolution: {self.__resolution} | Recording: {self.__recording}"
    @log_device_action()
    async def perform_action(self, action_type, value=None):
        await asyncio.sleep(0.5)
        try:
            if not self.is_on:
                raise DeviceOfflineError("camera is off please turn it on")

            if action_type == "start_recording":
                self.start_recording()
            elif action_type == "stop_recording":
                self.stop_recording()

            elif action_type == "set_resolution":
                if isinstance(value, str) and value in self.VALID_RESOLUTIONS:
                    self.__resolution = value
                    print(f"Resolution set to {value}")
                    data = f"{self.__class__.__name__},{self._device_id},Set Resolution: {value},{SmartDevice.get_system_time()}\n"
                    self.update_actions(data)
                else:
                    print(f"Resolution must be one of: {','.join(self.VALID_RESOLUTIONS)}")
                    raise InvalidParameterError(SmartHomeError)

            else:
                print("Invalid action for SmartCamera")
                raise InvalidParameterError("invalid action for camera")
        except (DeviceOfflineError,InvalidParameterError)as e:
            print(f"invalid action for camera{e}")

    def get_supported_actions(self):
        return ["start_recording", "stop_recording", "set_resolution"]
