import unittest
import asyncio
from core.devices.SmartLight import SmartLight
from core.devices.SmartThermostat import SmartThermostat
from core.devices.SmartSpeaker import SmartSpeaker
from core.devices.SmartDoorLock import SmartDoorLock
from core.devices.SmartCamera import SmartCamera
from core.exceptions import AuthenticationError, DeviceOfflineError


class TestBasicDevices(unittest.TestCase):

    def test_light(self):
        async def inner():
            light = SmartLight("light01")
            await light.turn_on()
            await light.perform_action("set_brightness", 80)
            self.assertTrue(light.is_on())
            self.assertEqual(light.brightness, 80)
        asyncio.run(inner())

    def test_thermostat(self):
        async def inner():
            thermo = SmartThermostat("thermo01")
            await thermo.turn_on()
            await thermo.perform_action("set_temperature", 21)
            self.assertEqual(thermo.temperature, 21)
        asyncio.run(inner())

    def test_speaker(self):
        async def inner():
            speaker = SmartSpeaker("speak01")
            await speaker.turn_on()
            await speaker.perform_action("set_volume", 5)
            self.assertEqual(speaker.volume, 5)
        asyncio.run(inner())

    def test_camera(self):
        async def inner():
            cam = SmartCamera("cam01")
            await cam.turn_on()
            await cam.perform_action("set_resolution", "1080p")
            self.assertEqual(cam.resolution, "1080p")
        asyncio.run(inner())

    def test_doorlock_correct_and_wrong_passcode(self):
        async def inner():
            door = SmartDoorLock("door01", "Secure@123")
            await door.turn_on()

            # Correct passcode
            await door.perform_action("change_lock", "Secure@123")
            self.assertTrue(door.lock)

            # Wrong passcode should raise
            with self.assertRaises(AuthenticationError):
                await door.perform_action("change_lock", "Wrong@123")
        asyncio.run(inner())

    def test_action_on_offline_device(self):
        async def inner():
            light = SmartLight("light02")
            with self.assertRaises(DeviceOfflineError):
                await light.perform_action("set_brightness", 50)
        asyncio.run(inner())


if __name__ == '__main__':
    unittest.main()
