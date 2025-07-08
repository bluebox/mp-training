import unittest
import asyncio
from core.devices import SmartLight
from core.devices import SmartThermoStat
from core.devices import SmartSpeaker
from core.devices import SmartDoorLock
# from core.devices import SmartCamera
from core.exceptions import AuthenticationError, DeviceOfflineError, WrongPasscodeError


class TestBasicDevices(unittest.TestCase):

    def test_light(self):
        async def inner():
            light = SmartLight("SL27273")
            await light.turn_on()
            await light.perform_action("set_brightness", 80)
            self.assertTrue(light._is_on)
            self.assertEqual(light.brightness, 80)
        asyncio.run(inner())

    def test_thermostat(self):
        async def inner():
            thermo = SmartThermoStat("STS3746")
            await thermo.turn_on()
            await thermo.perform_action("set_temperature", 21)
            self.assertEqual(thermo.temperature, 21)
        asyncio.run(inner())

    def test_speaker(self):
        async def inner():
            speaker = SmartSpeaker("SS37489")
            await speaker.turn_on()
            await speaker.perform_action("set_volume", 5)
            self.assertEqual(speaker.volume, 5)
        asyncio.run(inner())

    # def test_camera(self):
    #     async def inner():
    #         cam = SmartCamera("cam01")
    #         await cam.turn_on()
    #         await cam.perform_action("set_resolution", "1080p")
    #         self.assertEqual(cam.resolution, "1080p")
    #     asyncio.run(inner())

    def test_doorlock_correct_and_wrong_passcode(self):
        async def inner():
            door = SmartDoorLock("SDL2434")
            await door.turn_on("Admin")

            # Correct passcode
            await door.perform_action("lock",passcode="Admin")
            self.assertTrue(await door.perform_action('unlock',passcode='Admin'))

            # Wrong passcode should raise
            with self.assertRaises(WrongPasscodeError):
                await door.perform_action("unlock", passcode='admin')
        asyncio.run(inner())

    def test_action_on_offline_device(self):
        async def inner():
            light = SmartLight("SL34543")
            with self.assertRaises(DeviceOfflineError):
                await light.perform_action("set_brightness", 50)
        asyncio.run(inner())


if __name__ == '__main__':
    unittest.main()