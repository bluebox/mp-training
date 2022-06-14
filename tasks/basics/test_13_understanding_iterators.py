__author__ = 'Hari'

from placeholders import *

notes = '''
Iterators are objects that represent a stream of data. next() method on an iterator returns
the next available element. StopIteration is raised when elements are finished.

Python builtins like sequences (strings, lists, tuples), sets and dicts are iterable (ie) you can call iter(obj) on them
and get an iterator object on their data.

Iterators allows us to write functions and implement language features which can
work with any iterable instead of having specialized implementation for each of
list, tuple, string etc.
'''

def test_iterator_type():
    list_iter = iter(["one", "two", "three"])
    assert __ == type(list_iter).__name__
    assert __ == hasattr(list_iter, "next")

    string_iter = iter("hello")
    assert __ == type(string_iter).__name__
    assert __ == hasattr(string_iter, "next")

    tuple_iter = iter((1,2,3))
    assert __ == type(tuple_iter).__name__
    assert __ == hasattr(string_iter, "next")

def test_int_iterable():
    try:
        iter(10)
    except __:  # replace by appropriate except so this test passes
        pass

def test_enumerate_iter():
    list_iter = iter(["one", "two", "three"])
    try:
        assert __ == list_iter.next()
        assert __ == list_iter.next()
        assert __ == list_iter.next()
        assert __ == list_iter.next() #note what happens when items are finished.
    except __:
        pass

#note this function which can convert any iterable into a list.
def convert_to_list(iterable):
    seq_iterator = iter(iterable)
    result = []
    try:
        while True:
            item = seq_iterator.next()
            result.append(item)
    except StopIteration as se:
        return result

def test_convert():
    assert __ == convert_to_list("hello")
    assert __ == convert_to_list((1,2,3,4))
    assert __ == convert_to_list(range(5))

    #string.join also works using the iteration protocol!
    #accepts any iterable
    assert __ == ".".join("hello")
    assert __ == ".".join(["hello", "world"])
    assert __ == ".".join(("hello", "there"))

    try:
        ".".join([1,2,4]) #does not accept all element types though!
    except __ :
        assert True

# list creation also uses the iterator protocol!
# note via help(list). we have already used this, you know how it works now!
def test_list_creation():
    assert __ == list("hello")
    assert __ == list((1,2,3,4))
    assert __ == list(range(5))

# tuple constructor function works the same way!
def test_tuple_creation():
    assert __ == tuple("hello")
    assert __ == tuple([1,2,3,5])

# Note that none of these functions below know which exact type they are working
# with, as long as their parameters support the iterator protocol they will work.
# Consider the immense productivity gain you have with this approach.
def test_functions_that_work_on_iterables():
    test_dict = {"one": 1, "two":2}
    assert ["one", "two"] == sorted(test_dict)
    assert __ == list(test_dict)

# Go through the functions at http://docs.python.org/2/library/functions.html
# and enter all the functions that operate on iterables into the funcs list.
def test_find_builtins_that_work_on_iterables():
    funcs = [__]
    assert ___ == len(funcs)


three_things_i_learnt = """
-
-
-
"""

time_taken_minutes = ___
