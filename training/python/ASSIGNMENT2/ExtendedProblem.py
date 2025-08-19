from abc import ABC,abstractmethod
from datetime import datetime


class SmartDevice(ABC):
    device_count=0
    def __init__(self,device_id,is_on=False):
        SmartDevice.device_count+=1
        self._is_on=is_on
        self._device_id=device_id

    def turn_on(self):
        self.__is_on=True
        print("Device",self._device_id,"turned ON.")
    def turn_off(self):
        self.__is_on=False
        print("Device",self._device_id,"turned OFF.")
    @abstractmethod
    def get_status_report(self):
        pass

    @abstractmethod
    def perform_action(self,action_type, value=None):
        pass
    @property
    def is_on(self):
        return self._is_on

    @classmethod
    def get_device_count(cls):
        return cls.device_count

    @staticmethod
    def get_system_time():
        return datetime.now().time()








class SmartLight(SmartDevice):
    def __init__(self,device_id,brightness=0,is_on=True):
        self.__brightness=brightness
        super().__init__(device_id,is_on)
    @property
    def brightness(self):
        return self.__brightness

    @brightness.setter
    def brightness(self,level):
        if  self._is_on and  level>0 and level<100 :
            self.__brightness=level
        elif not (level>0 and level<100):
            print("brightness level is out of range")

        else:
            print("Device is in off mode")

    def get_status_report(self):
        print("current brightness=",self.__brightness)

    def perform_action(self,action_type, value=None):
        if action_type=="set_brightness" and value>0 and value<100:
            self.__brightness=value
            print("success")
        else:
            print("error during perfoming the action")





class SmartThermostat(SmartDevice):
    def __init__(self,device_id,tempreature=20.0,is_on=True):
        self.__tempreature=tempreature
        super().__init__(device_id,is_on)
    @property
    def tempreature(self):
        return  self.__tempreature
    @tempreature.setter
    def temperature(self,temp):
        if self._is_on and temp > 18 and temp < 30:
            self.__tempreature = temp
        elif not (temp > 18 and temp < 30):
            print("tempreature level is out of range")

        else:
            print("Device is in off mode")
    def get_status_report(self):
        print("current tempreture=",self.__tempreature)
    def perform_action(self,action_type, value=None):
        if action_type=="set_temperature" and value>18.0 and value<30.0:
            self.__tempreature=value
            print("success")
        else:
            print("error")



class Programmable:
    def schedule_task(self):
        return




class SecuritySensor(SmartDevice):
    def __init__(self, is_armed, device_id):
        super().__init__(device_id)
        self.__is_armed=is_armed

    def arm_sensor(self) :
        return




class SmartAlarmSystem(SmartDevice,Programmable):
    def __init__(self, device_id,is_on=True):
        super().__init__(device_id,is_on)

    def perform_action(self, action_type, value=None):
        if action_type=="arm":
            self.turn_on()
        elif action_type== "disarm":
            self.turn_off()
        else:
            print("error")

    def get_status_report(self):
        if self._is_on:
            print("arm")
        else:
            print("disarm")
    def schedule_task(self):
        print("Alarm system task scheduled.")




class HomeManager:
    def __init__(self):
        self._devices=[]
    def add_device(self,device):
        self._devices.append(device)

    def control_device(self,device_id, action_type, value=None):
        for i in self._devices:


            if i._device_id==device_id:
                i.perform_action(action_type, value)
                return
        print("divice is not found")
        return
    def get_all_device_statuses(self):
        for i in self._devices:
            i.get_status_report()
    def turn_on_all(self):
        for i in self._devices:
            i.turn_on()




manager=HomeManager()
smartLight=SmartLight("S123",50)
smartThermostat=SmartThermostat("T564",26.35)
smartAlarmSystem=SmartAlarmSystem("A102")
manager.add_device(smartLight)
manager.add_device(smartThermostat)
manager.add_device(smartAlarmSystem)

manager.turn_on_all()


manager.control_device("S123", "set_brightness", value= 68)

manager.control_device("T564", "set_temperature", value= 68)

manager.control_device("A102", "arm")
manager.control_device("A102", "disarm")

manager.control_device("S123", "set_brightness", value= 680)
print("----")

manager.get_all_device_statuses()

for i in manager._devices:
    if i._device_id=="S123":
        i.turn_off()
total_devices=SmartDevice.get_device_count()
print("total devices:",total_devices)


print("current time is ",SmartDevice.get_system_time())

print(SmartAlarmSystem.mro())



