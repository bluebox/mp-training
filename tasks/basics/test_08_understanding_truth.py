__author__ = 'Hari'

from tasks.placeholders import *

notes = '''
Just like C, python has notions on what values are considered true
and what values are considered false.

Assigning truth equivalence to non-bool types leads to much more
elegant way of writing code instead of having explicit comparisons
with base values of the data types like 0, '', [] etc.
'''

#None is a first class object in python
def test_none_type():
    assert "NoneType" == type(None).__name__

#In control flow, builtin objects like string, list, tuple have truth
#and false values

def test_truth_none():
    value = None
    result = "not-set"
    #is None treated as true or false? =>flase(else loop)
    if value:
        result = "true"
    else:
        result = "false"

    assert "false" == result

# a helper function used to test the truth value of an object.
def truth_test(object, description):
    if object:
        return description + " is treated as true"
    else:
        return description + " is treated as false"

def test_truth_values():
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

# The fact that certain things are treated as True or False by
# control flow statements does not mean that they are equal to True or False.
def test_equality():
    assert False == ("" is True)
    assert False == (() is True)
    assert False == ([] is True)
    assert False == (set() is True)
    assert False == (0 is True)
    assert False == ("" is False)
    assert False == (() is False)
    assert False == ([] is False)
    assert False == (set() is False)
    assert False == (0 is False)
    assert True == (1 == True)
    assert False == ("a" is True)
    assert False == ((1, 2) is True)
    assert False == ([1] is True)
    assert False == ({1} is True)


three_things_i_learnt = """
- character is not equal to true or false
- numbers are true but numbers in sets, tuples, sets are neither true nor false
- empty tuple, sets, lists are not true or false
"""

time_taken_minutes = 10
