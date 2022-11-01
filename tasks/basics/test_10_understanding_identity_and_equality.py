"""MODULE TO UNDERSTAND EQUALITY AND IDENTITY"""

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
    """test identity equality lists"""
    var_a = []
    var_b = []
    assert False is (var_a is var_b)
    assert True is (var_a == var_b)

    var_a.append("one")
    assert False is (var_a is var_b)
    assert False is (var_a == var_b)

    list_c = []
    list_d = list_c
    assert True is (list_c is list_d)
    assert True is (list_c == list_d)

    list_c.append("one")
    assert True is (list_c is list_d)
    assert True is (list_c == list_d)


def test_identity_equality_string():
    """test identity equality string"""
    var_a = var_b = "hello"

    assert True is (var_a is var_b)
    assert True is (var_a == var_b)

    var_c = "hello"
    var_d = "".join(["hel", "lo"])
    assert False is (var_c is var_d)
    assert True is (var_c == var_d)


def test_identity_equality_numbers():
    """test identity equality numbers"""
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
    --> small numbers are frequently in use so python have predefined objects for these numbers.
    """
    var_a = var_b = 10
    assert True is (var_a is var_b)
    assert True is (var_a == var_b)

    var_c = 10
    var_d = int("10")
    assert True is (var_c is var_d)
    assert True is (var_c == var_d)


def test_identity_equality_None():
    """test identity iquailty None"""
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
-variable which store none points to the same memory location
-numbers in the range (-5 to 256) are very commonly used in python, 
  so for better performance a particular number in the range points to same memory location.
- 'is' is a strict checker (matches value if memory location matches)
"""

TIME_TAKEN_MINUTES = 5
