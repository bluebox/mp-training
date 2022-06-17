__author__ = 'Hari'

NOTES = '''
sets are  unordered collection of elements without duplicates. Conceptually they are similar to dicts except that
the keys are not associated with any values.
'''

from tasks.placeholders import *

def test_set_type():
    """set type """
    test_set = {"one", "two", "three"}   # note the new syntax
    assert 'set' == type(test_set).__name__


def test_set_empty():
    """ set empty"""
    # curly braces are used for both sets and dicts, so how do you disambiguate?
    empty_set_wrong = {}
    assert False is  isinstance(empty_set_wrong, set)

    empty_set = set()
    assert True is  isinstance(empty_set, set)
    assert 0 == len(empty_set)


def test_set_length():
    """ set length """
    fruits = {"apple", "apple", "apple"}
    assert 1 == len(fruits)  # are duplicates removed?

    veggies = {"beetroot", "potato", "spinach"}
    assert 3 == len(veggies)


def test_set_creation():
    """
    sets can be created from any sequence like list or a tuple.
    """
    test_list = [1, 2, 1, 3]
    set1 = set(test_list)
    assert {1, 2, 3} == set1

    test_string = "apple"
    set2 = set(test_string)
    assert {'a', 'p', 'l', 'e'} == set2

    test_dict = {1: "one", 2: "two"}
    set3 = set(test_dict)
    assert {1, 2} == set3

    set4 = set(test_dict.values())
    assert {"one", "two"} == set4

    set5 = set(test_dict.items())
    assert {(1, 'one'), (2, 'two')} == set5


def test_set_membership():
    """set membership"""
    fruits = {"apple", "mango", "kiwi"}
    app='apple'
    assert app == "apple" in fruits
    assert False is ("dog" in fruits)


def test_set_operations():
    """ set operations """
    set1 = {"one", "two", "three"}
    set2 = {"three", "four"}

    s_all = set1 | set2  # union
    assert {"one", "two", "three", "four"} == s_all

    common = set1 & set2
    assert {"three"} == common

    diff1 = set1 - set2
    assert {"one", "two"} == diff1

    diff2 = set2 - set1
    assert {"four"} == diff2

    diff3 = set1 - set1
    assert set() == diff3

    diff4 = set1.symmetric_difference(set2)
    assert {"one", "two", "four"} == diff4

    # read up help on other method using the help method in the python console.


def test_set_valid_members():
    """ set valid members"""
    test_set = set()
    test_set.add("hello")
    test_set.add(1)
    test_set.add((1, 2))

    try:
        test_set.add([])
    except TypeError as type_error:
        print(type_error)
        assert True

    try:
        test_set.add((1, []))  # TypeError: unhashable type: 'list'
    except TypeError as type_error:
        print(type_error)
        assert True  



THREE_THINGS_I_LEARNT = """
-
-
-
"""

TIME_TAKEN_MINUTES = 15