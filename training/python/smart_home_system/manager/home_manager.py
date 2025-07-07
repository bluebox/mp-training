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

# function programming
async def smart_device_from_dict(data):
    device_type = data.get("__device_type__")
    device_id = data.get("device_id")
    is_on = data.get("is_on", False)

    cls = DeviceRegistrarMeta.registry.get(device_type)
    if not cls:
        print(f"Unknown device type: {device_type}")
        return None

    if device_type == "SmartDoorLock":
        passcode = data.get("passcode", "default_pass")
        device = cls(device_id, passcode)
    else:
        device = cls(device_id)

    if is_on:
        await device.turn_on()
    else:
        await device.turn_off()
    return device


class SmartHomeJSONEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, SmartDoorLock):
            return {
                "__device_type__": type(obj).__name__,
                "device_id": obj._device_id,
                "is_on": obj._is_on,
                "passcode": obj._SmartDoorLock__passcode
            }
        if isinstance(obj, SmartDevice):
            return {
                "__device_type__": type(obj).__name__,
                "device_id": obj._device_id,
                "is_on": obj._is_on,
            }
        return super().default(obj)


class HomeManager:
    def __init__(self, role):
        self._devices = {}
        self.__user_role = role

    def get_devices(self):
        return self._devices

    def add_device(self, device):
        if device._device_id in self._devices:
            print(f'devices with id {device._device_id} is already exist')
        else:
            self._devices[device._device_id] = device
            print(f'devices with id {device._device_id} is added')

    async def control_device(self, user_role, device_id, action_type, value=None,passcode_val=None):
        if user_role != self.__user_role:
            print(f"{user_role} doesn't have permission to control {device_id}")
            return

        device = self._devices.get(device_id)
        if not device:
            print(f"No device found with ID {device_id}")
            return

        try:
            if isinstance(device,SmartDoorLock):
                await device.perform_action(action_type,passcode=passcode_val)
            elif isinstance(value, dict):
                await device.perform_action(action_type, **value)
            else:
                await device.perform_action(action_type, value)
        except AuthenticationError as e:
            print(f"Authentication Failed: {e}")
        except DeviceOfflineError as e:
            print(f"Device is offline: {e}")
        except ActionNotSupportedError as e:
            print(f"Unsupported Action: {e}")
        except Exception as e:
            print(f"Unexpected Error: {e}")

    async def execute_action(self, device_id, action_type, value, user_role):
        device = self._devices.get(device_id)
        if not device:
            print(f"No device found with ID {device_id}")
            return

        if isinstance(device, SmartDoorLock):
            await self.control_device(user_role, device_id, action_type, passcode_val=value)
        else:
            await self.control_device(user_role, device_id, action_type, value)

    async def save_config(self):
        all_devices = []
        for device in self._devices.values():
            encoded = json.loads(json.dumps(device, cls=SmartHomeJSONEncoder))
            all_devices.append(encoded)

        async with aiofiles.open('/home/developer/Kanishka/training/python/smart_home_system/save_config.json',
                                 'w') as f:
            await f.write(json.dumps(all_devices, indent=2))

    async def load_config(self):
        async with aiofiles.open('/home/developer/Kanishka/training/python/smart_home_system/save_config.json',
                                 'r') as f:
            content = await f.read()
            content = json.loads(content)
            self._devices.clear()
            for value in content:
                device = await smart_device_from_dict(value)
                self.add_device(device)

    def get_all_device_statuses(self):
        for device_type_dict in self._devices.values():
            for device in device_type_dict.values():
                print(f'{type(device).__name__} device_id = {device._device_id}')


def all_id_of_online(home_manager):
    mp = home_manager.get_devices()
    ans = list(filter(lambda key: mp[key]._is_on, mp))
    return ans


def avg_temperature_thermostat(home_manager):
    mp = home_manager.get_devices()
    add, cnt = 0, 0
    for key, value in mp.items():
        if isinstance(value, SmartThermoStat):
            add += value.temperature
            cnt += 1
    return add / cnt


def get_properties(home_manager):
    mp = home_manager.get_devices()
    ans = list(map(lambda key: mp[key].get_supported_actions(), mp))
    return ans


def generator_id_online(ans):
    for i in ans:
        yield i


# def get_properties(home_manager):
#     devices = home_manager.get_devices()
#     properties = {}
#
#     for device_id, device in devices.items():
#         actions = device.get_supported_actions()
#         properties[device_id] = actions
#
#     return properties


async def main():
    a = HomeManager('admin')
    # # await a.load_config()
    b = SmartDoorLock('SDL3748')
    c = SmartThermoStat('ST32478')
    await c.turn_on()
    a.add_device(c)
    await b.turn_on('Admin')
    a.add_device(b)
    # await  a.save_config()
    # await a.load_config()
    await a.control_device('admin', 'ST32478', 'set_temperature', 27)
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

