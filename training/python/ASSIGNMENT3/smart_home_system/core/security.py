from abc import abstractmethod
from ASSIGNMENT3.smart_home_system.core.exceptions import *

from  ASSIGNMENT3.smart_home_system.core.devices import  SmartDevice



class SecuritySensor(SmartDevice):
    def __init__(self,device_id,is_on=False):
        self.is_on=is_on
        super().__init__(device_id,is_on)

    def get_status_report(self):
        print("SecuritySensor is " + "armed" if self.is_on else "unarmed")


class Programmable:
    @abstractmethod
    def schedule_task(self, time_str, action, value):
        pass



class SmartAlarmSystem(SmartDevice,Programmable):
    def __init__(self, device_id,is_on=True):
        super().__init__(device_id,is_on)
        self.__schedule={}

    async def perform_action(self, action_type, user_role=None,value=None,time_str=""):
        if action_type=="arm":
            if user_role.upper()=="PRASAD":
                await self.turn_on()
            else:
                raise AuthenticationError
        elif action_type== "disarm":
            await self.turn_off()
        elif action_type=="get status report":
            return self.get_status_report()
        elif action_type=="schedule task":
            return self.schedule_task(time_str,action_type,value)
        else:
            raise ActionNotSupportedError

    def get_status_report(self):
        return {"__schedule":self.__schedule,"is_on":self.is_on}
    def get_supported_actions(self):
        return ["arm","disarm","get status report","schedule task"]

    def schedule_task(self, time_str, action, value):
        self.__schedule[time_str] = (action, value)

