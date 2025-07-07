import asyncio

from core.devices.SmartDevice import SmartDevice
from core.exceptions import ActionNotSupportedError, DeviceOfflineError
from utils.decorators import require_device_on


class SmartCamera(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self._is_recording = False
        self._resolution = "1080p"

    @require_device_on
    def start_recording(self):
        if self._is_recording:
            print("Error: SmartCamera is already recording")
        else:
            self._is_recording = True
            print("SmartCamera recording is On")

    def stop_recording(self):
        if not self.is_on():
            raise DeviceOfflineError()
        self._is_recording = False
        print("SmartCamera recording is Off")

    @property
    def resolution(self):
        return self._resolution

    @resolution.setter
    @require_device_on
    def resolution(self, res):
        self._resolution = res
        print(f'resolution of {self._device_id} is set to {res}')

    def get_status_report(self):
        return f"SmartCamera {self._device_id}: ON={self.is_on()}, RECORDING={self._is_recording}, RESOLUTION={self._resolution}"

    async def perform_action(self, action_type, value=None):
        try:
            if action_type == "start_recording":
                self.start_recording()
            elif action_type == "stop_recording":
                self.stop_recording()
            elif action_type == "set_resolution":
                self.resolution = value
            else:
                raise ActionNotSupportedError()
        except ActionNotSupportedError as e:
            print("ERROR:", e)

    def get_supported_actions(self):
        return ["start_recording", "stop_recording", "set_resolution"]

async def main():
    a = SmartCamera('l001')
    print(a.get_status_report())
    await a.perform_action('set_resolution', '2048p')
    await a.turn_on()
    await a.perform_action("start_recording")
    await a.perform_action("start_recording")
    await a.perform_action("stop_recording")
    await a.perform_action('set_resolution', '2048p')
    print(a.get_status_report())
if __name__ == '__main__':
    asyncio.run(main())
