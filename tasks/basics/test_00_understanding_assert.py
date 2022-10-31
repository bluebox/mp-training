"""MODULE TO ILLUSTRATE FUNCTIONALITY OF ASSERT"""

__author__ = 'Hari'

from tasks.placeholders import *

NOTES = '''
This lesson introduces the basic assert statement in python. assert is generally used to 'assert' the truth of an
expression. It takes the form assert <expr>, <optional message>. If <expr> evaluates to False an AssertionError is 
raised with
the <optional message>. If is evaluates to True, nothing happens.
 In the tests below, replace the blanks with values so that the resulting expression is True.
'''


def test_assert_true():
    """Test assert true"""
    # throws assertion error
    assert True  # This should be True -- replace ___ with True.


def test_assert_true_with_message():
    """test assert true with message"""
    # replace ___ with True to stop seeing the assertion error
    assert True, "This is the failure message"


def test_assert_equality():
    """test assert equality"""
    assert 7 == 2 + 5   # replace __ with the expected value


# Fill in __ in the statements below to make the asserts succeed
def test_make_assert_true_1():
    """test make assert true 1"""
    assert 8 >= 7, "Fill in a value greater than 7"


# you can use the interpreter to find the value of 2**30
def test_make_assert_true_2():
    """Test make assert true 2"""
    assert 2 ** 43 > 2 ** 30, "Fill in value greater than 2**30"


def test_make_assert_true_3():
    """test make assert true 3"""
    s1_ = "Hello, World"
    s2_ = "Hello, World"
    assert s1_ == s2_


THREE_THINGS_I_LEARNT = """
assert ==bool
-assert raises AssertException if "condition followed by assert statement is false"
-string followed by assert condition is: "AssertException statement"
-assert can be used to test output of a python module
"""

TIME_TAKEN_MINUTES = 1
