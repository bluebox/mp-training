import re

pattern=re.compile(r'\d{10}')

match=pattern.search('97016749447998798')
if match:
    print(match.group())

pattern=re.compile(r'^a.*b$')
match=pattern.search('asdljfsldfjsdlfjldsjflsfb')
if match:
    print(match.group())