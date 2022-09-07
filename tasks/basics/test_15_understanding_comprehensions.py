__author__ = 'Hari'

notes = '''
 These features make creating lists, dicts and sets from other sequences easy and compact.
 lc -> list comprehensions
 dc -> dict comprehensions
 sc -> set comprehensions
'''

from tasks.placeholders import *
import string

def is_even(x):
    '''method'''
    return x%2 == 0

def square(x):
    '''method'''
    return x*x

def test_lc_basic():
    '''method'''
    input = [1,2,3]
    result = [2* x for x in input]
    assert 3 == len(result)
    assert [2,4,6] == result

def test_lc_map_func():
    '''method'''
    input = [1,2,3]
    result = [square(x) for x in input]
    assert [1,4,9] == result

def test_lc_trim_words():
    '''method'''
    words = ["one\n", "two\n", " three\n"]
    result = [word.strip() for word in words]
    assert ["one",'two','three'] == result

def test_lc_filter_func():
    '''method'''
    input = range(10)
    result = [x for x in input if is_even(x)]
    assert [0,2,4,6,8] == result

def test_lc_filter_map():
    '''method'''
    result = [square(x) for x in range(5) if is_even(x)]
    assert [0,4,16] == result

def test_lc_nested():
    '''method'''
    result = [(x+y) for x in range(3) for y in range(3)]
    assert 9 == len(result)
    assert [0,1,2,1,2,3,2,3,4] == result

def test_lc_nested_filter():
    '''method'''
    result = [(x+y) for x in range(3) for y in range(3) if is_even(x+y)]
    assert 5 == len(result)
    assert [0,2,2,2,4] == result

# dict comprehensions work the same way, you use them to create dicts
# from some source of data
def test_dc_basic():
    '''method'''
    result = { i : chr(65 +i) for i in range(4)} # note the braces
    assert 4 == len(result)
    assert {0:'A',1:'B',2:'C',3:'D'} == result
    result = { v: k for k,v in result.items()}
    assert 4 == len(result)
    assert {'A':0,'B':1,'C':2,'D':3} == result

def test_dc_mapping():
    '''method'''
    result = { x : ord(x)-ord('A') + 1 for x in string.ascii_uppercase[:5] }
    assert 5 == len(result)
    assert {'A':1,'B':2,'C':3,'D':4,'E':5}== result

def test_dc_nested():
    '''method'''
    result = { (x,y): x+y for x in range(2) for y in range(2)}
    assert 4 == len(result)
    assert { (0,0):0 ,(0,1):1 ,(1,0):1 ,(1,1):2}== result

def test_dc_conditional():
    '''method'''
    result = { x : x**2 for x in range (5) if x % 2 == 1}
    assert 2 == len(result)
    assert {1:1,3:9} == result

# set comprehensions are very similar to dict comprehensions except that
# they deal a single value and create set objects
def test_sc_basic():
    '''method'''
    result = { x*2 for x in range (4)}
    assert 4 == len(result)
    assert {0,2,4,6}== result

def test_sc_nested():
    '''method'''
    result = { x+y for x in range(3) for y in range(3)}
    assert 5 == len(result)
    assert {0,1,2,3,4}== result

def test_sc_conditional():
    '''method'''
    result = { x**2 for x in range (5) if x % 2 == 1}
    assert 2 == len(result)
    assert {1,9} == result

def test_sc_filtering():
    '''method'''
    all = set(range(10))
    evens = {x for x in all if x%2 == 0}
    assert {0,2,4,6,8} == evens

    odds = {x for x in all if x%2 == 1}
    assert {1,3,5,7,9} == odds


THREE = """
-
-
-
"""

TIME = 14
