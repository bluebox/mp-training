"""__author__ = 'Hari' 12th exercise"""


def demo(first, second=2, third=3):
    """understanding behavior of functions"""
    return [first, second, third]


# keyword arguments allows you to write one api without having a large number
# of overloads for various scenarios.
# add extra arguments where necessary.
def test_function_call_with_keyword_arguments():
    """understanding behavior of functions"""
    assert [10, 2, 3] == demo(10)
    assert [10, 20, 3] == demo(10, 20)
    assert [10, 20, 30] == demo(10, 20, 30)
    assert [10, 30, 3] == demo(10, second=20)
    assert [100, 20, 30] == demo(100, second=20, third=30)
    assert [10, 2, 30] == demo(first=10, third=30)
    assert [10, 2, 30] == demo(10, third=30)


def demo_variable_args(first, *args):
    """understanding behavior of functions"""
    return args


def my_merge(separator, *args):
    """understanding behavior of functions"""
    return separator.join(args)


def test_function_with_variable_args():
    """understanding behavior of functions"""
    result = demo_variable_args("hello", "world")
    assert 'tuple' == type(result).__name__  # this is the type of args
    assert ('world',) == result  # this is the value of args

    assert (1, 2, 3) == demo_variable_args("hello", 1, 2, 3)

    assert 'one.two.three' == my_merge(".", "one", "two", "three")
    assert 'one,two,three' == my_merge(",", "one", "two", "three")


def demo_with_keyword_args(name, *args, **kwargs):
    """understanding behavior of functions"""
    return kwargs


def test_function_with_keyword_args():
    """understanding behavior of functions"""
    result = demo_with_keyword_args("jack", age=10, height=100)
    assert 'dict' == type(result).__name__
    assert {'age': 10, 'height': 100} == result
    assert {'age': 10, 'height': 100} == demo_with_keyword_args("jack", "address", age=10, height=100)
    assert {'address': 'address', 'age': 10, 'height': 100} == demo_with_keyword_args("jack", address="address", age=10,
                                                                                      height=100)


def demo_sub(*args, **kwargs):
    """understanding behavior of functions"""
    return args, kwargs


def demo_unpacking(name, *args, **kwargs):
    """understanding behavior of functions"""
    return demo_sub(*args, **kwargs)


def demo_no_unpacking(name, *args, **kwargs):
    """understanding behavior of functions"""
    return demo_sub(args, kwargs)


def test_function_unpacking():
    """understanding behavior of functions"""
    result = demo_unpacking("jack", 1, 2, k1="v1", k2="v2")
    assert ((1, 2), {'k1': 'v1', 'k2': 'v2'}) == result

    result = demo_no_unpacking("jack", 1, 2, k1="v1", k2="v2")
    assert (((1, 2), {'k1': 'v1', 'k2': 'v2'}), {}) == result

    result = demo_sub(1, 2, k1="v1")
    assert ((1, 2), {'k1': 'v1'}) == result

    result = demo_sub((1, 2), {"k1": "v1"})
    assert (((1, 2), {'k1': 'v1'}), {}) == result

    result = demo_sub(*(1, 2), **{"k1": "v1"})
    assert ((1, 2), {'k1': 'v1'}) == result

    # you can unpack lists as well
    result = demo_sub(*[1, 2], **{"k1": "v1"})
    assert ((1, 2), {'k1': 'v1'}) == result

# three_things_i_learnt = """
# packing and unpacking of arguments and keyword arguments
# difference between arguments and kwargs
# packing and unpacking of list
# """
#
# time_taken_minutes = 30
