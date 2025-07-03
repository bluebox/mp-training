from functools import wraps
class CustomMeta(type):
    def __new__(cls, name, bases,dct):
        print(name)
        print(bases)
        print(dct)
        if "func" not in dct:
            raise TypeError(f"{name} class has no attribute func")
        a = dct["func"]
        @wraps(a)
        def loggs(*args,**kwargs):
            print("this is logging")
            return a(args,kwargs)
        dct["func"] = loggs
        return super().__new__(cls,name,bases,dct)
class MyClass(metaclass=CustomMeta):
    def func(self,*args,**kwargs):
        print("hello")
clas = MyClass()
clas.func()