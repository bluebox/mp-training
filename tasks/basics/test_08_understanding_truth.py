'''program file'''
__author__ = 'Hari'

#from tasks.placeholders import *

NOTES = '''
Just like C, python has notions on what values are considered true
and what values are considered false.

Assigning truth equivalence to non-bool types leads to much more
elegant way of writing code instead of having explicit comparisons
with base values of the data types like 0, '', [] etc.
'''
#None is a first class object in python
def test_none_type():
    '''function'''
    assert 'NoneType' == type(None).__name__
#In control flow, builtin objects like string, list, tuple have truth
#and false values
def test_truth_none():
    '''function'''
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
    '''function'''
    if object:
        return description + " is treated as true"
    return description + " is treated as false"

def test_truth_values():
    '''function'''
    assert 'empty string is treated as false' == truth_test("", "empty string")
    assert 'empty tuple is treated as false' == truth_test((), "empty tuple")
    assert 'empty list is treated as false' == truth_test([], "empty list")
    assert 'empty dict is treated as false' == truth_test({}, "empty dict")
    assert 'empty set is treated as false' == truth_test(set(), "empty set")
    assert 'white space is treated as true' == truth_test(" ", "white space")
    assert not None is truth_test(0, "0")
    assert not None is truth_test(1, "1")
    assert not None is truth_test("a", "non-empty-string")
    assert not None is truth_test((1,2), "non-empty-tuple")
    assert not None is truth_test([1], "non-empty-list")
    assert not None is truth_test({1:2}, "non-empty-dict")
    assert not None is truth_test({1}, "non-empty-set")

# The fact that certain things are treated as True or False by
# control flow statements does not mean that they are equal to True or False.
def test_equality():
    '''Truth'''
    test_a=""
    assert False is (test_a is True)
    test_a=()
    assert False is (test_a is True)
    test_a=[]
    assert False is (test_a is True)
    test_a=set()
    assert False is (test_a is True)
    test_a=0
    assert False is ( test_a is True)
    test_a=""
    assert False is (test_a is False)
    test_a=()
    assert False is (test_a is False)
    test_a=[]
    assert False is (test_a is False)
    test_a=set()
    assert False is (test_a is False)
    test_a=0
    assert False is (test_a is False)
    test_a=1
    assert False is (test_a is True)
    test_a="a"
    assert False is (test_a is True)
    test_a=(1,2)
    assert False is (test_a is True)
    test_a=[1]
    assert False is (test_a is True)
    test_a={1}
    assert False is (test_a is True)


THREE_THINGS_I_LEARNT = """
-
-
-
"""
TIME_TAKEN_MINUTES = 30
