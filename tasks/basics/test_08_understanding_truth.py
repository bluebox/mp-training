'''Truth'''
__author__ = 'Hari'

# from tasks.placeholders import *

NOTES_1 = '''
Just like C, python has notions on what
values are considered true
and what values are considered false.

Assigning truth equivalence to
non-bool types leads to much more
elegant way of writing code
instead of having explicit comparisons
with base values of the data types like 0, '', [] etc.
'''

#None is a first class object in python
def test_none_type():
    '''Truth'''
    assert type(None).__name__ == 'NoneType'

#In control flow, builtin objects like string, list, tuple have truth
#and false values

def test_truth_none():
    '''Truth'''
    value = None
    result = "not-set"
    #is None treated as true or false? =>flase(else loop)
    if value:
        result = "true"
    else:
        result = "false"

    assert result == "false"

# a helper function used to test the truth value of an object.
def truth_test(object, description):
    '''Truth'''
    if object:
        return description + " is treated as true"
    else:
        return description + " is treated as false"

def test_truth_values():
    '''Truth'''
    assert truth_test("", "empty string") == "empty string is treated as false"
    assert truth_test((), "empty tuple") == "empty tuple is treated as false"
    assert truth_test([], "empty list") == "empty list is treated as false"
    assert truth_test({}, "empty dict") == "empty dict is treated as false"
    assert truth_test(set(), "empty set") == "empty set is treated as false"
    assert truth_test(" ", "white space") == "white space is treated as true"
    assert truth_test(0, "0") == "0 is treated as false"
    assert truth_test(1, "1") == "1 is treated as true"
    assert truth_test("a", "non-empty-string") == "non-empty-string is treated as true"
    assert truth_test((1, 2), "non-empty-tuple") == "non-empty-tuple is treated as true"
    assert truth_test([1], "non-empty-list") == "non-empty-list is treated as true"
    assert truth_test({1:2}, "non-empty-dict") == "non-empty-dict is treated as true"
    assert truth_test({1}, "non-empty-set") == "non-empty-set is treated as true"

# The fact that certain things are treated as True or False by
# control flow statements does not mean that they are equal to True or False.
def test_equality():
    '''Truth'''
    assert "" == True is False
    assert () == True is False
    assert [] == True is False
    assert set() == True is False
    assert 0 == True is False
    assert "" == False is False
    assert () == False is False
    assert [] == False is False
    assert set() == False is False
    assert (0 == False) is True
    assert (1 == True) is True
    assert "a" == True is False
    assert (1, 2) == True is False
    assert [1] == True is False
    assert {1} == True is False


THREE_THINGS_I_LEARNT = """
1. white space is treated as true
2. non-empty set, list, dict tuple treated as true
3. empty set, tuple,list,dict trated as false
"""

TIME_TAKEN_MINUTES = 5
