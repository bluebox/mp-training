import unittest
import asyncio
from unittest.mock import patch, mock_open
from datetime import datetime

from core.devicess import SmartLight,SmartDevice

from manager.home_manager import HomeManager

class TestFunctionalGenerators(unittest.IsolatedAsyncioTestCase):
    async def test_functional_and_generator_methods(self):
        manager = HomeManager()
        light = SmartLight("SD1")
        await light.turn_on()
        light.brightness = 75
        manager.add_device(light)

        self.assertEqual(manager.get_online_device_ids(), ["SD1"])
        self.assertEqual(manager.get_all_brightness_levels(), [75])

        types = manager.get_active_device_types()
        self.assertIn("SmartLight", types)

        lights = list(manager.generate_lights())
        self.assertTrue(any(isinstance(d, SmartLight) for d in lights))

class TestMetaclass(unittest.TestCase):
    def test_metaclass_registry(self):
        self.assertIn("SmartLight", SmartDevice.__class__.registry)
        self.assertIn("SmartDoorLock", SmartDevice.__class__.registry)
# class TestExceptions(unittest.TestCase):
#     def test_custom_exceptions(self):
#         with self.assertRaises(DeviceOfflineError):
#             raise DeviceOfflineError("Device is off")
#         with self.assertRaises(InvalidParameterError):
#             raise InvalidParameterError("Bad param")
#         with self.assertRaises(PermissionDeniedError):
#             raise PermissionDeniedError("User","arm")


if __name__ == '__main__':
    unittest.main()
