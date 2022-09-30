'''program file'''
__author__ = 'Hari'
#from tasks.placeholders import *
def demo(first=0, second=2, third=3):
    '''function'''
    return [first, second, third]

# keyword arguments allows you to write one api without having a large number
# of overloads for various scenarios.
# add extra arguments where necessary.
def test_function_call_with_keyword_arguments():
    '''function'''
    assert [10,2,3] == demo(10)
    assert [10,20,3] == demo(10, 20)
    assert [10,20,30] == demo(10, 20, 30)
    assert [0,20,3] == demo(second=20)
    assert [0,20,30] == demo(second=20, third=30)
    assert [10,2,30] == demo(first=10, third=30)
    assert [10,2,30] == demo(10, third=30)

def demo_variable_args(first, *args):
    '''function'''
    return args

def my_merge(separator, *args):
    '''function'''
    return separator.join(args)

def test_function_with_variable_args():
    '''function'''
    result = demo_variable_args("hello", "world")
    assert 'tuple' == type(result).__name__ #this is the type of args
    assert ("world",) == result              #this is the value of args
    assert (1,2,3) == demo_variable_args("hello", 1, 2, 3)
    assert "one.two.three" == my_merge(".", "one", "two", "three")
    assert "one,two,three"== my_merge(",", "one", "two", "three")

def demo_with_keyword_args(name, *args, **kwargs):
    '''function'''
    return kwargs

def test_function_with_keyword_args():
    '''function'''
    result = demo_with_keyword_args("jack", age=10, height=100)
    assert "dict" == type(result).__name__
    assert  {'age': 10, 'height': 100} == result
    assert {'age': 10, 'height': 100} == demo_with_keyword_args("jack", "address", age=10, height=100)
    assert {'address':"address",'age': 10, 'height': 100} == demo_with_keyword_args("jack", address="address", age=10, height=100)

def demo_sub(*args, **kwargs):
    '''function'''
    return args, kwargs

def demo_unpacking(name, *args, **kwargs):
    '''function'''
    return demo_sub(*args, **kwargs)

def demo_no_unpacking(name, *args, **kwargs):
    '''function'''
    return demo_sub(args, kwargs)

def test_function_unpacking():
    '''function'''
    result = demo_unpacking("jack", 1, 2, k1="v1", k2="v2")
    assert ((1, 2), {'k1': 'v1', 'k2': 'v2'}) == result
    result = demo_no_unpacking("jack", 1, 2, k1="v1", k2="v2")
    assert (((1, 2), {'k1': 'v1', 'k2': 'v2'}), {}) == result
    result = demo_sub(1,2, k1="v1")
    assert  ((1, 2), {'k1': 'v1'}) == result
    result = demo_sub((1,2), {"k1" :"v1"})
    assert (((1, 2), {'k1': 'v1'}), {}) == result
    result = demo_sub(*(1,2), **{"k1": "v1"})
    assert (((1, 2), {'k1': 'v1'})) == result
    #you can unpack lists as well
    result = demo_sub(*[1,2], **{"k1":"v1"})
    assert ((1, 2), {'k1': 'v1'}) == result

THREE_THINGS_I_LEARNT = """
-
-
-
"""
TIME_TAKEN_MINUTES = 45
