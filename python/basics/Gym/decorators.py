def log(func):
    def wrapper():
        print('ajslfjd')
        func()
        print('asdfsdf')

    return wrapper


@log
def solve():
    print("100")

solve()

def add(func):
    def wrapper(a, b):
        func(a, b)
        return a + b
    return wrapper

@add
def find(a, b):
    print(a + b)

find(20, 30)
