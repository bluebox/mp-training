import re
def pattern_match(pattern,data):
    pattern_c=re.compile(pattern)
    return pattern_c.match(data)