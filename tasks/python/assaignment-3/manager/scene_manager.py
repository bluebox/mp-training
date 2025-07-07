import asyncio
from core.exceptions import PermissionDeniedError, DeviceNotFoundError, SmartHomeError
from manager.home_manager import HomeManager
from core.devices import SmartLight, SmartCamera

class SceneManager:
    _scenes = {
        "morning": [("L001", "set_brightness", 20)],
        "night": [("C001", "stop_recording", None), ("L001", "set_brightness", 10)]
    }

    def add_scene(self, scene_name, actions):
        self._scenes[scene_name] = list(actions)

    async def activate_scene(self, home_manager, scene_name, user_role="admin"):
        if scene_name not in self._scenes:
            raise KeyError(scene_name)

        for device_id, action, val in self._scenes[scene_name]:
            try:
                await home_manager.control_device(user_role, device_id, action, val)
            except PermissionDeniedError as e:
                print(f"[Scene Warning] {e}")
                raise
            except Exception as e:
                print(f"[Scene Error] Failed action ({action}) on {device_id}: {e}")
                raise


async def main():
    manager = HomeManager()
    manager.add_device(SmartLight("L001"))
    manager.add_device(SmartCamera("C001"))
    scene = SceneManager()
    scene.add_scene("evening", [("L001", "set_brightness", 60), ("C001", "start_recording", None)])
    await scene.activate_scene(manager, "evening", user_role="admin")
    manager.get_all_device_statuses()

if __name__ == "__main__":
    asyncio.run(main())
