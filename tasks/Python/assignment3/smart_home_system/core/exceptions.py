
class InvalidParameterError(Exception):
    def __init__(self, message = "Invalid Parameter raised an Error"):
        self.message = message
        super().__init__(self.message)

class DeviceOfflineError(Exception):
    def __init__(self, message = "Device is offline raised an error"):
        self.message = message
        super().__init__(self.message)

class ActionNotSupportedError(Exception):
    def __init__(self, message = "This action is not supported in this device"):
        self.message = message
        super().__init__(self.message)

class AuthenticationError(Exception):
    def __init__(self, message = "Error in Authentication"):
        self.message = message
        super().__init__(self.message)

class PermissionDeniedError(Exception):
    def __init__(self, message = "Permission is denied"):
        self.message = message
        super().__init__(self.message)

class SmartHomeError(Exception):
    def __init__(self, message = "error in inheriting"):
        self.message = message
        super().__init__(self.message)

