from core.devices.SmartDevice import SmartDevice
from core.exceptions import ActionNotSupportedError, DeviceOfflineError
from utils.decorators import require_device_on
import asyncio


class SmartThermostat(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__temperature = 20.0

    @property
    def temperature(self):
        return self.__temperature

    @temperature.setter
    @require_device_on
    def temperature(self, temp):
        if 18.0 <= temp <= 30.0:
            self.__temperature = temp
            print(f"Temperature of {self._device_id} set to {temp}")
        else:
            print("Error: Temperature must be between 18 and 30 Celsius.")

    def get_status_report(self):
        return f"SmartThermostat {self._device_id}: ON={self.is_on()}, Temperature={self.__temperature}"

    async def perform_action(self, action_type, value=None):
        try:
            if action_type == "set_temperature":
                self.temperature = value
            else:
                raise ActionNotSupportedError()
        except ActionNotSupportedError as e:
            print("ERROR", e)

    def get_supported_actions(self):
        return "set_temperature"

async def main():
    a = SmartThermostat('l001')
    print(a.get_supported_actions())
    print(a.get_system_time())
    print(a.get_status_report())
    await a.turn_on()
    await a.perform_action('set_temperature', 20)
if __name__ == '__main__':
    asyncio.run(main())