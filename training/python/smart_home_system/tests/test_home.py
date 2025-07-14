import unittest

from core.exceptions import PermissionDeniedError
from core.devicess import SmartDevice, SmartLight
from manager.home_manager import HomeManager


class TestHomeManager(unittest.IsolatedAsyncioTestCase):
    def setUp(self):
        SmartDevice.devices.clear()
    async def test_add_and_control_device(self):
        manager = HomeManager()
        light = SmartLight("SD1")
        manager.add_device(light)
        await light.turn_on()
        await manager.control_device("Admin", "SD1", "set_brightness", 80)
        self.assertEqual(light.brightness, 80)

    async def test_permission_denied(self):
        manager = HomeManager()
        light = SmartLight("SD1")
        manager.add_device(light)
        await light.turn_on()
        with self.assertRaises(PermissionDeniedError):
            await manager.control_device("Guest", "SD1", "set_brightness", 55)

    async def test_save_and_load_config(self):
        manager = HomeManager()
        light = SmartLight("SD1")
        await light.turn_on()
        manager.add_device(light)
        await manager.save_config("test_config.json")
        manager2 = HomeManager()
        await manager2.load_config("test_config.json")
        statuses = [d.get_status_report() for d in manager2._register]
        self.assertTrue(any("SD1" in s for s in statuses))
if __name__=="__main__":
    unittest.main()