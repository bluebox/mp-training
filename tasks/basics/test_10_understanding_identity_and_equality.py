__author__ = 'Hari'
from tasks.placeholders import *

Notes = '''
 Identity and equality are 2 concepts which most beginners are confused about.
 The 'is' operator is used to test identity and == is used to test equality.

 Two objects are identical if they are the same object
 Two objects can be equal even if they are not the same object, if they are of the same type and the type defines some
 equality semantics. E.g. all string objects with content "abc" are equal irrespective of where the objects are in memory,
 two lists can be equal if all elements in them are equal in same order etc.
'''


def test_identity_equality_lists():
    ''' equality lists'''
    a_l = []
    b_l = []
    assert False is (a_l is b_l)
    assert True is (a_l == b_l)

    a_l.append("one")
    assert False is (a_l is b_l)
    assert False is (a_l == b_l)

    c_l = []
    d_l= c_l
    assert True is (c_l is d_l)
    assert True is (c_l == d_l)

    c_l.append("one")
    assert True is (c_l is d_l)
    assert True is (c_l== d_l)


def test_identity_equality_string():
    '''equality string'''
    a_l = b_l = "hello"

    assert True is (a_l is b_l)
    assert True is (a_l == b_l)

    c_l= "hello"
    d_l = "".join(["hel", "lo"])
    assert False is (c_l is d_l)
    assert True == (c_l == d_l)


def test_identity_equality_numbers():
    '''equality numbers'''
    a_l = b_l = 10000
    assert True is (a_l is b_l)
    assert True is (a_l == b_l)

    c_l = 10000
    d_l= int("10000")
    assert False is (c_l is d_l)
    assert True is (c_l == d_l)


def test_identity_equality_small_numbers():
    """
    why do small numbers behave differently? google and find out!
    """
    a_l = b_l = 10
    assert True is (a_l is b_l)
    assert True is (a_l == b_l)

    c_l = 10
    d_l = int("10")
    assert True is (c_l is d_l)
    assert True is (c_l == d_l)


def test_identity_equality_None():
    '''equality none'''
    a_l= b_l= None
    assert True is (a_l is b_l)
    assert True is (a_l== b_l)

    a_l= None
    b_l= None
    assert True is (a_l is b_l)
    assert True is (a_l== b_l)


NOTES_ON_NONE = '''
None is a builtin constant as you can see above. This allows you to write more
readable code like if x is None: instead of if x == None:
'''

THREE_THINGS_I_LEARNT = """
-
-
-
"""

TIME_TAKEN_MINUTES = 10