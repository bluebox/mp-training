def passable():
    print("hi from passable")

def outer_func(func):
    print("outer func")
    def wrapper():
        print("wrapper")
        func()
        return
    return wrapper

passable = outer_func(passable)
passable()

@outer_func
def passable2():
    print("hi from passable2")
passable2()

#decorator with arguments
def outerfunc1(args):
    def outerfunc(func):
        def wrapper(a):
            print("wrapper")
            for i in range(args):
                func(a)
            return
        return wrapper
    return outerfunc
@outerfunc1(2)
def func1(a):
    print(a)


func1("hello")

class Wrappers:
    @staticmethod
    def wrapper1(func):
        def inner_funct(arg):
            func(arg)
        return inner_funct
    def wrapper2(self,func):
        def inner_funct(arg):
            func(arg)
        return inner_funct

@Wrappers.wrapper1
def funct(i):
    print("in funct ",i)

funct(100)

wrap = Wrappers()
@wrap.wrapper2
def funct2(i):
    print("in funct2 ",i)

funct2(100)