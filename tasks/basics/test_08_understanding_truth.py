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
    assert __ == type(None).__name__

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

    assert __ == result

# a helper function used to test the truth value of an object.
def truth_test(object, description):
    if object:
        return description + " is treated as true"
    else:
        return description + " is treated as false"

def test_truth_values():
    assert __ == truth_test("", "empty string")
    assert __ == truth_test((), "empty tuple")
    assert __ == truth_test([], "empty list")
    assert __ == truth_test({}, "empty dict")
    assert __ == truth_test(set(), "empty set")
    assert __ == truth_test(" ", "white space")
    assert __ == truth_test(0, "0")
    assert __ == truth_test(1, "1")
    assert __ == truth_test("a", "non-empty-string")
    assert __ == truth_test((1,2), "non-empty-tuple")
    assert __ == truth_test([1], "non-empty-list")
    assert __ == truth_test({1:2}, "non-empty-dict")
    assert __ == truth_test({1}, "non-empty-set")

# The fact that certain things are treated as True or False by
# control flow statements does not mean that they are equal to True or False.
def test_equality():
    assert __ == ("" == True)
    assert __ == (() == True)
    assert __ == ([] == True)
    assert __ == (set() == True)
    assert __ == (0 == True)
    assert __ == ("" == False)
    assert __ == (() == False)
    assert __ == ([] == False)
    assert __ == (set() == False)
    assert __ == (0 == False)
    assert __ == (1 == True)
    assert __ == ("a" == True)
    assert __ == ((1,2) == True)
    assert __ == ([1] == True)
    assert __ == ({1} == True)


three_things_i_learnt = """
-
-
-
"""

time_taken_minutes = ___
