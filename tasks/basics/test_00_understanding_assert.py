""" Exercise 00 Basics of Pthon and about Assert """

__author__ = 'Hari'

# from tasks.placeholders import *

NOTES = '''
This lesson introduces the basic assert statement in python. assert is generally used to 'assert' the truth of an
expression. It takes the form assert <expr>, <optional message>. If <expr> evaluates to False an AssertionError is raised with
the <optional message>. If is evaluates to True, nothing happens.

 In the tests below, replace the blanks with values so that the resulting expression is True.
'''


def test_assert_true():
    """ About Assert"""
    # throws assertion error
    assert True   # This should be True -- replace ___ with True.


def test_assert_true_with_message():
    """ Assert with Error Message"""
    assert True, "This is the failure message"  # replace ___ with True to stop seeing the assertion error


def test_assert_equality():
    """ Arithematic Operation + """
    assert 7 == 2 + 5   # replace __ with the expected value


# Fill in __ in the statements below to make the asserts succeed
def test_make_assert_true_1():
    """ Arithematic Operation >"""
    assert 8 > 7, "Fill in a value greater than 7"


# you can use the interpreter to find the value of 2**30
def test_make_assert_true_2():
    """ Arithematic Operation ** """
    assert 2**43 > 2**30, "Fill in value greater than 2**30"


def test_make_assert_true_3():
    """ Using == operator """
    s_1 = "Hello, World"
    s_2 = "Hello, World"
    assert s_1 == s_2


THREE_THINGS_I_LEARNT = """
assert evaluates the expression and results in a boolean value
If the expression valued as True it does nothing, else throws an error with the Message which we pass after the expression seperated by comma(,)
Here in expression we can evaluate as complicated as we can until it evaluates as a boolean value.
"""

TIME_TAKEN_IN_MINUTES = 1

