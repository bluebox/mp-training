class SceneManager:
    scenes = {'morning':[("Light1","set_brightness",20)]}
    def add_scene(self,scene_name,actions):
        self.scenes[scene_name] = list(actions)
    async def activate_scene(self,home_manager,scene_name,user_role):
        actions = self.scenes[scene_name]
        for device_id,action,val in actions:
            await home_manager.control_device(user_role,device_id,action,val)

