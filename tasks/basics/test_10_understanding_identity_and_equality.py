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
    var_a = []
    var_b = []
    assert False is (var_a is var_b)
    assert True is (var_a == var_b)

    var_a.append("one")
    assert False is (var_a is var_b)
    assert False is (var_a == var_b)

    var_c = []
    var_d = var_c
    assert True is (var_c is var_d)
    assert True is (var_c == var_d)

    var_c.append("one")
    assert True is (var_c is var_d)
    assert True is (var_c == var_d)

def test_identity_equality_string():
    var_a = var_b = "hello"

    assert True is (var_a is var_b)
    assert True is (var_a == var_b)

    var_c = "hello"
    var_d = "".join(["hel", "lo"])
    assert False is (var_c is var_d)
    assert True is (var_c == var_d)

def test_identity_equality_numbers():
    var_a = var_b = 10000
    assert True is (var_a is var_b)
    assert True is (var_a == var_b)

    var_c = 10000
    var_d = int("10000")
    assert False is (var_c is var_d)
    assert True is (var_c == var_d)

def test_identity_equality_small_numbers():
    """
    why do small numbers behave differently? google and find out!
    """
    var_a = var_b = 10
    assert True is (var_a is var_b)
    assert True is (var_a == var_b)

    var_c = 10
    var_d = int("10")
    assert True is (var_c is var_d)
    assert True is (var_c == var_d)

def test_identity_equality_None():
    var_a = var_b = None
    assert True is (var_a is var_b)
    assert True is (var_a == var_b)

    var_a = None
    var_b = None
    assert True is (var_a is var_b)
    assert True is (var_a == var_b)


NOTES_ON_NONE = '''
None is a builtin constant as you can see above. This allows you to write more
readable code like if x is None: instead of if x == None:
'''

THREE_THINGS_I_LEARNT = """
-key word "is" is checks the location of variable
-None value points same location
-is behave different for small values
"""

TIME_TAKEN_MINUTES = 15