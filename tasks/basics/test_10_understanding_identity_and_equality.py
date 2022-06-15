"""This is the 10th file of python exercise by medplus"""
__author__ = 'Hari'

# from tasks.placeholders import *

NOTES = '''
 Identity and equality are 2 concepts which most beginners are confused about.
 The 'is' operator is used to test identity and == is used to test equality.

 Two objects are identical if they are the same object
 Two objects can be equal even if they are not the same object, if they are of the same type and the type defines some
 equality semantics. E.g. all string objects with content "abc" are equal irrespective of where the objects are in memory,
 two lists can be equal if all elements in them are equal in same order etc.
'''

def test_identity_equality_lists():
    """basic docstring for pylint testing"""
    list_a = []
    list_b = []
    assert False is (list_a is list_b)
    assert True is (list_a == list_b)

    list_a.append("one")
    assert False is (list_a is list_b)
    assert False is (list_a == list_b)

    list_c = []
    list_d = list_c
    assert True is (list_c is list_d)
    assert True is (list_c == list_d)

    list_c.append("one")
    assert True is (list_c is list_d)
    assert True is (list_c == list_d)

def test_identity_equality_string():
    """basic docstring for pylint testing"""
    str_a = str_b = "hello"

    assert True is (str_a is str_b)
    assert True is (str_a == str_b)

    str_c = "hello"
    str_d = "".join(["hel", "lo"])
    assert False is (str_c is str_d)
    assert True is (str_c == str_d)

def test_identity_equality_numbers():
    """basic docstring for pylint testing"""
    int_a = int_b = 10000
    assert True is (int_a is int_b)
    assert True is (int_a == int_b)

    int_c = 10000
    int_d = int("10000")
    assert False is (int_c is int_d)
    assert True is (int_c == int_d)

def test_identity_equality_small_numbers():
    """
    why do small numbers behave differently? google and find out!
    """
    int_a = int_b = 10
    assert True is (int_a is int_b)
    assert True is (int_a == int_b)

    int_c = 10
    int_d = int("10")
    assert True is (int_c is int_d)
    assert True is (int_c == int_d)

def test_identity_equality_none():
    """basic docstring for pylint testing"""
    none_a = none_b = None
    assert True is (none_a is none_b)
    assert True is (none_a == none_b)

    none_a = None
    none_b = None
    assert True is (none_a is none_b)
    assert True is (none_a == none_b)


NOTES_ON_NONE = '''
None is a builtin constant as you can see above. This allows you to write more
readable code like if x is None: instead of if x == None:
'''

THREE_THINGS_I_LEARNT = """
is operator in python, object reference,== operator, boolean,join function
"""

TIME_TAKEN_MINUTES = 10
