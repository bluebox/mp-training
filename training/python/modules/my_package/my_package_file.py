def my_python_function(*args,**kwargs):
    sol = 0
    for i in args:
        sol+=i
    for i in kwargs:
        sol+=kwargs[i]
    return sol

class MyFilter:
    def __init__(self,func,predicate):
        self.func = func
        self.predicate = predicate
    def my_coroutine_filter(self):
        try:
            func1 = self.func
            next(func1)
            while(True):
                val = yield
                if self.predicate(val):
                    func1.send(val)
        except GeneratorExit:
            func1.close()


