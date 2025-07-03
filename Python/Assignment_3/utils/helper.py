import re

def device_id_validate(device_id):
    return re.fullmatch(r'[a-zA-Z0-9]+', device_id)

def passcode_validate(passcode):
    return re.match(r'^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@#$%]).{8,}$', passcode)