from abc import ABCMeta
class DeviceRegisterMeta(ABCMeta):
    device_register = set()
    def __new__(cls, name, bases, dct):
        new_cls = super().__new__(cls, name, bases, dct)
        cls.device_register.add(name)
        return new_cls

