import asyncio
from smart_home_system.core.exceptions import InvalidParameterError

class SceneManager:
    def __init__(self):
        self.scenes= {}

    def add_scene(self,scene_name, actions):#actions (device_id, action_type, value)
        self.scenes.setdefault(scene_name,[]).append(actions)

    async def activate_scene(self,home_manager, scene_name, user_role):
        if scene_name not in self.scenes:
            raise InvalidParameterError(f"Scene '{scene_name}' not found.")

        print(f"\n Activating scene: '{scene_name}'")
        for device_id, action_type, value in self.scenes[scene_name]:
            await home_manager.control_device(device_id, action_type, value, user_role)
