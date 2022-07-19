__author__ = 'Hari'

<<<<<<< HEAD
#from Tasks.placeholders import *
=======
from tasks.placeholders import *
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


NOTES = '''
dicts are unordered sets of key value pairs which facilitate
fast lookups by key.
'''

def test_dictionary_type():
    test_dict = {1 : "one"}   # note the new syntax
    assert __ == type(test_dict).__name__

def test_dictionary_empty():
    empty_dict_1 = {}
<<<<<<< HEAD
    assert True is isinstance(empty_dict_1, dict)

    empty_dict_2 = dict()  # another way of creating empty dict
    assert 0 is len(empty_dict_2)
=======
    assert __ == isinstance(empty_dict_1, dict)

    empty_dict_2 = dict() # another way of creating empty dict
    assert __ == len(empty_dict_2)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
    assert empty_dict_1 == empty_dict_2

def test_dictionary_create():
<<<<<<< HEAD
    dict_1 = {1: "one", 2: "two"}
    assert True is isinstance(dict_1, dict)
=======
    dict_1 = { 1 : "one", 2 : "two" }
    assert __ == isinstance(dict_1, dict)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

    #init from a sequence of tuple pairs, useful in many cases.
    dict_2 = dict([(1, "one"), (2, "two")])
    assert __ == dict_2[1]
    assert __ == dict_2[2]

def test_dictionary_length():
<<<<<<< HEAD
    word_to_digit = {"one": 1, "two": 2}
    # note that a key value pair is treated as one item
    assert 2 is len(word_to_digit)


def test_dictionary_is_indexed_by_key():
    word_to_digit = {"one": 1, "two": 2}
    assert 1 is word_to_digit["one"]
    assert 2 is word_to_digit["two"]

    try:
        word_to_digit[1]
    except KeyError as _ex:
        # Note that numeric indicies don't mean much like in case of lists and tuples
        print(_ex)   # ex=1(value from dict key-value pair stored in exception)
=======
    word_to_digit = { "one" : 1, "two" : 2}
    assert __ == len(word_to_digit) #note that a key value pair is treated as one item

def test_dictionary_is_indexed_by_key():
    word_to_digit = { "one" : 1, "two" : 2}
    assert __ == word_to_digit["one"]
    assert __ == word_to_digit["two"]

    try:
        word_to_digit[1]
    except Exception as ex:
    #Note that numeric indicies don't mean much like in case of lists and tuples
        print (ex)   # ex=1(value from dict key-value pair stored in exception)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
        assert True

def test_dictionary_is_mutable():
    word_to_digit = { "one" : 1, "two" : 2}

    word_to_digit["three"] = 3
    assert __ == word_to_digit

    del word_to_digit["one"]
    assert __ == word_to_digit

    word_to_digit["one"] = 10
    assert __ == word_to_digit
    # A regular dictionary doesn't track the insertion order.
    # So when iterating over it, items are returned in an arbitrary order.
    # When we want to make sure that items are returned
    # to the order they were inserted, we can use OrderedDict.

def test_dictionary_is_unordered():
    dict1 = { 'one': 1, 'two': 2 }
    dict2 = { 'two': 2, 'one': 1}

    equal = (dict1 == dict2)
<<<<<<< HEAD
    assert True is equal  # True or False?


def test_dictionary_keys_and_values():
    word_to_digit = {"one": 1, "two": 2}
    assert 2 is len(word_to_digit.keys())
    assert 2 is len(word_to_digit.values())
=======
    assert __ == equal # True or False?

def test_dictionary_keys_and_values():
    word_to_digit = { "one" : 1, "two" : 2}
    assert __ == len(word_to_digit.keys())
    assert __ == len(word_to_digit.values())
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
    keys = list(word_to_digit.keys())
    #sort to get a deterministic order
    keys.sort()
    assert __ == keys
    values = list(word_to_digit.values())
    values.sort()
    assert __ == values

def test_dictionary_contains():
    word_to_digit = { "one" : 1, "two" : 2}

<<<<<<< HEAD
    assert True is ("one" in word_to_digit)
    assert True is ("two" in word_to_digit)

    assert True is ("one" in word_to_digit.keys())
    assert True is ("two" in word_to_digit.keys())

    assert False is (1 in word_to_digit)
    assert False is (2 in word_to_digit)

    assert True is (1 in word_to_digit.values())
    assert True is (2 in word_to_digit.values())
=======
    assert __ == ("one" in word_to_digit)
    assert __ == ("two" in word_to_digit)

    assert __ == ("one" in word_to_digit.keys())
    assert __ == ("two" in word_to_digit.keys())

    assert __ == (1 in word_to_digit)
    assert __ == (2 in word_to_digit)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

    assert __ == (1 in word_to_digit.values())
    assert __ == (2 in word_to_digit.values())

def test_valid_dictionary_keys():
    test_dict = {}
    test_dict[1] = 1
    test_dict["one"] = "string"
    try:
        key = []
        test_dict[key] = "list"
<<<<<<< HEAD
    except TypeError as _te:
        print(_te)  # observe the error message.
=======
    except TypeError as te:
        print (te)  #observe the error message.
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
        assert True

    try:
        key = (1,2)
        test_dict[key] = "tuple with immutable elements"
<<<<<<< HEAD
    except TypeError as _te:
        print(_te)
        assert False  # do we reach here?
=======
    except TypeError as te:
        print (te)
        assert False # do we reach here?
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

    try:
        key = (1, [])
        test_dict[key] = "tuple with mutable element"
<<<<<<< HEAD
    except TypeError as _te:
        print(_te)
        assert True  # do we reach here?
=======
    except TypeError as te:
        print (te)
        assert True #do we reach here?
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

    assert {1:1,'one':'string',(1, 2): 'tuple with immutable elements'} == test_dict


THREE_THINGS_I_LERNT = """
using dicts 
using dict operations
"""

<<<<<<< HEAD
TIME_TAKEN_MINUTES = 25

NOTES_2 = '''
=======
time_taken_minutes = ___

notes2= '''
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
It is  a good idea to figure out how dictionaries are generally implemented
under the hood. Go through the thread at
http://stackoverflow.com/questions/730620/how-does-a-hash-table-work
and discuss in the group if required.
'''