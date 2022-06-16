__author__ = 'Hari'

from tasks.placeholders import __author__

NOTES = '''
 Identity and equality are 2 concepts which most beginners are confused about.
 The 'is' operator is used to test identity and == is used to test equality.

 Two objects are identical if they are the same object
 Two objects can be equal even if they are not the same object, if they are of the same 
 type and the type defines someequality semantics. E.g. all string objects with content
  "abc" are equal irrespective of where the objects are in memory,two lists can be equal
   if all elements in them are equal in same order etc.
'''

def test_identity_equality_lists():
    a = []
    b = []
    assert False is (a is b)
    assert True is (a == b)

    a.append("one")
    assert False is (a is b)
    assert False is (a == b)

    c = []
    d = c
    assert True is (c is d)
    assert True is (c == d)

    c.append("one")
    assert True is (c is d)
    assert True is (c == d)

def test_identity_equality_string():
    a = b = "hello"

    assert True is (a is b)
    assert True is (a == b)

    c = "hello"
    d = "".join(["hel", "lo"])
    assert False is (c is d)
    assert True is (c == d)

def test_identity_equality_numbers():
    a = b = 10000
    assert  True is (a is b)
    assert True is (a == b)

    c = 10000
    d = int("10000")
    assert False is (c is d)
    assert True is (c == d)

def test_identity_equality_small_numbers():
    """
    why do small numbers behave differently? google and find out!
    """
    a = b = 10
    assert True is (a is b)
    assert True is ( a == b)

    c = 10
    d = int("10")
    assert True is (c is d)
    assert True is (c == d)

def test_identity_equality_None():
    a = b = None
    assert True is (a is b)
    assert True is (a == b)

    a = None
    b = None
    assert True is (a is b)
    assert True is (a == b)


NOTES_ON_NONE = '''
None is a builtin constant as you can see above. This allows you to write more
readable code like if x is None: instead of if x == None:
'''

THREE_THINGS_I_LEARNT = """
-
-
-
"""

TIME_TAKEN_MINUTES = 30


