import asyncio

from core.devices import SmartDevice,SmartLight,SmartCamera
from utils.helpers import convert_to_json, load_class


class HomeManager:
    def __init__(self):
        self._devices = []

    def add_device(self, device):
        if not isinstance(device, SmartDevice):
            print("Device is not a SmartDevice.")
            return
        self._devices.append(device)
        print(f"Device {device.device_id} added to HomeManager.")

    #make async
    async def control_device(self,user_role ,device_id, action_type, value=None):#need to implement user role based access
        try:
            device = next(filter(lambda d:d.device_id==device_id,self._devices))
            await device.perform_action(action_type,value)
        except StopIteration:
            print(f"Device {device_id} not found.")#exception convert/raise

    #make async
    async def save_config(self,file_name):
        await asyncio.sleep(1)
        convert_to_json(self._devices,file_name)

    def load_config(self,file_name):
        self._devices = asyncio.run(load_class(self._devices,file_name))
        # print(self._devices)


    def get_all_device_statuses(self):
        for device in self._devices:
            print(device.get_status_report())


if __name__ == "__main__":
    manager = HomeManager()
    manager.add_device(SmartLight("home"))
    manager.add_device(SmartCamera("home3"))
    asyncio.run(manager.save_config("home.json"))
    print(manager.get_all_device_statuses())
    manager.load_config("home.json")
    manager.get_all_device_statuses()
