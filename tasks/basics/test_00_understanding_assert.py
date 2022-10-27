'''Feeling greater value'''
__author__ = 'Hari'

# from tasks.placeholders import *

NOTES_1 = '''
This lesson introduces the basic assert statement in python. 
assert is generally used to 'assert' the truth of an
expression. It takes the form assert <expr>, 
<optional message>. If <expr> evaluates to
 False an AssertionError is raised with
the <optional message>. If is evaluates to True,
 nothing happens.

 In the tests below, replace the blanks
  with values so that the resulting expression is True.
'''


def test_assert_true():
    '''throws assertion error '''
    assert True  #This should be True -- replace ___ with True.

def test_assert_true_with_message():
    '''throws assertion error '''
    assert True, "This is the failure message"
    # replace ___ with True to stop seeing the assertion error

def test_assert_equality():
    '''throws assertion error '''
    assert 2 + 5 == 7   #replace __ with the expected value

#Fill in __ in the statements below to make the asserts succeed
def test_make_assert_true_1():
    '''Feeling greater value'''
    assert 13 > 7, "Fill in a value greater than 7"

#you can use the interpreter to find the value of 2**30
def test_make_assert_true_2():
    '''Feeling greater value'''
    assert 2**38 > 2**30, "Fill in value greater than 2**30"

def test_make_assert_true_3():
    '''Feeling greater value'''
    s1_1 = "Hello, World"
    s2_2 = "Hello, World"
    assert s1_1 == s2_2

THREE_THINGS_I_LEARNT = """
1. assert is a boolean function that give us true if condition is true else false.
2. for testing any function we have to go to directory of that function and then use command pytest file_name.
3. How to create and maintain reusable testing utilities.
"""

TIME_TAKEN_MINUTES = 5
