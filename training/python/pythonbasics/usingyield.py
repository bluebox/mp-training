def generator():
    for i in range(9):
        yield i
        print("i= ",i)

for i in generator():
    print(i)