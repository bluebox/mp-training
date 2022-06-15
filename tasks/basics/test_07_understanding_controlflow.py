"""This is the 7th file of python exercise by medplus"""
__author__ = 'Hari'

<<<<<<< HEAD
# from basics.placeholders import *
=======
from tasks.placeholders import *
>>>>>>> 60a8770156443a866425d99b2119f19709472edd

from numpy import empty


NOTES = '''
python has support for standard control flow statements similar to other languages.
iteration over sequences like list, string etc. is built into the language itself (c# has
similar features) and the loops support an else clause which is not common elsewhere.
'''

def test_if():
    value_a = 1
    if True:
        value_a = 2
    assert 2 == value_a

    if not True:
        value = 3
    assert 2 == value_a

def test_if_else():
    value_a = 1
    if not True:
        value_a = 2
    else:
        value_a = 3
    assert 3 == value_a

def test_if_elif_else():
    value_a = 3
    string_str = "str"
    if value_a < 0:
        string_str = "negative"
    elif value_a == 0:
        string_str = "zero"
    else:
        string_str = "positive"

    assert "positive" == string_str

def test_for_loop_range():
    """
    for loops are used to iterate over arbitrary sequences
    """
    list_nums =[]
    for x in range(1,5):
        list_nums.append(x)
    assert [1,2,3,4] == list_nums


def test_for_loop_string():
    list_chars = []
    for x in "engine":
        list_chars.append(x)
    assert ["e","n","g","i","n","e"] == list_chars

def test_for_loop_list():
    str_result = ""
    for item_fruit in ["orange", "banana", "apple"]:
        str_result += item_fruit
    assert "orangebananaapple" == str_result

def test_for_loop_list_with_enumerate():
    list_words = ["one", "two", "three"]
    list_result = []
    for num_p in enumerate(list_words):
        list_result.append(num_p)

    assert [(0, 'one'), (1, 'two'), (2, 'three')] == list_result
    dict_mapping = dict(list_result)
    assert {0: 'one', 1: 'two', 2: 'three'}== dict_mapping

def test_for_loop_dict():
    num_to_word = {1 : "one", 2 : "two", 3 : "three"}
    list_result = []
    for item in num_to_word:
        list_result.append(item)
    assert [1,2,3] == list_result

def test_while_loop():
    list_result = []
    while len(list_result) < 3:
        list_result.append(10)
    assert [10,10,10] == list_result

def test_for_loop_break():
    list_result = []
    for num_x in range(1,10):
        if num_x % 5 == 0:
            break
        list_result.append(num_x)

    assert [1,2,3,4] == list_result

def test_for_loop_continue():
    list_result = []
    for num_x in range (1, 10):
        if num_x % 3 == 0:
            continue
        list_result.append(num_x)
    assert [1,2,4,5,7,8] == list_result

def test_nested_loop_break():
    list_result = []
    for num_x in range(2):
        for num_y in range(1,5):
            if num_y%3 == 0:
                break
            list_result.append(num_x)

    assert [0, 0, 1, 1] == list_result

def test_nested_loop_continue():
    list_result = []
    for num_x in range(2):
        for num_y in range(1,5):
            if num_y%3 == 0:
                continue
            list_result.append(num_x)

    assert [0,0,0,1,1,1] == list_result

def test_nested_loop_break_continue():
    list_result = []
    for num_x in range(3):
        for num_y in range(1,5):
            if num_y%3 == 0:
                continue
            if num_x%2 == 1:
                break
            list_result.append(num_x)

    assert [0,0,0,2,2,2] == list_result

# else on loops is not available in other common languages
def test_for_loop_else_plain():
    list_result = []
    for num_x in range(5):
        list_result.append(num_x)
        print (num_x)
        list_result.append(10)
    # else:
    # commenting because for loop has no break statement or any
    # obstacles

    assert [0,1,2,3,4,10] == list_result

def test_for_loop_else_break():
    list_result = []
    for num_x in range(5):
        if num_x %3 == 0:
            break
        list_result.append(num_x)
        print(num_x)
    else:
        list_result.append(10)

    assert list_result is empty

def test_for_loop_else_continue():
    list_result = []
    for num_x in range(5):
        if num_x %3 == 0:
            continue
        list_result.append(num_x)
        print (num_x)
        list_result.append(10)
    # else:
    # using else without a break statement or any other thing in for
    # loop is waste for commenting it.
    assert [1,2,4,10] == list_result

#same as above.
def test_while_loop_else():
    list_result = []
    num_x = 1
    while num_x in range(5):
        list_result.append(num_x)
        num_x = num_x+1
        if num_x%4 == 0:
            break
    else:
        list_result.append(10)

    assert [1,2,3] == list_result


THREE_THINGS_I_LEARNT = """
Loop, break , continue,% operator
"""

TIME_TAKEN_MINUTES = 15
