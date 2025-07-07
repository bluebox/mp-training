import unittest
import asyncio
from datetime import datetime, timedelta

from core.devices.SmartLight import SmartLight
from core.devices.SmartDoorLock import SmartDoorLock
from core.devices.SmartThermostat import SmartThermostat
from manager.HomeManager import HomeManager
from manager.SceneManager import SceneManager
from manager.Scheduler import Scheduler
from manager.HomeManager import all_id_of_online, avg_temperature_thermostat, get_properties


class TestIntegration(unittest.TestCase):

    def test_scene_activation_and_permission(self):
        async def inner():
            manager = HomeManager("admin")
            light = SmartLight("light01")
            lock = SmartDoorLock("door01", "Valid@123")  # ✅ Fixed valid passcode

            await light.turn_on()
            await lock.turn_on()

            manager.add_device(light)
            manager.add_device(lock)

            scene_mgr = SceneManager()
            scene_mgr.add_scene("lock_down", [
                ("light01", "set_brightness", 30),
                ("door01", "change_lock", "Valid@123")
            ])

            await scene_mgr.activate_scene(manager, "lock_down", "admin")
            self.assertEqual(light.brightness, 30)
            self.assertTrue(lock.lock)


            await scene_mgr.activate_scene(manager, "lock_down", "guest")
        asyncio.run(inner())

    def test_scheduler_execution(self):
        async def inner():
            manager = HomeManager("admin")
            thermo = SmartThermostat("thermo01")
            await thermo.turn_on()
            manager.add_device(thermo)

            scheduler = Scheduler()
            time_str = (datetime.now() + timedelta(seconds=3)).strftime("%H:%M")
            scheduler.add_scheduled_task(time_str, "thermo01", "set_temperature", 19, "admin")

            while scheduler.tasks:
                await scheduler.run_pending_tasks(manager)
                await asyncio.sleep(1)

            self.assertEqual(thermo.temperature, 19)
        asyncio.run(inner())

    def test_functional_utils(self):
        async def inner():
            manager = HomeManager("admin")
            light = SmartLight("light01")
            thermo1 = SmartThermostat("thermo01")
            thermo2 = SmartThermostat("thermo02")

            await light.turn_on()
            await thermo1.turn_on()
            await thermo2.turn_on()

            await thermo1.perform_action("set_temperature", 22)
            await thermo2.perform_action("set_temperature", 24)

            manager.add_device(light)
            manager.add_device(thermo1)
            manager.add_device(thermo2)

            online_ids = all_id_of_online(manager)
            self.assertIn("light01", online_ids)
            self.assertIn("thermo01", online_ids)

            avg_temp = avg_temperature_thermostat(manager)
            self.assertEqual(avg_temp, 23.0)

            props = get_properties(manager)
            print(props)
            self.assertIn('set_temperature',props.values())
        asyncio.run(inner())


if __name__ == '__main__':
    unittest.main()