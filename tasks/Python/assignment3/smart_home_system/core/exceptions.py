
class SmartHomeError(Exception):
    def __init__(self, message="A Smart Home error occurred"):
        self.message = message
        super().__init__(self.message)

class InvalidParameterError(SmartHomeError):
    def __init__(self, message="Invalid parameter provided"):
        super().__init__(message)

class DeviceOfflineError(SmartHomeError):
    def __init__(self, message="Device is currently offline"):
        super().__init__(message)

class ActionNotSupportedError(SmartHomeError):
    def __init__(self, message="This action is not supported by the device"):
        super().__init__(message)

class AuthenticationError(SmartHomeError):
    def __init__(self, message="Authentication failed"):
        super().__init__(message)

class PermissionDeniedError(SmartHomeError):
    def __init__(self, message="Permission denied for this action"):
        super().__init__(message)