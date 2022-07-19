__author__ = 'Hari'

<<<<<<< HEAD
#from tasks.placeholders import *


=======
from tasks.placeholders import *
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_list_type():
    fruits = ["banana", "orange", "grape"]
    assert __ == type(fruits).__name__

def test_list_len():
    fruits = ["banana", "orange", "grape"]
<<<<<<< HEAD
    assert 3 is len(fruits)

def test_list_can_be_indexed():
    fruits = ["banana", "orange", "grape"]
    assert "banana" == fruits[0]
    assert "orange" == fruits[1]
    assert "grape" == fruits[2]
    assert "grape" == fruits[-1]
    assert "orange" == fruits[-2]
    assert "banana" == fruits[-3]
=======
    assert __ == len(fruits)

def test_list_can_be_indexed():
    fruits = ["banana", "orange", "grape"]
    assert __ == fruits[0]
    assert __ == fruits[1]
    assert __ == fruits[2]
    assert __ == fruits[-1]
    assert __ == fruits[-2]
    assert __ == fruits[-3]
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_list_is_mutable():
    fruits = ["banana", "orange", "grape"]
    fruits[0] = "mango"
    assert __ == fruits  #replace __ with expected contents of list

def test_list_can_be_sliced():
    """
     Slicing works the same as on strings
    """
    fruits = ["banana", "orange", "grape"]
    assert __ == fruits[0:0]

    #begin : end
    assert __ == fruits[0:2]
    assert __ == fruits[0:5]
    assert __ == fruits[1:-1]

    # begin :
    assert __ == fruits[0:]
    assert __ == fruits[2:]
    assert __ == fruits[0:]

    #: end
    assert [] == fruits[:0]
    assert __ == fruits[:2]
    assert __ == fruits[:5]

    # note the invariant
    assert __ == fruits[:1] + fruits[1:]


def test_slice_creates_a_new_list():
    fruits = ["banana", "orange", "grape"]
    slice_1 = fruits[0:2]
    slice_1.append("guava")

<<<<<<< HEAD
    assert ["banana", "orange", "grape"] == fruits  # did this change?  No
    assert ['banana', 'orange', 'guava'] == slice_1
=======
    assert __ == fruits # did this change?  No
    assert __ == slice
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


def test_list_merge():
    fruits = ["banana", "orange", "grape"]
    veggies = ["beetroot", "tomato"]
    all_1 = fruits + veggies

<<<<<<< HEAD
    assert ['banana', 'orange', 'grape', 'beetroot', 'tomato'] == all_1
    assert ["banana", "orange", "grape"] == fruits
    assert ["beetroot", "tomato"] == veggies
    assert ["orange", "grape", "beetroot"] == fruits[1:] + veggies[:1]

=======
    assert __ == all
    assert __ == fruits
    assert __ == veggies
    assert __ == fruits[1:] + veggies[:1]
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_list_slice_replacement_is_inplace():
    fruits = ["banana", "orange", "grape"]

    fruits[1:2] = ["litchi", "guava"]
    assert __ == fruits

    fruits[3:] = ['grape1']
    assert __ == fruits

    fruits[:2] = ["banana1", "litchi"]
    assert __ == fruits

def test_list_common_methods():
    """
     You can find methods supported by lists by entering help([]) in the python console.
     Ignore the methods that start with __ for now.

     For help on a specific function like pop enter help([].pop)
    """
    fruits = []
    fruits.append("orange")

    assert __ == fruits

    fruits.insert(0, "banana")
    assert __ == fruits

    fruits.extend(["litchi", "guava"])
    assert __ == fruits

    fruits.reverse()
    assert __ == fruits

    fruits.pop()
    assert __ == fruits

    fruits.pop(0)
    assert __ == fruits

def test_list_can_contain_lists():
    fruits = ["orange", "banana"]
    veggies = ["beetroot", "tomato"]
<<<<<<< HEAD
    all_2 = [fruits, veggies]

    assert 2 == len(all_2)
    assert ["orange", "banana"] == all_2[0]
    assert ["beetroot", "tomato"] == all_2[1]

def test_list_can_contain_objects_of_different_types():
    mixed = ["string", 10]
    assert "string" == mixed[0]
    assert 10 == mixed[1]
=======
    all = [fruits, veggies]

    assert __ == len(all)
    assert __ == all[0]
    assert __ == all[1]

def test_list_can_contain_objects_of_different_types():
    mixed = ["string", 10]
    assert __ == mixed[0]
    assert __ == mixed[1]
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_list_sort():
    numbers = [ 5, 4, 3, 8 ]
    numbers.sort()
    assert __ == numbers
    numbers.sort(reverse=True)
    assert __ == numbers

# if something unexpected happens see,
# http://docs.python.org/2/reference/expressions.html#operator-precedence
# and fix accordingly.
def test_list_membership():
<<<<<<< HEAD
    numbers = [5, 4, 3]
    _a=5
    assert 5 is _a in numbers
    assert False is (10 in numbers)

=======
    numbers = [ 5, 4, 3]
    assert __ == 5 in numbers
    assert __ == (10 in numbers)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_list_range():
    numbers = range(1,5)
    assert __ == numbers

    numbers = range(1, 5, 2)
    assert __ == numbers

THREE_THINGS_I_LERNT = """
Lists
list_operations
range_function
"""

<<<<<<< HEAD
TIME_TAKEN_MINUTES = 25
=======
time_taken_minutes = __
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
