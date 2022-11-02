""" Understanding How functions are created and how they work """

__author__ = 'Hari'

from tasks.placeholders import *

NOTES = '''
Functions are the basic unit of modularization in python. You use functions to group
together a meaningful action and use it when you need it.

The feature set of functions in python is richer than every major programming
language and makes it easy to expose elegant and usable apis.

This is a big topic, we will revisit this topic again.
'''


def my_print(x):
    """ Print Function"""
    print (x)


def my_increment(x):
    """ Increment Function"""
    return x + 1


def my_min_max(numbers):
    """ min and max Function"""
    return min(numbers), max(numbers)


# functions are kinds of objects, they have a type too!
def test_function_type():
    """ Function Type"""
    assert 'function' == type(my_print).__name__
    assert 'function' == type(my_increment).__name__
    assert 'function' == type(test_function_type).__name__

    
# functions are objects which can be 'called'
def test_function_callable_type():
    """ Function which is callable"""
    assert False == callable(1)
    assert True == callable(my_increment)
    assert False == callable(my_increment(10))


# functions can be held by references just like any other object
def test_function_assignment():
    """ function assignment"""
    demo = my_increment
    result = demo(20)
    assert 21 == result


# every function returns an object, even when it does not!
def test_every_function_returns_something():
    """ Function always returns something"""
    result = my_print(10)
    assert None == result

    result = my_increment(10)
    assert 11 == result

    result = my_min_max([20, 30, 5])
    assert (5, 30) == result


def demo1():
    """returns 10"""
    return 10


def demo2():
    """ function returns 20"""
    return 20

# The documentation of every function, if the author wrote it, is available at runtime.
# This makes it easy to access help from console or build specialized help commands like help.


def test_function_documentation():
    """ function returns the documentation of a particular function we need"""
    assert 'returns 10' == demo1.__doc__
    assert None == demo2.__doc__


def my_callfunc(func):
    """ function calling and returning the output of that function"""
    return func()


# functions can be passed around.
def test_functions_can_be_passed_as_objects():
    """ Functions can be passed as argumnets """
    assert 10 == my_callfunc(demo1)
    assert 20 == my_callfunc(demo2)


def my_greet(greeting, name="world"):
    """ formating function"""
    return "{0} {1}".format(greeting, name)


def test_default_arguments():
    """ default Arguments"""
    assert "Hello world" == my_greet("Hello")
    assert "Hello john" == my_greet("Hello", "john")


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
    """ defaults are Evaluated at definition time"""
    assert ['h', 'i'] == my_add_to_list1("hi")
    assert ['h', 'i', 'b', 'y', 'e'] == my_add_to_list1("bye")

    assert None == my_add_to_list2("hi")
    assert None == my_add_to_list2("bye")


def demo_parameter_passing1(x):
    """ parameter passing 1"""
    x = x + 1


def demo_parameter_passing2(names):
    """ parameter passing 2 """
    names = []


def demo_parameter_passing3(names):
    """ Demo parameter passing 3"""
    names.append("something")

# read up after you finish this to make sure you get this right: http://effbot.org/zone/call-by-object.htm


def test_function_params_passed_by_object_reference():
    """ Parameters passed by Object reference"""
    x = 10
    demo_parameter_passing1(x)
    assert 10 == x

    names = ["one", "two"]
    demo_parameter_passing2(names)
    assert ['one', 'two'] == names

    demo_parameter_passing3(names)
    assert ['one', 'two', 'something'] == names

    
THREE_THINGS_I_LEARNT = """
- callable() is a builtIn function which takes the object and checks whether it is callable or not
- When the objects are passed in the functions then we pass by reference and when we do any changes in the object it reflects after the function exceution
- Usually defaults are evaluated at the time of excution and when Uses a mutable default, usually leads to unexpected behavior, 
"""

TIME_TAKEN_MINUTES = 10
