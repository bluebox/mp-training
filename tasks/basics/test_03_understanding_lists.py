"""This is the 3rd file of python exercise by medplus"""
__author__ = 'Hari'

<<<<<<< HEAD
# from Tasks.basics.placeholders

from numpy import empty

=======
from tasks.placeholders import *
>>>>>>> 60a8770156443a866425d99b2119f19709472edd

def test_list_type():
    """List and its type"""
    fruits = ["banana", "orange", "grape"]
    assert "list" == type(fruits).__name__

def test_list_len():
    """List and its length"""
    fruits = ["banana", "orange", "grape"]
    assert 3 == len(fruits)

def test_list_can_be_indexed():
    """List and its slicing"""
    fruits = ["banana", "orange", "grape"]
    assert "banana" == fruits[0]
    assert "orange" == fruits[1]
    assert "grape" == fruits[2]
    assert "grape" == fruits[-1]
    assert "orange" == fruits[-2]
    assert "banana" == fruits[-3]

def test_list_is_mutable():
    """List mutable property"""
    fruits = ["banana", "orange", "grape"]
    fruits[0] = "mango"
    assert ["mango", "orange", "grape"] == fruits  #replace __ with expected contents of list

def test_list_can_be_sliced():
    """
     Slicing works the same as on strings
    """
    fruits = ["banana", "orange", "grape"]
    assert fruits[0:0] is empty

    #begin : end
    assert ["banana", "orange"] == fruits[0:2]
    assert  ["banana", "orange", "grape"] == fruits[0:5]
    assert  ["orange"] == fruits[1:-1]

    # begin :
    assert ["banana", "orange", "grape"] == fruits[0:]
    assert ["grape"] == fruits[2:]
    assert ["banana", "orange", "grape"] == fruits[0:]

    #: end
    assert fruits[:0] is empty
    assert ["banana", "orange"] == fruits[:2]
    assert ["banana", "orange", "grape"] == fruits[:5]

    # note the invariant
    assert  ["banana", "orange", "grape"] == fruits[:1] + fruits[1:]


def test_slice_creates_a_new_list():
    """List slicing and mutable property"""
    fruits = ["banana", "orange", "grape"]
    slice_fruits = fruits[0:2]
    slice_fruits.append("guava")

    assert ["banana", "orange", "grape"] == fruits # did this change?  No
    assert ["banana", "orange", "guava"] == slice_fruits


def test_list_merge():
    """List addition"""
    fruits = ["banana", "orange", "grape"]
    veggies = ["beetroot", "tomato"]
    all_list = fruits + veggies

    assert ["banana", "orange", "grape","beetroot", "tomato"] == all_list
    assert ["banana", "orange", "grape"] == fruits
    assert ["beetroot", "tomato"] == veggies
    assert ["orange", "grape","beetroot"] == fruits[1:] + veggies[:1]

def test_list_slice_replacement_is_inplace():
    """List slice_replacement"""
    fruits = ["banana", "orange", "grape"]

    fruits[1:2] = ["litchi", "guava"]
    assert ["banana", "litchi", "guava", "grape"] == fruits

    fruits[3:] = ['grape1']
    assert ["banana", "litchi", "guava", "grape1"] == fruits

    fruits[:2] = ["banana1", "litchi"]
    assert ["banana1", "litchi", "guava","grape1"] == fruits

def test_list_common_methods():
    """
     You can find methods supported by lists by entering help([]) in the python console.
     Ignore the methods that start with __ for now.

     For help on a specific function like pop enter help([].pop)
    """
    fruits = []
    fruits.append("orange")

    assert ["orange"] == fruits

    fruits.insert(0, "banana")
    assert ["banana","orange"] == fruits

    fruits.extend(["litchi", "guava"])
    assert ["banana","orange","litchi", "guava"] == fruits

    fruits.reverse()
    assert ["guava","litchi","orange","banana"] == fruits

    fruits.pop()
    assert ["guava","litchi","orange"] == fruits

    fruits.pop(0)
    assert ["litchi","orange"] == fruits

def test_list_can_contain_lists():
    """List inside list"""
    fruits = ["orange", "banana"]
    veggies = ["beetroot", "tomato"]
    all_list = [fruits, veggies]

    assert 2 == len(all_list)
    assert ["orange", "banana"] == all_list[0]
    assert ["beetroot", "tomato"] == all_list[1]

def test_list_can_contain_objects_of_different_types():
    """List items with different datatypes"""
    mixed = ["string", 10]
    assert "string" == mixed[0]
    assert 10 == mixed[1]

def test_list_sort():
    """List sorting property and reverse sorting"""
    numbers = [ 5, 4, 3, 8 ]
    numbers.sort()
    assert [3,4,5,8] == numbers
    numbers.sort(reverse=True)
    assert [8,5,4,3] == numbers

# if something unexpected happens see,
# http://docs.python.org/2/reference/expressions.html#operator-precedence
# and fix accordingly.
def test_list_membership():
    """List slice_replacement"""
    numbers = [ 5, 4, 3]
    assert True is (5 in numbers)
    assert False is (10 in numbers)

def test_list_range():
    """List slice_replacement"""
    numbers = range(1,5)
    assert range(1,5) == numbers

    numbers = range(1, 5, 2)
    assert range(1, 5, 2) == numbers

THREE_THINGS_I_LEARNT = """
characterstics of list,
they are immutable
string slicing
indexing
in operator ,etc
"""

TIME_TAKEN_MINUTES = 10
