import unittest
import asyncio
from core.exceptions import DeviceOfflineError, InvalidParameterError
from core.devicess import SmartLight

class TestSmartLight(unittest.IsolatedAsyncioTestCase):

    async def asyncSetUp(self):
        self.light = SmartLight("SD100")

    async def test_initial_state(self):
        self.assertFalse(self.light.is_on)
        self.assertEqual(self.light.brightness, 0)

    async def test_turn_on_and_off(self):
        await self.light.turn_on()
        self.assertTrue(self.light.is_on)
        await self.light.turn_off()
        self.assertFalse(self.light.is_on)

    async def test_perform_valid_set_brightness_action(self):
        await self.light.turn_on()
        await self.light.perform_action("set_brightness", 75)
        self.assertEqual(self.light.brightness, 75)

    async def test_perform_set_brightness_when_off_raises(self):
        with self.assertRaises(DeviceOfflineError):
            await self.light.perform_action("set_brightness", 50)

    async def test_set_brightness_invalid_value_raises(self):
        await self.light.turn_on()
        with self.assertRaises(InvalidParameterError):
            await self.light.perform_action("set_brightness", 150)

    async def test_perform_invalid_action_raises(self):
        await self.light.turn_on()
        with self.assertRaises(InvalidParameterError):
            await self.light.perform_action("invalid_action")

    def test_status_report_format(self):
        report = self.light.get_status_report()
        self.assertIn("Light", report)
        self.assertIn("SD100", report)

    async def test_brightness_property_setter_raises_when_off(self):
        with self.assertRaises(DeviceOfflineError):
            self.light.brightness = 50

    async def test_brightness_property_setter_accepts_valid(self):
        await self.light.turn_on()
        self.light.brightness = 80
        self.assertEqual(self.light.brightness, 80)

    async def test_brightness_property_setter_raises_on_invalid_value(self):
        await self.light.turn_on()
        with self.assertRaises(InvalidParameterError):
            self.light.brightness = 120

if __name__ == "__main__":
    unittest.main()
