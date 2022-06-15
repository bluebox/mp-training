__author__ = 'Hari'

from tasks.placeholders import *

def test_list_type():
    fruits = ["banana", "orange", "grape"]
    assert 'list' == type(fruits).__name__

def test_list_len():
    fruits = ["banana", "orange", "grape"]
    assert 3 == len(fruits)

def test_list_can_be_indexed():
    fruits = ["banana", "orange", "grape"]
    assert "banana" == fruits[0]
    assert "orange" == fruits[1]
    assert "grape" == fruits[2]
    assert "grape" == fruits[-1]
    assert "orange" == fruits[-2]
    assert "banana" == fruits[-3]

def test_list_is_mutable():
    fruits = ["banana", "orange", "grape"]
    fruits[0] = "mango"
    assert ["mango","orange","grape"] == fruits  #replace __ with expected contents of list

def test_list_can_be_sliced():
    """
     Slicing works the same as on strings
    """
    fruits = ["banana", "orange", "grape"]
    assert [] == fruits[0:0]

    #begin : end
    assert ["banana","orange"] == fruits[0:2]
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


def test_slice_creates_a_new_list():
    fruits = ["banana", "orange", "grape"]
    slice = fruits[0:2]
    slice.append("guava")

    assert ["banana", "orange", "grape"]== fruits # did this change?  No
    assert ["banana", "orange", "guava"]== slice


def test_list_merge():
    fruits = ["banana", "orange", "grape"]
    veggies = ["beetroot", "tomato"]
    all = fruits + veggies

    assert ['banana', 'orange', 'grape', 'beetroot', 'tomato'] == all
    assert ["banana", "orange", "grape"] == fruits
    assert ["beetroot", "tomato"] == veggies
    assert ['orange', 'grape', 'beetroot'] == fruits[1:] + veggies[:1]

def test_list_slice_replacement_is_inplace():
    fruits = ["banana", "orange", "grape"]

    fruits[1:2] = ["litchi", "guava"]
    assert ["banana", "litchi","guava", "grape"] == fruits

    fruits[3:] = ['grape1']
    assert ["banana", "litchi","guava", "grape1"] == fruits

    fruits[:2] = ["banana1", "litchi"]
    assert ["banana1", "litchi","guava", "grape1"] == fruits

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
    assert ["banana","orange","litchi","guava"] == fruits

    fruits.reverse()
    assert ["guava","litchi","orange","banana"] == fruits

    fruits.pop()
    assert  ["guava","litchi","orange"]== fruits

    fruits.pop(0)
    assert ["litchi","orange"] == fruits

def test_list_can_contain_lists():
    fruits = ["orange", "banana"]
    veggies = ["beetroot", "tomato"]
    all = [fruits, veggies]

    assert 2 == len(all)
    assert ["orange","banana"] == all[0]
    assert ["beetroot","tomato"]== all[1]

def test_list_can_contain_objects_of_different_types():
    mixed = ["string", 10]
    assert "string" == mixed[0]
    assert 10 == mixed[1]

def test_list_sort():
    numbers = [ 5, 4, 3, 8 ]
    numbers.sort()
    assert [3,4,5,8] == numbers
    numbers.sort(reverse=True)
    assert [8,5,4,3] == numbers

# if something unexpected happens see,
# http://docs.python.org/2/reference/expressions.html#operator-precedence
# and fix accordingly.
def test_list_membership():
    numbers = [ 5, 4, 3]
    assert 5 == 5 in numbers
    assert False == (10 in numbers)

def test_list_range():
    numbers = range(1,5)
    assert  range(1,5)== numbers

    numbers = range(1, 5, 2)
    assert range(1,5,2) == numbers

time_taken_minutes =1
