"""Exercise 04 Python basics"""
__author__ = 'Hari'

# from tasks.placeholders import *

NOTES = '''
Tuples are yet another sequence type along the lines of strings and lists with
its own characteristics.
'''

def test_tuple_type():
    """Tuple and its type"""
    test_tuple = (1, 2)  # note the syntax
    assert "tuple" == type(test_tuple).__name__

def test_tuple_length():
    """Tuple and its length"""
    colors = ('red', 'blue', 'green')
    assert 3 == len(colors)

def test_tuple_with_no_elements():
    """Tuple and isinstance property"""
    empty = ()
    assert True is isinstance(empty, tuple)
    assert 0 == len(empty)

def test_tuple_with_one_element():
    """tuple with single item"""
    test1 = (1)
    assert "int" == type(test1).__name__
    test2 = (1,)  # note the syntax used to disambiguate
    assert "tuple" == type(test2).__name__

def test_tuple_can_be_indexed():
    """Tuple and indexing"""
    colors = ('red', 'blue', 'green')
    assert 'red' == colors[0]
    assert 'blue' == colors[1]
    assert 'green' == colors[2]

def test_tuple_can_be_sliced():
    """Tuple and slicing"""
    colors = ('red', 'blue', 'green')
    assert ('blue', 'green') == colors[1:3]
    assert ("blue",) == colors[1:2]  # remember the awkward syntax for single element tuples :)

def test_tuples_are_immutable():
    """Type errors in tuples"""
    colors = ('red', 'blue', 'green')
    try:
        colors[0] = 'orange'
        print(colors[0])
    except TypeError as error_te:
        print(error_te)
        #  print te # note the exception -> SyntaxError: Missing parentheses in call to 'print'.
        assert True

def test_tuples_can_be_nested():
    """Type errors in tuples"""
    top_left = (10, 20)
    bottom_right = (40, 50)
    rectangle = (top_left, bottom_right)

    assert 2 == len(rectangle)
    assert (10, 20) == rectangle[0]
    assert 10 == rectangle[0][0]
    assert 50 == rectangle[1][1]

def test_tuple_unpacking():
    """Variables distribution in tuple"""
    pair = (10, 20)
    var_a, var_b = pair
    var_c = None
    assert 10 == var_a
    assert 20 == var_b
    assert None is var_c
    triplet = (10, 20, 30)
    try:
        var_a, var_b, var_c = triplet
        assert True  # should not come here.
        # adding third variables because of pylint
    except ValueError as error_ve:
        print(error_ve)  # observe what is printed here. =>In Python 3,
        #  printing values changed from being a distinct statement to being
        # an ordinary function call, so it now needs parentheses
        assert True  # ve=>too many values to unpack (expected 2)

def test_sequence_conversion():
    """
    sequences can be converted across forms using the builtin functions.
    """
    word = "testing"
    tup_1 = tuple(word)
    assert ("t", "e", "s", "t", "i", "n", "g") == tup_1
    list_1 = list(word)
    assert ["t", "e", "s", "t", "i", "n", "g"] == list_1
    list_2 = list(tup_1)
    assert ["t", "e", "s", "t", "i", "n", "g"] == list_2
    word2 = str(tup_1)
    assert "('t', 'e', 's', 't', 'i', 'n', 'g')" == word2
    word3 = "".join(tup_1)
    assert "testing" == word3
    word4 = "".join(list_1)
    assert "testing" == word4

THREE_THINGS_I_LEARNT = """
What are tuples
tuples are immutable
nestable
"""
TIME_TAKEN_MINUTES = 15
