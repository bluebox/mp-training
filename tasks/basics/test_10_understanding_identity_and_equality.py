__author__ = 'Hari'

from ctypes import c_bool
from tasks.basics.test_00_understanding_assert import NOTES, THREE_THINGS_I_LEARNT, TIME_TAKEN_MINUTES
from tasks.placeholders import __author__

NOTES = '''
 Identity and equality are 2 concepts which most beginners are confused about.
 The 'is' operator is used to test identity and == is used to test equality.

 Two objects are identical if they are the same object
 Two objects can be equal even if they are not the same object, if they are of the same type and the type defines some
 equality semantics. E.g. all string objects with content "abc" are equal irrespective of where the objects are in memory,
 two lists can be equal if all elements in them are equal in same order etc.
'''

def test_identity_equality_lists():
    a_e = []
    b_e= []
    assert False is (a_e is b_e)
    assert  True is (a_e == b_e)

    a_e.append("one")
    assert False is (a_e is b_e)
    assert  False is (a_e == b_e)

    c_e = []
    d_e = c_e
    assert True is (c_e is d_e)
    assert  True is (c_e == d_e)

    c_e.append("one")
    assert True is (c_e is d_e)
    assert  True is (c_e == d_e)

def test_identity_equality_string():
    a_e = b_e = "hello"

    assert True is (a_e is b_e)
    assert  True is (a_e == b_e)

    c_e = "hello"
    d_e = "".join(["hel", "lo"])
    assert False is (c_e is d_e)
    assert  True is (c_e == d_e)

def test_identity_equality_numbers():
    a_e = b_e = 10000
    assert True is (a_e is b_e)
    assert  True is (a_e == b_e)

    c_e = 10000
    d_e = int("10000")
    assert False is (c_e is d_e)
    assert  True is (c_e == d_e)

def test_identity_equality_small_numbers():
    """
    why do small numbers behave differently? google and find out!
    """
    a_e = b_e = 10
    assert True is (a_e is b_e)
    assert   True is ( a_e == b_e)

    c_e = 10
    d_e = int("10")
    assert True is (c_e is d_e)
    assert  True is (c_e == d_e)

def test_identity_equality_None():
    a_e = b_e = None
    assert True is (a_e is b_e)
    assert  True is (a_e == b_e)

    a_e = None
    b_e = None
    assert True is (a_e is b_e)
    assert  True is (a_e == b_e)


NOTES_ON_NONE = '''
None is a builtin constant as you can see above. This allows you to write more
readable code like if x is None: instead of if x == None:
'''

THREE_THINGS_I_LEARNT = """
-is and == are completely different
-
-
"""

TIME_TAKEN_MINUTES = 15
