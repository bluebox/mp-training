"""Exercise 00 Python basics"""

__author__ = 'Hari'

# import Tasks.placeholders

NOTES = '''
This lesson introduces the basic assert statement in python. assert is generally used to 'assert' the truth of an
expression. It takes the form assert <expr>, <optional message>. If <expr> evaluates to False an AssertionError is raised with
the <optional message>. If is evaluates to True, nothing happens.
 In the tests below, replace the blanks with values so that the resulting expression is True.
'''


def test_assert_true():
    """throws assertion error"""
    assert True  #This should be True -- replace ___ with True.

def test_assert_true_with_message():
    """If fails throws message"""
    assert True , "This is the failure message"
    # replace ___ with True to stop seeing the assertion error

def test_assert_equality():
    """equate both, and returns bool value"""
    assert 7 == 2 + 5   #replace __ with the expected value

#Fill in __ in the statements below to make the asserts succeed
def test_make_assert_true_1():
    """Compare both values"""
    a_1=10
    a_2=7
    assert a_1 > a_2, "Fill in a value greater than 7"

#you can use the interpreter to find the value of 2**30
def test_make_assert_true_2():
    """Compare both with power"""
    assert 2**31 > 2**30, "Fill in value greater than 2**30"

def test_make_assert_true_3():
    """variables comparison and assert"""
    s_1 = "Hello, World"
    s_2 = s_1
    assert s_1 == s_2

THREE_THINGS_I_LEARNT ="""Python basics, assert pytest"""
TIME_TAKEN_MINUTES = 1