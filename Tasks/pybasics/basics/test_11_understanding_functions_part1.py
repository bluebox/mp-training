__author__ = 'Hari'

from placeholders import *

notes = '''
Functions are the basic unit of modularization in python. You use functions to group
together a meaningful action and use it when you need it.

The feature set of functions in python is richer than every major programming
language and makes it easy to expose elegant and usable apis.

This is a big topic, we will revisit this topic again.
'''


def my_print(x):
    print x


def my_increment(x):
    return x + 1


def my_min_max(numbers):
    return min(numbers), max(numbers)

# functions are kinds of objects, they have a type too!
def test_function_type():
    assert __ == type(my_print).__name__
    assert __ == type(my_increment).__name__
    assert __ == type(test_function_type).__name__

# functions are objects which can be 'called'
def test_function_callable_type():
    assert __ == callable(1)
    assert __ == callable(my_increment)
    assert __ == callable(my_increment(10))

# functions can be held by references just like any other object
def test_function_assignment():
    demo = my_increment
    result = demo(20)
    assert __ == result

# every function returns an object, even when it does not!
def test_every_function_returns_something():
    result = my_print(10)
    assert __ == result

    result = my_increment(10)
    assert __ == result

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
    assert __ == demo1.__doc__
    assert __ == demo2.__doc__


def my_callfunc(func):
    return func()

# functions can be passed around.
def test_functions_can_be_passed_as_objects():
    assert __ == my_callfunc(demo1)
    assert __ == my_callfunc(demo2)


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

    assert [__] == my_add_to_list2("hi")
    assert [__] == my_add_to_list2("bye")


def demo_parameter_passing1(x):
    x = x + 1


def demo_parameter_passing2(names):
    names = []


def demo_parameter_passing3(names):
    names.append("something")

# read up after you finish this to make sure you get this right: http://effbot.org/zone/call-by-object.htm
def test_function_params_passed_by_object_reference():
    x = 10
    demo_parameter_passing1(x)
    assert __ == x

    names = ["one", "two"]
    demo_parameter_passing2(names)
    assert [__] == names

    demo_parameter_passing3(names)
    assert [__] == names


three_things_i_learnt = """
-
-
-
"""

time_taken_minutes = ___
