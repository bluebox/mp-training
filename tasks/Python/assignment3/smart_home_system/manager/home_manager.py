import asyncio
from smart_home_system.core.exceptions import (
    InvalidParameterError, DeviceOfflineError, ActionNotSupportedError,
    AuthenticationError,
    PermissionDeniedError,
    SmartHomeError,
)
from smart_home_system.utils.helpers import smart_device_from_dict, smart_home_json_encoder


ROLE_PERMISSIONS = {
    "admin": {"turn_on", "turn_off", "get_status_report", "set_brightness", "get_brightness", "set_temperature", "get_temperature",  "set_resolution", "set_recording", "play_track", "set_volume", "arm_sensor", "disarm_sensor", "arm", "disarm", "unlock"},
    "user": {"turn_on", "turn_off", "get_status_report", "set_brightness", "set_temperature", "play_track", "set_volume", "set_resolution"},
    "guest": {"turn_on", "turn_off", "get_status_report"},
}


class HomeManager:
    def __init__(self):
        self.devices = []

    def add_device(self, device):
        if any(d.device_id == device.device_id for d in self.devices):
            print(f"Device '{device.device_id}' is already added.")
            return
        self.devices.append(device)
        print(f"Device '{device.device_id}' was added successfully.")

    async def control_device(self, device_id, action_type, value=None, user_role="guest"):
        try:
            if action_type not in ROLE_PERMISSIONS.get(user_role, set()):
                raise PermissionDeniedError(f"Sorry! '{user_role}' is not allowed to do '{action_type}'.")

            for device in self.devices:
                if device.device_id == device_id:
                    await device.perform_action(action_type, value)
                    return

            raise InvalidParameterError(f"No device found with ID '{device_id}'.")

        except (InvalidParameterError, DeviceOfflineError, ActionNotSupportedError,
                AuthenticationError, PermissionDeniedError, SmartHomeError) as e:
            print(f"Oops! Something went wrong: {e}")

    def get_all_device_statuses(self):
        print("The status reports of all devices:")
        for device in self.devices:
            print(device.get_status_report())

    async def save_config(self, file_name):
        state_dict = {}

        for device in self.devices:
            state_dict[device.device_id] = {
                "class_name": type(device).__name__,
                "is_on": device.is_on
            }

        await asyncio.sleep(1)  # Simulating time delay for saving
        smart_home_json_encoder(state_dict, file_name)
        print(f"Device states saved successfully to '{file_name}'.")

    async def load_config(self, file_name):
        try:
            devices = smart_device_from_dict(file_name)
            for device in devices:
                if getattr(device, "_restore_on_state", False):
                    await device.turn_on()
                self.add_device(device)
            print("Device settings loaded successfully!")
        except InvalidParameterError as e:
            print(e.message)