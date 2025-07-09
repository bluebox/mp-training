import re
import json
from SmartHomeAutomationSystem.core.devices import DeviceRegisterMeta

def validate_device_id(device_id):
    if not re.fullmatch(r'[A-Z]\d{3}', device_id):
        raise ValueError("Invalid device id format.")

class SmartHomeJSONEncoder(json.JSONEncoder):
    def default(self, obj):
        if hasattr(obj, "get_status_report"):
            data = obj.get_status_report()
            data["_class"] = obj.__class__.__name__
            return data
        return super().default(obj)

def smart_device_from_dict(data):
    class_name = data.pop("_class", None)
    cls = DeviceRegisterMeta.registry.get(class_name)
    if cls:
        obj = cls(data['id'])
        for key, value in data.items():
            if key != "id" and hasattr(obj, key):
                try:
                    setattr(obj, key, value)
                except Exception:
                    pass
        return obj
    raise ValueError(f"Unknown device class: {class_name}")
