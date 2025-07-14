
f = open("sample","r+")
print(f.readline())
f.seek(0)
print(f.readlines())
f.seek(0)
print(f.read())

