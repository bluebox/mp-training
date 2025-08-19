from abc import ABC,abstractmethod
from datetime import datetime
from ASSIGNMENT3.smart_home_system.core.exceptions import *
import  re

from ASSIGNMENT3.smart_home_system.utils.decorators import *



class DeviceRegistrarMeta(type):
    registry = {}

    def __new__(cls, name, bases, class_dict):
        cls_obj = super().__new__(cls, name, bases, class_dict)
        if name != 'SmartDevice' and not name.startswith('Abstract'):
            DeviceRegistrarMeta.registry[name] = cls_obj
        return cls_obj



class SmartDevice(metaclass=DeviceRegistrarMeta):
    _total_devices_created=0
    def __init__(self,device_id,is_on=False):
        if not re.match(r"^[A-Z][0-9]{3}$",device_id):
            raise InvalidParameterError(f"Invalid device_id: {device_id}")

        SmartDevice._total_devices_created+=1
        self._is_on=is_on
        self._device_id=device_id

    @log_device_state_change
    async def turn_on(self,user_role="PRASAD"):
        if user_role.upper()=="PRASAD":
            self._is_on = True
            await asyncio.sleep(1)
            print("Device", self._device_id, "turned ON.")
        else:
            raise AuthenticationError

    async def turn_off(self):
        self._is_on=False
        print("Device",self._device_id,"turned OFF.")

    @abstractmethod
    def get_status_report(self):
        pass

    def __eq__(self, other):
        return type(self) == type(other)

    def __hash__(self):
        return hash(type(self))


    @abstractmethod
    def perform_action(self,action_type,user_role,value=None):
        pass
    @property
    def device_id(self):
        return self._device_id
    @property
    def is_on(self):
        return self._is_on

    @classmethod
    def get_total_devices_created(cls):
        return cls._total_devices_created

    @staticmethod
    def get_system_time():
        return datetime.now().time()

    @abstractmethod
    def get_supported_actions(self):
        pass



class SmartLight(SmartDevice):
    def __init__(self,device_id,brightness=0,is_on=True):
        self._brightness=brightness
        super().__init__(device_id,is_on)



    @property
    def brightness(self):
        return self._brightness

    @brightness.setter
    def brightness(self,level_user_role):
        if isinstance(level_user_role,int):
            level=level_user_role
            user_role="null"
        else:
            level,user_role=level_user_role

        if user_role.upper()== "PRASAD":
            if self._is_on and 0 < level < 100:
                self._brightness = level

            elif not (0 < level < 100):
                raise  InvalidParameterError

            else:
                raise DeviceOfflineError
        else:
            raise AuthenticationError


    def get_status_report(self):
        return {"_brightness":self._brightness,"_is_on":bool(self._is_on )}
        # print("current brightness=", self.__brightness, )

    async def perform_action(self, action_type,user_role, value=None,):
        if action_type.lower() == "set brightness":

            self.brightness = value,user_role
        elif action_type.lower()== 'get status report':
            print(self.get_status_report())
        elif action_type.upper()== "TURN OFF":
            await self.turn_off()
        elif action_type.upper() == "TURN ON":
            await self.turn_on(user_role)
        else:
            raise  ActionNotSupportedError
    def get_supported_actions(self):
        return ["set brightness", "get status report","Turn OFF","Turn ON"]


class SmartThermostat(SmartDevice):
    def __init__(self,device_id,tempreature=20.0,is_on=True):
        self._tempreature =tempreature
        super().__init__(device_id,is_on)
    @property
    def tempreature(self):

            return self._tempreature


    @tempreature.setter
    def temperature(self,temp_user_role):
        temp,user_role=temp_user_role
        if user_role.upper() == "PRASAD":
            if self._is_on and temp > 18 and temp < 30:
                self._tempreature\
                    = temp
            elif not (temp > 18 and temp < 30):
                raise InvalidParameterError

            else:
                raise DeviceOfflineError
        else:
            raise  AuthenticationError

    def get_status_report(self):
        return {"_tempreature"
                "":self._tempreature
            ,"_is_on":bool(self._is_on) }
        # print("current tempreture=",self._tempreature
        # )
    async def perform_action(self,action_type,user_role, value=None):
        if action_type== "set temperature" :
            self._tempreature =value

            print("success")
        elif action_type.lower()=="get status report":
            print(self.get_status_report())
        elif action_type.upper()=="TURN OFF":
            await self.turn_off()
        elif action_type.upper()== "TURN ON":
            await self.turn_on(user_role)
        else:
            raise  ActionNotSupportedError

    def get_supported_actions(self):
        return ["set temperature","get_status_report", "Turn OFF","Turn ON"]


class SmartDoorLock(SmartDevice):
    def __init__(self,device_id,passcode="user",is_on=True):
        if not re.match(r"^[A-Z][0-9]{3}$",device_id):
            raise InvalidParameterError(f"Invalid device_id: {device_id}")

        self._passcode = passcode
        self._lock_state="Locked" if is_on else "Un locked"
        super().__init__(device_id,is_on)


    @property
    def passcode(self):
        return self._passcode

    @passcode.setter
    def passcode(self, new_passcode_user_role):
        new_passcode,user_role=new_passcode_user_role
        if user_role.upper()=="PRASAD":
            if re.match(r"((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[-@#$!])){8,}", new_passcode):
                self._passcode = new_passcode
            else:
                raise InvalidParameterError(f"Invalid Password Type(Conditions not reached)")
        else:
            raise  AuthenticationError


    def get_status_report(self):
        return {"_passcode":self._passcode,"_lock_state":self._lock_state,"_is_on":self._is_on}

    async  def perform_action(self, action_type,user_role, new_passcode=None):
        if action_type =="Lock Door":
            self._lock_state="Locked"

            self._is_on = True
        elif action_type == "Un Lock Door" or action_type== "set passcode":

            if  re.match(r"((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[-@#$!])){8,}",new_passcode):
                if action_type== "Un Lock Door" and new_passcode==self.passcode:
                    self._is_on=False

                    self._lock_state="UnLocked"
                else:
                    if user_role.upper()=="PRASAD":
                        self._passcode = (new_passcode,user_role)

                        print("Passcode changed successfully")
                    else:
                        raise  AuthenticationError

            else:
                raise InvalidParameterError
        elif action_type=="get status report":
            return {"_lock_state":self._lock_state}
        else:
            raise ActionNotSupportedError

    def get_supported_actions(self):
        return ["set passcode","get status report","Lock Door","Un Lock Door"]


class SmartCamera(SmartDevice):
    def __init__(self,device_id,resolution=0,is_on=True):
        self._resolution=resolution
        super().__init__(device_id,is_on)
    @property
    def resolution(self):
        return self._resolution

    @resolution.setter
    def resolution(self,level_user_role):
        level,user_role=level_user_role
        if user_role.upper()=="PRASAD":
            if self._is_on and level > 0.3:
                self._resolution = level
                print("resolution setting is success")
            elif not (level > 0.3):
                print("resolution level is out of range")

            else:
                raise  DeviceOfflineError
        else:
            raise  AuthenticationError

    def get_status_report(self):
        return {"_resolution":self._resolution,"_is_on" : bool(self._is_on )}
        # print("current resolution:",self._resolution,)
        # print("currently camera is in  ","On" if self._is_on else "OFF","mode")

    async def perform_action(self, action_type,user_role,value=None):
        if action_type == "set resolution" :
            if 0.3< value :
                self.resolution = (value,user_role)
            else:
                print("resolution is out of range")
        elif action_type.upper()=="TURN OFF":
            await  self.turn_off()
        elif action_type.upper()== "TURN ON":
            await self.turn_on(user_role)
        elif action_type=="get status report":
           return  self.get_status_report()
        else:
            print("invalid action type !!!")


    def get_supported_actions(self):
        return ["set resolution","get status report","Turn off","Turn on"]


class SmartSpeaker(SmartDevice):
    def __init__(self, device_id, volume=30, is_on=True, playlist=None):
        if playlist is None:
            playlist =  {
                "Romantic Hits": {
                    "ఇంకేమ్ ఇంకేమ్ ఇంకేమ్ కావాలే": 5,
                    "నీ కన్ను నీలి సముద్రం": 5,
                    "సామజవరగమనా": 4
                },
                "Dance Numbers": {
                    "బుట్ట బొమ్మ": 3,
                    "టాప్ లేసి పోడ్డి": 4,
                    "సినిమా చూపిస్త మామ": 3
                },
                "Mass Beats": {
                    "నాటు నాటు": 4,
                    "రాములో రాములా": 4,
                    "సీటీ మార్": 3
                },
                "Melody Hits": {
                    "ఊసుపోడు": 4,
                    "కలుసుకోవాలని": 4,
                    "తారక": 4
                }
            }
        self._volume=volume
        self.playlist=playlist
        super().__init__(device_id,is_on)
    @property
    def volume(self):
        return  self._volume
    @volume.setter
    def volume(self,value_user_role):
        value,user_role=value_user_role
        if user_role.upper()=="PRASAD":
            print(self._is_on,"----->>")
            if self._is_on and 0 <= value < 100:
                self._volume=value
                print("Volume setting is successful")
            elif not (0 <= value < 100):
                print("volume level is out of range")

            else:
                raise DeviceOfflineError
        else:
            raise  AuthenticationError
    async def music(self,track_name=None):
        if not track_name:
            for i in self.playlist:
                print("playing {} track".format(i))
                print("-"*30)
                for j,k in self.playlist[i].items():
                    print("playing song:"+j+"({} min)".format(k))
                    print()
                    await asyncio.sleep(k)
                    print("song {} completed".format(j))
                    print()
        elif track_name in self.playlist:
            print("_" * 30)
            print("playing {} track".format(track_name))
            print("_" * 30)
            for j, k in self.playlist[track_name].items():
                print("playing song:" + j + "({} min)".format(k))
                print()
                await asyncio.sleep(k)
                print("song {} completed".format(j))
                print()
        else:
            print("Track name is not available")
    async def get_play(self, track_name=None):
            await self.music(track_name)

    def get_status_report(self):
        return {"_volume" :self._volume,"_is_on": self._is_on}
        # print("current Volume=",self.volume)
        # print("currently Speaker is in  ","On" if self._is_on else "OFF","mode")

    async  def perform_action(self, action_type, user_role,value=None):
        if action_type == "set volume" :
            self.volume=(value,user_role)
        elif action_type.upper()=="TURN OFF":
            await  self.turn_off()
        elif action_type.upper() == "TURN ON":
            await  self.turn_on(user_role)
        elif action_type.lower()== "play":
            await self.get_play(value)
        elif action_type=="get status report":
            return self.get_status_report()
        else:
            raise  AuthenticationError

    def get_supported_actions(self):
        return [ "set volume","get status report","play","Turn OFF", "Turn ON"]










# speaker2= SmartSpeaker("S502", 10)

