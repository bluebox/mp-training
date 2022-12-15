"""Fibonacci series module"""


def fib(num, lis):
    if num == 1 or num == 2:
        return 1
    if lis[num] != 0:
        return lis[num]
    lis[num] = fib(num-1, lis) + fib(num-2, lis)
    return lis[num]


term = int(input('Enter a number until which fibonacci series will be printed: '))
fib_list = [0] * (term+1)
fib_list[0] = 0
fib_list[1] = fib_list[2] = 1
fib(term, fib_list)
print(fib_list)
