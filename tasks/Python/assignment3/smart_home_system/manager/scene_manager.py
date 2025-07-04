import asyncio
class SceneManager:
    def __init__(self):
        self.scenes= {}

    def add_scene(self,scene_name, actions):#actions (device_id, action_type, value)
        self.scenes.setdefault(scene_name,[]).append(actions)

    async def activate_scene(self,home_manager, scene_name, user_role):
        for action in self.scenes[scene_name]:
            device_id, action_type, value = action
            asyncio.run(home_manager.control_device(device_id, action_type, value, user_role))

