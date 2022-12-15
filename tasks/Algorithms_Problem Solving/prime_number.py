"""Check Prime"""


def check_prime(ele):
    """Check Prime or not"""
    for i in range(2, int(ele/2) + 1):
        if ele % i == 0:
            print("Not a prime number")
            break
    else:
        print("Prime number")


n = int(input("Enter a number greater than 1\n"))
check_prime(n)
