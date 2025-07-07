import asyncio
from core.devices import SmartDoorLock

class SceneManager:
    def __init__(self):
        self._scenes = {}

    def add_scene(self, scene_name, actions):
        self._scenes[scene_name] = actions

    async def activate_scene(self, home_manager, scene_name, user_role):
        if scene_name not in self._scenes:
            return

        actions = self._scenes[scene_name]
        tasks = []
        print(actions)
        # for device_id, action_type, value in actions:
        #     task = home_manager.control_device(user_role, device_id, action_type, value)
        #     tasks.append(task)
        #
        # await asyncio.gather(*tasks)
        devices = home_manager.get_devices()

        for device_id, action_type, value in actions:
            device = devices.get(device_id)
            if not device:
                print(f"No device found with ID {device_id}")
                continue

            if isinstance(device, SmartDoorLock):
                task = home_manager.control_device(user_role, device_id, action_type, passcode_val=value)
            else:
                task = home_manager.control_device(user_role, device_id, action_type, value)

            tasks.append(task)

        await asyncio.gather(*tasks)
