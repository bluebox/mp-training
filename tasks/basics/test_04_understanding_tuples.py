__author__ = 'Hari'

from tasks.placeholders import *

notes = '''
Tuples are yet another sequence type along the lines of strings and lists with
its own characteristics.
'''

""" Program to understand tuple in python"""
def test_tuple_type():
    """ function to check type of tuple"""
    test_tuple = (1, 2)   # note the syntax
    assert "tuple" == type(test_tuple).__name__
test_tuple_type()
def test_tuple_length():
    """ function to check length of tuple"""
    colors = ('red', 'blue', 'green')
    assert 3 == len(colors)
test_tuple_length()
def test_tuple_with_no_elements():
    """ function to check empty tuple"""
    empty = ()
    assert True is isinstance(empty, tuple)
    assert 0 == len(empty)
test_tuple_with_no_elements()
def test_tuple_with_one_element():
    """ function to check tuple with one element"""
    test1 = (1)
    assert "int" == type(test1).__name__

    test2 = (1,)  #note the syntax used to disambiguate
    assert "tuple" == type(test2).__name__
test_tuple_with_one_element()
def test_tuple_can_be_indexed():
    """ function to check tuple can be indexed or not"""
    colors = ('red', 'blue', 'green')
    assert 'red' == colors[0]
    assert 'blue' == colors[1]
    assert 'green' == colors[2]
test_tuple_can_be_indexed()
def test_tuple_can_be_sliced():
    """ function to slice a tuple"""
    colors = ('red', 'blue', 'green')
    assert ('blue', 'green') == colors[1:3]
    assert ('blue',) == colors[1:2]  #remember the awkward syntax for single element tuples :)
test_tuple_can_be_sliced()

def test_tuples_are_immutable():
    """ function to test tuple are immutable"""
    colors = ('red', 'blue', 'green')
    try:
        colors[0] = 'orange'
    except TypeError as t_e:
        print(t_e) # note the exception -> SyntaxError: Missing parentheses in call to 'print'.
        assert True
test_tuples_are_immutable()
def test_tuples_can_be_nested():
    """ function to check tuples nesting """
    top_left = (10, 20)
    bottom_right = (40, 50)
    rectangle = (top_left, bottom_right)

    assert 2 == len(rectangle)
    assert (10, 20) == rectangle[0]
    assert 10 == rectangle[0][0]
    assert 50 == rectangle[1][1]
test_tuples_can_be_nested()

def test_tuple_unpacking():
    """ function to unpack a tuple """
    pair = (10, 20)
    a_1, b_1 = pair
    assert 10 == a_1
    assert 20 == b_1

    triplet = (10, 20, 30)
    try:
        a_1, b_1 = triplet
        assert a_1 == 10  # should not come here.
    except ValueError as v_e:
        print(v_e) # observe what is printed here. =>In Python 3, printing values changed from being a distinct statement to being an ordinary function call, so it now needs parentheses
        assert True  # ve=>too many values to unpack (expected 2)
test_tuple_unpacking()
def test_sequence_conversion():
    """
    sequences can be converted across forms using the builtin functions.
    """
    word = "testing"
    tup_1 = tuple(word)
    assert ('t', 'e', 's', 't', 'i', 'n', 'g') == tup_1

    list_1 = list(word)
    assert ['t', 'e', 's', 't', 'i', 'n', 'g'] == list_1

    list_2 = list(tup_1)
    assert ['t', 'e', 's', 't', 'i', 'n', 'g'] == list_2

    word2 = str(tup_1)
    assert "('t', 'e', 's', 't', 'i', 'n', 'g')" == word2

    word3 = "".join(tup_1)
    assert "testing" == word3

    word4 = "".join(list_1)
    assert "testing" == word4
test_sequence_conversion()

three_things_i_learnt = """
-uses of tuple in python
-immutability of tuple
-slicing of tuple
"""

time_taken_minutes = 70
