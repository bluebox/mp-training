import unittest
import asyncio
from core.devicess import SmartCamera
from core.exceptions import DeviceOfflineError, InvalidParameterError

class TestSmartCamera(unittest.IsolatedAsyncioTestCase):
    def setUp(self):
        self.camera = SmartCamera("SD1")

    def test_default_status(self):
        report = self.camera.get_status_report()
        self.assertIn("SD1", report)
        self.assertIn("1080p", report)
        self.assertIn("False", report)

    def test_start_recording_when_offline(self):
        with self.assertRaises(DeviceOfflineError):
            self.camera.start_recording()

    async def test_start_stop_recording_when_online(self):
        await self.camera.turn_on()
        self.camera.start_recording()
        self.assertIn("True", self.camera.get_status_report())
        self.camera.stop_recording()
        self.assertIn("False", self.camera.get_status_report())

    async def test_perform_action_start_stop_recording(self):
        await self.camera.turn_on()
        await self.camera.perform_action("start_recording")
        self.assertIn("True", self.camera.get_status_report())
        await self.camera.perform_action("stop_recording")
        self.assertIn("False", self.camera.get_status_report())

    async def test_set_valid_resolution(self):
        await self.camera.turn_on()
        await self.camera.perform_action("set_resolution", "4K")
        report = self.camera.get_status_report()
        self.assertIn("4K", report)

    async def test_set_invalid_resolution(self):
        await self.camera.turn_on()
        with self.assertRaises(InvalidParameterError):
            await self.camera.perform_action("set_resolution", "16K")

    async def test_invalid_action_type(self):
        await self.camera.turn_on()
        with self.assertRaises(InvalidParameterError):
            await self.camera.perform_action("zoom_in")

    async def test_perform_action_when_offline(self):
        with self.assertRaises(DeviceOfflineError):
            await self.camera.perform_action("start_recording")

if __name__ == "__main__":
    unittest.main()
