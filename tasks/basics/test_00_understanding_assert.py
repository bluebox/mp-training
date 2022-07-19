__author__ = 'Hari'

from tasks.placeholders import *

NOTES = '''
This lesson introduces the basic assert statement in python. assert is generally used to 'assert' the truth of an
expression. It takes the form assert <expr>, <optional message>. If <expr> evaluates to False an AssertionError is raised with
the <optional message>. If is evaluates to True, nothing happens.

 In the tests below, replace the blanks with values so that the resulting expression is True.
'''


def test_assert_true():
    #throws assertion error
    assert True  #This should be True -- replace ___ with True.

def test_assert_true_with_message():
    assert True, "This is the failure message" # replace ___ with True to stop seeing the assertion error

def test_assert_equality():
    assert 7 == 2 + 5   #replace __ with the expected value

#Fill in __ in the statements below to make the asserts succeed
def test_make_assert_true_1():
<<<<<<< HEAD
    _a=8
    _b=7
    assert _a > _b, "Fill in a value greater than 7"

# you can use the interpreter to find the value of 2**30

=======
    assert 8>7, "Fill in a value greater than 7"
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

#you can use the interpreter to find the value of 2**30
def test_make_assert_true_2():
    assert 2**43 > 2**30, "Fill in value greater than 2**30"

def test_make_assert_true_3():
    s_1 = "Hello, World"
    s_2 = "Hello, World"
    assert s_1 == s_2

<<<<<<< HEAD

THREE_THINGS_I_LERNT = """asseret is bool opreation ,used for testing."""

TIME_TAKEN_MINUTES = 10
=======
three_things_i_learnt = """
asseret ==bool
-
-
"""

time_taken_minutes = 1

>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
