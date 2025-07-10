import asyncio
from datetime import datetime

from smart_home_system.core.devices import SmartLight
from smart_home_system.core.exceptions import SmartHomeError, InvalidParameterError
from smart_home_system.manager.home_manager import HomeManager


class Scheduler:

    def __init__(self):
        self.scheduled_tasks = []

    def add_scheduled_task(self, time_str, device_id, action_type, value, user_role):
        self.scheduled_tasks.append((time_str, device_id, action_type, value, user_role))
        print(f" Task added for {time_str} -> Device: {device_id}, Action: {action_type}, Role: {user_role}")

    async def run_pending_tasks(self,home_manager):
        print(" Scheduler started... Waiting for tasks to trigger...")
        self.scheduled_tasks.sort(key = lambda x:x[0])
        i = 0
        while self.scheduled_tasks:
            time_str, device_id, action_type, value, user_role = self.scheduled_tasks[i]
            try:
                hours, minutes = map(int, time_str.split(":"))
            except ValueError:
                print(f" Invalid time format in task: {time_str}")
                self.scheduled_tasks.pop(i)
                continue
            now = datetime.now()
            diff = (hours - now.hour)*60*60 + (minutes - now.minute)*60
            if minutes != now.minute:
                diff -= now.second
            if diff < 0:
                print(f" Missed Task (Past): {self.scheduled_tasks[i]} — Skipping")
                a = self.scheduled_tasks.pop(i)
            elif diff == 0:
                print(f" Running Task Now: {self.scheduled_tasks[i]}")
                try:
                    await home_manager.control_device(device_id, action_type, value, user_role)
                except SmartHomeError as e:
                    print(f" Error while running task: {e}")
                self.scheduled_tasks.pop(i)

            else:
                await asyncio.sleep(diff)
                # await home_manager.control_device(device_id, action_type, value, user_role)
                # self.scheduled_tasks.pop(i)


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
        scheduler.add_scheduled_task("14:54", "L001", "get_status_report", None, "admin")
        scheduler.add_scheduled_task("14:54","L001","set_brightness",30,"admin")
        scheduler.add_scheduled_task("14:54", "L001", "get_brightness", None, "admin")
        scheduler.add_scheduled_task("14:54", "L001", "turn_on", 30, "admin")
        scheduler.add_scheduled_task("14:54", "L001", "turn_off", 30, "admin")
        scheduler.add_scheduled_task("14:54", "L001", "get_status_report", 30, "admin")
        asyncio.run(scheduler.run_pending_tasks(manager))