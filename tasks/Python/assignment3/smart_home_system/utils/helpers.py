import json
import re

def smart_home_json_encoder(data,filename):
    with open(filename,"w+") as f1:
        a = json.dumps(data)
        f1.write(a)

def smart_device_from_dict(filename):
    data = ""
    with open(filename,"r+") as f2:
        json_data = f2.read()
        data = json.loads(json_data)
    if data:
        return data
    return "Error in smart_device_from_dict"

def validate_id(device_id):
    valid = bool(re.match(r"^[LTDCSA][0-9]{3}$",device_id))
    if valid:
        print("valid device id")
        return valid
    print("Invalid device_id")
    return valid