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
        if not self.is_on():
            raise DeviceOfflineError("Device is offline")
        if 0 <= level <= 100:
            self._brightness = level
            print(f"Brightness of {self._device_id} set to {level}")
        else:
            raise ValueError("Enter a valid brightness value (0-100)")

    def get_status_report(self):
        return f"SmartLight {self._device_id}: ON={self.is_on()}, Brightness={self._brightness}"

    async def perform_action(self, action_type, value=None):
        if action_type == "set_brightness":
            self.brightness = value
        else:
            raise ActionNotSupportedError(f"Action '{action_type}' is not supported for SmartLight")

    def get_supported_actions(self):
        return "set_brightness"


async def main():
    a = SmartLight('L001')
    print("Supported Actions:", a.get_supported_actions())
    print("System Time:", a.get_system_time())
    print("Status Report:", a.get_status_report())

    await a.turn_on()
    await a.perform_action('set_brightness', 20)

    print("Status After Brightness Change:", a.get_status_report())


if __name__ == '__main__':
    asyncio.run(main())
