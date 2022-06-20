"""This is the 7th file of python exercise by medplus"""
__author__ = 'Hari'

# from tasks.placeholders import *


NOTES = '''
python has support for standard control flow statements similar to other languages.
iteration over sequences like list, string etc. is built into the language itself (c# has
similar features) and the loops support an else clause which is not common elsewhere.
'''


def test_if():
    """if, control flow basics"""
    value_a = 1
    value_a = 2
    assert 2 == value_a

    if not True:
        value_a = 3
    assert 2 == value_a


def test_if_else():
    """if else, control flow basics"""
    value_a = 1
    if not True:
        value_a = 2
    else:
        value_a = 3
    assert 3 == value_a


def test_if_elif_else():
    """if elif, control flow basics"""
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
    list_nums = []
    for num_x in range(1, 5):
        list_nums.append(num_x)
    assert [1, 2, 3, 4] == list_nums


def test_for_loop_string():
    """for loop, control flow basics"""
    list_chars = []
    for num_x in "engine":
        list_chars.append(num_x)
    assert ["e", "n", "g", "i", "n", "e"] == list_chars


def test_for_loop_list():
    """for loop iteration"""
    str_result = ""
    for item_fruit in ["orange", "banana", "apple"]:
        str_result = str_result + "".join(item_fruit)
    assert "orangebananaapple" == str_result


def test_for_loop_list_with_enumerate():
    """enumerate functions"""
    list_words = ["one", "two", "three"]
    list_result = []
    for num_p in enumerate(list_words):
        list_result.append(num_p)

    assert [(0, 'one'), (1, 'two'), (2, 'three')] == list_result
    dict_mapping = dict(list_result)
    assert {0: 'one', 1: 'two', 2: 'three'} == dict_mapping


def test_for_loop_dict():
    """For and append in dict"""
    num_to_word = {1: "one", 2: "two", 3: "three"}
    list_result = []
    for item in num_to_word:
        list_result.append(item)
    assert [1, 2, 3] == list_result


def test_while_loop():
    """while loop in dict"""
    list_result = []
    while len(list_result) < 3:
        list_result.append(10)
    assert [10, 10, 10] == list_result


def test_for_loop_break():
    """for loop in dict with break"""
    list_result = []
    for num_x in range(1, 10):
        if num_x % 5 == 0:
            break
        list_result.append(num_x)

    assert [1, 2, 3, 4] == list_result


def test_for_loop_continue():
    """for loop with continue"""
    list_result = []
    for num_x in range(1, 10):
        if num_x % 3 == 0:
            continue
        list_result.append(num_x)
    assert [1, 2, 4, 5, 7, 8] == list_result


def test_nested_loop_break():
    """nested loop and append"""
    list_result = []
    for num_x in range(2):
        for num_y in range(1, 5):
            if num_y % 3 == 0:
                break
            list_result.append(num_x)

    assert [0, 0, 1, 1] == list_result


def test_nested_loop_continue():
    """continue with nested loop and append"""
    list_result = []
    for num_x in range(2):
        for num_y in range(1, 5):
            if num_y % 3 == 0:
                continue
            list_result.append(num_x)

    assert [0, 0, 0, 1, 1, 1] == list_result


def test_nested_loop_break_continue():
    """continue and break in nested loop"""
    list_result = []
    for num_x in range(3):
        for num_y in range(1, 5):
            if num_y % 3 == 0:
                continue
            if num_x % 2 == 1:
                break
            list_result.append(num_x)

    assert [0, 0, 0, 2, 2, 2] == list_result


# else on loops is not available in other common languages
def test_for_loop_else_plain():
    """loop with else function"""
    list_result = []
    for num_x in range(5):
        list_result.append(num_x)
        print(num_x)
    list_result.append(10)
    # else:
    # commenting because for loop has no break statement or any
    # obstacles

    assert [0, 1, 2, 3, 4, 10] == list_result


def test_for_loop_else_break():
    """Loop with else and break"""
    list_result = []
    for num_x in range(5):
        if num_x % 3 == 0:
            break
        list_result.append(num_x)
        print(num_x)
    else:
        list_result.append(10)

    assert len(list_result) == 0


def test_for_loop_else_continue():
    """loop with else function"""
    list_result = []
    for num_x in range(5):
        if num_x % 3 == 0:
            continue
        list_result.append(num_x)
        print(num_x)
    list_result.append(10)
    # else:
    # using else without a break statement or any other thing in for
    # loop is waste for commenting it.
    assert [1, 2, 4, 10] == list_result


# same as above.
def test_while_loop_else():
    """while loop else"""
    list_result = []
    num_x = 1
    while num_x in range(5):
        list_result.append(num_x)
        num_x = num_x + 1
        if num_x % 4 == 0:
            break
    else:
        list_result.append(10)

    assert [1, 2, 3] == list_result


THREE_THINGS_I_LEARNT = """
loop, if else, operators
"""

TIME_TAKEN_MINUTES = 10
