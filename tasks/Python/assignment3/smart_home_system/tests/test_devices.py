import unittest
import asyncio

from smart_home_system.core.devices import (
    SmartLight, SmartThermostat, SmartDoorLock, SmartCamera, SmartSpeaker, SmartDevice
)
from smart_home_system.core.exceptions import (
    InvalidParameterError, DeviceOfflineError, ActionNotSupportedError,
    PermissionDeniedError, AuthenticationError, SmartHomeError
)
from smart_home_system.core.metaclass import DeviceRegistrarMeta
from smart_home_system.core.security import SmartAlarmSystem, SecuritySensor
from smart_home_system.manager.home_manager import HomeManager
from smart_home_system.manager.scene_manager import SceneManager

class TestSmartLight(unittest.IsolatedAsyncioTestCase):
    def setUp(self):
        self.light = SmartLight("L001")

    async def test_turn_on_off(self):
        await self.light.turn_on()
        self.assertTrue(self.light.is_on)
        await self.light.turn_off()
        self.assertFalse(self.light.is_on)

    async def test_brightness_setting(self):
        await self.light.turn_on()
        self.light.brightness = 80
        self.assertEqual(self.light.brightness, 80)

    async def test_invalid_brightness(self):
        await self.light.turn_on()  # Turn on first
        with self.assertRaises(InvalidParameterError):
            self.light.brightness = 150

    async def test_unsupported_action(self):
        with self.assertRaises(ActionNotSupportedError):
            await self.light.perform_action("play_mode")


class TestSmartThermostat(unittest.IsolatedAsyncioTestCase):
    def setUp(self):
        self.thermo = SmartThermostat("T001")

    async def test_turn_on_set_temp(self):
        await self.thermo.turn_on()
        self.thermo.temperature = 25
        self.assertEqual(self.thermo.temperature, 25)

    async def test_invalid_temp(self):
        await self.thermo.turn_on()
        with self.assertRaises(InvalidParameterError):
            self.thermo.temperature = 10

    async def test_action_set_temperature(self):
        await self.thermo.turn_on()
        await self.thermo.perform_action("set_temperature", 26)
        self.assertEqual(self.thermo.temperature, 26)


class TestSmartDoorLock(unittest.IsolatedAsyncioTestCase):
    def setUp(self):
        self.door = SmartDoorLock("D001")


    async def test_unlock_wrong_pass(self):
        await self.door.turn_on()
        with self.assertRaises(PermissionDeniedError):
            self.door.unlock("runG123")

    async def test_unlock_right_pass(self):
        await self.door.turn_on()
        self.door.unlock("Admin@123")
        self.assertFalse(self.door.is_lock)

    async def test_change_passcode_success(self):
        await self.door.turn_on()
        self.door.change_passcode("Admin@123", "NewPass@123")
        self.door.unlock("NewPass@123")
        self.assertFalse(self.door.is_lock)

    def test_change_passcode_failure(self):
        with self.assertRaises(AuthenticationError):
            self.door.change_passcode("Wrong", "Another@123")

        with self.assertRaises(InvalidParameterError):
            self.door.change_passcode("Admin@123", "weakpassword")

    async def test_action_unlock(self):
        await self.door.turn_on()
        await self.door.perform_action("unlock", "Admin@123")
        self.assertFalse(self.door.is_lock)

class TestSmartCamera(unittest.IsolatedAsyncioTestCase):
    def setUp(self):
        self.cam = SmartCamera("C001")

    async def test_turn_on_record(self):
        with self.assertRaises(DeviceOfflineError):
            self.cam.set_recording(True)

    async def test_invalid_resolution(self):
        await self.cam.turn_on()
        with self.assertRaises(InvalidParameterError):
            self.cam.set_resolution("240p")

    async def test_action_resolution(self):
        await self.cam.turn_on()
        await self.cam.perform_action("set_resolution", "1080p")
        self.assertEqual(self.cam.resolution,"1080p")


class TestSmartSpeaker(unittest.IsolatedAsyncioTestCase):
    def setUp(self):
        self.speaker = SmartSpeaker("S001")

    async def test_volume_set(self):
        await self.speaker.turn_on()
        self.speaker.set_volume(70)
        self.assertEqual(self.speaker.volume, 70)

    async def test_volume_offline(self):
        with self.assertRaises(DeviceOfflineError):
            self.speaker.set_volume(70)

    async def test_invalid_volume(self):
        await self.speaker.turn_on()
        with self.assertRaises(InvalidParameterError):
            self.speaker.set_volume(120)

    async def test_play_track(self):
        await self.speaker.perform_action("play_track", "MySong")
        self.assertEqual(self.speaker.get_track(), "MySong")


class TestSmartDeviceAbstract(unittest.TestCase):
    def test_device_id_validation(self):
        with self.assertRaises(InvalidParameterError):
            SmartLight("X123")

    def test_device_count_increment(self):
        start = SmartDevice.get_device_count()
        SmartLight("L002")
        self.assertEqual(SmartDevice.get_device_count(), start + 1)

    def test_get_system_time(self):
        now = SmartDevice.get_system_time()
        self.assertIsNotNone(now)


class TestSecuritySensor(unittest.IsolatedAsyncioTestCase):

    def setUp(self):
        self.sensor = SecuritySensor("S001")

    async def test_turn_on_and_off(self):
        await self.sensor.turn_on()
        self.assertTrue(self.sensor.is_on)

        await self.sensor.turn_off()
        self.assertFalse(self.sensor.is_on)

    async def test_arm_sensor_success(self):
        await self.sensor.turn_on()
        self.sensor.arm_sensor(True)
        self.assertTrue(self.sensor.is_armed)

    async def test_arm_sensor_invalid_type(self):
        await self.sensor.turn_on()
        with self.assertRaises(InvalidParameterError):
            self.sensor.arm_sensor("yes")

    async def test_arm_sensor_offline(self):
        with self.assertRaises(DeviceOfflineError):
            self.sensor.arm_sensor(True)

    async def test_perform_action_valid(self):
        await self.sensor.perform_action("turn_on")
        await self.sensor.perform_action("arm")
        self.assertTrue(self.sensor.is_armed)

    async def test_perform_action_invalid(self):
        await self.sensor.turn_on()
        with self.assertRaises(ActionNotSupportedError):
            await self.sensor.perform_action("dance")


class TestSmartAlarmSystem(unittest.IsolatedAsyncioTestCase):

    def setUp(self):
        self.alarm = SmartAlarmSystem("A001")

    async def test_turn_on_and_off(self):
        await self.alarm.turn_on()
        self.assertTrue(self.alarm.is_on)

        await self.alarm.turn_off()
        self.assertFalse(self.alarm.is_on)

    async def test_arm_disarm_property(self):
        self.alarm.armed = True
        self.assertTrue(self.alarm.armed)

        self.alarm.armed = False
        self.assertFalse(self.alarm.armed)

    def test_invalid_armed_type(self):
        with self.assertRaises(InvalidParameterError):
            self.alarm.armed = "yes"

    async def test_set_volume_success(self):
        await self.alarm.turn_on()
        await self.alarm.perform_action("set_volume", 8)

    async def test_set_volume_invalid(self):
        await self.alarm.turn_on()
        with self.assertRaises(InvalidParameterError):
            await self.alarm.perform_action("set_volume", 100)

    async def test_set_volume_offline(self):
        with self.assertRaises(DeviceOfflineError):
            await self.alarm.perform_action("set_volume", 5)


    def test_schedule_task_invalid_format(self):
        with self.assertRaises(InvalidParameterError):
            self.alarm.schedule_task("9 PM")

    async def test_perform_action_invalid(self):
        await self.alarm.turn_on()
        with self.assertRaises(ActionNotSupportedError):
            await self.alarm.perform_action("explode")

if __name__ == "__main__":
    unittest.main()

