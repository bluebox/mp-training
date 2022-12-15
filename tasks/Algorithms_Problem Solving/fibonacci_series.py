"""Print fibonacci series"""


def fibo(num):
    """Function that returns fibonacci series"""

    if num < 2:
        return num
    return fibo(num - 1) + fibo(num - 2)


n = int(input('Enter a number\n'))
for i in range(n):
    print(fibo(i), end=' ')
