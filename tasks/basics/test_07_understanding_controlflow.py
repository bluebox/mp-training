__author__ = 'Hari'

#from tasks.placeholders import *


NOTES = '''
python has support for standard control flow statements similar to other languages.
iteration over sequences like list, string etc. is built into the language itself (c# has
similar features) and the loops support an else clause which is not common elsewhere.
'''


def test_if():
    value = 1
    if True:
        value = 2
    assert 2 is value

    if not True:
        value = 3
    assert 2 is value

def test_if_else():
    value = 1
    if not True:
        value = 2
    else:
        value = 3
    assert 3 is value

def test_if_elif_else():
    value = 3
    str = "str"
    if value < 0:
        str = "negative"
    elif value == 0:
        str = "zero"
    else:
        str = "positive"

    assert "positive" == str

def test_for_loop_range():
    """
    for loops are used to iterate over arbitrary sequences
    """
    nums = []
    for _x in range(1, 5):
        nums.append(_x)
    assert [1, 2, 3, 4] == nums


def test_for_loop_string():
    chars = []
    for _x in "engine":
        chars.append(_x)
    assert ['e', 'n', 'g', 'i', 'n', 'e'] == chars


def test_for_loop_list():
    result = ""
    for fruit in ["orange", "banana", "apple"]:
        result += fruit
    assert "orangebananaapple" == result

def test_for_loop_list_with_enumerate():
    words = ["one", "two", "three"]
    result = []
    for _p in enumerate(words):
        result.append(_p)

    assert [(0, 'one'), (1, 'two'), (2, 'three')] == result
    mapping = dict(result)
    assert {0: 'one', 1: 'two', 2: 'three'} == mapping


def test_for_loop_dict():
    num_to_word = {1: "one", 2: "two", 3: "three"}
    result = []
    for item in num_to_word:
        result.append(item)
    assert [1, 2, 3] == result


def test_while_loop():
    result = []
    while len(result) < 3:
        result.append(10)
    assert [10, 10, 10] == result


def test_for_loop_break():
    result = []
    for _x in range(1, 10):
        if _x % 5 == 0:
            break
        result.append(_x)

    assert [1, 2, 3, 4] == result


def test_for_loop_continue():
    result = []
    for _x in range(1, 10):
        if _x % 3 == 0:
            continue
        result.append(_x)
    assert [1, 2, 4, 5, 7, 8] == result


def test_nested_loop_break():
    result = []
    for _x in range(2):
        for _y in range(1, 5):
            if _y % 3 == 0:
                break
            result.append(_x)

    assert [0, 0, 1, 1] == result


def test_nested_loop_continue():
    result = []
    for _x in range(2):
        for _y in range(1, 5):
            if _y % 3 == 0:
                continue
            result.append(_x)

    assert [0, 0, 0, 1, 1, 1] == result


def test_nested_loop_break_continue():
    result = []
    for _x in range(3):
        for _y in range(1, 5):
            if _y % 3 == 0:
                continue
            if _x % 2 == 1:
                break
            result.append(_x)

    assert [0, 0, 0, 2, 2, 2] == result

# else on loops is not available in other common languages


def test_for_loop_else_plain():
    result = []
    for _x in range(5):
        result.append(_x)
        print(_x)
    else:
        result.append(10)

    assert [0, 1, 2, 3, 4, 10] == result


def test_for_loop_else_break():
    result = []
    for _x in range(5):
        if _x % 3 == 0:
            break
        result.append(_x)
        print(_x)
    else:
        result.append(10)

    assert [] == result

def test_for_loop_else_continue():
    result = []
    for _x in range(5):
        if _x % 3 == 0:
            continue
        result.append(_x)
        print(_x)
    else:
        result.append(10)

    assert [1, 2, 4, 10] == result

# same as above.


def test_while_loop_else():
    result = []
    _x = 1
    while _x in range(5):
        result.append(_x)
        _x = _x+1
        if _x % 4 == 0:
            break
    else:
        result.append(10)

    assert [1, 2, 3] == result


THREE_THINGS_I_LERNT = """
for loops
while loop
-
"""

TIME_TAKEN_MINUTES = 60
