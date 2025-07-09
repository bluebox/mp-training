class SmartHomeError(Exception):
    pass

class DeviceOfflineError(SmartHomeError):
    pass

class InvalidParameterError(SmartHomeError):
    pass

class ActionNotSupportedError(SmartHomeError):
    pass

class AuthenticationError(SmartHomeError):
    pass

class PermissionDeniedError(SmartHomeError):
    pass
