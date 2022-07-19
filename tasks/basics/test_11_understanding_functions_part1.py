__author__ = 'Hari'

<<<<<<< HEAD
#from tasks.placeholders import *
=======
from tasks.placeholders import *
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa



NOTES = '''
Functions are the basic unit of modularization in python. You use functions to group
together a meaningful action and use it when you need it.

The feature set of functions in python is richer than every major programming
language and makes it easy to expose elegant and usable apis.

This is a big topic, we will revisit this topic again.
'''


<<<<<<< HEAD
def my_print(_x):
    print(_x)
=======
def my_print(x):
    print (x)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


def my_increment(_x):
    return _x + 1


def my_min_max(numbers):
    return min(numbers), max(numbers)

# functions are kinds of objects, they have a type too!
def test_function_type():
    assert __ == type(my_print).__name__
    assert __ == type(my_increment).__name__
    assert __ == type(test_function_type).__name__

# functions are objects which can be 'called'
def test_function_callable_type():
<<<<<<< HEAD
    assert False is callable(1)
    assert True is callable(my_increment)
    assert False is callable(my_increment(10))
=======
    assert __ == callable(1)
    assert __ == callable(my_increment)
    assert __ == callable(my_increment(10))
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

# functions can be held by references just like any other object
def test_function_assignment():
    demo = my_increment
    result = demo(20)
<<<<<<< HEAD
    assert 21 is result
=======
    assert __ == result
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

# every function returns an object, even when it does not!
def test_every_function_returns_something():
    result = my_print(10)
<<<<<<< HEAD
    assert None is result

    result = my_increment(10)
    assert 11 is result
=======
    assert __ == result

    result = my_increment(10)
    assert __ == result
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

    result = my_min_max([20, 30, 5])
    assert __ == result


def demo1():
    """returns 10"""
    return 10


def demo2():
    return 20

#The documentation of every function, if the author wrote it, is available at runtime.
#This makes it easy to access help from console or build specialized help commands like help.
def test_function_documentation():
<<<<<<< HEAD
    assert 'returns 10' == demo1.__doc__
    assert None is demo2.__doc__
=======
    assert __ == demo1.__doc__
    assert __ == demo2.__doc__
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


def my_callfunc(func):
    return func()

# functions can be passed around.
def test_functions_can_be_passed_as_objects():
<<<<<<< HEAD
    assert 10 is my_callfunc(demo1)
    assert 20 is my_callfunc(demo2)
=======
    assert __ == my_callfunc(demo1)
    assert __ == my_callfunc(demo2)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


def my_greet(greeting, name="world"):
    return "{0} {1}".format(greeting, name)


def test_default_arguments():
    assert __ == my_greet("Hello")
    assert __ == my_greet("Hello", "john")


def my_add_to_list1(sequence, target=[]):
    """
    Uses a mutable default, usually leads to unexpected behavior
    """
    target.extend(sequence)
    return target


def my_add_to_list2(sequence, target=None):
    """
    Uses None as default and creates a target list on demand.
    """
    if target is None:
        target = []
    target.extend(sequence)


def test_function_defaults_are_evaluated_at_definition_time():
    assert [__] == my_add_to_list1("hi")
    assert [__] == my_add_to_list1("bye")

<<<<<<< HEAD
    assert None is my_add_to_list2("hi")
    assert None is my_add_to_list2("bye")
=======
    assert [__] == my_add_to_list2("hi")
    assert [__] == my_add_to_list2("bye")
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


def demo_parameter_passing1(_x):
    _x = _x + 1


def demo_parameter_passing2(names):
    names = []


def demo_parameter_passing3(names):
    names.append("something")

<<<<<<< HEAD
# read up after you finish this to make sure
# you get this right: http://effbot.org/zone/call-by-object.htm


def test_function_params_passed_by_object_reference():
    _x = 10
    demo_parameter_passing1(_x)
    assert 10 is _x
=======
# read up after you finish this to make sure you get this right: http://effbot.org/zone/call-by-object.htm
def test_function_params_passed_by_object_reference():
    x = 10
    demo_parameter_passing1(x)
    assert __ == x
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

    names = ["one", "two"]
    demo_parameter_passing2(names)
    assert [__] == names

    demo_parameter_passing3(names)
    assert [__] == names


THREE_THINGS_I_LERNT = """
functions creating
function calling
-
"""

<<<<<<< HEAD
TIME_TAKEN_MINUTES = 120
=======
time_taken_minutes = ___
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
