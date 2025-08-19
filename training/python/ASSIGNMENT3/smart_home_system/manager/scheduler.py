import asyncio
from datetime import datetime
from  ASSIGNMENT3.smart_home_system.manager.home_manager import HomeManager


class Scheduler(HomeManager):
    def __init__(self):

        self.schedule_dict={}
    def add_scheduled_task(self,time_str, device_id, action_type, value, user_role):
        time_str = datetime.strptime(time_str, "%H:%M").time().strftime("%H:%M")
        self.schedule_dict[time_str]=(device_id,action_type,value,user_role)

    async def run_scheduled_tasks(self,home_manager_instance):
        while True:
            current_time = datetime.now().time()
            if current_time.strftime("%H:%M") in self.schedule_dict:
                await home_manager_instance.control_device(*self.schedule_dict[current_time.strftime("%H:%M")])
                del self.schedule_dict[current_time.strftime("%H:%M")]
            if len(self.schedule_dict)==0 or all(datetime.strptime(key, "%H:%M").time()<current_time for key in self.schedule_dict.keys()):
                print("***ALL current tasks TASKS ARE COMPLETED***")
                self.run_pending_tasks(home_manager_instance)
                break
    def run_schedule(self):
        asyncio.create_task(self.run_scheduled_tasks())

    async def run_pending_schedule(self, home_manager_instance):
        temp=[]
        while True:

            current_time = datetime.now().time()
            times_less_than_now = [t  for t in self.schedule_dict if datetime.strptime(t, "%H:%M").time() < current_time]
            times_less_than_now=[i for i in times_less_than_now if i not in temp]
            temp=[]
            if len(times_less_than_now)==0:
                break
            for i in times_less_than_now:
                await  home_manager_instance.control_device(*self.schedule_dict[i])
                temp.append(i)



    def run_pending_tasks(self,home_manager_instance):
        asyncio.create_task(self.run_pending_schedule(home_manager_instance))




# z=Scheduler()
# z.add_scene("welcome",("S123","set brightness",40,"Prasad"))
# z.add_scene("welome",("K098","set reslution",6,"Prasad"))
# z.add_scene("welcome",("T564","et_temperature",32,"Prasad"))










