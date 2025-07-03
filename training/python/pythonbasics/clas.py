class A:

    @classmethod
    def some_func(cls):
        print(cls.__name__)


    def random_fun(self):
        print(self.__class__.__name__)


    @staticmethod
    def some_static_fun():
        print("i can be called ")

a = A()
a.some_func() # class_method class as first param
a.random_fun() #instance method object as first param
a.some_static_fun()

A.some_static_fun()
A.some_func()
A.random_fun(a)