""""Code Author"""
__author__ = 'Hari'

from tasks.placeholders import __author__

NOTES = '''Tuples are yet another sequence
type along the lines of strings
and lists with its own characteristics.'''

def test_tuple_type():
    """Tuples"""
    test_tuple = (1,2)   # note the syntax
    assert "tuple" == type(test_tuple).__name__

def test_tuple_length():
    """Length of Tuple"""
    colors = ('red', 'blue', 'green')
    assert 3 == len(colors)

def test_tuple_with_no_elements():
    """Empty Tuple"""
    empty = ()
    assert True is isinstance(empty, tuple)
    assert 0 == len(empty)

def test_tuple_with_one_element():
    """Tuple with 1 elemet acts as Int"""
    test1 = (1)
    assert 'int' == type(test1).__name__
    test2 = (1,)
    #note the syntax used to disambiguate
    assert "tuple" == type(test2).__name__

def test_tuple_can_be_indexed():
    """Tuple Indexing"""
    col = ('red', 'blue', 'green')
    assert "red" == col[0]
    assert "blue" == col[1]
    assert "green" == col[2]

def test_tuple_can_be_sliced():
    """Tuple Slicing"""
    col = ('red', 'blue', 'green')
    assert ("blue","green") == col[1:3]
    assert ("blue",) == col[1:2]
    #remember the awkward syntax for single element tuples :)


def test_tuples_are_immutable():
    """Tuples are immutable"""
    col = ('red', 'blue', 'green')
    try:
        col[0] = 'orange'
    except TypeError:
      # print te # note the exception -> SyntaxError: Missing parentheses in call to 'print'.
        print("SyntaxError: Missing parentheses")
        assert True

def test_tuples_can_be_nested():
    """Nested Tuples"""
    top_left = (10,20)
    bottom_right = (40,50)
    rectangle = (top_left, bottom_right)
    assert 2 == len(rectangle)
    assert (10,20) == rectangle[0]
    assert (10) == rectangle[0][0]
    assert (50) == rectangle[1][1]


def test_tuple_unpacking():
    """Tuple unpacking"""
    pair = (10, 20)
    var, varib = pair
    assert 10 == var
    assert 20 == varib

    triplet = (10, 20, 30)
    try:
        var, varib = triplet
        # should not come here.
    except ValueError:
        print ("ve=>too many values to unpack (expected 2)" )
        assert  True
        # ve=>too many values to unpack (expected 2)

def test_sequence_conversion():
    """sequences can be converted across forms using the builtin functions."""
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

TIME_TAKEN_MINUTES = 10
