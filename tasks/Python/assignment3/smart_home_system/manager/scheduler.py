import asyncio
from _datetime import datetime, time

from smart_home_system.core.devices import SmartLight
from smart_home_system.core.exceptions import InvalidParameterError
from smart_home_system.manager.home_manager import HomeManager


class Scheduler:

    def __init__(self):
        self.scheduled_tasks = []

    def add_scheduled_task(self, time_str, device_id, action_type, value, user_role):
        self.scheduled_tasks.append((time_str, device_id, action_type, value, user_role))

    async def run_pending_tasks(self,home_manager):
        self.scheduled_tasks.sort(key = lambda x:x[0])
        i = 0
        while len(self.scheduled_tasks) != 0:
            time_str, device_id, action_type, value, user_role = self.scheduled_tasks[i]
            hours, minutes = map(int, time_str.split(":"))
            now = datetime.now()
            diff = (hours - now.hour)*60*60 + (minutes - now.minute)*60
            if minutes != now.minute:
                diff -= now.second
            if diff < 0:
                a = self.scheduled_tasks.pop(i)
                print("past event,\n popped task",a)
            elif diff == 0:
                print("present event")
                await home_manager.control_device(device_id,action_type,value,user_role)
                self.scheduled_tasks.pop(i)

            else:
                print("future event")
                await asyncio.sleep(diff)
                await home_manager.control_device(device_id, action_type, value, user_role)
                self.scheduled_tasks.pop(i)


if __name__ == "__main__":
    manager = HomeManager()
    try:
        light = SmartLight("L001")
    except InvalidParameterError as e:
        print(e.message)
    else:
        manager.add_device(light)
        asyncio.run(light.turn_on())
        light.get_supported_actions()
        scheduler = Scheduler()
        scheduler.add_scheduled_task("18:58", "L001", "get_status_report", None, "admin")
        scheduler.add_scheduled_task("18:57","L001","set_brightness",30,"admin")
        scheduler.add_scheduled_task("18:58", "L001", "get_brightness", None, "admin")
        scheduler.add_scheduled_task("18:57", "L001", "turn_on", 30, "admin")
        scheduler.add_scheduled_task("18:58", "L001", "turn_off", 30, "admin")
        scheduler.add_scheduled_task("18:58", "L001", "get_status_report", 30, "admin")
        asyncio.run(scheduler.run_pending_tasks(manager))