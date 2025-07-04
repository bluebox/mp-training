from functools import wraps
class CustomMeta(type):
    class_name = ""
    device_types = []
    def __new__(cls, name, bases,dct):
        print(name)
        print(bases)
        print(dct)
        cls.device_types.append(name)
        print(cls.device_types)
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
class MyClass1(metaclass=CustomMeta):
    def func(self):
        print("hello from class1")
clas = MyClass()
# clas.func()
print(clas.__class__.__name__)