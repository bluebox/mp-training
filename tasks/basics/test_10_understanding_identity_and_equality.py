__author__ = 'Hari'

from tasks.placeholders import *

NOTES = '''
 Identity and equality are 2 concepts which most beginners are confused about.
 The 'is' operator is used to test identity and == is used to test equality.

 Two objects are identical if they are the same object
 Two objects can be equal even if they are not the same object, if they are of the same type and the type defines some
 equality semantics. E.g. all string objects with content "abc" are equal irrespective of where the objects are in memory,
 two lists can be equal if all elements in them are equal in same order etc.
'''

def test_identity_equality_lists():
    _a_ = []
    _b_ = []
    assert False == (_a_ is _b_)
    assert True == (_a_ == _b_)

    a.append("one")
    assert False == (_a_ is _b_)
    assert False == (_a_ == _b_)

    _c_ = []
    _d_ = c
    assert True == (_c_ is _d_)
    assert True == (_c_ == _d_)

    _c_.append("one")
    assert True == (_c_ is _d_)
    assert True == (_c_ == _d_)

def test_identity_equality_string():
    _a_ = _b_ = "hello"

    assert True == (_a_ is _b)
    assert True == (a == b)

    c = "hello"
    d = "".join(["hel", "lo"])
    assert False == (c is d)
    assert True == (c == d)

def test_identity_equality_numbers():
    a = b = 10000
    assert True == (a is b)
    assert True == (a == b)

    c = 10000
    d = int("10000")
    assert False == (c is d)
    assert True == (c == d)

def test_identity_equality_small_numbers():
    """
    why do small numbers behave differently? google and find out!
    """
    a = b = 10
    assert True == (a is b)
    assert True == ( a == b)

    c = 10
    d = int("10")
    assert True == (c is d)
    assert True == (c == d)

def test_identity_equality_None():
    a = b = None
    assert True == (a is b)
    assert True == (a == b)

    a = None
    b = None
    assert True == (a is b)
    assert True == (a == b)


notes_on_none = '''
None is a builtin constant as you can see above. This allows you to write more
readable code like if x is None: instead of if x == None:
'''

THREE_THINGS_I_LEARNT = """
-key word "is" is checks the location of variable
-None value points same location
-is behave different for small values
"""

TIME_TAKEN_MINUTES = 14