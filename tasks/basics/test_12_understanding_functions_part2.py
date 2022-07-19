__author__ = 'Hari'

<<<<<<< HEAD

#from tasks.placeholders import *
=======
from tasks.placeholders import *
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


def demo(first, second=2, third=3):
    return [first, second, third]

# keyword arguments allows you to write one api without having a large number
# of overloads for various scenarios.
# add extra arguments where necessary.
def test_function_call_with_keyword_arguments():
    assert [__] == demo(10)
    assert [__] == demo(10, 20)
    assert [__] == demo(10, 20, 30)
    assert [__] == demo(second=20)
    assert [__] == demo(second=20, third=30)
    assert [__] == demo(first=10, third=30)
    assert [__] == demo(10, third=30)


def demo_variable_args(first, *args):
    return args


def my_merge(separator, *args):
    return separator.join(args)


def test_function_with_variable_args():
    result = demo_variable_args("hello", "world")
    assert __ == type(result).__name__ #this is the type of args
    assert (__) == result              #this is the value of args

    assert (__) == demo_variable_args("hello", 1, 2, 3)

    assert __ == my_merge(".", "one", "two", "three")
    assert __ == my_merge(",", "one", "two", "three")


def demo_with_keyword_args(name, *args, **kwargs):
    return kwargs


def test_function_with_keyword_args():
    result = demo_with_keyword_args("jack", age=10, height=100)
    assert __ == type(result).__name__
    assert {__} == result
    assert {__} == demo_with_keyword_args("jack", "address", age=10, height=100)
    assert {__} == demo_with_keyword_args("jack", address="address", age=10, height=100)


def demo_sub(*args, **kwargs):
    return args, kwargs


def demo_unpacking(name, *args, **kwargs):
    return demo_sub(*args, **kwargs)


def demo_no_unpacking(name, *args, **kwargs):
    return demo_sub(args, kwargs)


def test_function_unpacking():
    result = demo_unpacking("jack", 1, 2, k1="v1", k2="v2")
    assert (__) == result

    result = demo_no_unpacking("jack", 1, 2, k1="v1", k2="v2")
    assert (__) == result

    result = demo_sub(1,2, k1="v1")
    assert (__) == result

    result = demo_sub((1,2), {"k1" :"v1"})
    assert (__) == result

    result = demo_sub(*(1,2), **{"k1": "v1"})
    assert (__) == result

    #you can unpack lists as well
    result = demo_sub(*[1,2], **{"k1":"v1"})
    assert (__) == result



THREE_THINGS_I_LERNT = """
passing multiple arguments
using keyword arguments
-
"""

<<<<<<< HEAD
TIME_TAKEN_MINUTES = 40
=======
time_taken_minutes = ___
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
