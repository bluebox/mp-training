__author__ = 'Hari'

from tasks.placeholders import *

notes = '''
python has support for standard control flow statements similar to other languages.
iteration over sequences like list, string etc. is built into the language itself (c# has
similar features) and the loops support an else clause which is not common elsewhere.
'''

""" Program to check the control flow """
def test_if():
    """ testing the if statement """
    value = 1
    if True:
        value = 2
    assert 2 == value

    if not True:
        value = 3
    assert 2 == value
test_if()
def test_if_else():
    """ function to check if else statement"""
    value = 1
    if not True:
        value = 2
    else:
        value = 3
    assert 3 == value
test_if_else()
def test_if_elif_else():
    """ function to check if elif """
    value = 3
    str = "str"
    if value < 0:
        str = "negative"
    elif value == 0:
        str = "zero"
    else:
        str = "positive"

    assert "positive" == str
test_if_elif_else()

def test_for_loop_range():
    """
    for loops are used to iterate over arbitrary sequences
    """
    nums =[]
    for x_1 in range(1,5):
        nums.append(x_1)
    assert [1, 2, 3, 4] == nums
test_for_loop_range()


def test_for_loop_string():
    """ function to test the loop string """
    chars = []
    for x_1 in "engine":
        chars.append(x_1)
    assert ["e", "n", "g", "i", "n", "e"] == chars
test_for_loop_string()

def test_for_loop_list():
    """ function to test the loop list """
    result = ""
    for fruit in ["orange", "banana", "apple"]:
        result += fruit
    assert "orangebananaapple" == result
test_for_loop_list()

def test_for_loop_list_with_enumerate():
    """ function to test the loop list with enumerate"""
    words = ["one", "two", "three"]
    result = []
    for p_1 in enumerate(words):
        result.append(p_1)

    assert [(0, "one"), (1, "two"), (2, "three")] == result
    mapping = dict(result)
    assert {0: "one", 1: "two", 2: "three"} == mapping
test_for_loop_list_with_enumerate()

def test_for_loop_dict():
    """ function to test for loop dict """
    num_to_word = {1: "one", 2: "two", 3: "three"}
    result = []
    for item in num_to_word:
        result.append(item)
    assert [1, 2, 3] == result
test_for_loop_dict()

def test_while_loop():
    """ function to test the while loop """
    result = []
    while len(result) < 3:
        result.append(10)
    assert [10, 10, 10] == result
test_while_loop()

def test_for_loop_break():
    """ function to test for loop and break """
    result = []
    for x_1 in range(1, 10):
        if x_1 % 5 == 0:
            break
        result.append(x_1)

    assert [1, 2, 3, 4] == result
test_for_loop_break()

def test_for_loop_continue():
    """ function to test loop continue """
    result = []
    for x in range(1, 10):
        if x % 3 == 0:
            continue
        result.append(x)
    assert [1, 2, 4, 5, 7, 8] == result
test_for_loop_continue()

def test_nested_loop_break():
    """ function to test the nested loop break """
    result = []
    for x in range(2):
        for y in range(1, 5):
            if y % 3 == 0:
                break
            result.append(x)

    assert [0, 0, 1, 1] == result
test_nested_loop_break()

def test_nested_loop_continue():
    """ function to test nested loop and continue """
    result = []
    for x in range(2):
        for y_1 in range(1, 5):
            if y_1 % 3 == 0:
                continue
            result.append(x)

    assert [0, 0, 0, 1, 1, 1] == result
test_nested_loop_continue()

def test_nested_loop_break_continue():
    """ function to test nested loop break """
    result = []
    for x in range(3):
        for y in range(1, 5):
            if y % 3 == 0:
                continue
            if x % 2 == 1:
                break
            result.append(x)

    assert [0, 0, 0, 2, 2, 2] == result
test_nested_loop_break_continue()

# else on loops is not available in other common languages
def test_for_loop_else_plain():
    """ function to check loop else plain"""
    result = []
    for x in range(5):
        result.append(x)
        print (x)
    else:
        result.append(10)

    assert [0, 1, 2, 3, 4, 10] == result
test_for_loop_else_plain()

def test_for_loop_else_break():
    """ function to test loop else break """
    result = []
    for x in range(5):
        if x % 3 == 0:
            break
        result.append(x)
        print(x)
    else:
        result.append(10)

    assert [] == result
test_for_loop_else_break()

def test_for_loop_else_continue():
    """ function to test loop else continue """
    result = []
    for x_1 in range(5):
        if x_1 %3 == 0:
            continue
        result.append(x_1)
        print(x_1)
    else:
        result.append(10)

    assert [1, 2, 4, 10] == result
test_for_loop_else_continue()

#same as above.
def test_while_loop_else():
    """ function to test while loop else """
    result = []
    x = 1
    while x in range(5):
        result.append(x)
        x = x+1
        if x%4 == 0:
            break
    else:
        result.append(10)

    assert [1, 2, 3] == result
test_while_loop_else()


three_things_i_learnt = """
-enumerate in list gives numbering to the elements
-for else can be used together
-behavior of range statement
"""

time_taken_minutes = 60

