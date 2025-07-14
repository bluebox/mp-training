from datetime import datetime, timedelta
from core.devicess import SmartLight, SmartDoorLock, SmartDevice, SmartSpeaker,SmartCamera, SmartThermostat
from core.devicess import DeviceRegistrarMeta
from manager.home_manager import HomeManager
from manager.scene_manager import SceneManager
from manager.scheduler import Scheduler
from core.security import SecuritySensor,SmartAlarmSystem
import asyncio

async def main():
    print(DeviceRegistrarMeta.registry)
    print("\n--- Creating Devices ---")
    light = SmartLight("SD1")
    speaker = SmartSpeaker(123)
    print(SmartDevice.devices)
    camera = SmartCamera("SD3")
    thermostat = SmartThermostat("SD4")
    door = SmartDoorLock("SD5", "Abc")
    print(SmartDevice.devices)

    await light.turn_on()

    await speaker.turn_on()
    await camera.turn_on()
    await thermostat.turn_on()
    await door.turn_on()
    print(light.is_on)

    # # Perform actions
    await light.perform_action("set_brightness", 795)
    await speaker.perform_action("set_volume", 60)
    await speaker.perform_action("set_volume", 60)
    await camera.perform_action("set_resolution", "4K")
    await thermostat.perform_action("set_temperature", 232)
    await thermostat.perform_action("set_temperature", 22)

    print("\n---Adding to HomeManager ---")
    manager = HomeManager()
    manager.add_device(light)
    manager.add_device(light)
    # manager.add_device(light)
    # manager.add_device(speaker)
    print(HomeManager.all_ids)
    manager2= HomeManager()
    manager2.add_device(light)
    print(HomeManager.all_ids)

    manager2.add_device(thermostat)
    manager2.add_device(door)

    print(HomeManager.manager_registry)
    print("\n---Saving Config ---")
    await manager.save_config("config.json")

    print("\n---Clearing In-Memory Devices ---")
    del manager
    manager = HomeManager()

    print("\n---Loading Config ---")
    await manager.load_config("config.json")
    print("\n---Functional Programming Results ---")
    print("Online Device IDs:", manager.get_online_device_ids())
    print("Light Brightness Levels:", manager.get_all_brightness_levels())

    print("\n---Comprehension Results---")
    print("Active Device Types:", manager.get_active_device_types())
    print("ID to Status Map:", manager.get_device_id_to_status_map())
    print("Supported Actions Map:", manager.get_device_supported_actions_map())

    print("\n---Generator Results---")
    print("SmartLights:")
    for light in manager.generate_lights():
        print(" -", light.get_status_report())

    print("Online Devices:")
    for dev in manager.generate_online_devices():
        print(dev._device_id)

    print("Devices supporting 'set_brightness':")
    for dev in manager.generate_devices_supporting_action("set_brightness"):
        print(" -", dev._device_id)

    print("\n---Device Statuses After Load---")
    manager.get_all_device_statuses()

    print("scene manager testing")
    scene_manager = SceneManager()

    # create scene
    scene_manager.add_scene("Good Morning", [
        ("SD1", "set_brightness", 80),
        ("SD2", "play_track", "Wake Up"),
        ("SD4", "set_temperature", 24),
        ("SD5", "unlock", None)
    ])
    #
    # Activate the scene with different roles
    await scene_manager.activate_scene(manager, "Good Morning", "Admin")
    await scene_manager.activate_scene(manager, "Good Morning", "User")
    await scene_manager.activate_scene(manager, "Good Morning", "Guest")

    # scheduler
    scheduler = Scheduler()

    now = datetime.now()
    scheduled_time = (now.replace(second=0, microsecond=0) + timedelta(minutes=1)).strftime("%H:%M")

    scheduler.add_scheduled_task("18:56", "SD1", "set_brightness", 50, "Admin")

    for _ in range(4):
        await scheduler.run_pending_tasks(manager)
        await asyncio.sleep(10)
    # alarm testing
    print()
    sensor = SecuritySensor("SD6")
    alarm = SmartAlarmSystem("SD7", sensor=sensor)
    await alarm.turn_on()

    manager.add_device(sensor)
    manager.add_device(alarm)

    await manager.control_device("Admin", "SD7", "arm")

    await alarm.schedule_task(scheduler)

    for _ in range(4):
        await scheduler.run_pending_tasks(manager)
        await asyncio.sleep(10)

asyncio.run(main())





