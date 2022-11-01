"""Understanding tuples"""
__author__ = 'Hari'

from tasks.placeholders import *

notes = '''
Tuples are yet another sequence type along the lines of strings and lists with
its own characteristics.
'''

def test_tuple_type():
    """type of tuple"""
    test_tuple = (1,2)   # note the syntax
    assert 'tuple' == type(test_tuple).__name__

def test_tuple_length():
    """len of tuple"""
    colors = ('red', 'blue', 'green')
    assert 3 == len(colors)

def test_tuple_with_no_elements():
    """empty tuple"""
    empty = ()
    assert True is isinstance(empty, tuple)
    assert False is len(empty)

def test_tuple_with_one_element():
    """syntax of initializing tuple"""
    test1 = (1)
    assert "int" == type(test1).__name__

    test2 = (1,)  #note the syntax used to disambiguate
    assert 'tuple' == type(test2).__name__

def test_tuple_can_be_indexed():
    """tuple indexing"""
    colors = ('red', 'blue', 'green')
    assert 'red' == colors[0]
    assert 'blue' == colors[1]
    assert 'green' == colors[2]

def test_tuple_can_be_sliced():
    """tuple slicing"""
    colors = ('red', 'blue', 'green')
    assert ('blue', 'green') == colors[1:3]
    assert ('blue', ) == colors[1:2]  #remember the awkward syntax for single element tuples :)


def test_tuples_are_immutable():
    """tuple are immutable"""
    colors = ('red', 'blue', 'green')
    try:
        colors[0] = 'orange'
    except TypeError as t_e:
        print (t_e)#  print te # note the exception ->
        # SyntaxError: Missing parentheses in call to 'print'.
        assert True

def test_tuples_can_be_nested():
    """nested tuple"""
    top_left = (10, 20)
    bottom_right = (40, 50)
    rectangle = (top_left, bottom_right)

    assert 2 == len(rectangle)
    assert (10, 20) == rectangle[0]
    assert 10 == rectangle[0][0]
    assert 50 == rectangle[1][1]


def test_tuple_unpacking():
    """unpacking only if no. of variables = no of elements"""
    pair = (10, 20)
    a, b = pair
    assert 10 == a
    assert 20 == b

    triplet = (10, 20, 30)
    try:
        a, b = triplet
        # should not come here.
    except ValueError as v_e:
        print (v_e ) # observe what is printed here.
        # =>In Python 3, printing values changed from being a distinct statement
        # to being an ordinary function call, so it now needs parentheses
        assert  True  # ve=>too many values to unpack (expected 2)

def test_sequence_conversion():
    """
    sequences can be converted across forms using the builtin functions.
    """
    word = "testing"
    tup_1 = tuple(word)
    assert ('t', 'e', 's', 't', 'i', 'n', 'g') == tup_1

    list_1 = list(word)
    assert ['t', 'e', 's', 't', 'i', 'n', 'g']== list_1

    list_2 = list(tup_1)
    assert ('t', 'e', 's', 't', 'i', 'n', 'g') == list_2

    word2 = str(tup_1)
    assert ('t', 'e', 's', 't', 'i', 'n', 'g') == word2

    word3 = "".join(tup_1)
    assert "testing" == word3

    word4 = "".join(list_1)
    assert "testing" == word4

THREE_THINGS_I_LEARNT = """
-immutable
-syntax : tuple_name = (element1, so_on)
-unpacking based no of varibales present
"""

TIME_TAKEN = 10
