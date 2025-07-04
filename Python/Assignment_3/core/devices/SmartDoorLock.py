from core.devices.SmartDevice import SmartDevice, DeviceRegistrarMeta
from utils.decorators import require_device_on
from utils.helper import passcode_validate
from core.exceptions import InvalidPasscodeError, ActionNotSupportedError, DeviceOfflineError
import asyncio


class SmartDoorLock(SmartDevice):
    def __init__(self, device_id, passcode):
        super().__init__(device_id)
        if not passcode_validate(passcode):
            raise InvalidPasscodeError()
        self._passcode = passcode
        self._lock = False

    @property
    def passcode(self):
        return self._passcode

    @passcode.setter
    @require_device_on
    def passcode(self, passcode):
        if not passcode_validate(passcode):
            print("ERROR:", InvalidPasscodeError())
            return
        self._passcode = passcode

    @property
    def lock(self):
        return self._lock

    @lock.setter
    @require_device_on
    def lock(self, value):
        self._lock = value

    def get_status_report(self):
        return f"SmartDoorLock {self._device_id}: ON={self.is_on()}, LOCKED={self.lock}"

    async def perform_action(self, action_type, current_passcode=None, new_passcode=None):
        if new_passcode==None:
            new_passcode=current_passcode
        try:
            if action_type == 'change_passcode':
                if current_passcode == self.passcode:
                    self.passcode = new_passcode
                    print(f'passcode of {self._device_id} is change to {new_passcode}')
                else:
                    print("Enter correct passcode to change the passcode")
            elif action_type == 'change_lock':
                if current_passcode == self.passcode:
                    self.lock = not self.lock
                    print(f'the device with id {self._device_id} is changed')
                else:
                    print('Enter correct passcode to change the lock')
            else:
                raise ActionNotSupportedError()
        except ActionNotSupportedError as e:
            print('ERROR:', e)

    def get_supported_actions(self):
        return ['change_passcode', 'change_lock']


# Example Usage
if __name__ == '__main__':
    a = SmartDoorLock('l001', '12345@Aa')
    print(a.get_supported_actions())
    a.turn_on()
    a.lock = False
    a.perform_action('change_passcode', 'sfsdfsf')
    a.perform_action('change_lock', '12345@Aa')
    print(a.get_status_report())
    a.perform_action('change_lock', '12345@Aa')
    print(a.get_status_report())
    a.turn_off()
    a.perform_action('change_lock', '12345@Aa')
    print(a.get_status_report())
