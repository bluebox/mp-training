import re

pattern=re.compile(r'[a-zA-Z0-9-+.%]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}')
match=pattern.match("a@yahoo.com")
if match:
    print("this is a valid email")
else :
    print("enter a valid email address")