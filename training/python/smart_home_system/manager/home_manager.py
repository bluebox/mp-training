from core.exceptions import InvalidParameterError, SmartHomeError, PermissionDeniedError, DuplicateDeviceId, \
    DeviceOfflineError
from core.devicess import SmartDevice, DeviceRegistrarMeta
import json
from utils.helpers import SmartHomeJSONEncoder, smart_device_from_dict

# class based uesr role control
class UserRole:
    def __init__(self,name):
        self.name=name
        self.permissions=[]

    def can_do(self,action):
        return action in self.permissions
class Admin(UserRole):
    def __init__(self):
        super().__init__("Admin")
        self.permissions=["turn_on", "turn_off", "set_brightness", "set_volume", "play_track", "start_recording",
                          "stop_recording", "set_resolution", "set_temperature", "lock", "unlock","arm","disarm"]
class User(UserRole):
    def __init__(self):
        super().__init__("User")
        self.permissions=["turn_on", "turn_off", "set_brightness", "set_volume", "play_track", "set_temperature",
                          "lock", "unlock", "start_recording", "stop_recording", "set_resolution"],
class Guest(UserRole):
    def __init__(self):
        super().__init__("Guest")
        self.permissions = ["turn_on", "turn_off"]

class HomeManager:
    manager_registry = []
    all_ids = set()
    roles = {
        "Admin": ["turn_on", "turn_off", "set_brightness", "set_volume", "play_track", "start_recording", "stop_recording", "set_resolution", "set_temperature", "lock", "unlock","arm","disarm"],
        "User": ["turn_on", "turn_off", "set_brightness", "set_volume", "play_track", "set_temperature", "lock", "unlock", "start_recording", "stop_recording", "set_resolution"],
        "Guest": ["turn_on", "turn_off"]
    }
    def __init__(self):
        HomeManager.manager_registry.append(self)
        self._register = []
    def add_device(self, device):
        try:
            for i in self._register:
                if i._device_id == device._device_id :
                    raise DuplicateDeviceId("device Already present")
            if device._device_id in HomeManager.all_ids:
                raise DuplicateDeviceId("device Already connected to another manager")

            self._register.append(device)
            HomeManager.all_ids.add(device._device_id)
            print(f"Device {device._device_id} added")
        except DuplicateDeviceId as e:
            print(f"{e}")

    async def control_device(self, user_role, device_id, action_type, value=None):
            try:
                if user_role not in self.roles:
                    print(f"Invalidrole: {user_role}")
                    raise InvalidParameterError("no role with this name")

                if action_type not in self.roles[user_role]:
                    print(f"Access denied: {user_role} cannot perform '{action_type}'")
                    raise PermissionDeniedError(user_role, action_type)
                for device in self._register:
                    if device._device_id == device_id:
                        await device.perform_action(action_type, value)
                        return
                raise InvalidParameterError(f"Device not there")
            except (InvalidParameterError,PermissionDeniedError,DeviceOfflineError):
                print("may be device not present")


    def get_all_device_statuses(self):
        print("getting")
        print(self._register)
        for device in self._register:
            print(device.get_status_report())


    #save and loading
    async def save_config(self, filename):
        try:
            with open(filename, 'w') as f:
                json.dump(self._register, f, cls=SmartHomeJSONEncoder, indent=4)
            print(f"Configuration saved to {filename}")
        except Exception as e:
            print("Error saving configuration:", e)

    async def load_config(self, filename):
        try:
            with open(filename, 'r') as f:
                data = json.load(f)
                self._register = [smart_device_from_dict(d) for d in data if d]
            print(f"Configuration loaded from {filename}")
        except Exception as e:
            print("Error loading configuration:", e)

    #functional programming 
    def get_online_device_ids(self):
        return list(
            map(lambda d: d._device_id, filter(lambda d: d.is_on, self._register))
        )
    def get_all_brightness_levels(self):
        return list(
            map(lambda d: d._SmartLight__brightness, filter(lambda d: d.__class__.__name__ == "SmartLight", self._register))
        )
        
    #unique device which are on
    def get_active_device_types(self):
        return {device.__class__.__name__ for device in self._register if device.is_on}

    #deviceid->status report
    def get_device_id_to_status_map(self):
        return {device._device_id: device.get_status_report() for device in self._register}

    #deviceid->supported actions
    def get_device_supported_actions_map(self):
        return {
            device._device_id: device.get_supported_actions()
            for device in self._register if hasattr(device, "get_supported_actions")
        }
    
    #genrtors
    #SmartLight devices
    def generate_lights(self):
        from core.devicess import SmartLight
        for device in self._register:
            if isinstance(device, SmartLight):
                yield device

    #devices currently ON
    def generate_online_devices(self):
        for device in self._register:
            if device.is_on:
                yield device

    # devices support a specific action
    def generate_devices_supporting_action(self, action_name):
        for device in self._register:
            if hasattr(device, "get_supported_actions") and action_name in device.get_supported_actions():
                yield device
