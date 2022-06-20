"""assert"""
__author__ = 'Hari'

# from tasks.placeholders import *
"""assert"""

NOTES = '''
This lesson introduces the basic assert statement in python. assert is generally used to 'assert' the truth of an
expression. It takes the form assert <expr>, <optional message>. If <expr> evaluates to False an AssertionError is raised with
the <optional message>. If is evaluates to True, nothing happens.

 In the tests below, replace the blanks with values so that the resulting expression is True.
'''
def test_assert_true():
    """Assertion test"""
    #throws assertion error
    assert True  #This should be True -- replace ___ with True.

def test_assert_true_with_message():
    """Assertion test"""
    assert True, "This is the failure message"
def test_assert_equality():
    """Assertion test"""
    assert 7 == 2 + 5   #replace __ with the expected value

#Fill in __ in the statements below to make the asserts succeed
def test_make_assert_true_1():
    """Assertion test"""
    assert 8 > 7, "Fill in a value greater than 7"

#you can use the interpreter to find the value of 2**30
def test_make_assert_true_2():
    """Assertion test"""
    assert 2**43 > 2**30, "Fill in value greater than 2**30"

def test_make_assert_true_3():
    """Assertion test"""
    _s1 = "Hello, World"
    _s2 = "Hello, World"
    assert _s1 == _s2

THREE_THINGS_I_LEARNT = """
asseret == bool
-
-
"""

TIME_TAKEN_MINUTES = 30
