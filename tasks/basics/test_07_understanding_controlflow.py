'''program file'''
__author__ = 'Hari'
#from tasks.placeholders import *
NOTES = '''
python has support for standard control flow statements similar to other languages.
iteration over sequences like list, string etc. is built into the language itself (c# has
similar features) and the loops support an else clause which is not common elsewhere.
'''

def test_if():
    '''function'''
    value = 1
    if True:
        value = 2
    assert 2 == value
    if not True:
        value = 3
    assert 2 == value

def test_if_else():
    '''function'''
    value = 1
    if not True:
        value = 2
    else:
        value = 3
    assert 3 == value

def test_if_elif_else():
    '''function'''
    value = 3
    str = "str"
    if value < 0:
        str = "negative"
    elif value == 0:
        str = "zero"
    else:
        str = "positive"
    assert  "positive" == str

def test_for_loop_range():
    """
    for loops are used to iterate over arbitrary sequences
    """
    nums =[]
    for x_1 in range(1,5):
        nums.append(x_1)
    assert [1,2,3,4] == nums


def test_for_loop_string():
    '''function'''
    chars = []
    for x_1 in "engine":
        chars.append(x_1)
    assert ['e','n','g','i','n','e'] == chars

def test_for_loop_list():
    '''function'''
    result = ""
    for fruit in ["orange", "banana", "apple"]:
        result += fruit
    assert "orangebananaapple" == result

def test_for_loop_list_with_enumerate():
    '''function'''
    words = ["one", "two", "three"]
    result = []
    for p_1 in enumerate(words):
        result.append(p_1)

    assert  [(0, 'one'),(1, 'two'), (2, 'three')] == result
    mapping = dict(result)
    assert {0:"one",1: "two",2: "three"}== mapping

def test_for_loop_dict():
    '''function'''
    num_to_word = {1 : "one", 2 : "two", 3 : "three"}
    result = []
    for item in num_to_word:
        result.append(item)
    assert [1,2,3] == result

def test_while_loop():
    '''function'''
    result = []
    while len(result) < 3:
        result.append(10)
    assert [10,10,10] == result

def test_for_loop_break():
    '''function'''
    result = []
    for x_1 in range(1,10):
        if x_1 % 5 == 0:
            break
        result.append(x_1)

    assert [1,2,3,4] == result

def test_for_loop_continue():
    '''function'''
    result = []
    for x_1 in range (1, 10):
        if x_1 % 3 == 0:
            continue
        result.append(x_1)
    assert [1,2,4,5,7,8] == result

def test_nested_loop_break():
    '''function'''
    result = []
    for x_1 in range(2):
        for y_1 in range(1,5):
            if y_1%3 == 0:
                break
            result.append(x_1)
    assert [0, 0, 1, 1] == result

def test_nested_loop_continue():
    '''function'''
    result = []
    for x_1 in range(2):
        for y_1 in range(1,5):
            if y_1%3 == 0:
                continue
            result.append(x_1)
    assert [0, 0, 0, 1, 1, 1] == result

def test_nested_loop_break_continue():
    '''function'''
    result = []
    for x_1 in range(3):
        for y_1 in range(1,5):
            if y_1 % 3 == 0:
                continue
            if x_1 % 2 == 1:
                break
            result.append(x_1)
    assert [0, 0, 0, 2, 2, 2] == result

# else on loops is not available in other common languages
def test_for_loop_else_plain():
    '''function'''
    result = []
    for x_1 in range(5):
        result.append(x_1)
        print (x_1)
    else:
        result.append(10)
    assert [0,1,2,3,4,10] == result

def test_for_loop_else_break():
    '''function'''
    result = []
    for x_1 in range(5):
        if x_1 % 3 == 0:
            break
        result.append(x_1)
        print( x_1)
    else:
        result.append(10)
    assert [] == result

def test_for_loop_else_continue():
    '''function'''
    result = []
    for x_1 in range(5):
        if x_1 %3 == 0:
            continue
        result.append(x_1)
        print (x_1)
    else:
        result.append(10)
    assert [1,2,4,10]== result

#same as above.
def test_while_loop_else():
    '''function'''
    result = []
    x_1 = 1
    while x_1 in range(5):
        result.append(x_1)
        x_1 = x_1+1
        if x_1%4 == 0:
            break
    else:
        result.append(10)
    assert [1,2,3] == result
THREE_THINGS_I_LEARNT = """
-
-
-
"""

TIME_TAKEN_MINUTES = 100
