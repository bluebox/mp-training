__author__ = 'Hari'

from tasks.placeholders import *

notes = '''
Functions are the basic unit of modularization in python. You use functions to group
together a meaningful action and use it when you need it.

The feature set of functions in python is richer than every major programming
language and makes it easy to expose elegant and usable apis.

This is a big topic, we will revisit this topic again.
'''


""" Program to understand function in python """
def my_print(x):
    """ function to print """
    print(x)


def my_increment(x):
    """ function to increment """
    return x + 1


def my_min_max(numbers):
    """ function to find min and max"""
    return min(numbers), max(numbers)

# functions are kinds of objects, they have a type too!
def test_function_type():
    """ function to test the type of function """
    assert 'function' == type(my_print).__name__
    assert 'function' == type(my_increment).__name__
    assert 'function' == type(test_function_type).__name__
test_function_type()

# functions are objects which can be 'called'
def test_function_callable_type():
    """ function to test function is callable"""
    assert False is callable(1)
    assert True is callable(my_increment)
    assert False is callable(my_increment(10))
test_function_callable_type()

# functions can be held by references just like any other object
def test_function_assignment():
    """ function to check assignment """
    demo = my_increment
    result = demo(20)
    assert 21 == result
test_function_assignment()

# every function returns an object, even when it does not!
def test_every_function_returns_something():
    """ function to return something """
    result = my_print(10)
    assert None is result

    result = my_increment(10)
    assert 11 == result

    result = my_min_max([20, 30, 5])
    assert (5, 30) == result
test_every_function_returns_something()


def demo1():
    """returns 10"""
    return 10


def demo2():
    """ demo """
    return 20

#The documentation of every function, if the author wrote it, is available at runtime.
#This makes it easy to access help from console or build specialized help commands like help.
def test_function_documentation():
    """ function ot test funtion documentation"""
    assert """returns 10""" == demo1.__doc__
    assert None is demo2.__doc__


def my_callfunc(func):
    """ callfunc"""
    return func()

# functions can be passed around.
def test_functions_can_be_passed_as_objects():
    """ test function can be passed as objects or not """
    assert 10 == my_callfunc(demo1)
    assert 20 == my_callfunc(demo2)
test_functions_can_be_passed_as_objects()


def my_greet(greeting, name="world"):
    """ greeting function """
    return "{0} {1}".format(greeting, name)


def test_default_arguments():
    """ function to test default arguments """
    assert "Hello world" == my_greet("Hello")
    assert "Hello john" == my_greet("Hello", "john")
test_default_arguments()


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
    """ function to check defaults are evaluated at time or not """
    assert ["h", "i"] == my_add_to_list1("hi")
    assert ["h", "i", "b", "y", "e"] == my_add_to_list1("bye")

    assert None is my_add_to_list2("hi")
    assert None is my_add_to_list2("bye")
test_function_defaults_are_evaluated_at_definition_time()


def demo_parameter_passing1(x_1):
    """ Demo function """
    x_1 = x_1 + 1


def demo_parameter_passing2(names):
    """ demo parameter function"""
    names = []


def demo_parameter_passing3(names):
    """ demo parameter passing"""
    names.append("something")

# read up after you finish this to make sure you get this right: http://effbot.org/zone/call-by-object.htm
def test_function_params_passed_by_object_reference():
    """ test the function parameters """
    x_1 = 10
    demo_parameter_passing1(x_1)
    assert 10 == x_1

    names = ["one", "two"]
    demo_parameter_passing2(names)
    assert ["one", "two"] == names

    demo_parameter_passing3(names)
    assert ["one", "two", "something"] == names
test_function_params_passed_by_object_reference()


three_things_i_learnt = """
-we can return multiple values in form of list
-the documentation if written is available at run time
-extend keywork in list
"""

time_taken_minutes = 60
