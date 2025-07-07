from datetime import datetime
import asyncio


class Scheduler:
    def __init__(self):
        self.tasks = []

    def add_scheduled_task(self, time_str, device_id, action_type, value, user_role):
        self.tasks.append({
            'time': time_str,
            'device_id': device_id,
            'action_type': action_type,
            'value': value,
            'user_role': user_role
        })

    async def run_pending_tasks(self, home_manager):
        now_str = datetime.now().strftime("%H:%M")
        tasks_to_run = [task for task in self.tasks if task['time'] == now_str]

        for task in tasks_to_run:
            await home_manager.execute_action(
                task['device_id'],
                task['action_type'],
                task['value'],
                task['user_role']
            )

        self.tasks = [task for task in self.tasks if task['time'] != now_str]
