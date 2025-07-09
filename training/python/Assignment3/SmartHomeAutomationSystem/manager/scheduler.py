import asyncio
import datetime

class Scheduler:
    def __init__(self):
        self.tasks = []

    def add_scheduled_task(self, time_str, device_id, action_type, value, user_role):
        if not isinstance(time_str, str) or len(time_str) != 5 or time_str[2] != ':':
            raise ValueError("Time format must be HH:MM")
        self.tasks.append({
            'time': time_str,
            'device_id': device_id,
            'action_type': action_type,
            'value': value,
            'user_role': user_role
        })

    async def run_pending_tasks(self, home_manager):
        while True:
            now = datetime.datetime.now().strftime("%H:%M")
            to_run = [t for t in self.tasks if t['time'] == now]
            for t in to_run:
                print(f"Running scheduled task for {t['device_id']} at {now}")
                await home_manager.control_device(
                    t['user_role'],
                    t['device_id'],
                    t['action_type'],
                    t['value']
                )
            self.tasks = [t for t in self.tasks if t not in to_run]
