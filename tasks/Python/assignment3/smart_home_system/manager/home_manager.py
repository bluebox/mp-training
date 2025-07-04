import asyncio

from smart_home_system.core.exceptions import InvalidParameterError, DeviceOfflineError, ActionNotSupportedError, \
    AuthenticationError, PermissionDeniedError, SmartHomeError
#from smart_home_system.core.security import SmartAlarmSystem
from smart_home_system.utils.helpers import smart_device_from_dict, smart_home_json_encoder
#from smart_home_system.core.devices import SmartLight, SmartThermostat, SmartDoorLock, SmartCamera, SmartSpeaker, SmartDevice

class HomeManager:
    def __init__(self):
        self.devices = []
        self.devices_map_reports = {}
    def add_device(self,device):
        if device._device_id in self.devices:
            print(f"Device with {device._device_id} is already added")
            return
        self.devices.append(device)
        print(f"Device {device._device_id} added to HomeManager.")
        self.devices_map_reports[device._device_id] = device.get_status_report()

    async def control_device(self,device_id, action_type, value=None, user_role = None):
        try:
            for device in self.devices:
                if device._device_id == device_id:
                    await device.perform_action(action_type, value)
                    return
        except InvalidParameterError as e:
            print(e.message)

        except DeviceOfflineError as e:
            print(e.message)

        except ActionNotSupportedError as e:
            print(e.message)

        except AuthenticationError as e:
            print(e.message)

        except PermissionDeniedError as e:
            print(e.message)

        except SmartHomeError as e:
            print(e.message)
        print("Device not found.")

    def get_all_device_statuses(self):
        print("Device Status Reports")
        for status in self.devices_map_reports.values():
            print(status)

    async def save_config(self,file_name):
        states = {}
        for device in self.devices:
            states[device._device_id] = device.is_on
        await asyncio.sleep(2)
        smart_home_json_encoder(states,file_name)

    async def load_config(self,file_name):
        states = smart_device_from_dict(file_name)
        print("After serialization ",states)
        await asyncio.sleep(3)
        for device in self.devices:
            if states[device._device_id] == "is_on":
                device.turn_on()

# if __name__ == "__main__":
#     manager = HomeManager()
#
#     light = SmartLight("L001")
#     thermo = SmartThermostat("T001")
#     door = SmartDoorLock("D001")
#     camera = SmartCamera("C001")
#     speaker = SmartSpeaker("S001")
#
    # manager.add_device(light)
#     manager.add_device(thermo)
#     manager.add_device(door)
#     manager.add_device(camera)
#     manager.add_device(speaker)
#
#
    # asyncio.run(light.turn_on())
#     asyncio.run(thermo.turn_on())
#     asyncio.run(door.turn_on())
#     asyncio.run(camera.turn_on())
#     asyncio.run(speaker.turn_on())
#
#     asyncio.run(light.turn_off())
#     asyncio.run(thermo.turn_off())
#     asyncio.run(door.turn_off())
#     asyncio.run(camera.turn_off())
#     asyncio.run(speaker.turn_off())
#
#
#     light.brightness = 75print(f"Device {self._device_id} already ON.")
#     thermo.temperature = 23.5
#     light.brightness = 150
#
#     manager.get_all_device_statuses()
#
#     asyncio.run(light.turn_off())

    # asyncio.run(manager.save_config("abc.json"))
