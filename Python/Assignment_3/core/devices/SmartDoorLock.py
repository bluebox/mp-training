import asyncio
from core.devices.SmartDevice import SmartDevice, DeviceRegistrarMeta
from utils.decorators import require_device_on
from utils.helper import passcode_validate
from core.exceptions import InvalidPasscodeError,ActionNotSupportedError,DeviceOfflineError,AuthenticationError


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
        if new_passcode is None:
            new_passcode = current_passcode

        if action_type == 'change_passcode':
            if current_passcode == self.passcode:
                self.passcode = new_passcode
                print(f'Passcode of {self._device_id} changed to {new_passcode}')
            else:
                raise AuthenticationError(f"Incorrect passcode for changing passcode on {self._device_id}")

        elif action_type == 'change_lock':
            if current_passcode == self.passcode:
                self.lock = not self.lock
                print(f'Lock status of device {self._device_id} toggled.')
            else:
                raise AuthenticationError(f"Incorrect passcode for changing lock on {self._device_id}")

        else:
            raise ActionNotSupportedError(f"Action '{action_type}' is not supported for SmartDoorLock")

    def get_supported_actions(self):
        return ['change_passcode', 'change_lock']


async def main():
    a = SmartDoorLock('l001', '12345@Aa')
    print(a.get_supported_actions())
    await a.turn_on()
    a.lock = False

    try:
        await a.perform_action('change_passcode', 'sfsdfsf')
    except AuthenticationError as e:
        print("ERROR:", e)

    await a.perform_action('change_lock', '12345@Aa')
    print(a.get_status_report())

    await a.perform_action('change_lock', '12345@Aa')
    print(a.get_status_report())

    await a.turn_off()

    try:
        await a.perform_action('change_lock', '12345@Aa')
    except DeviceOfflineError as e:
        print("ERROR:", e)

    print(a.get_status_report())


if __name__ == '__main__':
    asyncio.run(main())
