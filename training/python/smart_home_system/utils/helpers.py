import re
def pattern_match(pattern,data):
    pattern_c=re.compile(pattern)
    return pattern_c.match(data)
def passcode_validate(passcode):
    return re.match(r'^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@#$%]).{8,}$', passcode)