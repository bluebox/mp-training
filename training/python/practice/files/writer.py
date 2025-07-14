data=""

with open("sample","r") as f:
    data = f.read()
with open("sample2","w") as f:
    f.write("Files access modes")
    f.write(data)