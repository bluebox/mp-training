from core.devices.SmartDevice import SmartDevice
from core.exceptions import ActionNotSupportedError
from utils.decorators import require_device_on
import asyncio


class SmartSpeaker(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__volume = 0
        self.__track = None

    @property
    def volume(self):
        return self.__volume

    @volume.setter
    @require_device_on
    def volume(self, value):
        if value >= 0 and value <= 100:
            self.__volume = value
            print(f"Volume of {self._device_id} set to {value}")
        else:
            print("enter a valid range of volume in 1-100")

    @property
    def track(self):
        return self.__track

    @track.setter
    @require_device_on
    def track(self, track):
        self.track = track

    def get_status_report(self):
        return f"SmartSpeaker {self._device_id}: ON={self.is_on()}, Volume={self.__volume}"

    def get_supported_actions(self):
        return ['set_volume', 'change_track']

    async def perform_action(self, action_type, value=None):
        try:
            if action_type == 'change_track':
                self.track = value
            elif action_type == 'set_volume':
                self.volume = value
            else:
                raise ActionNotSupportedError()
        except ActionNotSupportedError as e:
            print("ERROR", e)


if __name__ == '__main__':
    a = SmartSpeaker('l0012')
    a.turn_on()
    a.turn_on()
    a.perform_action('change_passcode')
    print(a.get_supported_actions())
    a.perform_action('set_volume', 80)
    a.perform_action('set_volume', 2000)
    print(a.volume)
