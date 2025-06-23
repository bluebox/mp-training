def generator(a):
    for i in range(a):
        yield i

for i in generator(10):
    print(i)

def generator2(n):
    n+=1
    yield n
    n+=1
    yield n
    n+=1
    yield n

gen = generator2(10)
print(next(gen))
print(next(gen))
print(next(gen))

l = [1,2,3,4]
itr = iter(l)
print(next(itr))