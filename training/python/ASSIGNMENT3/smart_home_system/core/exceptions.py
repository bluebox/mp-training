class SmartHomeError(Exception):
    # print("it is  a SmartHomeErrr exception")
    pass

class InvalidParameterError(SmartHomeError):
    # print("The parameters given are not valid !!!!!")
    pass


class ActionNotSupportedError(SmartHomeError):
    # print("error during performing the action since Invalid action type!!!")
    pass


class AuthenticationError(SmartHomeError):
    # print("Invalid privileges!!!so it raises AuthenticationError")
    pass


class DeviceOfflineError(SmartHomeError):
    # print("Device is in off mode so it raises DeviceOfflineError")

    pass


class PermissionDeniedError(SmartHomeError):
    pass