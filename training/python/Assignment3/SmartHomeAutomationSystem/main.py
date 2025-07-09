import asyncio

from SmartHomeAutomationSystem.core.devices import SmartLight, SmartThermostat, SmartDoorLock, SmartCamera, SmartSpeaker
from SmartHomeAutomationSystem.core.security import SecuritySensor, SmartAlarmSystem, Programmable
from SmartHomeAutomationSystem.core.exceptions import SmartHomeError
from SmartHomeAutomationSystem.manager.home_manager import HomeManager
from SmartHomeAutomationSystem.manager.scene_manager import SceneManager
from SmartHomeAutomationSystem.manager.scheduler import Scheduler

async def main():
    print("-------> Smart Home Automation System Demo <-------")

    home = HomeManager()
    scenes = SceneManager()
    scheduler = Scheduler()

    light = SmartLight("L001", brightness=80)
    light_1  = SmartLight("L001", brightness=80)

    thermostat = SmartThermostat("T001", temperature=22)
    door = SmartDoorLock("D001", passcode="Secure@123")
    camera = SmartCamera("C001", resolution="1080p")
    speaker = SmartSpeaker("S001", volume=30)
    sensor = SecuritySensor("X001")
    alarm = SmartAlarmSystem("A001", siren_volume=70)

    for dev in [light, light_1, thermostat, door, camera, speaker, sensor, alarm]:
        try:
            home.add_device(dev)
        except Exception as e:
            print(f"Add device error: {e}")

    await home.control_device("admin", "L001", "turn_on")
    await home.control_device("admin", "L001", "set_brightness", 80)
    await home.control_device("user", "T001", "turn_on")
    await home.control_device("user", "T001", "set_temperature", 25)
    await home.control_device("admin", "D001", "turn_on")
    print(await home.control_device("admin", "D001", "unlock", "Secure@123"))
    print(await home.control_device("user", "D001", "lock"))

    await home.control_device("admin", "C001", "turn_on")
    await home.control_device("user", "C001", "set_resolution", "4K")
    await home.control_device("admin", "S001", "turn_on")
    await home.control_device("user", "S001", "set_volume", 50)
    await home.control_device("user", "S001", "play_track", "Song.mp3")

    await home.control_device("admin", "X001", "turn_on")
    await home.control_device("admin", "X001", "arm")
    await home.control_device("admin", "A001", "turn_on")
    await home.control_device("admin", "A001", "set_siren_volume", 80)
    await home.control_device("admin", "A001", "arm")

    print(await home.control_device("guest", "A001", "set_siren_volume", 20))

    scenes.add_scene("Night Mode", [
        ("L001", "turn_off", None),
        ("T001", "set_temperature", 20),
        ("D001", "lock", None),
        ("A001", "arm", None)
    ])
    print("\nActivating Night Mode (as user):")
    print(await scenes.activate_scene(home, "Night Mode", "user"))

    scenes.add_scene("Good Morning", [
        ("L001", "turn_on", None),
        ("T001", "set_temperature", 20),
        ("D001", "unlock", 'Surya12345$'),
        ("A001", "arm", None)
    ])
    print("\nActivating Good Morning (as user):")
    print(await scenes.activate_scene(home, "Good Morning", "user"))

    scheduler.add_scheduled_task("23:59", "L001", "turn_off", None, "admin")
    print("Scheduled a light turn-off for 23:59.")

    asyncio.create_task(scheduler.run_pending_tasks(home))

    await home.save_config("my_home.json")
    print("Home configuration saved.")

    new_home = HomeManager()
    await new_home.load_config("my_home.json")
    print("Loaded configuration: ", new_home.get_all_device_statuses())

    print("Online device IDs:", home.get_online_device_ids())
    print("Average thermostat temperature:", home.get_average_thermostat_temp())
    print("Brightness list:", home.get_brightness_list())
    print("Unique active types:", home.get_unique_active_types())
    print("Device ID to status:", home.get_device_id_to_status())

    print("Iterate all lights:")
    for light in home.iter_lights():
        print(light.get_status_report())

    try:
        await home.control_device("user", "D001", "unlock", "wrongpass")
    except SmartHomeError as e:
        print("Handled error:", e)

    print("\nMRO for SmartAlarmSystem:", [cls.__name__ for cls in SmartAlarmSystem.__mro__])

    print("\nCheck smart_home_log.txt for device state change logs!")

if __name__ == "__main__":
    asyncio.run(main())

