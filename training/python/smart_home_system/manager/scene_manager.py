import asyncio

class SceneManager:
    def __init__(self):
        self._scenes = {}

        self.roles = {
            "Admin": ["turn_on", "turn_off", "set_brightness", "set_volume", "play_track", "start_recording", "stop_recording", "set_resolution", "set_temperature", "lock", "unlock"],
            "User": ["turn_on", "turn_off", "set_brightness", "set_volume", "play_track", "set_temperature", "lock",
                     "unlock", "start_recording", "stop_recording", "set_resolution"],
            "Guest": ["turn_on", "turn_off"]
        }

    def add_scene(self, scene_name, actions):

        if scene_name in self._scenes:
            print(f"Scene '{scene_name}' already exists.overwriting")
        self._scenes[scene_name] = actions
        print(f"Scene '{scene_name}' added with {len(actions)} actions")

    async def activate_scene(self, home_manager, scene_name, user_role):
        if scene_name not in self._scenes:
            print(f"Scene '{scene_name}' does not exist")
            return

        allowed_actions = self.roles.get(user_role, [])
        scene_actions = self._scenes[scene_name]

        tasks = []
        for device_id, action_type, value in scene_actions:
            if action_type not in allowed_actions:
                print(f"Role '{user_role}' is not allowed to perform '{action_type}' on {device_id}")
                continue
            tasks.append(home_manager.control_device(user_role,device_id, action_type, value))

        await asyncio.gather(*tasks)
        print(f"Scene '{scene_name}' activated for role '{user_role}'")