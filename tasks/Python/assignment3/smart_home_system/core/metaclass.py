from abc import ABCMeta

class DeviceRegistrarMeta(ABCMeta):
    _registry = {}

    def __new__(cls, name, bases, attrs):
        new_class = super().__new__(cls, name, bases, attrs)
        if name != "SmartDevice":
            cls._registry[name] = new_class
        return new_class

    @classmethod
    def get_device_class(cls, name):
        return cls._registry.get(name)