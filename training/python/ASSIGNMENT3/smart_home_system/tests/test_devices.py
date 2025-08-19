import unittest
import asyncio
from ASSIGNMENT3.smart_home_system.core.devices import *
from ASSIGNMENT3.smart_home_system.core.exceptions import InvalidParameterError, DeviceOfflineError

class TestSmartDevices(unittest.IsolatedAsyncioTestCase):

    async def asyncSetUp(self):
        self.light = SmartLight("L001", 50,True)
        self.thermostat = SmartThermostat("T001",23.5)
        self.doorLock = SmartDoorLock("D301", "Prasad123#", True)
        self.camera = SmartCamera("C401", 6)
        self.speaker = SmartSpeaker("S501", 30)

    async def test_light_turn_on_off(self):
        await self.light.turn_on()
        self.assertTrue(self.light.get_status_report()["_is_on"])
        await self.light.turn_off()
        self.assertFalse(self.light.get_status_report()["_is_on"])
        await self.doorLock.turn_off()
        self.assertFalse(self.doorLock.get_status_report()["_is_on"])
        await self.camera.turn_off()
        self.assertFalse(self.camera.get_status_report()["_is_on"])
        await self.speaker.turn_off()
        self.assertFalse(self.speaker.get_status_report()["_is_on"])

    async def test_light_brightness_set_get(self):
        self.light.brightness = (80,"Prasad")
        self.light = SmartLight("L001", 50)
        self.thermostat = SmartThermostat("T001", 22.5)

    async def test_light_turn_on_off(self):
        await self.light.turn_on()
        self.assertTrue(self.light.get_status_report()["is_on"])
        await self.light.turn_off()
        self.assertFalse(self.light.get_status_report()["is_on"])

    async def test_light_brightness_set_get(self):
        self.light.brightness = 80
        self.assertEqual(self.light.brightness, 80)

    async def test_light_brightness_invalid(self):
        with self.assertRaises(InvalidParameterError):
            self.light.brightness = (150,"Prasad")

    async def test_thermostat_turn_on_off(self):
        await self.thermostat.turn_on()
        self.assertTrue(self.thermostat.get_status_report()["_is_on"])
        await self.thermostat.turn_off()
        self.assertFalse(self.thermostat.get_status_report()["_is_on"])

    async def test_thermostat_temperature_set_get(self):
        self.thermostat.temperature = (24.0,"prasad")
        self.light.brightness = 150

    async def test_thermostat_turn_on_off(self):
        await self.thermostat.turn_on()
        self.assertTrue(self.thermostat.get_status_report()["is_on"])
        await self.thermostat.turn_off()
        self.assertFalse(self.thermostat.get_status_report()["is_on"])

    async def test_thermostat_temperature_set_get(self):
        self.thermostat.temperature = 24.0
        self.assertEqual(self.thermostat.temperature, 24.0)

    async def test_thermostat_temperature_invalid(self):
        with self.assertRaises(InvalidParameterError):
            self.thermostat.temperature = (-10,"prasad")
            self.thermostat.temperature = -10

    async def test_device_offline_action(self):
        await self.light.turn_off()
        with self.assertRaises(DeviceOfflineError):
            await self.light.perform_action("set brightness", "prasad",30)
            await self.light.perform_action("set_brightness", 30)
