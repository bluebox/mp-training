class DeviceRegisterMeta(type):
    device_register = set()
    def __new__(cls, name, bases,dct):
        if(name not in cls.device_register):
            cls.device_register.add(name)
