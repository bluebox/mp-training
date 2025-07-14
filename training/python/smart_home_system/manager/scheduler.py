import asyncio
from datetime import datetime

class Scheduler:
    def __init__(self):
        self._tasks = []

    def add_scheduled_task(self, time_str, device_id, action_type, value, user_role):
        task = {
            "time": time_str,
            "device_id": device_id,
            "action_type": action_type,
            "value": value,
            "user_role": user_role
        }
        self._tasks.append(task)
        print(f"task scheduled for {device_id} at {time_str} to perform '{action_type}'")

    async def run_pending_tasks(self, home_manager):
        current_time = datetime.now().strftime("%H:%M")

        tasks_to_run = [task for task in self._tasks if task["time"] == current_time]
        print(self._tasks)
        if not tasks_to_run:
            print(f"No tasks scheduled now ({current_time})")
            return

        tasks =[
             home_manager.control_device(
                task["user_role"],
                task["device_id"],
                task["action_type"],
                task["value"]
            ) for task in tasks_to_run
         ]
        await asyncio.gather(*tasks)

        self._tasks = [task for task in self._tasks if task["time"] != current_time]


scheduler_instance = Scheduler()