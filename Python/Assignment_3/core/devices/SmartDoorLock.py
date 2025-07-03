from core.devices.SmartDevice import SmartDevice
from utils.decorators import require_device_on
from utils.helper import passcode_validate
from core.exceptions import InvalidPasscodeError, ActionNotSupportedError, DeviceOfflineError


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

    def perform_action(self, action_type, value=None):
        try:
            if action_type == 'change_passcode':
                self.passcode = value
            elif action_type == 'change_lock':
                self.lock = not self.lock
            else:
                raise ActionNotSupportedError()
        except ActionNotSupportedError as e:
            print('ERROR:', e)

    def get_supported_actions(self):
        return ['change_passcode', 'change_lock']


# Example Usage
if __name__ == '__main__':
    a = SmartDoorLock('l001', 'Slfj@123')
    print(a.get_supported_actions())
    a.turn_on()
    a.perform_action('change_passcode', 'adflsdjlf')
    a.perform_action('change_lock')
    print(a.get_status_report())
    a.perform_action('change_lock')
    print(a.get_status_report())
    a.turn_off()
    a.perform_action('change_lock')
    print(a.get_status_report())
