import unittest
from SmartHomeAutomationSystem.core.devices import SmartLight
from SmartHomeAutomationSystem.core.exceptions import InvalidParameterError

class TestSmartLight(unittest.IsolatedAsyncioTestCase):
    async def asyncSetUp(self):
        self.light = SmartLight("L002", brightness=75)

    async def test_turn_on_off(self):
        await self.light.turn_on()
        self.assertTrue(self.light.is_on)
        await self.light.turn_off()
        self.assertFalse(self.light.is_on)

    async def test_brightness(self):
        self.light.brightness = 50
        self.assertEqual(self.light.brightness, 50)
        with self.assertRaises(InvalidParameterError):
            self.light.brightness = 120

    def test_device_id_validation(self):
        with self.assertRaises(InvalidParameterError):
            SmartLight("INVALID", brightness=60)

if __name__ == "__main__":
    unittest.main()
