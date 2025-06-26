import string
from curses.ascii import isascii

a = "  Hello World  "
# b="123"

print(a.lower())
print(a.strip().capitalize())
print(a.title().swapcase())

print(sorted(a.strip().lower()))

b = "12345654321"
print(b[::-1] == b)

eval("\nprint('eval is not safe') ")

# print(a.zfill(20))
txt = "O"*2
print(txt.center(20,"J"))

print(int("110",2))

print(isascii("q")) #only for a single character

a = '''
hello
new line
3rd line '''

a += "where"
print(a)

print(string.punctuation)
print(len(string.punctuation))
print(len(string.whitespace))

print(len(string.printable))
print(string.ascii_letters == string.ascii_lowercase + string.ascii_uppercase)
