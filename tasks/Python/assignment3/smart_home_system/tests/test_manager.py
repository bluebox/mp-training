import unittest

from smart_home_system.core.devices import SmartLight, SmartThermostat
from smart_home_system.core.exceptions import InvalidParameterError
from smart_home_system.manager.home_manager import HomeManager
from smart_home_system.manager.scene_manager import SceneManager



class TestHomeManager(unittest.IsolatedAsyncioTestCase):

    def setUp(self):
        self.manager = HomeManager()
        self.light = SmartLight("L001")
        self.thermostat = SmartThermostat("T001")
        self.manager.add_device(self.light)
        self.manager.add_device(self.thermostat)

    async def test_add_device(self):
        self.assertEqual(len(self.manager.devices), 2)
        self.assertEqual(self.manager.devices[0].device_id, "L001")

    async def test_control_device_valid(self):
        result = await self.manager.control_device("L001", "turn_on", user_role="admin")
        self.assertIsNone(result)
        self.assertTrue(self.light.is_on)


    async def test_control_device_invalid_action(self):
        result = await self.manager.control_device("L001", "invalid_action", user_role="admin")
        self.assertIsNone(result)

    def tearDown(self):
        self.manager = None


class TestSceneManager(unittest.IsolatedAsyncioTestCase):

    def setUp(self):
        self.scene_manager = SceneManager()
        self.manager = HomeManager()
        self.light = SmartLight("L001")

    def test_add_scene(self):
        self.scene_manager.add_scene("movie_time", ("L001", "set_brightness", 30))
        self.assertEqual(self.scene_manager.scenes["movie_time"],[("L001", "set_brightness", 30)])

    async def test_activate_scene(self):
        self.scene_manager.add_scene("movie_time", ("L001", "set_brightness", 30))
        with self.assertRaises(InvalidParameterError):
            await self.scene_manager.activate_scene(self.manager,"time_pass","admin")
