def generate(num):
    for i in range(num):
        yield i

a=generate(100)
print(next(a))
print(next(a))

for i in generate(10):
    print(i,end=' ')

# yield generally pass the function and returns back to the function and then it repeats if needed
# for loop using generate
print()
li=[1,2,3,4]
iterator = iter(li)
while True:
    try:
        item = next(iterator)
        print(item,end=' ')
    except StopIteration:
        print()
        break

li=list(range(20))
it=iter(li)
while True:
    try:
        item=next(it)
        print(item,end=' ')
    except StopIteration:
        break
