
import asyncio
class SceneManager:
    def __init__(self):
        self.action_dict={}
    def add_scene(self,scene_name, *actions):
         self.action_dict[scene_name]=actions #device_id,action_type,values
    async def activate_scene(self,home_manager_instance,scene_name,user_role):
        if scene_name in self.action_dict:
            for z in self.action_dict[scene_name]:
                i, j, k = z
                await  asyncio.sleep(2)
                await home_manager_instance.control_device(i, j, k, user_role)




