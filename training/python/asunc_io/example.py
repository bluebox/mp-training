
def func(a):
    try:
        # x = yield
        for i in range(a):
            yield i
            x = yield
            print("in func :: ",x)
    except GeneratorExit:
        pass
x = func(10)
next(x)
for i in func(10):
    x.send(i)
    print("in outer for:: ",i)
