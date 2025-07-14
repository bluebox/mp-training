import unittest
import asyncio
from core.devicess import SmartThermostat
from core.exceptions import DeviceOfflineError, InvalidParameterError


class TestSmartThermostat(unittest.IsolatedAsyncioTestCase):

    def setUp(self):
        self.device = SmartThermostat("SD11")

    def test_initial_temperature(self):
        self.assertEqual(self.device.temperature, 22)

    async def test_turn_on_and_set_valid_temperature(self):
        await self.device.turn_on()
        self.device.temperature = 25
        self.assertEqual(self.device.temperature, 25)

    async def test_turn_on_and_set_invalid_temperature(self):
        await self.device.turn_on()
        with self.assertRaises(InvalidParameterError):
            self.device.temperature = 40

        with self.assertRaises(InvalidParameterError):
            self.device.temperature = 10

    def test_set_temperature_when_offline(self):
        with self.assertRaises(DeviceOfflineError):
            self.device.temperature = 24

    async def test_perform_valid_action(self):
        await self.device.turn_on()
        await self.device.perform_action("set_temperature", 26)
        self.assertEqual(self.device.temperature, 26)

    async def test_perform_invalid_action(self):
        await self.device.turn_on()
        with self.assertRaises(InvalidParameterError):
            await self.device.perform_action("invalid_action")

    async def test_perform_action_invalid_type(self):
        await self.device.turn_on()
        with self.assertRaises(InvalidParameterError):
            await self.device.perform_action("set_temperature", "not_an_int")

    def test_status_report_format(self):
        report = self.device.get_status_report()
        self.assertIn("[Thermostat]", report)


if __name__ == "__main__":
    unittest.main()
