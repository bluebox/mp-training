"""__author__ = 'Hari'
    5th Exercise
"""


NOTES = '''
dicts are unordered sets of key value pairs which facilitate
fast lookups by key.
'''


def test_dictionary_type():
    """type of dict"""
    test_dict = {1: "one"}  # note the new syntax
    assert "dict" == type(test_dict).__name__


def test_dictionary_empty():
    """empty dictionary"""
    empty_dict_1 = {}
    assert True is isinstance(empty_dict_1, dict)

    empty_dict_2 = dict()  # another way of creating empty dict
    assert 0 == len(empty_dict_2)
    assert empty_dict_1 == empty_dict_2


def test_dictionary_create():
    """create dictionary"""
    dict_1 = {1: "one", 2: "two"}
    assert True is isinstance(dict_1, dict)

    # init from a sequence of tuple pairs, useful in many cases.
    dict_2 = dict([(1, "one"), (2, "two")])
    assert 'one' == dict_2[1]
    assert 'two' == dict_2[2]


def test_dictionary_length():
    """length of dictionary"""
    word_to_digit = {"one": 1, "two": 2}
    assert 2 == len(word_to_digit)  # note that a key value pair is treated as one item


def test_dictionary_is_indexed_by_key():
    """index with try"""
    word_to_digit = {"one": 1, "two": 2}
    assert 1 == word_to_digit["one"]
    assert 2 == word_to_digit["two"]

    try:
        word_to_digit[1]
    except KeyError as ex:
        print(ex)
        assert True


def test_dictionary_is_mutable():
    """dictionary is mutable"""
    word_to_digit = {"one": 1, "two": 2}

    word_to_digit["three"] = 3
    assert {"one": 1, "two": 2, "three": 3} == word_to_digit

    del word_to_digit["one"]
    assert {"two": 2, "three": 3} == word_to_digit

    word_to_digit["one"] = 10
    assert {"one": 10, "two": 2, "three": 3} == word_to_digit


def test_dictionary_is_unordered():
    """dict is unordered"""
    dict1 = {'one': 1, 'two': 2}
    dict2 = {'two': 2, 'one': 1}

    equal = (dict1 == dict2)
    assert True is equal  # True or False?


def test_dictionary_keys_and_values():
    """Key and value pair in dictionary"""
    word_to_digit = {"one": 1, "two": 2}
    assert 2 == len(word_to_digit.keys())
    assert 2 == len(word_to_digit.values())
    keys = list(word_to_digit.keys())
    # sort to get a deterministic order
    keys.sort()
    assert ["one", "two"] == keys
    values = list(word_to_digit.values())
    values.sort()
    assert [1, 2] == values


def test_dictionary_contains():
    """dictionary contains"""
    word_to_digit = {"one": 1, "two": 2}

    assert True is ("one" in word_to_digit)
    assert True is ("two" in word_to_digit)

    assert True is ("one" in word_to_digit.keys())
    assert True is ("two" in word_to_digit.keys())

    assert False is (1 in word_to_digit)
    assert False is (2 in word_to_digit)

    assert True is (1 in word_to_digit.values())
    assert True is (2 in word_to_digit.values())


def test_valid_dictionary_keys():
    """key validation"""
    test_dict = {}
    test_dict[1] = 1
    test_dict["one"] = "string"
    try:
        key = []
        test_dict[key] = "list"
    except TypeError as t_e:
        print(t_e)  # observe the error message.
        assert True

    try:
        key = (1, 2)
        test_dict[key] = "tuple with immutable elements"
    except TypeError as t_e:
        print(t_e)
        assert False  # do we reach here?

    try:
        key = (1, [])
        test_dict[key] = "tuple with mutable element"
    except TypeError as t_e:
        print(t_e)
        assert True

    assert {1: 1, 'one': 'string', (1, 2): 'tuple with immutable elements'} == test_dict

# three_things_i_learnt = """
# Dictionaries and key value pairs
# Accessing key and values separately is also possible
# How dictionary track and not tracks the key value pairs that are entered in it
# """

# time_taken_minutes = 20
#
# notes2= '''
# It is  a good idea to figure out how dictionaries are generally implemented
# under the hood. Go through the thread at
# http://stackoverflow.com/questions/730620/how-does-a-hash-table-work
# and discuss in the group if required.
# '''
