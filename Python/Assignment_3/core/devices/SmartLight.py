import asyncio

from core.devices.SmartDevice import SmartDevice
from core.exceptions import ActionNotSupportedError, DeviceOfflineError


class SmartLight(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self._brightness = 0

    @property
    def brightness(self):
        return self._brightness

    @brightness.setter
    def brightness(self, level):
        try:
            if self.is_on():
                if 0 <= level <= 100:
                    self._brightness = level
                    print(f"Brightness of {self._device_id} set to {level}")
                else:
                    print("Error: Brightness level must be between 0 and 100.")
            else:
                raise DeviceOfflineError()
        except DeviceOfflineError as e:
            print("ERROR", e)

    def get_status_report(self):
        return f"SmartLight {self._device_id}: ON={self.is_on()}, Brightness={self._brightness}"

    async def perform_action(self, action_type, value=None):
        try:
            if action_type == "set_brightness":
                self.brightness = value
            else:
                raise ActionNotSupportedError()
        except ActionNotSupportedError as e:
            print('Error ', e)

    def get_supported_actions(self):
        return "set_brightness"


async def main():
    a = SmartLight('L001')
    print(a.get_supported_actions())
    print(a.get_system_time())
    print(a.get_status_report())
    await a.turn_on()
    a.perform_action('set_brightness', 20)
    print(a.get_status_report())


if __name__ == '__main__':
    asyncio.run(main())
