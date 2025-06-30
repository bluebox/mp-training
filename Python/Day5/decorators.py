from time import time


def calculate_time(func):
    def wrapper():
        t1=time()
        func()
        t2=time()-t1
        print(round(t2,2))
    return wrapper

@calculate_time
def solve():
    for i in range(100000000):
        pass

solve()
#by @calculate_time this solve becomes part of calculate_time decarator
# behind the hood

a=calculate_time(solve)
a()

def calculate(func):
    def wrapper(*args,**kwargs):
        t1 = time()
        func(*args,**kwargs)
        t2 = time() - t1
        print(round(t2, 2))
    return wrapper
@calculate
def solve_raw(x):
    for i in range(x):
        pass
solve_raw(100000000)
solve_raw(100000000)