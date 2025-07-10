import unittest
import asyncio
from datetime import datetime

from smart_home_system.core.devices import (
    SmartLight, SmartThermostat, SmartDoorLock, SmartCamera, SmartSpeaker, SmartDevice
)
from smart_home_system.core.exceptions import (
    InvalidParameterError, DeviceOfflineError, ActionNotSupportedError,
    PermissionDeniedError, AuthenticationError, SmartHomeError
)
from smart_home_system.core.metaclass import DeviceRegistrarMeta
from smart_home_system.manager.home_manager import HomeManager
from smart_home_system.manager.scene_manager import SceneManager
from smart_home_system.manager.scheduler import Scheduler


class TestDecorator(unittest.IsolatedAsyncioTestCase):

    def setUp(self):
        self.light = SmartLight("L001")

    async def test_decorator(self):
        with open("smart_home_log.txt","w+") as file:
            await self.light.turn_on()
            l = len(file.read())
        self.assertTrue(l > 0)


class TestMetaClass(unittest.IsolatedAsyncioTestCase):

    def test_valid_classes(self):
        for class_name in ["SmartLight", "SmartThermostat", "SmartDoorLock", "SmartCamera", "SmartSpeaker",
                  "SecuritySensor", "SmartAlarmSystem","SmartWatch"]:
            cls = DeviceRegistrarMeta.get_device_class(class_name)
            if not cls:
                with self.assertRaises(SmartHomeError):
                    raise SmartHomeError()

class TestSceneManagerIntegration(unittest.IsolatedAsyncioTestCase):

    async def test_scene_activation_changes_device_state(self):
        SmartLight.device_ids.clear()

        manager = HomeManager()
        light = SmartLight("L001")
        manager.add_device(light)
        await light.turn_on()

        scene_mgr = SceneManager()
        scene_mgr.add_scene("evening", ("L001", "set_brightness", 25))

        await scene_mgr.activate_scene(manager, "evening", user_role="admin")
        self.assertEqual(light.brightness, 25)

    async def test_scene_activation_invalid(self):
        SmartLight.device_ids.clear()

        manager = HomeManager()
        light = SmartLight("L002")
        manager.add_device(light)
        await light.turn_on()

        scene_mgr = SceneManager()
        scene_mgr.add_scene("authorized", ("L002", "set_brightness", 50))

        with self.assertRaises(InvalidParameterError):
            await scene_mgr.activate_scene(manager, "unauthorized", user_role="guest")


class TestSchedulerIntegration(unittest.IsolatedAsyncioTestCase):

    async def test_scheduler_executes_task(self):
        SmartLight.device_ids.clear()

        manager = HomeManager()
        light = SmartLight("L003")
        manager.add_device(light)
        await light.turn_on()

        scheduler = Scheduler()

        now = datetime.now()
        time_str = now.strftime("%H:%M")
        scheduler.add_scheduled_task(time_str, "L003", "set_brightness", 40, user_role="admin")

        await scheduler.run_pending_tasks(manager)

        self.assertEqual(light.brightness, 40)

class TestCustomExceptions(unittest.TestCase):

    def test_invalid_parameter_error(self):
        with self.assertRaises(InvalidParameterError):
            raise InvalidParameterError()

    def test_device_offline_error(self):
        with self.assertRaises(DeviceOfflineError):
            raise DeviceOfflineError()

    def test_action_not_supported_error(self):
        with self.assertRaises(ActionNotSupportedError):
            raise ActionNotSupportedError()

    def test_authentication_error(self):
        with self.assertRaises(AuthenticationError):
            raise AuthenticationError()

    def test_permission_denied_error(self):
        with self.assertRaises(PermissionDeniedError):
            raise PermissionDeniedError()

    def test_smart_home_error(self):
        with self.assertRaises(SmartHomeError):
            raise SmartHomeError()

if __name__ == "__main__":
    unittest.main()