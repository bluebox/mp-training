from core.devices.SmartCamera import SmartCamera
from core.devices.SmartDevice import SmartDevice, DeviceRegistrarMeta
from core.devices.SmartDoorLock import SmartDoorLock
from core.devices.SmartLight import SmartLight
from core.devices.SmartSpeaker import SmartSpeaker
from core.devices.SmartThermostat import SmartThermostat
import json
import asyncio
import aiofiles

from manager.SceneManager import SceneManager


# function programming
async def smart_device_from_dict(data):
    device_type = data.get("__device_type__")
    device_id = data.get("device_id")
    is_on = data.get("is_on", False)

    cls = DeviceRegistrarMeta.registry.get(device_type)
    if not cls:
        return print(f"Unknown device type: {device_type}")

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
                "is_on": obj.is_on(),
                "passcode": obj._passcode
            }
        if isinstance(obj, SmartDevice):
            return {
                "__device_type__": type(obj).__name__,
                "device_id": obj._device_id,
                "is_on": obj.is_on(),
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

    async def control_device(self, user_role, device_id, action_type, value=None):
        if user_role == self.__user_role:
            for device in self._devices.values():
                if device._device_id == device_id:
                    await device.perform_action(action_type, value)
                    return
            print(f"No device found with ID {device_id}")
        else:
            print(f"{user_role} doesn't have permission to control {device_id}")

    async def save_config(self):
        all_devices = []
        for device in self._devices.values():
            encoded = json.loads(json.dumps(device, cls=SmartHomeJSONEncoder))
            all_devices.append(encoded)

        async with aiofiles.open('/home/developer/PycharmProjects/Python/Assignment_3/manager/save_config.json',
                                 'w') as f:
            await f.write(json.dumps(all_devices, indent=2))

    async def load_config(self):
        async with aiofiles.open('/home/developer/PycharmProjects/Python/Assignment_3/manager/save_config.json',
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


def all_id_of_online(homemanager):
    mp = homemanager.get_devices()
    ans = list(filter(lambda key: mp[key].is_on(), mp))
    return ans


def avg_temperature_thermostat(homemanager):
    mp = homemanager.get_devices()
    sum, cnt = 0, 0
    for key, value in mp.items():
        if isinstance(value, SmartThermostat):
            sum += value.temperature
            cnt += 1
    return sum / cnt


def get_properties(homemanager):
    mp = homemanager.get_devices()
    ans = list(map(lambda key: mp[key].get_supported_actions(), mp))
    return ans


def generator_id_online(ans):
    for i in ans:
        yield i


async def main():
    a = HomeManager('admin')
    # # await a.load_config()
    b = SmartDoorLock('l001', 'Aanand@123')
    c = SmartThermostat('l002')
    await c.turn_on()
    a.add_device(c)
    await b.turn_on()
    a.add_device(b)
    # await  a.save_config()
    # await a.load_config()
    await a.control_device('admin', 'l002', 'set_temperature', 25)
    await a.control_device('admin','l001','change_lock','Aanand@123')
    print(b.lock)
    ans = all_id_of_online(a)
    for i in generator_id_online(ans):
        print(i)
    print(avg_temperature_thermostat(a))
    print(get_properties(a))
    # print(a._devices)
    scene=SceneManager()
    scene.add_scene('good_morning',[('l001','change_lock','Aanand@123')])
    await scene.activate_scene(a,'good_morning','admin')
if __name__ == '__main__':
    asyncio.run(main())
