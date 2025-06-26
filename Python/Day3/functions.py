import random


def print_hello():
    print("hello world")
    # function without parameter and without return type


print_hello()


def return_random_val():
    return random.randint(0, 100)
    # function without parameter and with return type


print(return_random_val())


def sum(a, b):
    print(a + b)
    # function with parameter and without return type

sum(10, 20)

def add(a,b):
    return a+b
    # function with parameter and with return type
print(add(20,30))


# we  have function with default parameter they are

def solve(name="anand"):
    print(name)

solve() # this print anand
solve("abhi") # this print abhi and we can we have many defalut parameter as well
