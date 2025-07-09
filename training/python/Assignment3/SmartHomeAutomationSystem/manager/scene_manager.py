import asyncio
from SmartHomeAutomationSystem.core.exceptions import PermissionDeniedError

class SceneManager:
    def __init__(self):
        self.scenes = {}

    def add_scene(self, scene_name, actions):
        if scene_name in self.scenes:
            raise ValueError("Scene already exists.")
        self.scenes[scene_name] = actions

    async def activate_scene(self, home_manager, scene_name, user_role):
        if scene_name not in self.scenes:
            return f"Scene '{scene_name}' does not exist."
        actions = self.scenes[scene_name]
        tasks = []
        for device_id, action_type, value in actions:
            tasks.append(home_manager.control_device(user_role, device_id, action_type, value))
        results = await asyncio.gather(*tasks, return_exceptions=True)
        for res in results:
            if isinstance(res, PermissionDeniedError):
                return f"Permission Denied: {res}"
        return results
