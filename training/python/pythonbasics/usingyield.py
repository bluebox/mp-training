def generator():
    for i in range(9):
        yield i
        print("i= ",i)

for j in generator():
    print(j)