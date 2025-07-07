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
