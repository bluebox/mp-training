import unittest
import asyncio
from core.devices import SmartLight
from core.devices import SmartThermoStat
from manager.home_manager import HomeManager
from core.exceptions import AuthenticationError


class TestHomeManager(unittest.TestCase):

    def test_add_and_control_device(self):
        async def inner():
            manager = HomeManager("admin")
            light = SmartLight("SL37456")
            await light.turn_on()
            manager.add_device(light)
            await manager.control_device("admin", "SL37456", "set_brightness", 70)
            self.assertEqual(light.brightness, 70)
        asyncio.run(inner())

    def test_permission_denied(self):
        async def inner():
            manager = HomeManager("admin")
            thermo = SmartThermoStat("STS45678")
            await thermo.turn_on()
            manager.add_device(thermo)
            await manager.control_device("user", "STS45678", "set_temperature", 25)
            print(thermo.temperature)
            self.assertNotEqual(thermo.temperature, 25)
        asyncio.run(inner())

    def test_duplicate_device_addition(self):
        manager = HomeManager("admin")
        light = SmartLight("SL374678")
        manager.add_device(light)
        manager.add_device(light)
        self.assertEqual(len(manager.get_devices()), 1)

    def test_save_and_load_config(self):
        async def inner():
            manager = HomeManager("admin")
            light = SmartLight("SL374645")
            await light.turn_on()
            manager.add_device(light)
            await manager.save_config()

            new_manager = HomeManager("admin")
            await new_manager.load_config()
            devices = new_manager.get_devices()
            self.assertIn("SL374645", devices)
            self.assertTrue(devices["SL374645"]._is_on)
        asyncio.run(inner())


if __name__ == '__main__':
    unittest.main()