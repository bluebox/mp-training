# from core.devices import SmartCamera
from core.devices import SmartDevice, DeviceRegistrarMeta
from core.devices import SmartDoorLock
from core.devices import SmartLight
from core.devices import SmartSpeaker
from core.devices import SmartThermoStat
import json
import asyncio
import aiofiles
from datetime import datetime, timedelta
from manager.scene_manager import SceneManager
from manager.scheduler import Scheduler
from core.exceptions import AuthenticationError,DeviceOfflineError,ActionNotSupportedError
from functools import reduce
from manager.home_manager import HomeManager
def all_id_of_online(home_manager):
    mp = home_manager.get_devices()
    ans = list(filter(lambda key: mp[key]._is_on, mp))
    return ans


def avg_temperature_thermostat(home_manager):
    mp = home_manager.get_devices()
    thermostats = [device for device in mp.values() if isinstance(device, SmartThermoStat)]
    if not thermostats:
        return 0
    total_temp = reduce(lambda acc, t: acc + t.temperature, thermostats, 0)
    return total_temp / len(thermostats)


# def get_properties(home_manager):
#     mp = home_manager.get_devices()
#     ans = list(map(lambda key: mp[key].get_supported_actions(), mp))
#     return ans


def generator_id_online(ans):
    for i in ans:
        yield i


def get_properties(home_manager):
    devices = home_manager.get_devices()
    properties = {}

    for device_id, device in devices.items():
        actions = device.get_supported_actions()
        properties[device_id] = actions

    return properties

async def main():
    a = HomeManager('admin')
    # # await a.load_config()
    b = SmartDoorLock('SDL3748')
    c = SmartThermoStat('ST32478')
    d = SmartThermoStat('ST32479')

    await c.turn_on()
    a.add_device(c)
    await b.turn_on('Admin')
    a.add_device(b)
    await d.turn_on()
    a.add_device(d)
    await  a.save_config()
    await a.load_config()
    await a.control_device('admin', 'ST32478', 'set_temperature', 27)
    await a.control_device('admin', 'ST32479', 'set_temperature', 20)

    await a.control_device('admin', 'SDL3748', 'lock',passcode_val='Admin')
    # print(b.lock())
    ans = all_id_of_online(a)
    for i in generator_id_online(ans):
        print(i)
    print(avg_temperature_thermostat(a))
    print(get_properties(a))
    # print(a._devices)
    scene = SceneManager()
    scene.add_scene('open it', [('SDL3748', 'unlock', 'Admin')])
    scene.add_scene('close it',[('SDL3748','lock','Admin')])
    await scene.activate_scene(a, 'open it', 'admin')
    await scene.activate_scene(a,'close it','admin')
    scheduler = Scheduler()
    next_time = (datetime.now() + timedelta(minutes=1)).strftime("%H:%M")
    scheduler.add_scheduled_task(next_time, 'SDL3748', 'unlock', 'Admin', 'admin')
    scheduler.add_scheduled_task(next_time, 'ST32478', 'set_temperature', 22, 'admin')

    print(f"Waiting for scheduled tasks at: {next_time}")
    while scheduler.tasks:
        await scheduler.run_pending_tasks(a)
        await asyncio.sleep(10)

    print("All scheduled tasks executed.")


if __name__ == '__main__':
    asyncio.run(main())