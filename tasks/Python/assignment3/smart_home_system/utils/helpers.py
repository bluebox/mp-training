import json
import re

from smart_home_system.core.exceptions import InvalidParameterError
from smart_home_system.core.metaclass import DeviceRegistrarMeta

def smart_home_json_encoder(data,filename):
    with open(filename,"w+") as f1:
        a = json.dumps(data)
        f1.write(a)

def smart_device_from_dict(filename):
    try:
        with open(filename,"r+") as f2:
            json_data = f2.read()
            data = json.loads(json_data)
    except FileNotFoundError:
        print(f"[ERROR] File '{filename}' not found.")
        raise InvalidParameterError()

    devices = []
    for device_id, props in data.items():
        class_name = props.get("class_name")
        is_on = props.get("is_on", False)

        cls = DeviceRegistrarMeta.get_device_class(class_name)
        if cls:
            obj = cls(device_id)
            obj._restore_on_state = is_on
            devices.append(obj)
        else:
            print(f"Class '{class_name}' not found in registry. Skipping device '{device_id}'.")

    print(f"[INFO] Loaded {len(devices)} device(s) from '{filename}'.")
    return devices

def validate_id(device_id):
    valid = bool(re.match(r"^[LTDCSA][0-9]{3}$", device_id))
    if valid:
        print(f" Device ID '{device_id}' is valid.")
    else:
        print(f" Device ID '{device_id}' is invalid.")
    return valid