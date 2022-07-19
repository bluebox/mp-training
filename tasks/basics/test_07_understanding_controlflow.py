__author__ = 'Hari'

<<<<<<< HEAD
#from tasks.placeholders import *
=======
from tasks.placeholders import *
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


NOTES = '''
python has support for standard control flow statements similar to other languages.
iteration over sequences like list, string etc. is built into the language itself (c# has
similar features) and the loops support an else clause which is not common elsewhere.
'''

def test_if():
    value = 1
    if True:
        value = 2
<<<<<<< HEAD
    assert 2 is value

    if not True:
        value = 3
    assert 2 is value
=======
    assert __ == value

    if not True:
        value = 3
    assert __ == value
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_if_else():
    value = 1
    if not True:
        value = 2
    else:
        value = 3
<<<<<<< HEAD
    assert 3 is value
=======
    assert __ == value
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_if_elif_else():
    value = 3
    str = "str"
    if value < 0:
        str = "negative"
    elif value == 0:
        str = "zero"
    else:
        str = "positive"

<<<<<<< HEAD
    assert "positive" == str
=======
    assert __ == str
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_for_loop_range():
    """
    for loops are used to iterate over arbitrary sequences
    """
<<<<<<< HEAD
    nums = []
    for _x in range(1, 5):
        nums.append(_x)
    assert [1, 2, 3, 4] == nums
=======
    nums =[]
    for x in range(1,5):
        nums.append(x)
    assert __ == nums
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


def test_for_loop_string():
    chars = []
<<<<<<< HEAD
    for _x in "engine":
        chars.append(_x)
    assert ['e', 'n', 'g', 'i', 'n', 'e'] == chars

=======
    for x in "engine":
        chars.append(x)
    assert __ == chars
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_for_loop_list():
    result = ""
    for fruit in ["orange", "banana", "apple"]:
        result += fruit
<<<<<<< HEAD
    assert "orangebananaapple" == result
=======
    assert __ == result
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_for_loop_list_with_enumerate():
    words = ["one", "two", "three"]
    result = []
    for _p in enumerate(words):
        result.append(_p)

    assert __ == result
    mapping = dict(result)
    assert __ == mapping

def test_for_loop_dict():
    num_to_word = {1 : "one", 2 : "two", 3 : "three"}
    result = []
    for item in num_to_word:
        result.append(item)
    assert __ == result

def test_while_loop():
    result = []
    while len(result) < 3:
        result.append(10)
    assert __ == result

def test_for_loop_break():
    result = []
<<<<<<< HEAD
    for _x in range(1, 10):
        if _x % 5 == 0:
=======
    for x in range(1,10):
        if x % 5 == 0:
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
            break
        result.append(_x)

    assert __ == result

def test_for_loop_continue():
    result = []
<<<<<<< HEAD
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
=======
    for x in range (1, 10):
        if x % 3 == 0:
            continue
        result.append(x)
    assert __ == result

def test_nested_loop_break():
    result = []
    for x in range(2):
        for y in range(1,5):
            if y%3 == 0:
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
                break
            result.append(_x)

    assert __ == result

def test_nested_loop_continue():
    result = []
<<<<<<< HEAD
    for _x in range(2):
        for _y in range(1, 5):
            if _y % 3 == 0:
=======
    for x in range(2):
        for y in range(1,5):
            if y%3 == 0:
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
                continue
            result.append(_x)

    assert __ == result

def test_nested_loop_break_continue():
    result = []
<<<<<<< HEAD
    for _x in range(3):
        for _y in range(1, 5):
            if _y % 3 == 0:
                continue
            if _x % 2 == 1:
=======
    for x in range(3):
        for y in range(1,5):
            if y%3 == 0:
                continue
            if x%2 == 1:
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
                break
            result.append(_x)

    assert __ == result

# else on loops is not available in other common languages
def test_for_loop_else_plain():
    result = []
<<<<<<< HEAD
    for _x in range(5):
        result.append(_x)
        print(_x)
=======
    for x in range(5):
        result.append(x)
        print (x)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
    else:
        result.append(10)

    assert __ == result

def test_for_loop_else_break():
    result = []
<<<<<<< HEAD
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
=======
    for x in range(5):
        if x %3 == 0:
            break
        result.append(x)
        print( x)
    else:
        result.append(10)

    assert __ == result

def test_for_loop_else_continue():
    result = []
    for x in range(5):
        if x %3 == 0:
            continue
        result.append(x)
        print (x)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
    else:
        result.append(10)

    assert __ == result

#same as above.
def test_while_loop_else():
    result = []
<<<<<<< HEAD
    _x = 1
    while _x in range(5):
        result.append(_x)
        _x = _x+1
        if _x % 4 == 0:
=======
    x = 1
    while x in range(5):
        result.append(x)
        x = x+1
        if x%4 == 0:
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
            break
    else:
        result.append(10)

    assert __ == result


THREE_THINGS_I_LERNT = """
for loops
while loop
-
"""

<<<<<<< HEAD
TIME_TAKEN_MINUTES = 60
=======
time_taken_minutes = ___
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
