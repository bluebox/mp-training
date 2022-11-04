__author__ = 'Hari'

NOTES = '''
Just like C, python has notions on what values are considered true
and what values are considered false.

Assigning truth equivalence to non-bool types leads to much more
elegant way of writing code instead of having explicit comparisons
with base values of the data types like 0, '', [] etc.
'''

#None is a first class object in python
def test_none_type():
    '''return none data type'''
    assert "NoneType" == type(None).__name__

#In control flow, builtin objects like string, list, tuple have truth
#and false values

def test_truth_none():
    '''none is treated as false'''
    value = None
    result = "not-set"
    #is None treated as true or false? =>flase(else loop)  
    if value:
        result = "true"
    else:
        result = "false"
    # None is treated as False value
    assert 'false' == result

# a helper function used to test the truth value of an object.
def truth_test(object, description):
    '''testing truthness'''
    if object:
        return description + " is treated as true"
    else:
        return description + " is treated as false"

def test_truth_values():
    '''truth values for different data type'''
    assert 'empty string is treated as false' == truth_test("", "empty string")
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
    assert 'non-empty-dict is treated as true' == truth_test({1:2}, "non-empty-dict")
    assert "non-empty-set is treated as true" == truth_test({1}, "non-empty-set")

# The fact that certain things are treated as True or False by
# control flow statements does not mean that they are equal to True or False.
def test_equality():
    ''' testing equality'''
    assert False is ("" is True)
    empty_tuple=()
    assert False is (empty_tuple is True)
    empty_list=[]
    assert False is (empty_list is True)
    empty_set=set()
    assert False is (empty_set is True)
    zero=0
    assert False is (zero is True)
    empty_string=""
    assert False is (empty_string is False)
    assert False is (() is False)
    assert False is ([] is False)
    assert False is (set() is False)
    assert False is (0 is False)
    assert False is (1 is True)
    _a ="a"
    assert False is (_a is True)
    tup = (1,2)
    assert False is (tup is True)
    l =[1]
    assert False is (l is True)
    dictionary={1}
    assert False is (dictionary is True)


THREE_THINGS_I_LEARNT = """
- about truthness of objects like None , dict, string tuple , list etc
- empty datatypes are treated as False
- non empty are true
- 0 is False and 1 is True
"""

TIME_TAKEN_MINUTES = 10
