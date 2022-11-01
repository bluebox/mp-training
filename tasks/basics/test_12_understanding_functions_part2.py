"""MODULE-2 FOR UNDERSTANDING FUNCTIONS"""

__author__ = 'Hari'

# from tasks.placeholders import *


def demo(first, second=2, third=3):
    """demo helper function"""
    return [first, second, third]

# keyword arguments allows you to write one api without having a large number
# of overloads for various scenarios.
# add extra arguments where necessary.


def test_function_call_with_keyword_arguments():
    """test function call with keyword arguments"""
    assert [10, 2, 3] == demo(10)
    assert [10, 20, 3] == demo(10, 20)
    assert [10, 20, 30] == demo(10, 20, 30)
    assert [1, 20, 3] == demo(1, second=20)
    assert [1, 20, 30] == demo(1, second=20, third=30)
    assert [10, 2, 30] == demo(first=10, third=30)
    assert [10, 2, 30] == demo(10, third=30)


def demo_variable_args(first, *args):
    """demo variable args"""
    return args


def my_merge(separator, *args):
    """my merge helper function"""
    return separator.join(args)


def test_function_with_variable_args():
    """test function with variable args"""
    result = demo_variable_args("hello", "world")
    assert 'tuple' == type(result).__name__  # this is the type of args
    assert ('world',) == result              # this is the value of args

    assert (1, 2, 3) == demo_variable_args("hello", 1, 2, 3)

    assert 'one.two.three' == my_merge(".", "one", "two", "three")
    assert 'one,two,three' == my_merge(",", "one", "two", "three")


def demo_with_keyword_args(name, *args, **kwargs):
    """demo with keyword args"""
    return kwargs


def test_function_with_keyword_args():
    """test function with keyword args"""
    result = demo_with_keyword_args("jack", age=10, height=100)
    assert 'dict' == type(result).__name__
    assert {'age': 10, 'height': 100} == result
    assert {'age': 10, 'height': 100} == demo_with_keyword_args("jack", "address", age=10, height=100)
    assert {'address': 'address', 'age': 10, 'height': 100} == demo_with_keyword_args("jack", address="address", age=10, height=100)


def demo_sub(*args, **kwargs):
    """demo sub"""
    return args, kwargs


def demo_unpacking(name, *args, **kwargs):
    """demo unpacking"""
    return demo_sub(*args, **kwargs)


def demo_no_unpacking(name, *args, **kwargs):
    """demo no unpacking"""
    return demo_sub(args, kwargs)


def test_function_unpacking():
    """test function unpacking"""
    result = demo_unpacking("jack", 1, 2, k1="v1", k2="v2")
    print(result)
    assert ((1, 2), {'k1': 'v1', 'k2': 'v2'}) == result

    result = demo_no_unpacking("jack", 1, 2, k1="v1", k2="v2")
    assert (((1, 2), {'k1': 'v1', 'k2': 'v2'}), {}) == result

    result = demo_sub(1, 2, k1="v1")
    assert ((1, 2), {'k1': 'v1'}) == result

    result = demo_sub((1,2), {"k1" :"v1"})
    assert (((1, 2), {'k1': 'v1'}), {}) == result

    result = demo_sub(*(1, 2), **{"k1": "v1"})
    assert ((1, 2), {'k1': 'v1'}) == result

    # you can unpack lists as well
    result = demo_sub(*[1,2], **{"k1":"v1"})
    assert ((1, 2), {'k1': 'v1'}) == result


THREE_THINGS_I_LEARNT = """
- keyword arguments allows you to write one api without having a large number of overloads for various scenarios. Add extra arguments where necessary.
- We can have functions with variable arguments and we can also have variable keyword arguments and they are represented by * and ** beside them
- The variable arguments are stored inside a tuple and the variable keyword are stored inside dict and we can easily pack and unpack them.
"""

TIME_TAKEN_MINUTES = 10
