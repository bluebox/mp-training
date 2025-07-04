import asyncio

class SceneManager:
    def __init__(self):
        self._scenes = {}

    def add_scene(self, scene_name, actions):
        self._scenes[scene_name] = actions
        #actions (device_id, action_type, value).
    async def activate_scene(self, home_manager, scene_name, user_role):
        if scene_name not in self._scenes:
            return

        actions = self._scenes[scene_name]
        tasks = []
        print(actions)
        for device_id, action_type, value in actions:
            task = home_manager.control_device(user_role, device_id, action_type, value)
            tasks.append(task)

        await asyncio.gather(*tasks)
