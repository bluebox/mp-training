def factorial(num):
    if(num == 1):
        return 1
    return factorial(num-1)*num

n = int(input('Enter a number: '))
print(factorial(n))