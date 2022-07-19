__author__ = 'Hari'

<<<<<<< HEAD
#from tasks.placeholders import *
=======
from tasks.placeholders import *
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa



NOTES = '''
Tuples are yet another sequence type along the lines of strings and lists with
its own characteristics.
'''

def test_tuple_type():
    test_tuple = (1,2)   # note the syntax
    assert __ == type(test_tuple).__name__

def test_tuple_length():
    colors = ('red', 'blue', 'green')
<<<<<<< HEAD
    assert 3 is len(colors)

def test_tuple_with_no_elements():
    empty = ()
    assert True is isinstance(empty, tuple)
    assert 0 is len(empty)
=======
    assert __ == len(colors)

def test_tuple_with_no_elements():
    empty = ()
    assert __ == isinstance(empty, tuple)
    assert __ == len(empty)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_tuple_with_one_element():
    test1 = (1)
    assert __ == type(test1).__name__

    test2 = (1,)  #note the syntax used to disambiguate
    assert __ == type(test2).__name__

def test_tuple_can_be_indexed():
    colors = ('red', 'blue', 'green')
    assert __ == colors[0]
    assert __ == colors[1]
    assert __ == colors[2]

def test_tuple_can_be_sliced():
    colors = ('red', 'blue', 'green')
    assert __ == colors[1:3]
    assert __ == colors[1:2]  #remember the awkward syntax for single element tuples :)


def test_tuples_are_immutable():
    colors = ('red', 'blue', 'green')
    try:
        colors[0] = 'orange'
    except TypeError as _te:
      #  print (_te) # note the exception -> SyntaxError: Missing parentheses in call to 'print'.
        assert True

def test_tuples_can_be_nested():
    top_left = (10,20)
    bottom_right = (40,50)
    rectangle = (top_left, bottom_right)

<<<<<<< HEAD
    assert 2 is len(rectangle)
    assert (10, 20) == rectangle[0]
    assert 10 == rectangle[0][0]
    assert 50 == rectangle[1][1]
=======
    assert __ == len(rectangle)
    assert __ == rectangle[0]
    assert __ == rectangle[0][0]
    assert __ == rectangle[1][1]
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


def test_tuple_unpacking():
    pair = (10, 20)
<<<<<<< HEAD
    _a, _b = pair
    assert 10 == _a
    assert 20 == _b

    triplet = (10, 20, 30)
    try:
        _a, _b = triplet
        assert False  # should not come here.
    except ValueError as _ve:
        print(_ve)  # observe what is printed here.
        #=>In Python 3, printing values changed from being a distinct statement
        #  to being an ordinary function call so it now needs parentheses
        assert True  # ve=>too many values to unpack (expected 2)

=======
    a, b = pair
    assert __ == a
    assert __ == b

    triplet = (10, 20, 30)
    try:
        a, b = triplet
        assert __ # should not come here.
    except ValueError as ve:
        print (ve ) # observe what is printed here. =>In Python 3, printing values changed from being a distinct statement to being an ordinary function call, so it now needs parentheses
        assert  True  # ve=>too many values to unpack (expected 2)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_sequence_conversion():
    """
    sequences can be converted across forms using the builtin functions.
    """
    word = "testing"
    tup_1 = tuple(word)
    assert __ == tup_1

    list_1 = list(word)
    assert __ == list_1

    list_2 = list(tup_1)
    assert __ == list_2

    word2 = str(tup_1)
    assert __ == word2

    word3 = "".join(tup_1)
    assert __ == word3

    word4 = "".join(list_1)
    assert __ == word4

THREE_THINGS_I_LERNT = """
use of tuples
tuple operations
"""

<<<<<<< HEAD
TIME_TAKEN_MINUTES = 25
=======
time_taken_minutes = __
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
