import unittest
import asyncio

from core.devices import (
    SmartLight,
    SmartThermostat,
    SmartCamera,
    SmartSpeaker,
    SmartDoor,
    SmartDevice,
)
from core.exceptions import (
    InvalidParameterError,
    DuplicateDeviceError,
    PermissionDeniedError,
    UnsupportedActionError,
)
from manager.home_manager import HomeManager
from manager.scene_manager import SceneManager
from manager.scheduler import Scheduler
from core.meta_class import DeviceRegisterMeta   # adjust path if needed


# ── helper ────────────────────────────────────────────────────────────
def _reset_globals():
    SmartDevice._devices.clear()
    SmartDevice._device_count = 0


# ── Device tests ──────────────────────────────────────────────────────
class TestSmartLight(unittest.IsolatedAsyncioTestCase):
    async def asyncSetUp(self):
        _reset_globals()
        self.light = SmartLight("L001")
        await self.light.turn_on()

    async def test_set_valid_brightness(self):
        await self.light.perform_action("set_brightness", 60)
        self.assertEqual(self.light.brightness, 60)

    async def test_invalid_brightness_raises(self):
        with self.assertRaises(InvalidParameterError):
            await self.light.perform_action("set_brightness", 150)


class TestSmartSpeaker(unittest.IsolatedAsyncioTestCase):
    async def asyncSetUp(self):
        _reset_globals()
        self.speaker = SmartSpeaker("S001")
        await self.speaker.turn_on()

    async def test_play_and_volume(self):
        await self.speaker.perform_action("play")
        self.assertTrue(self.speaker.playing)
        await self.speaker.perform_action("set_volume", 45)
        self.assertEqual(self.speaker.volume, 45)

    async def test_unsupported_action(self):
        with self.assertRaises(UnsupportedActionError):
            await self.speaker.perform_action("invalid_action")


class TestSmartDoor(unittest.IsolatedAsyncioTestCase):
    async def asyncSetUp(self):
        _reset_globals()
        self.door = SmartDoor("D001", passcode="Admin@123")
        await self.door.turn_on()

    async def test_lock_unlock(self):
        await self.door.perform_action("lock", "Admin@123")
        self.assertTrue(self.door.lock)
        await self.door.perform_action("unlock", "Admin@123")
        self.assertFalse(self.door.lock)

    async def test_wrong_passcode(self):
        with self.assertRaises(PermissionDeniedError):
            await self.door.perform_action("lock", "wrong")


# ── HomeManager tests ────────────────────────────────────────────────
class TestHomeManager(unittest.IsolatedAsyncioTestCase):
    async def asyncSetUp(self):
        _reset_globals()
        self.manager = HomeManager()
        self.light = SmartLight("L010")
        self.manager.add_device(self.light)

    def test_duplicate_device(self):
        with self.assertRaises(DuplicateDeviceError):
            self.manager.add_device(SmartLight("L010"))

    async def test_permission_denied(self):
        with self.assertRaises(PermissionDeniedError):
            await self.manager.control_device("user", "L010", "set_temperature", 20)

    async def test_control_success(self):
        await self.manager.control_device("admin", "L010", "set_brightness", 40)
        self.assertEqual(self.light.brightness, 40)


# ── SceneManager tests ────────────────────────────────────────────────
class TestSceneManager(unittest.IsolatedAsyncioTestCase):
    async def asyncSetUp(self):
        _reset_globals()
        self.manager = HomeManager()
        self.light = SmartLight("L020")
        self.manager.add_device(self.light)
        self.scene = SceneManager()
        self.scene.add_scene("evening", [("L020", "set_brightness", 25)])

    async def test_scene_admin(self):
        await self.scene.activate_scene(self.manager, "evening", user_role="admin")
        self.assertEqual(self.light.brightness, 25)

    async def test_scene_permission_denied(self):
        with self.assertRaises(PermissionDeniedError):
            await self.scene.activate_scene(self.manager, "evening", user_role="user")


# ── Scheduler tests ──────────────────────────────────────────────────
# class TestScheduler(unittest.IsolatedAsyncioTestCase):
#     async def asyncSetUp(self):
#         _reset_globals()
#
#     async def test_scheduler_runs(self):
#         manager = HomeManager()
#         light = SmartLight("L030")
#         manager.add_device(light)
#
#         scheduler = Scheduler()
#         now = SmartDevice.get_system_time()
#         scheduler.add_scheduled_task(now.strftime("%H:%M"), "L030", "set_brightness", value=33)
#
#         await asyncio.sleep(1)
#         await scheduler.run_pending_tasks(manager)
#         self.assertEqual(light.brightness, 33)
#
#
# ── Functional & metaclass sanity checks ─────────────────────────────
class TestFunctionalProgramming(unittest.TestCase):
    def setUp(self):
        _reset_globals()

    def test_comprehension_and_generator(self):
        devices = [SmartLight(f"L{i:03}") for i in range(3)]
        ids = [d.device_id for d in devices]
        self.assertEqual(ids, ["L000", "L001", "L002"])
        self.assertIn("set_brightness", list(devices[0].get_supported_actions()))


class TestMetaclassRegistration(unittest.TestCase):
    def test_registered(self):
        self.assertIn("SmartLight", DeviceRegisterMeta.device_register)
        self.assertIn("SmartDoor", DeviceRegisterMeta.device_register)


if __name__ == "__main__":
    unittest.main()
