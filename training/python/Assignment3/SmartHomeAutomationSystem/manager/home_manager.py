import asyncio
import json
from SmartHomeAutomationSystem.core.exceptions import (
    DeviceOfflineError, InvalidParameterError,
    ActionNotSupportedError, PermissionDeniedError,
)
from SmartHomeAutomationSystem.utils.helpers import SmartHomeJSONEncoder, smart_device_from_dict

ROLE_PERMISSIONS = {
    "admin": {"all"},
    "user": {"turn_on", "turn_off", "set_brightness", "set_temperature",
             "lock", "unlock", "play_track", "set_volume", "start_recording", "stop_recording",
             "arm", "disarm", "set_siren_volume"},
    "guest": {"turn_on", "turn_off", "play_track"},
}

class HomeManager:
    def __init__(self):
        self.devices = {}

    def add_device(self, device):
        if device.device_id in self.devices:
            raise InvalidParameterError("Device with this ID already exists.")
        self.devices[device.device_id] = device

    async def control_device(self, user_role, device_id, action_type, value=None):
        try:
            device = self.devices[device_id]
            allowed = ROLE_PERMISSIONS.get(user_role, set())
            if "all" not in allowed and action_type not in allowed:
                raise PermissionDeniedError(f"{user_role} cannot perform {action_type}.")
            if action_type == "turn_on":
                await device.turn_on()
            elif action_type == "turn_off":
                await device.turn_off()
            else:
                await device.perform_action(action_type, value)
            return f"Action '{action_type}' performed on {device_id}."
        except KeyError:
            return f"Device {device_id} not found."
        except Exception as e:
            return f"Error: {e}"

    def get_all_device_statuses(self):
        return {id_: device.get_status_report() for id_, device in self.devices.items()}

    def get_online_device_ids(self):
        return list(map(lambda d: d.device_id, filter(lambda d: d.is_on, self.devices.values())))

    def get_average_thermostat_temp(self):
        thermostats = [d for d in self.devices.values() if d.__class__.__name__ == "SmartThermostat"]
        temps = [d.temperature for d in thermostats]
        return sum(temps) / len(temps) if temps else 0

    def get_brightness_list(self):
        return [d.brightness for d in self.devices.values() if hasattr(d, 'brightness')]

    def get_unique_active_types(self):
        return set(d.__class__.__name__ for d in self.devices.values() if d.is_on)

    def get_device_id_to_status(self):
        return {d.device_id: d.get_status_report() for d in self.devices.values()}

    def get_device_id_to_supported_actions(self):
        return {d.device_id: d.get_supported_actions() for d in self.devices.values()}

    def iter_lights(self):
        for d in self.devices.values():
            if d.__class__.__name__ == "SmartLight":
                yield d

    def iter_active_devices(self):
        for d in self.devices.values():
            if d.is_on:
                yield d

    def iter_devices_by_condition(self, predicate):
        for d in self.devices.values():
            if predicate(d):
                yield d

    async def save_config(self, filename):
        data = list(self.devices.values())
        loop = asyncio.get_event_loop()
        json_str = await loop.run_in_executor(None, lambda: json.dumps(data, cls=SmartHomeJSONEncoder, indent=2))
        with open(filename, "w") as f:
            f.write(json_str)

    async def load_config(self, filename):
        with open(filename, "r") as f:
            raw = f.read()
            data = json.loads(raw)
            self.devices = {}
            for d in data:
                obj = smart_device_from_dict(d)
                self.devices[obj.device_id] = obj
