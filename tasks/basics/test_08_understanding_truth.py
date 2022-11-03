__author__ = 'Hari'

from tasks.placeholders import *

notes = '''
Just like C, python has notions on what values are considered true
and what values are considered false.

Assigning truth equivalence to non-bool types leads to much more
elegant way of writing code instead of having explicit comparisons
with base values of the data types like 0, '', [] etc.
'''

""" Program to understand truth"""
def test_none_type():
    """ Function to test the none type """
    assert "NoneType" == type(None).__name__
test_none_type()

#In control flow, builtin objects like string, list, tuple have truth
#and false values

def test_truth_none():
    """ Function to test truth of none """
    value = None
    result = "not-set"
    #is None treated as true or false? =>flase(else loop)
    if value:
        result = "true"
    else:
        result = "false"

    assert "false" == result
test_truth_none()

# a helper function used to test the truth value of an object.
def truth_test(object_1, description):
    """ function to check the truth """
    if object_1:
        return description + " is treated as true"
    return description + " is treated as false"

def test_truth_values():
    """ function to test the truth values """
    assert "empty string is treated as false" == truth_test("", "empty string")
    assert "empty tuple is treated as false" == truth_test((), "empty tuple")
    assert "empty list is treated as false" == truth_test([], "empty list")
    assert "empty dict is treated as false" == truth_test({}, "empty dict")
    assert "empty set is treated as false" == truth_test(set(), "empty set")
    assert "white space is treated as true" == truth_test(" ", "white space")
    assert "0 is treated as false" == truth_test(0, "0")
    assert "1 is treated as true" == truth_test(1, "1")
    assert "non-empty-string is treated as true" == truth_test("a", "non-empty-string")
    assert "non-empty-tuple is treated as true" == truth_test((1,2), "non-empty-tuple")
    assert "non-empty-list is treated as true" == truth_test([1], "non-empty-list")
    assert "non-empty-dict is treated as true" == truth_test({1:2}, "non-empty-dict")
    assert "non-empty-set is treated as true" == truth_test({1}, "non-empty-set")
test_truth_values()

# The fact that certain things are treated as True or False by
# control flow statements does not mean that they are equal to True or False.
def test_equality():
    """ function to test the equality """
    assert False is ("" == True)
    assert False is (() == True)
    assert False is ([] is True)
    assert False is (set() is True)
    assert False is bool(0)
    assert False is ("" == False)
    assert False is (() == False)
    assert False is ([] is False)
    assert False is (set() is False)
    assert False is bool(0)
    assert True is bool(True)
    assert False is ("a" == True)
    assert False is ((1, 2) == True)
    assert False is ([1] is True)
    assert False is ({1} is True)
test_equality()


three_things_i_learnt = """
-working of boolean function
-NoneType in python 
-boolean usage in program flow
"""

time_taken_minutes = 60
