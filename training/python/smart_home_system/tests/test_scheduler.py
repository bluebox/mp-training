import unittest
from datetime import datetime

from core.devicess import SmartDevice, SmartLight
from manager.home_manager import HomeManager
from manager.scene_manager import SceneManager
from manager.scheduler import Scheduler


class TestSceneSchedulerIntegration(unittest.IsolatedAsyncioTestCase):
    def setUp(self):
        SmartDevice.devices.clear()
    async def test_scene_activation_and_schedule(self):
        manager = HomeManager()
        scene_mgr = SceneManager()
        scheduler = Scheduler()

        light = SmartLight("SD1")
        await light.turn_on()
        manager.add_device(light)

        scene_mgr.add_scene("Morning", [("SD1", "set_brightness", 85)])
        await scene_mgr.activate_scene(manager, "Morning", "Admin")
        self.assertEqual(light.brightness, 85)

        scheduler.add_scheduled_task(datetime.now().strftime("%H:%M"), "SD1", "set_brightness", 90, "Admin")
        await scheduler.run_pending_tasks(manager)
        self.assertEqual(light.brightness, 90)

if __name__=="__main__":
    unittest.main()