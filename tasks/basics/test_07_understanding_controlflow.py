"""understanding control flow"""

__author__ = 'Hari'

from tasks.placeholders import *

NOTES = '''
python has support for standard control flow statements similar to other languages.
iteration over sequences like list, string etc. is built into the language itself (c# has
similar features) and the loops support an else clause which is not common elsewhere.
'''

def test_if():
    """if function"""
    value = 1
    if True:
        value = 2
    assert 2 == value

    if not True:
        value = 3
    assert 2 == value

def test_if_else():
    """if else function"""
    value = 1
    if not True:
        value = 2
    else:
        value = 3
    assert 3 == value

def test_if_elif_else():
    """if elif else function"""
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
    nums =[]
    for item in range(1,5):
        nums.append(item)
    assert [1, 2, 3, 4] == nums


def test_for_loop_string():
    """for loop string"""
    chars = []
    for item in "engine":
        chars.append(item)
    assert ['e', 'n', 'g', 'i', 'n', 'e'] == chars

def test_for_loop_list():
    """for loop list"""
    result = ""
    for fruit in ["orange", "banana", "apple"]:
        result += fruit
    assert "orangebananaapple" == result

def test_for_loop_list_with_enumerate():
    """for loop list with enumerate"""
    words = ["one", "two", "three"]
    result = []
    for item in enumerate(words):
        result.append(item)

    assert [(0, "one"), (1, "two"), (2, "three")] == result
    mapping = dict(result)
    assert {0:"one", 1:"two", 2:"three"} == mapping

def test_for_loop_dict():
    """for loop dict"""
    num_to_word = {1 : "one", 2 : "two", 3 : "three"}
    result = []
    for item in num_to_word:
        result.append(item)
    assert [1, 2, 3] == result

def test_while_loop():
    """while loop"""
    result = []
    while len(result) < 3:
        result.append(10)
    assert [10, 10, 10] == result

def test_for_loop_break():
    """for loop break"""
    result = []
    for item in range(1,10):
        if item % 5 == 0:
            break
        result.append(item)

    assert [1, 2, 3, 4] == result

def test_for_loop_continue():
    """for loop continue"""
    result = []
    for item in range (1, 10):
        if item % 3 == 0:
            continue
        result.append(item)
    assert [1, 2, 4, 5, 7, 8] == result

def test_nested_loop_break():
    """nested loop break"""
    result = []
    for row in range(2):
        for col in range(1,5):
            if col%3 == 0:
                break
            result.append(row)

    assert [0, 0, 1, 1] == result

def test_nested_loop_continue():
    """nested loop continue"""
    result = []
    for row in range(2):
        for col in range(1,5):
            if col%3 == 0:
                continue
            result.append(row)

    assert [0, 0, 0, 1, 1, 1] == result

def test_nested_loop_break_continue():
    """nested loop break continue"""
    result = []
    for row in range(3):
        for col in range(1,5):
            if col%3 == 0:
                continue
            if row%2 == 1:
                break
            result.append(row)

    assert [0, 0, 0, 2, 2, 2] == result

# else on loops is not available in other common languages
def test_for_loop_else_plain():
    """for loop else plain"""
    result = []
    for item in range(5):
        result.append(item)
        print (item)
    else:
        result.append(10)

    assert [0, 1, 2, 3, 4, 10] == result

def test_for_loop_else_break():
    """for loop else break"""
    result = []
    for item in range(5):
        if item%3 == 0:
            break
        result.append(item)
        print(item)
    else:
        result.append(10)

    assert [] == result

def test_for_loop_else_continue():
    """for loop else continue"""
    result = []
    for item in range(5):
        if item %3 == 0:
            continue
        result.append(item)
        print (item)
    else:
        result.append(10)

    assert [1, 2, 4, 10] == result

#same as above.
def test_while_loop_else():
    """while loop else"""
    result = []
    item = 1
    while item in range(5):
        result.append(item)
        item = item+1
        if item%4 == 0:
            break
    else:
        result.append(10)

    assert [1, 2, 3] == result


THREE_THING_I_LEARNT = """
- else after the for loop will not be executed if the loop is terminated by a break statement
- how nested loops operated
- how break and continue statements can be used
"""

TIME_TAKEN_MINUTES = 20
