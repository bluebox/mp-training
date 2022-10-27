'''Controlflow'''
__author__ = 'Hari'

# from tasks.placeholders import *

NOTES_1 = '''
python has support for standard control
flow statements similar to other languages.
iteration over sequences like list, string
etc. is built into the language itself (c# has
similar features) and the loops support
an else clause which is not common elsewhere.
'''

def test_if():
    '''Controlflow'''
    value = 1
    if True:
        value = 2
    assert value == 2

    if not True:
        value = 3
    assert value == 2

def test_if_else():
    '''Controlflow'''
    value = 1
    if not True:
        value = 2
    else:
        value = 3
    assert value == 3

def test_if_elif_else():
    '''Controlflow'''
    value = 3
    str = "str"
    if value < 0:
        str = "negative"
    elif value == 0:
        str = "zero"
    else:
        str = "positive"

    assert str == "positive"

def test_for_loop_range():
    """
    for loops are used to iterate over arbitrary sequences
    """
    nums = []
    for x_1 in range(1, 5):
        nums.append(x_1)
    assert nums == [1, 2, 3, 4]


def test_for_loop_string():
    '''Controlflow'''
    chars = []
    for x_1 in "engine":
        chars.append(x_1)
    assert chars == ['e', 'n', 'g', 'i', 'n', 'e']

def test_for_loop_list():
    '''Controlflow'''
    result = ""
    for fruit in ["orange", "banana", "apple"]:
        result += fruit
    assert result == "orangebananaapple"

def test_for_loop_list_with_enumerate():
    '''Controlflow'''
    words = ["one", "two", "three"]
    result = []
    for p_1 in enumerate(words):
        result.append(p_1)

    assert result == [(0, 'one'), (1, 'two'), (2, 'three')]
    mapping = dict(result)
    assert mapping == {0: 'one', 1: 'two', 2: 'three'}

def test_for_loop_dict():
    '''Controlflow'''
    num_to_word = {1 : "one", 2 : "two", 3 : "three"}
    result = []
    for item in num_to_word:
        result.append(item)
    assert result == [1, 2, 3]

def test_while_loop():
    '''Controlflow'''
    result = []
    while len(result) < 3:
        result.append(10)
    assert result == [10, 10, 10]

def test_for_loop_break():
    '''Controlflow'''
    result = []
    for x_1 in range(1, 10):
        if x_1 % 5 == 0:
            break
        result.append(x_1)

    assert result == [1, 2, 3, 4]

def test_for_loop_continue():
    '''Controlflow'''
    result = []
    for x_1 in range(1, 10):
        if x_1 % 3 == 0:
            continue
        result.append(x_1)
    assert result == [1, 2, 4, 5, 7, 8]

def test_nested_loop_break():
    '''Controlflow'''
    result = []
    for x_1 in range(2):
        for y_1 in range(1, 5):
            if y_1%3 == 0:
                break
            result.append(x_1)

    assert result == [0, 0, 1, 1]

def test_nested_loop_continue():
    '''Controlflow'''
    result = []
    for x_1 in range(2):
        for y_1 in range(1, 5):
            if y_1%3 == 0:
                continue
            result.append(x_1)

    assert result == [0, 0, 0, 1, 1, 1]

def test_nested_loop_break_continue():
    '''Controlflow'''
    result = []
    for x_1 in range(3):
        for y_1 in range(1, 5):
            if y_1%3 == 0:
                continue
            if x_1%2 == 1:
                break
            result.append(x_1)

    assert result == [0, 0, 0, 2, 2, 2]

# else on loops is not available in other common languages
def test_for_loop_else_plain():
    '''Controlflow'''
    result = []
    for x_1 in range(5):
        result.append(x_1)
        print(x_1)
    else:
        result.append(10)

    assert result == [0, 1, 2, 3, 4, 10]

def test_for_loop_else_break():
    '''Controlflow'''
    result = []
    for x_1 in range(5):
        if x_1%3 == 0:
            break
        result.append(x_1)
        print(x_1)
    else:
        result.append(10)

    assert result == []

def test_for_loop_else_continue():
    '''Controlflow'''
    result = []
    for x_1 in range(5):
        if x_1%3 == 0:
            continue
        result.append(x_1)
        print(x_1)
    else:
        result.append(10)

    assert result == [1, 2, 4, 10]

#same as above.
def test_while_loop_else():
    '''Controlflow'''
    result = []
    x_1 = 1
    while x_1 in range(5):
        result.append(x_1)
        x_1 = x_1+1
        if x_1%4 == 0:
            break
    else:
        result.append(10)

    assert result == [1, 2, 3]


THREE_THINGS_I_LEARNT = """
1. else on loops is not available in other
common languages but available in python
2. insertion of elements in lists, dict
3. Use of if, else condition
"""

TIME_TAKEN_MINUTES = 10
