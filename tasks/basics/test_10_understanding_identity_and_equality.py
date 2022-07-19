__author__ = 'Hari'

<<<<<<< HEAD
#from tasks.placeholders import *
=======
from tasks.placeholders import *
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

NOTES = '''
 Identity and equality are 2 concepts which most beginners are confused about.
 The 'is' operator is used to test identity and == is used to test equality.

 Two objects are identical if they are the same object
 Two objects can be equal even if they are not the same object, if they are of the same type and the type defines some
 equality semantics. E.g. all string objects with content "abc" are equal irrespective of where the objects are in memory,
 two lists can be equal if all elements in them are equal in same order etc.
'''

def test_identity_equality_lists():
<<<<<<< HEAD
    _a = []
    _b = []
    assert False is (_a is _b)
    assert True is (_a == _b)

    _a.append("one")
    assert False is (_a is _b)
    assert False is (_a == _b)

    _c = []
    _d = _c
    assert True is (_c is _d)
    assert True is (_c == _d)

    _c.append("one")
    assert True is (_c is _d)
    assert True is (_c == _d)

def test_identity_equality_string():
    _a = _b = "hello"

    assert True is (_a is _b)
    assert True is (_a == _b)

    _c = "hello"
    _d = "".join(["hel", "lo"])
    assert False is (_c is _d)
    assert True is (_c == _d)

def test_identity_equality_numbers():
    _a = _b = 10000
    assert True is (_a is _b)
    assert True is (_a == _b)

    _c = 10000
    _d = int("10000")
    assert False is (_c is _d)
    assert True is (_c == _d)
=======
    a = []
    b = []
    assert __ == (a is b)
    assert __ == (a == b)

    a.append("one")
    assert __ == (a is b)
    assert __ == (a == b)

    c = []
    d = c
    assert __ == (c is d)
    assert __ == (c == d)

    c.append("one")
    assert __ == (c is d)
    assert __ == (c == d)

def test_identity_equality_string():
    a = b = "hello"

    assert __ == (a is b)
    assert __ == (a == b)

    c = "hello"
    d = "".join(["hel", "lo"])
    assert __ == (c is d)
    assert __ == (c == d)

def test_identity_equality_numbers():
    a = b = 10000
    assert __ == (a is b)
    assert __ == (a == b)

    c = 10000
    d = int("10000")
    assert __ == (c is d)
    assert __ == (c == d)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_identity_equality_small_numbers():
    """
    why do small numbers behave differently? google and find out!
    """
<<<<<<< HEAD
    _a = _b = 10
    assert True is (_a is _b)
    assert True is (_a == _b)

    _c = 10
    _d = int("10")
    assert True is (_c is _d)
    assert True is (_c == _d)

def test_identity_equality_none():
    _a = _b = None
    assert True is (_a is _b)
    assert True is (_a == _b)

    _a = None
    _b = None
    assert True is (_a is _b)
    assert True is (_a == _b)
=======
    a = b = 10
    assert __ == (a is b)
    assert __ == ( a == b)

    c = 10
    d = int("10")
    assert __ == (c is d)
    assert __ == (c == d)

def test_identity_equality_None():
    a = b = None
    assert __ == (a is b)
    assert __ == (a == b)

    a = None
    b = None
    assert __ == (a is b)
    assert __ == (a == b)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


NOTES_ON_NONE = '''
None is a builtin constant as you can see above. This allows you to write more
readable code like if x is None: instead of if x == None:
'''

THREE_THINGS_I_LERNT = """
is
==
NONe
"""

<<<<<<< HEAD
TIME_TAKEN_MINUTES = 120
=======
time_taken_minutes = ___


>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
