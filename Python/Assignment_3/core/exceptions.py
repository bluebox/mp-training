class InvalidParameterError(Exception):
    def __init__(self, message="Device ID format is invalid"):
        self.message = message
        super().__init__(self.message)


class InvalidPasscodeError(Exception):
    def __init__(self, message='Enter a valid Passcode'):
        self.message = message
        super().__init__(self.message)


class ActionNotSupportedError(Exception):
    def __init__(self, message="Enter a valid Action"):
        super().__init__(message)


class DeviceOfflineError(Exception):
    def __init__(self, message="Firstly Turn On the Device to perform Action"):
        super().__init__(message)
