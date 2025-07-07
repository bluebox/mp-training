import asyncio
from datetime import timedelta
from core.devices import SmartDevice
from manager.scene_manager import SceneManager
from manager.home_manager import HomeManager


class Scheduler:
    def __init__(self):
        self.schedule = []

    @staticmethod
    def _next_fire_datetime(time_str: str):
        now = SmartDevice.get_system_time()
        hrs, mins = map(int, time_str.split(":"))
        fire_at = now.replace(hour=hrs, minute=mins, second=0, microsecond=0)
        if fire_at <= now:
            fire_at += timedelta(days=1)
        return fire_at

    def add_scheduled_task(
        self, time_str, device_id, action_type,
        is_scene: bool = False, value=None
    ):
        fire_at = self._next_fire_datetime(time_str)
        self.schedule.append((fire_at, device_id, action_type, is_scene, value))
        self.schedule.sort(key=lambda t: t[0])
        print(f"[Scheduler] Task added: {device_id} -> {action_type} at {fire_at.time()}")

    async def run_pending_tasks(self, home_manager: HomeManager):
        while self.schedule:
            now = SmartDevice.get_system_time()
            ready_tasks = []

            while self.schedule and self.schedule[0][0] <= now:
                ready_tasks.append(self.schedule.pop(0))

            for fire_at, device_id, action_type, is_scene, value in ready_tasks:
                print(f"[Scheduler] Executing task: {device_id}  {action_type} at {now.time()}")
                try:
                    if is_scene:
                        scene = SceneManager()
                        await scene.activate_scene(home_manager, device_id, user_role="admin")
                    else:
                        await home_manager.control_device("admin", device_id, action_type, value)
                except Exception as e:
                    print(f"[Scheduler] Error executing task for {device_id}: {e}")

            if self.schedule:
                next_fire_at = self.schedule[0][0]
                delay = max((next_fire_at - SmartDevice.get_system_time()).total_seconds(), 0)
                await asyncio.sleep(delay)


if __name__ == "__main__":
    from core.devices import SmartLight

    async def test_scheduler():
        sch = Scheduler()
        manager = HomeManager()

        light = SmartLight("L001")
        manager.add_device(light)

        sch.add_scheduled_task("6:42", "L001", "set_brightness", value=50)
        sch.add_scheduled_task("6:42", "L001", "set_brightness", value=40)
        sch.add_scheduled_task("6:42", "morning", None, is_scene=True)

        await sch.run_pending_tasks(manager)

    asyncio.run(test_scheduler())
