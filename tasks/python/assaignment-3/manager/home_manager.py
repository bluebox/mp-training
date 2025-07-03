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
        print(f"Device {device._device_id} added to HomeManager.")

    #make async
    def control_device(self,user_role ,device_id, action_type, value=None):#need to implement user role based access
        for device in self._devices:
            if device._device_id == device_id:
                device.perform_action(action_type, value)
                return
        print(f"Device {device_id} not found.")#exception convert/raise

    #make async
    def save_config(self,file_name):
        convert_to_json(self,file_name)

    def load_config(self,file_name):
        self.__dict__.update(load_class(file_name))


    def get_all_device_statuses(self):
        for device in self._devices:
            print(device.get_status_report())


if __name__ == "__main__":
    manager = HomeManager()
    manager.add_device(SmartLight("home"))
    manager.add_device(SmartCamera("home2"))
    manager.save_config("home.json")
    print(manager.get_all_device_statuses())
    manager.load_config("home.json")
    print(manager.get_all_device_statuses())