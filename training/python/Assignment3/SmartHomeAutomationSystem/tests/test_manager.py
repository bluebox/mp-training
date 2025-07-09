import unittest
from unittest.mock import patch, mock_open, AsyncMock
import asyncio
import json

from SmartHomeAutomationSystem.manager.home_manager import HomeManager
from SmartHomeAutomationSystem.core.exceptions import (
    PermissionDeniedError, InvalidParameterError
)
from SmartHomeAutomationSystem.core.devices import SmartLight, SmartThermostat


class TestHomeManager(unittest.IsolatedAsyncioTestCase):
    def setUp(self):
        self.manager = HomeManager()
        self.device = SmartLight("L005", brightness=50)
        self.manager.add_device(self.device)

    def test_add_device_duplicate(self):
        with self.assertRaises(InvalidParameterError):
            self.manager.add_device(self.device)

    async def test_control_device_turn_on_allowed(self):
        result = await self.manager.control_device("admin", "L005", "turn_on")
        self.assertTrue(self.device.is_on)
        self.assertIn("Action 'turn_on'", result)

    async def test_control_device_permission_denied(self):
        result = await self.manager.control_device("guest", "L005", "set_brightness", 80)
        self.assertIn("cannot perform set_brightness", result)

    async def test_control_device_invalid_device(self):
        result = await self.manager.control_device("admin", "invalid_id", "turn_on")
        self.assertEqual(result, "Device invalid_id not found.")

    def test_get_all_device_statuses(self):
        status = self.manager.get_all_device_statuses()
        self.assertIn("L005", status)
        self.assertIsInstance(status["L005"], dict)

    async def test_get_online_device_ids(self):
        await self.device.turn_on()
        self.assertIn("L005", self.manager.get_online_device_ids())

    def test_get_average_thermostat_temp(self):
        self.assertEqual(self.manager.get_average_thermostat_temp(), 0)
        thermo = SmartThermostat("T006", temperature=28)
        self.manager.add_device(thermo)
        self.assertEqual(self.manager.get_average_thermostat_temp(), 28)

    def test_get_brightness_list(self):
        self.assertIn(self.device.brightness, self.manager.get_brightness_list())

    async def test_get_unique_active_types(self):
        await self.device.turn_on()
        self.assertIn("SmartLight", self.manager.get_unique_active_types())

    def test_get_device_id_to_status(self):
        status_map = self.manager.get_device_id_to_status()
        self.assertIn("L005", status_map)

    def test_get_device_id_to_supported_actions(self):
        actions = self.manager.get_device_id_to_supported_actions()
        self.assertIn("turn_on", actions["L005"])

    def test_iter_lights_empty(self):
        manager = HomeManager()
        self.assertEqual(len(list(manager.iter_lights())), 0)

    async def test_iter_active_devices(self):
        await self.device.turn_on()
        devices = list(self.manager.iter_active_devices())
        self.assertEqual(len(devices), 1)
        self.assertEqual(devices[0].device_id, "L005")

    def test_iter_devices_by_condition(self):
        result = list(self.manager.iter_devices_by_condition(lambda d: d.brightness == 50))
        self.assertEqual(len(result), 1)