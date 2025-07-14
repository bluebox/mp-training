import json
from core.devicess import SmartDevice,SmartLight, SmartDoorLock, SmartSpeaker, SmartCamera, SmartThermostat

class SmartHomeJSONEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, object) and hasattr(obj, "_device_id"):
            return {
                "__device_type__": obj.__class__.__name__,
                "_device_id": obj._device_id,
                "state": obj.is_on,
                "attributes": obj.__dict__,
            }
        return super().default(obj)


def smart_device_from_dict(d):
    device_type = d.get("__device_type__")
    device_id = d.get("_device_id")
    attributes = d.get("attributes", {})
    
    device_classes = {
        "SmartLight": SmartLight,
        "SmartDoorLock": SmartDoorLock,
        "SmartSpeaker": SmartSpeaker,
        "SmartCamera": SmartCamera,
        "SmartThermostat": SmartThermostat
    }

    if device_type in device_classes:
        cls = device_classes[device_type]
        device = cls(device_id)

        for k, v in attributes.items():
            setattr(device, k, v)
        return device
    else:
        print(f"Unknown device type: {device_type}")
        return None
