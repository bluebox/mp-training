# home_manager.py
import asyncio
from utils.decorators import role_guard
from core.devices import SmartDevice
from core.exceptions import SmartHomeError,DuplicateDeviceError,DeviceNotFoundError,PermissionDeniedError
from utils.helpers import convert_to_json, load_class

class HomeManager:
    def __init__(self):
        self._devices: list[SmartDevice] = []

    def add_device(self, device: SmartDevice):
        try:
            if not isinstance(device, SmartDevice):
                raise TypeError("Object must inherit SmartDevice.")
            if any(d.device_id == device.device_id for d in self._devices):
                raise DuplicateDeviceError(f"Device ID {device.device_id} already exists.")
            self._devices.append(device)
            print(f"{device.device_id} added.")
        except SmartHomeError as exc:
            print("Error:", exc)

    @role_guard
    async def control_device(self, user_role, device_id, action_type, value=None):
        try:
            device = next(d for d in self._devices if d.device_id == device_id)
            await device.perform_action(action_type, value)
        except StopIteration:
            raise DeviceNotFoundError(f"No device with id '{device_id}'") from None
        except SmartHomeError as exc:
            print("Error:", exc)

    async def save_config(self, file_name: str):
        try:
            await asyncio.sleep(1)
            convert_to_json(self._devices, file_name)
        except SmartHomeError as exc:
            print("Error:", exc)

    async def load_config(self, file_name: str):
        try:
            self._devices = await load_class(self._devices, file_name)
        except SmartHomeError as exc:
            print("Error:", exc)

    def get_all_device_statuses(self):
        for device in self._devices:
            try:
                print(device.get_status_report())
            except SmartHomeError as exc:
                print("Error:", exc)


if __name__ == "__main__":
    from core.devices import SmartLight, SmartCamera

    async def demo():
        manager = HomeManager()
        manager.add_device(SmartLight("L001"))
        manager.add_device(SmartCamera("C001"))

        await manager.control_device("admin", "L001", "set_brightness", 70)
        await manager.save_config("home.json")
        await manager.load_config("home.json")
        manager.get_all_device_statuses()


    asyncio.run(demo())
