import asyncio
from manager.home_manager import HomeManager
from manager.scene_manager import SceneManager
from manager.scheduler import Scheduler
from core.devices import SmartLight, SmartThermostat, SmartCamera, SmartSpeaker, SmartDoor
from core.security import SmartAlarmSystem
from core.exceptions import *

async def main():
    print("Initializing Smart Home System...\n")
    manager = HomeManager()

    # Adding devices
    light = SmartLight("L001")
    thermostat = SmartThermostat("T001")
    camera = SmartCamera("C001")
    speaker = SmartSpeaker("S001")
    door = SmartDoor("D001", passcode="Admin@123")
    alarm = SmartAlarmSystem("A001")

    for device in [light, thermostat, camera, speaker, door, alarm]:
        manager.add_device(device)

    # Turning on devices and setting parameters
    await light.turn_on()
    await thermostat.turn_on()
    await manager.control_device("admin", "L001", "set_brightness", 75)
    await manager.control_device("admin", "T001", "set_temperature", 22.5)

    await manager.control_device("admin", "C001", "start_recording")
    await manager.control_device("admin", "S001", "play")
    await manager.control_device("admin", "S001", "set_volume", 60)
    await manager.control_device("admin", "D001", "lock", "Admin@123")

    # Test incorrect password
    try:
        await manager.control_device("admin", "D001", "unlock", "wrongpass")
    except PermissionDeniedError as e:
        print(f"Permission check: {e}")

    # Test SmartAlarmSystem
    await manager.control_device("admin", "A001", "arm")
    await manager.control_device("admin", "A001", "disarm")

    # Create and activate a scene
    scene = SceneManager()
    scene.add_scene("night", [
        ("L001", "set_brightness", 10),
        ("C001", "stop_recording", None),
        ("S001", "stop", None),
    ])
    await scene.activate_scene(manager, "night", user_role="admin")


    try:
        await scene.activate_scene(manager, "night", user_role="user")
    except PermissionDeniedError as e:
        print(f"Role access denied: {e}")

    # Scheduling tasks
    scheduler = Scheduler()
    scheduler.add_scheduled_task("23:59", "L001", "set_brightness", value=40)
    scheduler.add_scheduled_task("23:59", "night", None, is_scene=True)
    await asyncio.sleep(1)  # short wait
    asyncio.create_task(scheduler.run_pending_tasks(manager))

    # Save and Load state
    await manager.save_config("smart_home_config.json")
    await manager.load_config("smart_home_config.json")

    print("\nAll Device Statuses:")
    manager.get_all_device_statuses()

    print("\nFunctional Programming Examples:")
    all_ids = [device.device_id for device in manager._devices]
    print("Device IDs:", all_ids)

    supported = {d.device_id: list(d.get_supported_actions()) for d in manager._devices}
    print("Supported Actions:", supported)

    print(f"\nCheck manager/logs.txt to verify decorator logs.")

if __name__ == "__main__":
    asyncio.run(main())
