from functools import reduce
from ASSIGNMENT3.smart_home_system.core.devices import *



class HomeManager:
    def __init__(self):
        self._devices=[]
    def add_device(self,device):
        for i in self._devices:
            if i.device_id == device.device_id:
                print(f"Device {device.id} already added")
                return
        self._devices.append(device)
        print(f"devices {device.device_id} added")

    async  def control_device(self,device_id, action_type, value=None,user_role="user"):
        for i in self._devices:
            if i._device_id==device_id:
                await i.perform_action(action_type, user_role,value)
                return "success"
        await  asyncio.sleep(2)
        raise ValueError

    def get_all_device_statuses(self):
        l=[]
        for i in self._devices:
            l.append(i.get_status_report())
        return  l
    async def turn_on_all(self,user_role):
        for i in self._devices:
            await i.turn_on(user_role)

    async def turn_off_all(self):
        for i in self._devices:
            await i.turn_off()




    def get_online_device_statuses(self):
        result=list(filter(lambda x: x._is_on, self._devices))
        # return list(filter(lambda x: x._is_on, self._devices))
        return [i._device_id for i in result]
    def status(self):
        return list(map(lambda  x:vars(x),self._devices))
        # return list(map(lambda x:"ON" if x.is_online else "OFF", self._devices))
    def get_average_temperature(self):
        l=[i._tempreature  for i in self._devices if isinstance(i,SmartThermostat)]
        return reduce(lambda x,y:x+y, l)/len(l)



    def Getting_unique_types_of_active_devices(self):
        return  list({i._device_id for i in self._devices if  i._is_on})
    def Mapping_device_IDsfull_status_reports(self):
        return {i.device_id :i.get_status_report for i in self._devices  }


    def generator_functions(self,device_type,active_or_inactive):
        for i in self._devices:
            if active_or_inactive:
                if i.__class__.__name__ == device_type and i._is_on:
                    yield  i
            else:
                if i.__class__.__name__ == device_type and not i._is_on:
                    yield  i
    def get_required_devices(self,device_type,active_or_inactive):
        l=[]
        for i in self.generator_functions(device_type,active_or_inactive):
            l.append(i)
        return l






