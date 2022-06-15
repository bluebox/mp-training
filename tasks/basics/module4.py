__author__ = 'Hari'

#__all__ can be used to specify what the publicly available functions are when you do an * import
# This affects from module import *
__all__ = ["m4_func1", "_m4_func3"]

def m4_func1():
    pass

def m4_func2():
    pass

def _m4_func3():
    pass