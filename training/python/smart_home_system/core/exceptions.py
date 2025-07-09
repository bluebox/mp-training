class SmartHomeError(Exception):
    def __init__(self, message):
        super().__init__(message)

class InvalidParameterError(SmartHomeError):
    def __init__(self,message,error_code):
        super().__init__(message)
        self.error_code=error_code
    def __str__(self):
        return "This error has occured because the ID you enetered is invalid"
# class WrongPasscodeError(SmartHomeError):
#     def __init__(self,message,error_code):
#         super().__init__(message)
#         self.error_code=error_code
#     def __str__(self):
#         return "This error has occured because the credentials you entered is incorrect."
class ActionNotSupportedError(SmartHomeError):
    def __init__(self,message,error_code):
        super().__init__(message)
        self.error_code=error_code
    def __str__(self):
        return "This error has occured because the action you requested is not supported."
class DeviceOfflineError(SmartHomeError):
    def __init__(self,message,error_code):
        super().__init__(message)
        self.error_code=error_code
    def __str__(self):
        return "This error has occured because the device is offline"
class AuthenticationError(SmartHomeError):
    def __init__(self,message,error_code):
        super().__init__(message)
        self.error_code=error_code
    def __str__(self):
        return "This error has occured because you entered the wrong credentials"
class DuplicateDeviceError(SmartHomeError):
    def __init__(self,message,error_code):
        super().__init__(message)
    def __str__(self):
        return "This error has occured because you already added this device"