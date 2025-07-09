import unittest
from datetime import datetime
from SmartHomeAutomationSystem.manager.scene_manager import SceneManager
from SmartHomeAutomationSystem.manager.scheduler import Scheduler
from SmartHomeAutomationSystem.manager.home_manager import HomeManager
from SmartHomeAutomationSystem.core.devices import SmartLight, SmartThermostat
from SmartHomeAutomationSystem.core.exceptions import PermissionDeniedError


class TestSceneAndSchedulerIntegration(unittest.IsolatedAsyncioTestCase):
    async def asyncSetUp(self):
        self.home_manager = HomeManager()
        self.scene_manager = SceneManager()
        self.scheduler = Scheduler()

        self.light = SmartLight("L007", brightness=50)
        self.thermostat = SmartThermostat("T008", temperature=20)

        self.home_manager.add_device(self.light)
        self.home_manager.add_device(self.thermostat)

    async def test_scene_activation_admin_role(self):
        self.scene_manager.add_scene("Night Mode", [
            ("L007", "turn_on", None),
            ("T008", "set_temperature", 22)
        ])
        result = await self.scene_manager.activate_scene(self.home_manager, "Night Mode", user_role="admin")

        self.assertTrue(self.light.is_on)
        self.assertEqual(self.thermostat.temperature, 22)
        self.assertIn("Action", result[0])
        self.assertIn("Action", result[1])

    async def test_scene_activation_with_permission_denied(self):
        self.scene_manager.add_scene("Restricted Mode", [
            ("T008", "set_temperature", 25)
        ])
        result = await self.scene_manager.activate_scene(self.home_manager, "Restricted Mode", user_role="guest")

        self.assertTrue(any("guest cannot perform set_temperature" in r for r in result))
        self.assertEqual(self.thermostat.temperature, 20)

    async def test_scheduler_runs_task_at_correct_time(self):
        current_time = datetime.now().strftime("%H:%M")
        self.scheduler.add_scheduled_task(
            time_str=current_time,
            device_id="L007",
            action_type="turn_on",
            value=None,
            user_role="user"
        )

        await self.scheduler.run_pending_tasks(self.home_manager)

        self.assertTrue(self.light.is_on)
        self.assertEqual(len(self.scheduler.tasks), 0)

    async def test_scheduler_permission_denied(self):
        current_time = datetime.now().strftime("%H:%M")
        self.scheduler.add_scheduled_task(
            time_str=current_time,
            device_id="T008",
            action_type="set_temperature",
            value=26,
            user_role="guest"
        )

        await self.scheduler.run_pending_tasks(self.home_manager)

        self.assertEqual(self.thermostat.temperature, 20)

    async def test_scene_activation_nonexistent_scene(self):
        result = await self.scene_manager.activate_scene(self.home_manager, "UnknownScene", user_role="admin")
        self.assertEqual(result, "Scene 'UnknownScene' does not exist.")
