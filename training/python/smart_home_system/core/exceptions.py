

class SmartHomeError(Exception):
    pass

class DeviceOfflineError(SmartHomeError):
    def __init__(self, device_id):
        super().__init__(f"Device {device_id} is offline")

class InvalidParameterError(SmartHomeError):
    def __init__(self, message="Invalid parameter provided"):
        super().__init__(message)

class ActionNotSupportedError(SmartHomeError):
    def __init__(self, action, device):
        super().__init__(f"Action '{action}' not supported by device {device} ")

class AuthenticationError(SmartHomeError):
    def __init__(self, message="Authentication failed"):
        super().__init__(message)

class PermissionDeniedError(SmartHomeError):
    def __init__(self, role, action):
        super().__init__(f"Permission denied: Role '{role}' cannot perform {action} ")

class DuplicateDeviceId(Exception):
    def __init__(self, message="Duplicate device ID detected"):
        super().__init__(message)

class InvalidPasswordError(Exception):
    pass
