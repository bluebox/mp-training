__author__ = 'Hari'
from tasks.placeholders import *

NOTES = '''
python has support for standard control flow statements similar to other languages.
iteration over sequences like list, string etc. is built into the language itself (c# has
similar features) and the loops support an else clause which is not common elsewhere.
'''


def test_if():
    """test if"""
    value = 1
    if True:
        value = 2
    assert 2 == value

    if not True:
        value = 3
    assert 2 == value


def test_if_else():
    """test if else"""
    value = 1
    if not True:
        value = 2
    else:
        value = 3
    assert 3 == value


def test_if_elif_else():
    """test elif"""
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
    for x in range(1, 5):
        nums.append(x)
    assert [1, 2, 3, 4] == nums


def test_for_loop_string():
    """ test for loop"""
    chars = []
    for x in "engine":
        chars.append(x)
    assert ['e', 'n', 'g', 'i', 'n', 'e'] == chars


def test_for_loop_list():
    """ test loop list"""
    result = ""
    for fruit in ["orange", "banana", "apple"]:
        result += fruit
    assert "orangebananaapple" == result


def test_for_loop_list_with_enumerate():
    """list with enumerate"""
    words = ["one", "two", "three"]
    result = []
    for p_word in enumerate(words):
        result.append(p_word)

    assert [(0, 'one'), (1, 'two'), (2, 'three')] == result
    mapping = dict(result)
    assert {0: 'one', 1: 'two', 2: 'three'} == mapping


def test_for_loop_dict():
    """loop dict"""
    num_to_word = {1: "one", 2: "two", 3: "three"}
    result = []
    for item in num_to_word:
        result.append(item)
    assert [1, 2, 3] == result


def test_while_loop():
    """ while"""
    result = []
    while len(result) < 3:
        result.append(10)
    assert [10, 10, 10] == result


def test_for_loop_break():
    """break"""
    result = []
    for num_x in range(1, 10):
        if num_x >5:
            break
        result.append(num_x)

    assert [1, 2, 3, 4] == result


def test_for_loop_continue():
    """ continue"""
    result = []
    for num_x in range(1, 10):
        if num_x % 3 == 0:
            continue
        result.append(num_x)
    assert [1, 2, 4, 5, 7, 8] == result


def test_nested_loop_break():
    """nested loop break"""
    result = []
    for num_x in range(2):
        for num_y in range(1, 5):
            if num_y % 3 == 0:
                break
            result.append(num_x)

    assert [0, 0, 1, 1] == result


def test_nested_loop_continue():
    """continue"""
    result = []
    for num_x in range(2):
        for num_y in range(1, 5):
            if num_y % 3 == 0:
                continue
            result.append(x)

    assert [0, 0, 0, 1, 1, 1] == result


def test_nested_loop_break_continue():
    """break continue"""
    result = []
    for num_x in range(3):
        for num_y in range(1, 5):
            if num_y % 3 == 0:
                continue
            if num_x % 2 == 1:
                break
            result.append(num_x)

    assert [0, 0, 0, 2, 2, 2] == result

# else on loops is not available in other common languages


def test_for_loop_else_plain():
    """loop else plain"""
    result = []
    for x in range(5):
        result.append(x)
        print(x)
    else:
        result.append(10)

    assert [0, 1, 2, 3, 4, 10] == result


def test_for_loop_else_break():
    """else break"""
    result = []
    for num_x in range(5):
        if num_x % 3 == 0:
            break
        result.append(num_x)
        print(num_x)
    
    else:
        result.append(10)

    assert [] == result


def test_for_loop_else_continue():
    """else continue"""
    result = []
    for num_x in range(5):
        if num_x % 3 == 0:
            continue
        result.append(num_x)
        print(num_x)
    else:
        result.append(10)

    assert [1, 2, 4, 10] == result

# same as above.


def test_while_loop_else():
    """while else"""
    result = []
    x = 1
    while x in range(5):
        result.append(x)
        x = x+1
        if x % 4 == 0:
            break
    else:
        result.append(10)

    assert [1, 2, 3] == result


THREE_THINGS_I_TO_LEARN= """
-
-
-
"""
TIME_TAKEN_IN_MINUTES = 20