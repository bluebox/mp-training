fib = lambda x: x if x < 2 else fib(x-1)+fib(x-2)

n = int(input('Enter a number: '))
# print(fib(n))
lst = []
for i in range(n+1):
    if i < 2:
        lst.append(i)
    else:
        lst.append(lst[i-2]+lst[i-1])

print(lst)