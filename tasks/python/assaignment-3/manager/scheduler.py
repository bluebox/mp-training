import asyncio

from core.devices import SmartDevice,SmartLight
from Scene_manager import SceneManager
from manager.home_manager import HomeManager

class Scheduler:
    schedule = []
    def add_scheduled_task(self,time_str, device_id, action_type,is_scene=False,value=None):
        self.schedule.append((time_str,device_id,action_type,is_scene,value))
    async def run_pending_tasks(self,home_manager):
        while len(self.schedule) >0:
            for time_str, device_id, action_type, is_scene,value in self.schedule:
                hrs,minutes = map(str,time_str.split(":"))
                now = SmartDevice.get_system_time()
                scheduled_time = now.replace(hour=int(hrs),minute=int(minutes))
                if now > scheduled_time:
                    continue
                if now == scheduled_time:
                    if is_scene:
                        scene_instance = SceneManager()
                        await scene_instance.activate_scene(home_manager,device_id,user_role="Admin")
                        self.schedule.remove((time_str, device_id, action_type, is_scene,value))
                        break
                    else:
                        await home_manager.control_device("Admin",device_id,action_type,value)
                        self.schedule.remove((time_str, device_id, action_type, is_scene, value))
                        break

if __name__ == "__main__":
    sch = Scheduler()
    manager = HomeManager()
    light = SmartLight("Light1")
    light.turn_on()
    manager.add_device(light)
    sch.add_scheduled_task("17:27","Light1","set_brightness",value=50)
    sch.add_scheduled_task("17:27","Light1","set_brightness",value=40)
    scene = SceneManager()
    sch.add_scheduled_task("17:29","morning",None,is_scene=True)
    asyncio.run(sch.run_pending_tasks(manager))


