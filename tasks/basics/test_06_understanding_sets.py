#from Tasks.placeholders import *
__author__ = 'Hari'


NOTES = '''
sets are  unordered collection of elements without duplicates. Conceptually they are similar to dicts except that
the keys are not associated with any values.
'''


def test_set_type():
    test_set = {"one", "two", "three"}   # note the new syntax
    assert 'set' == type(test_set).__name__


def test_set_empty():
    # curly braces are used for both sets and dicts, so how do you disambiguate?
    empty_set_wrong = {}
    assert False is isinstance(empty_set_wrong, set)

    empty_set = set()
    assert True is isinstance(empty_set, set)
    assert 0 is len(empty_set)


def test_set_length():
    fruits = {"apple", "apple", "apple"}
    assert 1 is len(fruits)  # are duplicates removed?

    veggies = {"beetroot", "potato", "spinach"}
    assert 3 is len(veggies)


def test_set_creation():
    """
    sets can be created from any sequence like list or a tuple.
    """
    test_list = [1, 2, 1, 3]
    set_1 = set(test_list)
    assert {1, 2, 3} == set_1

    test_string = "apple"
    set_2 = set(test_string)
    assert {'a', 'p', 'l', 'e'} == set_2

    test_dict = {1: "one", 2: "two"}
    set_3 = set(test_dict)
    assert {1, 2} == set_3

    set_4 = set(test_dict.values())
    assert {"one", "two"} == set_4

    set_5 = set(test_dict.items())
    assert {(1, 'one'), (2, 'two')} == set_5


def test_set_membership():
    fruits = {"apple", "mango", "kiwi"}
    assert 'apple' == "apple" in fruits
    assert False is ("dog" in fruits)


def test_set_operations():
    set_1 = {"one", "two", "three"}
    set_2 = {"three", "four"}

    all = set_1 | set_2  # union
    assert {"one", "two", "three", "four"} == all

    common = set_1 & set_2
    assert {"three"} == common

    diff_1 = set_1 - set_2
    assert {"one", "two"} == diff_1

    diff_2 = set_2 - set_1
    assert {"four"} == diff_2

    diff_3 = set_1 - set_1
    assert set() == diff_3

    diff_4 = set_1.symmetric_difference(set_2)
    assert {"one", "two", "four"} == diff_4

    # read up help on other method using the help method in the python console.


def test_set_valid_members():
    test_set = set()
    test_set.add("hello")
    test_set.add(1)
    test_set.add((1, 2))

    try:
        test_set.add([])
    except TypeError as _te:
        print(_te)
        assert True

    try:
        test_set.add((1, []))  # TypeError: unhashable type: 'list'
    except TypeError as _te:
        print(_te)
        assert True


THREE_THINGS_I_LERNT = """
using sets
set operations
error which occur in sets
"""

TIME_TAKEN_MINUTES = 30
