import unittest
from core.devicess import SmartSpeaker
from core.exceptions import InvalidParameterError, DeviceOfflineError
import asyncio

class TestSmartSpeaker(unittest.IsolatedAsyncioTestCase):

    def setUp(self):
        self.speaker = SmartSpeaker("SD1")

    def test_initial_state(self):
        self.assertEqual(self.speaker.volume, 50)
        self.assertIn("None", self.speaker.get_status_report())

    async def test_set_volume_offline_raises(self):
        await self.speaker.turn_off()
        with self.assertRaises(DeviceOfflineError):
            self.speaker.volume = 30

    async def test_set_invalid_volume_range(self):
        await self.speaker.turn_on()
        with self.assertRaises(InvalidParameterError):
            self.speaker.volume = 150

    async def test_set_volume_successfully(self):
        await self.speaker.turn_on()
        self.speaker.volume = 75
        self.assertEqual(self.speaker.volume, 75)

    async def test_play_track_when_offline(self):
        await self.speaker.turn_off()
        with self.assertRaises(DeviceOfflineError):
            self.speaker.play_track("Song A")

    async def test_play_track_success(self):
        await self.speaker.turn_on()
        self.speaker.play_track("Doom")
        self.assertIn("Doom", self.speaker.get_status_report())

    async def test_perform_set_volume_success(self):
        await self.speaker.turn_on()
        await self.speaker.perform_action("set_volume", 45)
        self.assertEqual(self.speaker.volume, 45)

    async def test_perform_set_volume_invalid_type(self):
        await self.speaker.turn_on()
        with self.assertRaises(InvalidParameterError):
            await self.speaker.perform_action("set_volume", "loud")

    async def test_perform_play_track_success(self):
        await self.speaker.turn_on()
        await self.speaker.perform_action("play_track", "track123")
        self.assertIn("track123", self.speaker.get_status_report())

    async def test_perform_play_track_invalid_type(self):
        await self.speaker.turn_on()
        with self.assertRaises(InvalidParameterError):
            await self.speaker.perform_action("play_track", 12345)

    async def test_perform_invalid_action(self):
        await self.speaker.turn_on()
        with self.assertRaises(InvalidParameterError):
            await self.speaker.perform_action("dance")

    async def test_perform_action_when_offline(self):
        await self.speaker.turn_off()
        with self.assertRaises(DeviceOfflineError):
            await self.speaker.perform_action("play_track", "Song A")

    def test_supported_actions(self):
        expected = ["set_volume", "play_track"]
        self.assertListEqual(self.speaker.get_supported_actions(), expected)

if __name__=="__main__":
    unittest.main()