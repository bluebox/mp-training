import unittest
from ASSIGNMENT3.smart_home_system.manager.home_manager import HomeManager
from ASSIGNMENT3.smart_home_system.core.devices import SmartLight
from ASSIGNMENT3.smart_home_system.core.exceptions import *


class TestHomeManager(unittest.IsolatedAsyncioTestCase):

    async def asyncSetUp(self):
        self.home = HomeManager()
        self.light = SmartLight("L005", brightness=60)
        await self.light.turn_on()
        self.home.add_device(self.light)

    async def test_control_device_success(self):
        result=await self.home.control_device("L005", "set brightness", 90, "prasad")
        self.assertEqual(result,"success")

    async def test_control_device_AuthenticationError(self):
        with self.assertRaises(AuthenticationError):
            await self.home.control_device("L005", "set brightness", 90, "guest")

    async def test_control_device_not_found(self):
        with self.assertRaises(ValueError):
            await self.home.control_device("X999", "turn on", None, "admin")
        self.hm = HomeManager()
        self.light = SmartLight("L005", brightness=60)
        await self.light.turn_on()
        self.hm.add_device(self.light)

    async def test_control_device_success(self):
        result = await self.hm.control_device("admin", "L005", "set_brightness", 90)
        self.assertIn("Brightness set to 90", result)

    async def test_control_device_permission_denied(self):
        with self.assertRaises(PermissionDeniedError):
            await self.hm.control_device("guest", "L005", "set_brightness", 90)

    async def test_control_device_not_found(self):
        with self.assertRaises(ValueError):
            await self.hm.control_device("admin", "X999", "turn_on", None)

    async def test_get_all_status(self):
        statuses = self.hm.get_all_device_statuses()
        self.assertIsInstance(statuses, list)
    async def test_get_all_status(self):
        statuses = self.home.get_all_device_statuses()
        self.assertIsInstance(statuses, list)