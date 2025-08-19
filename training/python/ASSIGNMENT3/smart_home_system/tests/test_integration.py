
import unittest
import asyncio
from ASSIGNMENT3.smart_home_system.manager.home_manager import HomeManager
from ASSIGNMENT3.smart_home_system.manager.scene_manager import SceneManager
from ASSIGNMENT3.smart_home_system.manager.scheduler import Scheduler
from ASSIGNMENT3.smart_home_system.core.devices import SmartLight

class TestIntegration(unittest.IsolatedAsyncioTestCase):

    async def asyncSetUp(self):
        self.home = HomeManager()
        self.sceneManager = SceneManager()
        self.scheduler = Scheduler()

        self.light = SmartLight("L009", 50,)
        await self.light.turn_on()
        self.home.add_device(self.light)

        self.sceneManager.add_scene("Morning", ("L009", "set brightness", 70))
        self.scheduler.add_scheduled_task("10:00", "L009", "set brightness", 85, "prasad")

    async def test_activate_scene(self):
        await self.sceneManager.activate_scene(self.home, "Morning", "prasad")
        self.assertEqual(self.light.brightness, 70)

    async def test_run_scheduler(self):
        print(self.light.brightness)
        await self.scheduler.run_pending_schedule(self.home)
        self.assertEqual(self.light.brightness, 85)
