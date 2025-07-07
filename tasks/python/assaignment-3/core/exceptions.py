class SmartHomeError(Exception):
    pass

class InvalidParameterError(SmartHomeError, ValueError):
    pass


class DuplicateDeviceError(SmartHomeError):
    pass


class DeviceNotFoundError(SmartHomeError, LookupError):
    pass



class PermissionDeniedError(SmartHomeError, PermissionError):
    pass


class UnsupportedActionError(SmartHomeError):
    pass


class DeviceStateError(SmartHomeError):
    pass
