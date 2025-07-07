import asyncio
from datetime import datetime, timedelta

from core.securityDevices.SecuritySensor import SecuritySensor
from manager.Scheduler import Scheduler
from core.devices.SmartDoorLock import SmartDoorLock
from core.devices.SmartThermostat import SmartThermostat
from core.devices.SmartCamera import SmartCamera
from core.devices.SmartSpeaker import SmartSpeaker
from core.devices.SmartLight import SmartLight
from core.securityDevices.SmartAlarmSystem import SmartAlarmSystem
from core.exceptions import SmartDeviceException
from manager.SceneManager import SceneManager
from manager.HomeManager import HomeManager
from manager.HomeManager import all_id_of_online,avg_temperature_thermostat,generator_id_online,get_properties
async def main():
    print("\nInitializing Smart Home System...\n")
    admin_home = HomeManager('admin')
    user_home = HomeManager('user')

    print("\nAdding Devices...")
    light = SmartLight("light01")
    thermostat = SmartThermostat("thermo01")
    speaker = SmartSpeaker("speaker01")
    door_lock = SmartDoorLock("door01", "Secure@123")
    camera = SmartCamera("cam01")
    alarm = SmartAlarmSystem("alarm01")
    security_sensor=SecuritySensor('sensor001')

    await light.turn_on()
    await thermostat.turn_on()
    await speaker.turn_on()
    await door_lock.turn_on()
    await camera.turn_on()
    await alarm.turn_on()
    await security_sensor.turn_on()

    for device in [light, thermostat, speaker, door_lock, camera, alarm,security_sensor]:
        admin_home.add_device(device)

    print("\nControlling Device Parameters...")
    await admin_home.control_device('admin', 'light01', 'set_brightness', 70)
    await admin_home.control_device('admin', 'thermo01', 'set_temperature', 24)
    await admin_home.control_device('admin', 'speaker01', 'set_volume', 9)
    await admin_home.control_device('admin', 'cam01', 'set_resolution', '1080p')

    print("\nDoor Lock Security...")
    try:
        await admin_home.control_device('admin', 'door01', 'change_lock', 'WrongPass')
    except SmartDeviceException as e:
        print(f"Exception caught: {e}")
    await admin_home.control_device('admin', 'door01', 'change_lock', 'Secure@123')

    print("\nArming/Disarming Alarm...")
    await admin_home.control_device('admin', 'alarm01', 'arm')
    await admin_home.control_device('admin', 'alarm01', 'disarm')

    print("\nScene Management...")
    scene_mgr = SceneManager()
    scene_mgr.add_scene('good_night', [
        ('light01', 'turn_off', None),
        ('door01', 'change_lock', 'Secure@123'),
        ('alarm01', 'arm', None)
    ])
    print("Activating scene with valid role:")
    await scene_mgr.activate_scene(admin_home, 'good_night', 'admin')
    print("Activating scene with invalid role:")
    await scene_mgr.activate_scene(admin_home, 'good_night', 'guest')

    print("\nScheduling Tasks...")
    scheduler = Scheduler()
    time_str = (datetime.now() + timedelta(minutes=1)).strftime("%H:%M")
    scheduler.add_scheduled_task(time_str, "thermo01", "set_temperature", 22, "admin")
    scheduler.add_scheduled_task(time_str, "speaker01", "set_volume", 6, "admin")
    print(f"Scheduled tasks for {time_str}")

    print("\nSaving Configuration...")
    await admin_home.save_config()

    print("Resetting and Loading Configuration...")
    admin_home = HomeManager('admin')
    await admin_home.load_config()

    print("\nRunning Comprehension Functions...")
    print("Online device IDs:", all_id_of_online(admin_home))
    print("Avg thermostat temp:", avg_temperature_thermostat(admin_home))
    print("Device capabilities:", get_properties(admin_home))

    print("\nGenerator Test:")
    gen = generator_id_online(all_id_of_online(admin_home))
    for device_id in gen:
        print("Yielded device ID:", device_id)

    print("\nExecuting Scheduled Tasks...")
    while scheduler.tasks:
        await scheduler.run_pending_tasks(admin_home)
        await asyncio.sleep(10)

    print("\nVerifying Decorator Logs...")
    with open("smart_home_log.txt") as f:
        logs = f.readlines()
        print("Recent Log Entries:")
        print("".join(logs[-5:]))

    print("\nAll features demonstrated successfully.")

if __name__ == '__main__':
    asyncio.run(main())
