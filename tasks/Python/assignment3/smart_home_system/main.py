import asyncio

from smart_home_system.core.devices import (
    SmartLight, SmartThermostat, SmartDoorLock,
    SmartCamera, SmartSpeaker, SmartDevice
)
from smart_home_system.core.security import SmartAlarmSystem
from smart_home_system.manager.home_manager import HomeManager
from smart_home_system.manager.scene_manager import SceneManager
from smart_home_system.core.exceptions import SmartHomeError, PermissionDeniedError, InvalidParameterError

async def main():
    print(" Welcome to the Smart Home System Demo!\n")

    manager_ = HomeManager()
    scene_manager = SceneManager()

    light = SmartLight("L001")
    thermostat = SmartThermostat("T001")
    doorlock = SmartDoorLock("D001")
    camera = SmartCamera("C001")
    speaker = SmartSpeaker("S001")

    for device in [light, thermostat, doorlock, camera, speaker]:
        manager_.add_device(device)

    print("\n All devices added to the manager.\n")

    for device_id in ["L001", "T001", "D001", "C001", "S001"]:
        await manager_.control_device(device_id, "turn_on", user_role="admin")

    await manager_.control_device("L001", "set_brightness", 70)
    await manager_.control_device("T001", "set_temperature", 24)
    await manager_.control_device("C001", "set_recording", True)
    await manager_.control_device("C001", "set_resolution", "1080p")
    await manager_.control_device("S001", "set_volume", 60)
    await manager_.control_device("S001", "play_track", "Imagine - John Lennon")
    await manager_.control_device("D001", "unlock", "Admin@123")

    try:
        await manager_.control_device("T001", "set_temperature", 100)
    except SmartHomeError as e:
        print(f"Error caught: {e}")

    try:
        await manager_.control_device("D001", "unlock", "WrongPass")
    except PermissionDeniedError as e:
        print(f"Door unlock failed: {e}")

    print("\nCurrent status of all devices:\n")
    manager_.get_all_device_statuses()

    await manager_.save_config("device_config.json")

    print("\nLoading configuration into a new manager...\n")
    new_manager = HomeManager()
    try:
        for device in [SmartLight("L001"), SmartThermostat("T002"), SmartDoorLock("D002"), SmartCamera("C002"),
                   SmartSpeaker("S002")]:
            new_manager.add_device(device)
        await new_manager.load_config("device_config.json")
    except PermissionDeniedError as e:
        print(e.message)

    scene_manager.add_scene("movie_time", ("L001", "set_brightness", 30))
    scene_manager.add_scene("movie_time", ("S001", "set_volume", 40))
    scene_manager.add_scene("movie_time", ("C001", "set_recording", True))
    scene_manager.add_scene("movie_time", ("T001", "set_temperature", 22))

    try:
        await scene_manager.activate_scene(manager_, "movie_time", user_role="admin")
    except InvalidParameterError as e:
        print(e.message)

    print("\n")
    manager_.get_all_device_statuses()

    print("\nSmart Home System Demo Complete!\n")

    print(f"\nTotal devices created: {SmartDevice.get_device_count()}")
    print(f"Current System Time: {SmartDevice.get_system_time()}\n")

    print("Turning OFF all devices...\n")
    for device_id in ["L001", "T001", "D001", "C001", "S001"]:
        await manager_.control_device(device_id, "turn_off", user_role="admin")

    print("\nChanging doorlock passcode...")
    doorlock.change_passcode("Admin@123", "Secure@2025")

    print("\nSupported actions per device:")

    for device in [light, thermostat, doorlock, camera, speaker]:
        print(f"{type(device).__name__} supports: {device.get_supported_actions()}")

    print(SmartAlarmSystem.__mro__)


if __name__ == "__main__":
    asyncio.run(main())