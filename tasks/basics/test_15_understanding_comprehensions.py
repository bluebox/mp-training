'''comprehensions'''
__author__ = 'Hari'

import string

NOTES_1 = '''
 These features make creating lists, dicts and sets
 from other sequences easy and compact.
 lc -> list comprehensions
 dc -> dict comprehensions
 sc -> set comprehensions
'''



def is_even(x_1):
    '''comprehensions'''
    return x_1%2 == 0

def square(x_1):
    '''comprehensions'''
    return x_1*x_1

def test_lc_basic():
    '''comprehensions'''
    input = [1, 2, 3]
    result = [2* x for x in input]
    assert len(result) == 3
    assert result == [2, 4, 6]

def test_lc_map_func():
    '''comprehensions'''
    input = [1, 2, 3]
    result = [square(x_1) for x_1 in input]
    assert result == [1, 4, 9]

def test_lc_trim_words():
    '''comprehensions'''
    words = ["one\n", "two\n", " three\n"]
    result = [word.strip() for word in words]
    assert result == ['one', 'two', 'three']

def test_lc_filter_func():
    '''comprehensions'''
    input = range(10)
    result = [x for x in input if is_even(x)]
    assert result == [0, 2, 4, 6, 8]

def test_lc_filter_map():
    '''comprehensions'''
    result = [square(x) for x in range(5) if is_even(x)]
    assert result == [0, 4, 16]

def test_lc_nested():
    '''comprehensions'''
    result = [(x+y) for x in range(3) for y in range(3)]
    assert len(result) == 9
    assert result == [0, 1, 2, 1, 2, 3, 2, 3, 4]

def test_lc_nested_filter():
    '''comprehensions'''
    result = [(x+y) for x in range(3) for y in range(3) if is_even(x+y)]
    assert len(result) == 5
    assert result == [0, 2, 2, 2, 4]

# dict comprehensions work the same way, you use them to create dicts
# from some source of data
def test_dc_basic():
    '''comprehensions'''
    result = {i : chr(65 +i) for i in range(4)} # note the braces
    assert len(result) == 4
    assert result == {0: 'A', 1: 'B', 2: 'C', 3: 'D'}

    result = {v: k for k, v in result.items()}
    assert len(result) == 4
    assert result == {'A': 0, 'B': 1, 'C': 2, 'D':3}

def test_dc_mapping():
    '''comprehensions'''
    result = {x : ord(x)-ord('A') + 1 for x in string.ascii_uppercase[:5]}
    assert len(result) == 5
    assert result == {'A': 1, 'B': 2, 'C': 3, 'D':4, 'E':5}

def test_dc_nested():
    '''comprehensions'''
    result = {(x, y): x+y for x in range(2) for y in range(2)}
    assert len(result) == 4
    assert result == {(0, 0): 0, (0, 1): 1, (1, 0): 1, (1, 1): 2}

def test_dc_conditional():
    '''comprehensions'''
    result = {x : x**2 for x in range(5) if x % 2 == 1}
    assert len(result) == 2
    assert result == {1: 1, 3: 9}

# set comprehensions are very similar to dict comprehensions except that
# they deal a single value and create set objects
def test_sc_basic():
    '''comprehensions'''
    result = {x*2 for x in range(4)}
    assert len(result) == 4
    assert result == {0, 2, 4, 6}

def test_sc_nested():
    '''comprehensions'''
    result = {x+y for x in range(3) for y in range(3)}
    assert len(result) == 5
    assert result == {0, 1, 2, 3, 4}

def test_sc_conditional():
    '''comprehensions'''
    result = {x**2 for x in range(5) if x % 2 == 1}
    assert len(result) == 2
    assert result == {1, 9}

def test_sc_filtering():
    '''comprehensions'''
    all = set(range(10))
    evens = {x for x in all if x%2 == 0}
    assert evens == {0, 2, 4, 6, 8}

    odds = {x for x in all if x%2 == 1}
    assert odds == {1, 3, 5, 7, 9}


THREE_THINGS_I_LEARNT = """
- These features make creating lists, dicts and sets
from other sequences easy and compact.
 lc -> list comprehensions
 dc -> dict comprehensions
 sc -> set comprehensions
"""

TIME_TAKEN_MINUTES = 15
