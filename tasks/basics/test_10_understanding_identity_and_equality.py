'''Identity and equality'''
__author__ = 'Hari'

NOTES_2 = '''
 Identity and equality are 2 concepts which most beginners are confused about.
 The 'is' operator is used to test identity and == is used to test equality.

 Two objects are identical if they are the same object
 Two objects can be equal even if they are not the same object,
 if they are of the same type and the type defines some
 equality semantics. E.g. all string objects with content
 "abc" are equal irrespective of where the objects are in memory,
 two lists can be equal if all elements in them are equal
 in same order etc.
'''

def test_identity_equality_lists():
    '''Identity and equality'''
    a_1 = []
    b_1 = []
    assert (a_1 is b_1) is False
    assert (a_1 == b_1) is True

    a_1.append("one")
    assert (a_1 is b_1) is False
    assert (a_1 == b_1) is False

    c_1 = []
    d_1 = c_1
    assert (c_1 is d_1) is True
    assert (c_1 == d_1) is True

    c_1.append("one")
    assert (c_1 is d_1) is True
    assert (c_1 == d_1) is True

def test_identity_equality_string():
    '''Identity and equality'''
    a_1 = b_1 = "hello"

    assert (a_1 is b_1) is True
    assert (a_1 == b_1) is True

    c_1 = "hello"
    d_1 = "".join(["hel", "lo"])
    assert (c_1 is d_1) is False
    assert (c_1 == d_1) is True

def test_identity_equality_numbers():
    '''Identity and equality'''
    a_1 = b_1 = 10000
    assert (a_1 is b_1) is True
    assert (a_1 == b_1) is True

    c_1 = 10000
    d_1 = int("10000")
    assert (c_1 is d_1) is False
    assert (c_1 == d_1) is True

def test_identity_equality_small_numbers():
    """
    why do small numbers behave differently? google and find out!
    """
    a_1 = b_1 = 10
    assert (a_1 is b_1) is True
    assert (a_1 == b_1) is True

    c_1 = 10
    d_1 = int("10")
    assert (c_1 is d_1) is True
    assert (c_1 == d_1) is True

def test_identity_equality_none():
    '''Identity and equality'''
    a_1 = b_1 = None
    assert (a_1 is b_1) is True
    assert (a_1 == b_1) is True

    a_1 = None
    b_1 = None
    assert (a_1 is b_1) is True
    assert (a_1 == b_1) is True


NOTES_1 = '''
None is a builtin constant as you can see above. This allows you to write more
readable code like if x is None: instead of if x == None:
'''

THREE_THINGS_I_LEARNT = """
1. The 'is' operator is used to test identity and == is used to test equality.
2. Two objects are identical if they are the same object
3. Two objects can be equal even if they are not the same
object, if they are of the same type and the type defines some
 equality semantics. E.g. all string objects with content
 "abc" are equal irrespective of where the objects are in memory,
 two lists can be equal if all elements in
 them are equal in same order etc.
"""

TIME_TAKEN_MINUTES = 6
