'''Sets'''
__author__ = 'Hari'

# from tasks.basics.test_00_understanding_assert import
# NOTES_1, THREE_THINGS_I_LEARNT, TIME_TAKEN_MINUTES

NOTES_1 = '''
sets are  unordered collection of elements without
duplicates. Conceptually they are similar to dicts except that
the keys are not associated with any values.
'''

# from tasks.placeholders import  *

def test_set_type():
    '''Sets'''
    test_set = {"one", "two", "three"}   # note the new syntax
    assert type(test_set).__name__ == 'set'

def test_set_empty():
    '''Sets'''
    empty_set_wrong = {}  #curly braces are used for both
    # sets and dicts, so how do you disambiguate?
    assert isinstance(empty_set_wrong, set) is False

    empty_set = set()
    assert isinstance(empty_set, set) is True
    assert len(empty_set) == 0

def test_set_length():
    '''Sets'''
    fruits = {"apple", "apple", "apple"}
    assert len(fruits) == 1  #are duplicates removed?

    veggies = {"beetroot", "potato", "spinach"}
    assert len(veggies) == 3

def test_set_creation():
    """
    sets can be created from any sequence like list or a tuple.
    """
    test_list = [1, 2, 1, 3]
    set1 = set(test_list)
    assert set1 == {1, 2, 3}

    test_string = "apple"
    set2 = set(test_string)
    assert set2 == {'p', 'a', 'l', 'e'}

    test_dict = {1: "one", 2 : "two"}
    set3 = set(test_dict)
    assert set3 == {1, 2}

    set4 = set(test_dict.values())
    assert set4 == {'one', 'two'}

    set5 = set(test_dict.items())
    assert set5 == {(1, 'one'), (2, 'two')}

def test_set_membership():
    '''Sets'''
    fruits = {"apple", "mango", "kiwi"}
    assert "apple" in fruits == {"apple", "mango", "kiwi"}
    assert ("dog" in fruits) is False

def test_set_operations():
    '''Sets'''
    set1 = {"one", "two", "three"}
    set2 = {"three", "four"}

    all = set1 | set2 # union
    assert all == {'four', 'one', 'two', 'three'}

    common = set1 & set2
    assert common == {'three'}

    diff1 = set1 - set2
    assert diff1 == {'one', 'two'}

    diff2 = set2 - set1
    assert diff2 == {'four'}

    diff3 = set1 - set1
    assert diff3 == set()

    diff4 = set1.symmetric_difference(set2)
    assert diff4 == {'four', 'one', 'two'}

    #read up help on other method using the help method in the python console.

def test_set_valid_members():
    '''Sets'''
    test_set = set()
    test_set.add("hello")
    test_set.add(1)
    test_set.add((1, 2))

    try:
        test_set.add([])
    except TypeError as te_1:
        print(te_1)
        assert True

    try:
        test_set.add((1, []))   #  TypeError: unhashable type: 'list'
    except TypeError as te_1:
        print(te_1)
        assert True

    assert test_set == {1, 'hello', (1, 2)}

THREE_THINGS_I_LEARNT = """
1. sets are  unordered collection of elements without duplicates.
2. elements are sorted in sets
3. creating  sets from lists or tuple
"""

TIME_TAKEN_MINUTES = 8
