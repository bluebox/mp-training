"""Code Author"""
__author__ = 'Hari'

from tasks.placeholders import __author__

NOTES = '''
python has support for standard control flow statements similar to other languages.
iteration over sequences like list, string etc. is built into the language itself (c# has
similar features) and the loops support an else clause which is not common elsewhere.
'''

def test_if():
    """If Statement"""
    value = 1
    if True:
        value = 2
    assert 2 == value

    if not True:
        value = 3
    assert 2 == value

def test_if_else():
    """If-else Statements"""
    value = 1
    if not True:
        value = 2
    else:
        value = 3
    assert 3 == value

def test_if_elif_else():
    """If-elif-else statements"""
    value = 3
    stri = "str"
    if value < 0:
        stri = "negative"
    elif value == 0:
        stri = "zero"
    else:
        stri = "positive"

    assert "positive" == stri

def test_for_loop_range():
    """for loops are used to iterate over arbitrary sequences"""
    nums =[]
    for var in range(1,5):
        nums.append(var)
    assert [1,2,3,4] == nums


def test_for_loop_string():
    """String Looping using for loop"""
    chars = []
    for var in "engine":
        chars.append(var)
    assert ['e','n','g','i','n','e'] == chars

def test_for_loop_list():
    """List looping using for loop"""
    result = ""
    for fruit in ["orange", "banana", "apple"]:
        result += fruit
    assert "orangebananaapple" == result

def test_for_loop_list_with_enumerate():
    """For loop List with enumerate"""
    words = ["one", "two", "three"]
    result = []
    for var in enumerate(words):
        result.append(var)

    assert [(0, 'one'), (1, 'two'), (2, 'three')] == result
    mapping = dict(result)
    assert {0:"one",1:"two",2:"three"}== mapping

def test_for_loop_dict():
    """Dictionary Looping"""
    num_to_word = {1 : "one", 2 : "two", 3 : "three"}
    result = []
    for item in num_to_word:
        result.append(item)
    assert [1,2,3] == result

def test_while_loop():
    """While Loop"""
    result = []
    while len(result) < 3:
        result.append(10)
    assert [10,10,10] == result

def test_for_loop_break():
    """Break Statement"""
    result = []
    for var in range(1,10):
        if var % 5 == 0:
            break
        result.append(var)

    assert [1,2,3,4] == result

def test_for_loop_continue():
    """Continue Statement"""
    result = []
    for var in range (1, 10):
        if var % 3 == 0:
            continue
        result.append(var)
    assert [1,2,4,5,7,8] == result

def test_nested_loop_break():
    """Nested Loop Break statement"""
    result = []
    for var in range(2):
        for varb in range(1,5):
            if varb%3 == 0:
                break
            result.append(var)

    assert [0,0,1,1] == result

def test_nested_loop_continue():
    """Nested For loop continue"""
    result = []
    for var in range(2):
        for varb in range(1,5):
            if varb%3 == 0:
                continue
            result.append(var)

    assert [0,0,0,1,1,1] == result

def test_nested_loop_break_continue():
    """Nested Loop Break, Continue"""
    result = []
    for var in range(3):
        for varb in range(1,5):
            if varb%3 == 0:
                continue
            if var%2 == 1:
                break
            result.append(var)

    assert [0,0,0,2,2,2] == result

# else on loops is not available in other common languages
def test_for_loop_else_plain():
    """For-Else"""
    result = []
    for var in range(5):
        result.append(var)
        print(var)
    else:
        result.append(10)

    assert [0,1,2,3,4,10] == result

def test_for_loop_else_break():
    """For-Else-Break"""
    result = []
    for var in range(5):
        if var %3 == 0:
            break
        result.append(var)
        print(var)
    else:
        result.append(10)

    assert [] == result

def test_for_loop_else_continue():
    """For-Else-Continue"""
    result = []
    for var in range(5):
        if var %3 == 0:
            continue
        result.append(var)
        print (var)
    else:
        result.append(10)

    assert [1,2,4,10] == result

#same as above.
def test_while_loop_else():
    """While-else"""
    result = []
    var = 1
    while var in range(5):
        result.append(var)
        var = var+1
        if var%4 == 0:
            break
    else:
        result.append(10)

    assert [1,2,3] == result


THREE_THINGS_I_LEARNT = """
-if-else
-for loop
-while loop
"""

TIME_TAKEN_MINUTES = 10
