__author__ = 'Hari'

from tasks.placeholders import *

NOTES = '''
DICTS ARE UNORDERED SETS OF KEY VALUE PAIRS WHICH FACILITATE
FAST LOOKUPS BY KEY.
'''

def test_dictionary_type():
    test_dict = {1 : "one"}   # note the new syntax
    assert 'dict' == type(test_dict).__name__

def test_dictionary_empty():
    empty_dict_1 = {}
    assert True == isinstance(empty_dict_1, dict)

    empty_dict_2 = dict() # another way of creating empty dict
    assert 0 == len(empty_dict_2)
    assert empty_dict_1 == empty_dict_2

def test_dictionary_create():
    dict_1 = { 1 : "one", 2 : "two" }
    assert True == isinstance(dict_1, dict)

    #init from a sequence of tuple pairs, useful in many cases.
    dict_2 = dict([(1, "one"), (2, "two")])
    assert 'one' == dict_2[1]
    assert 'two' == dict_2[2]

def test_dictionary_length():
    word_to_digit = { "one" : 1, "two" : 2}
    assert 2 == len(word_to_digit) #note that a key value pair is treated as one item

def test_dictionary_is_indexed_by_key():
    word_to_digit = { "one" : 1, "two" : 2}
    assert 1 == word_to_digit["one"]
    assert 2 == word_to_digit["two"]

    try:
        word_to_digit[1]
    except Exception as ex:
    #Note that numeric indicies don't mean much like in case of lists and tuples
        print (ex)   # ex=1(value from dict key-value pair stored in exception)
        assert True

def test_dictionary_is_mutable():
    word_to_digit = { "one" : 1, "two" : 2}

    word_to_digit["three"] = 3
    assert {'one': 1, 'two': 2, 'three': 3} == word_to_digit

    del word_to_digit["one"]
    assert {'two': 2, 'three': 3} == word_to_digit

    word_to_digit["one"] = 10
    assert {'two': 2, 'three': 3, 'one': 10} == word_to_digit
    # A REGULAR DICTIONARY DOESN'T TRACK THE INSERTION ORDER.
    # SO WHEN ITERATING OVER IT, ITEMS ARE RETURNED IN AN ARBITRARY ORDER.
    # WHEN WE WANT TO MAKE SURE THAT ITEMS ARE RETURNED TO THE ORDER THEY WERE INSERTED, WE CAN USE ORDEREDDICT.

def test_dictionary_is_unordered():
    dict1 = { 'one': 1, 'two': 2 }
    dict2 = { 'two': 2, 'one': 1}

    equal = (dict1 == dict2)
    assert True == equal # True or False?

def test_dictionary_keys_and_values():
    word_to_digit = { "one" : 1, "two" : 2}
    assert 2 == len(word_to_digit.keys())
    assert 2 == len(word_to_digit.values())
    keys = list(word_to_digit.keys())
    #sort to get a deterministic order
    keys.sort()
    assert ['one','two'] == keys
    values = list(word_to_digit.values())
    values.sort()
    assert [1,2] == values

def test_dictionary_contains():
    word_to_digit = { "one" : 1, "two" : 2}

    assert True is ("one" in word_to_digit)
    assert True is ("two" in word_to_digit)

    assert True is ("one" in word_to_digit.keys())
    assert True is ("two" in word_to_digit.keys())

    assert False is (1 in word_to_digit)
    assert False is (2 in word_to_digit)

    assert True is (1 in word_to_digit.values())
    assert True is (2 in word_to_digit.values())

def test_valid_dictionary_keys():
    test_dict = {}
    test_dict[1] = 1
    test_dict["one"] = "string"
    try:
        key = []
        test_dict[key] = "list"
    except TypeError as te:
        print (te)  #observe the error message.
        assert True

    try:
        key = (1,2)
        test_dict[key] = "tuple with immutable elements"
    except TypeError as te:
        print (te)
        assert False # do we reach here?

    try:
        key = (1, [])
        test_dict[key] = "tuple with mutable element"
    except TypeError as te:
        print (te)
        assert True #do we reach here?

    assert {1:1,'one':'string',(1, 2): 'tuple with immutable elements'} == test_dict


THREE_THINGS_I_LEARNT = """
-in keyword
-list,tuple in dict
-sort keyword
"""

time_taken_minutes = 30

NOTES_2= '''
IT IS  A GOOD IDEA TO FIGURE OUT HOW DICTIONARIES ARE GENERALLY IMPLEMENTED
UNDER THE HOOD. GO THROUGH THE THREAD AT
HTTP://STACKOVERFLOW.COM/QUESTIONS/730620/HOW-DOES-A-HASH-TABLE-WORK
AND DISCUSS IN THE GROUP IF REQUIRED.
'''