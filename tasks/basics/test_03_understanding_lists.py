__author__ = 'Hari'

from tasks.placeholders import *

""" Understanding the use of list in python """
def test_list_type():
    """ function to test type of list"""
    fruits = ["banana", "orange", "grape"]
    assert "list" == type(fruits).__name__
test_list_type()
def test_list_len():
    """ function to test the list """
    fruits = ["banana", "orange", "grape"]
    assert 3 == len(fruits)
test_list_len()
def test_list_can_be_indexed():
    """ function to test the indexing of list """
    fruits = ["banana", "orange", "grape"]
    assert "banana" == fruits[0]
    assert "orange" == fruits[1]
    assert "grape" == fruits[2]
    assert "grape" == fruits[-1]
    assert "orange" == fruits[-2]
    assert "banana" == fruits[-3]
test_list_can_be_indexed()
def test_list_is_mutable():
    """ function to test the list is mutable or not"""
    fruits = ["banana", "orange", "grape"]
    fruits[0] = "mango"
    assert ["mango", "orange", "grape"] == fruits  #replace __ with expected contents of list
test_list_is_mutable()
def test_list_can_be_sliced():
    """
     Slicing works the same as on strings
    """
    fruits = ["banana", "orange", "grape"]
    assert [] == fruits[0:0]

    #begin : end
    assert ["banana", "orange"] == fruits[0:2]
    assert ["banana", "orange", "grape"] == fruits[0:5]
    assert ["orange"] == fruits[1:-1]

    # begin :
    assert ["banana", "orange", "grape"] == fruits[0:]
    assert ["grape"] == fruits[2:]
    assert ["banana", "orange", "grape"] == fruits[0:]

    #: end
    assert [] == fruits[:0]
    assert ["banana", "orange"] == fruits[:2]
    assert ["banana", "orange", "grape"] == fruits[:5]

    # note the invariant
    assert ["banana", "orange", "grape"] == fruits[:1] + fruits[1:]
test_list_can_be_sliced()

def test_slice_creates_a_new_list():
    """ function to check modification of list """
    fruits = ["banana", "orange", "grape"]
    slice = fruits[0:2]
    slice.append("guava")

    assert ["banana", "orange", "grape"] == fruits # did this change?  No
    assert ["banana", "orange", "guava"] == slice
test_slice_creates_a_new_list()

def test_list_merge():
    """ function to check merging of list """
    fruits = ["banana", "orange", "grape"]
    veggies = ["beetroot", "tomato"]
    all = fruits + veggies

    assert ["banana", "orange", "grape", "beetroot", "tomato"] == all
    assert ["banana", "orange", "grape"] == fruits
    assert ["beetroot", "tomato"] == veggies
    assert ["orange", "grape", "beetroot"] == fruits[1:] + veggies[:1]
test_list_merge()

def test_list_slice_replacement_is_inplace():
    """ function to replacement of list items """
    fruits = ["banana", "orange", "grape"]

    fruits[1:2] = ["litchi", "guava"]
    assert ["banana", "litchi", "guava", "grape"] == fruits

    fruits[3:] = ['grape1']
    assert ["banana", "litchi", "guava", "grape1"] == fruits

    fruits[:2] = ["banana1", "litchi"]
    assert ["banana1", "litchi", "guava", "grape1"] == fruits
test_list_slice_replacement_is_inplace()
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
    assert ["banana", "orange"] == fruits

    fruits.extend(["litchi", "guava"])
    assert ["banana", "orange", "litchi", "guava"] == fruits

    fruits.reverse()
    assert ["guava", "litchi", "orange", "banana"] == fruits

    fruits.pop()
    assert ["guava", "litchi", "orange"] == fruits

    fruits.pop(0)
    assert ["litchi", "orange"] == fruits
test_list_common_methods()
def test_list_can_contain_lists():
    """ function to test list can contain list or not"""
    fruits = ["orange", "banana"]
    veggies = ["beetroot", "tomato"]
    all = [fruits, veggies]

    assert 2 == len(all)
    assert ["orange", "banana"] == all[0]
    assert ["beetroot", "tomato"] == all[1]
test_list_can_contain_lists()
def test_list_can_contain_objects_of_different_types():
    """ function to check whether list can contain different data type items"""
    mixed = ["string", 10]
    assert "string" == mixed[0]
    assert 10 == mixed[1]
test_list_can_contain_objects_of_different_types()
def test_list_sort():
    """ function to check sorting of list """
    numbers = [5, 4, 3, 8]
    numbers.sort()
    assert [3, 4, 5, 8] == numbers
    numbers.sort(reverse=True)
    assert [8, 5, 4, 3] == numbers
test_list_sort()
# if something unexpected happens see,
# http://docs.python.org/2/reference/expressions.html#operator-precedence
# and fix accordingly.
def test_list_membership():
    """ function to test list membership """
    numbers = [5, 4, 3]
    assert True is (5 in numbers)
    assert False is (10 in numbers)
test_list_membership()
def test_list_range():
    """ function to check range of list"""
    numbers = range(1, 5)
    assert range(1, 5) == numbers

    numbers = range(1, 5, 2)
    assert range(1, 5, 2) == numbers
test_list_range()

three_things_i_learnt = """
-uses of list
-slicing in list
-application of list
"""

time_taken_minutes = 70
