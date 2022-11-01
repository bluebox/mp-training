"""understanding assert"""

__author__ = 'Hari'




NOTES = '''
This lesson introduces the basic assert statement in python. assert is generally used to 'assert' the truth of an
expression. It takes the form assert <expr>, <optional message>. If <expr> evaluates to False an AssertionError is raised with
the <optional message>. If is evaluates to True, nothing happens.

 In the tests below, replace the blanks with values so that the resulting expression is True.
'''


def test_assert_true():
    """thorows exception error"""
    assert True, "This should be True -- replace ___ with True."


def test_assert_true_with_message():
    """assert with error message"""
    assert True, "This is the failure message"


def test_assert_equality():
    """assert equality"""
    assert 7 == 2 + 5, "replace __ with the expected value"


# Fill in __ in the statements below to make the asserts succeed
def test_make_assert_true_1():
    """make assert true 1"""
    assert 8 > 7, "Fill in a value greater than 7"


# you can use the interpreter to find the value of 2**30
def test_make_assert_true_2():
    """make assert true 2"""
    assert 2 ** 43 > 2 ** 30, "Fill in value greater than 2**30"


def test_make_assert_true_3():
    """make assert true 3"""
    s_1 = "Hello, World"
    s_2 = "Hello, World"
    assert s_1 == s_2, "Not equal"


THREE_THINGS_I_LEARNT = """
- assert == bool
- how assert statement works
- assertion failure message can also be added to the assert statement
"""

TIME_TAKEN_MINUTES = 1
