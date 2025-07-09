class InvalidParameterError(Exception):
    def __init__(self,message,error_code):
        super().__init__(message)
        self.error_code=error_code
class WrongPasscodeError(Exception):
    def __init__(self,message,error_code):
        super().__init__(message)
        self.error_code=error_code
class ActionNotSupportedError(Exception):
    def __init__(self,message,error_code):
        super().__init__(message)
        self.error_code=error_code
class DeviceOfflineError(Exception):
    def __init__(self,message,error_code):
        super().__init__(message)
        self.error_code=error_code
class AuthenticationError(Exception):
    def __init__(self,message):
        super().__init__(message)
class DuplicateDeviceError(Exception):
    def __init__(self,message,error_code):
        super().__init__(message)
    def __str__(self):
        return "This error has occured because you already added this device"