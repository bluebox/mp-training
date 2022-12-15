def is_prime(num):
    for i in range(2, num//2):
        if num%i == 0:
            return False

    return True


x = int(input('Enter a number: '))
if is_prime(x):
    print(f'{x} is a prime')
else:
    print(f'{x} is not a prime')