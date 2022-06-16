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
    assert True, "This is the failure message" 
    # replace with True to stop seeing assertion error
def test_assert_equality():
    assert 7 == 2 + 5   #replace __ with the expected value

#Fill in __ in the statements below to make the asserts succeed
def test_make_assert_true_1():
    assert 8 > 7, "Fill in a value greater than 7"

#you can use the interpreter to find the value of 2**30
def test_make_assert_true_2():
    assert 2**43 > 2**30, "Fill in value greater than 2**30"

def test_make_assert_true_3():
    _s1_ = "Hello, World"
    _s2_ = "Hello, World"
    assert _s1_ == _s2_

THREE_THINGS_I_LEARNT = """
assert ==bool
-
-
"""

TIME_TAKEN_MINUTES = 1
