import unittest
from datetime import datetime
import os

from core.devicess import SmartDevice, SmartLight
from core.exceptions import PermissionDeniedError
from manager.home_manager import HomeManager
from manager.scene_manager import SceneManager
from manager.scheduler import Scheduler


class TestSmartHomeIntegration(unittest.IsolatedAsyncioTestCase):
    def setUp(self):
        SmartDevice.devices.clear()

        self.home_manager = HomeManager()
        self.scene_manager = SceneManager()
        self.scheduler = Scheduler()

        self.light = SmartLight("SD1")
        self.home_manager.add_device(self.light)

    async def test_scene_and_scheduler_integration(self):
        await self.light.turn_on()

        self.scene_manager.add_scene("EveningMode", [
            ("SD1", "set_brightness", 70)
        ])

        await self.scene_manager.activate_scene(self.home_manager, "EveningMode", "Admin")
        self.assertEqual(self.light.brightness, 70, "Scene did not update brightness")

        now_str = datetime.now().strftime("%H:%M")
        self.scheduler.add_scheduled_task(now_str, "SD1", "set_brightness", 90, "Admin")

        await self.scheduler.run_pending_tasks(self.home_manager)
        self.assertEqual(self.light.brightness, 90, "Scheduled task did not update brightness")

    async def test_permission_enforcement_in_scene(self):
        await self.light.turn_on()

        self.scene_manager.add_scene("RestrictedScene", [
            ("SD1", "set_brightness", 50)
        ])

        await self.scene_manager.activate_scene(self.home_manager, "RestrictedScene", "Guest")

        self.assertNotEqual(self.light.brightness, 50, "Guest should not be able to set brightness")

    async def test_permission_enforcement_in_scheduler(self):
        await self.light.turn_on()

        now_str = datetime.now().strftime("%H:%M")

        self.scheduler.add_scheduled_task(now_str, "SD1", "set_brightness", 40, "Guest")
        await self.scheduler.run_pending_tasks(self.home_manager)

        self.assertNotEqual(self.light.brightness, 40, "Guest scheduled action should not apply")

if __name__ == "__main__":
    unittest.main()
