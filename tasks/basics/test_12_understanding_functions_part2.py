__author__ = 'Hari'

<<<<<<< HEAD
from basics.placeholders import *
=======
from tasks.placeholders import *
>>>>>>> 60a8770156443a866425d99b2119f19709472edd


def demo(first, second=2, third=3):
    return [first, second, third]

# keyword arguments allows you to write one api without having a large number
# of overloads for various scenarios.
# add extra arguments where necessary.
def test_function_call_with_keyword_arguments():
    assert [10,2,3] == demo(10)
    assert [10,20,3] == demo(10, 20)
    assert [10,20,30] == demo(10, 20, 30)
    # assert "TypeError: demo() missing 1 required positional argument: 'first'"== demo(second=20)    #doubt
    # assert [__] == demo(second=20, third=30)
    assert [10,2,30] == demo(first=10, third=30)
    assert [10,2,30] == demo(10, third=30)


def demo_variable_args(first, *args):
    return args


def my_merge(separator, *args):
    return separator.join(args)


def test_function_with_variable_args():
    result = demo_variable_args("hello", "world")
    assert "tuple" == type(result).__name__ #this is the type of args
    assert ("world",) == result              #this is the value of args

    assert (1,2,3) == demo_variable_args("hello", 1, 2, 3)

    assert "one.two.three" == my_merge(".", "one", "two", "three")
    assert "one,two,three" == my_merge(",", "one", "two", "three")


def demo_with_keyword_args(name, *args, **kwargs):
    return kwargs


def test_function_with_keyword_args():
    result = demo_with_keyword_args("jack", age=10, height=100)
    assert "dict" == type(result).__name__
    assert {"age":10,"height":100} == result
    assert {"age":10,"height":100} == demo_with_keyword_args("jack", "address", age=10, height=100)
    assert {"address":"address","age":10,"height":100} == demo_with_keyword_args("jack", address="address", age=10, height=100)


def demo_sub(*args, **kwargs):
    return args, kwargs


def demo_unpacking(name, *args, **kwargs):
    return demo_sub(*args, **kwargs)


def demo_no_unpacking(name, *args, **kwargs):
    return demo_sub(args, kwargs)


def test_function_unpacking():
    result = demo_unpacking("jack", 1, 2, k1="v1", k2="v2")
    assert ((1,2),{"k1":"v1","k2":"v2"}) == result

    result = demo_no_unpacking("jack", 1, 2, k1="v1", k2="v2")
    assert (((1, 2), {'k1': 'v1', 'k2': 'v2'}), {}) == result

    result = demo_sub(1,2, k1="v1")
    assert ((1,2),{"k1":"v1"}) == result

    result = demo_sub((1,2), {"k1" :"v1"})
    assert (((1, 2), {'k1': 'v1'}), {})== result

    result = demo_sub(*(1,2), **{"k1": "v1"})
    assert ((1, 2), {'k1': 'v1'}) == result

    #you can unpack lists as well
    result = demo_sub(*[1,2], **{"k1":"v1"})
    assert ((1, 2), {'k1': 'v1'}) == result



three_things_i_learnt = """
agr,kwargs as arguments, list and dict passing in function, packing using return
"""

time_taken_minutes = 20
