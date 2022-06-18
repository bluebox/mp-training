"""Code Author"""
__author__ = 'Hari'

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
    """Identity and Equality"""
    var = []
    vari = []
    assert False is (var is vari)
    assert True is (var == vari)

    var.append("one")
    assert False is (var is vari)
    assert False is (var == vari)

    varia = []
    variab = varia
    assert True is (varia is variab)
    assert True is (varia == variab)

    varia.append("one")
    assert True is (varia is variab)
    assert True is (varia == variab)

def test_identity_equality_string():
    """String Identity and Equality"""
    var = vari = "hello"

    assert True is (var is vari)
    assert True is (var == vari)

    varia = "hello"
    variab = "".join(["hel", "lo"])
    assert False is (varia is variab)
    assert True is (varia == variab)

def test_identity_equality_numbers():
    """Integer Equality and Identity"""
    var = vari = 10000
    assert True is (var is vari)
    assert True is (var == vari)

    varia = 10000
    variab = int("10000")
    assert False is (varia is variab)
    assert True is (varia == variab)

def test_identity_equality_small_numbers():
    """why do small numbers behave differently? google and find out!"""
    var = vari = 10
    assert True is (var is vari)
    assert True is ( var == var)

    varia = 10
    variab = int("10")
    assert True is (varia is variab)
    assert True is (varia == variab)

def test_identity_equality_none():
    """None"""
    var = vari = None
    assert True is (var is vari)
    assert True is (var == vari)

    var = None
    vari = None
    assert True is (var is vari)
    assert True is (var == vari)


NOTES_ON_NONE = '''
None is a builtin constant as you can see above. This allows you to write more
readable code like if x is None: instead of if x == None:
'''

THREE_THINGS_I_LEARNT = """
-Equality
-Identity
-Difference between Identity and Equality
"""

TIME_TAKEN_MINUTES = 2
