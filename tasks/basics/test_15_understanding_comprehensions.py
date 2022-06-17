"CODE AUTHOR"
__author__ = 'Hari'

from tasks.placeholders import __author__

NOTES = '''
 These features make creating lists, dicts and sets from other sequences easy and compact.
 lc -> list comprehensions
 dc -> dict comprehensions
 sc -> set comprehensions
'''


def is_even(var):
    """"Even"""
    return var%2 == 0

def square(var):
    """Square"""
    return var*var

def test_lc_basic():
    """List Comprehension"""
    inpu = [1,2,3]
    result = [2* var for var in inpu]
    assert 3 == len(result)
    assert [2,4,6] == result

def test_lc_map_func():
    """List Comprehension"""
    inpu = [1,2,3]
    result = [square(var) for var in inpu]
    assert [1,4,9] == result

def test_lc_trim_words():
    """List Comprehension"""
    words = ["one\n", "two\n", " three\n"]
    result = [word.strip() for word in words]
    assert ["one","two","three"] == result

def test_lc_filter_func():
    """List Comprehension"""
    inpu = range(10)
    result = [var for var in inpu if is_even(var)]
    assert [0,2,4,6,8] == result

def test_lc_filter_map():
    """List Comprehension"""
    result = [square(var) for var in range(5) if is_even(var)]
    assert [0,4,16] == result

def test_lc_nested():
    """List Comprehension"""
    result = [(var+vari) for var in range(3) for vari in range(3)]
    assert 9 == len(result)
    assert [0,1,2,1,2,3,2,3,4] == result

def test_lc_nested_filter():
    """List Comprehension"""
    result = [(var+vari) for var in range(3) for vari in range(3) if is_even(var+vari)]
    assert 5 == len(result)
    assert [0,2,2,2,4] == result

# dict comprehensions work the same way, you use them to create dicts
# from some source of data
def test_dc_basic():
    """Dictionary Comprehension"""
    result = { var : chr(65 +var) for var in range(4)} # note the braces
    assert 4 == len(result)
    assert {0:"A",1:"B",2:"C",3:"D"} == result
    #Since iteritems() is not valid in python3 I changed it to items()
    result = { var: kar for kar,var in result.items()}
    assert 4 == len(result)
    assert {"A":0,"B":1,"C":2,"D":3} == result

#def test_dc_mapping():
#    """Dictionary Comprehension"""
#    result = { var : ord(var)-ord('A') + 1 for var in string.uppercase[:5] }
#    assert __ == len(result)
#    assert {__}== result

def test_dc_nested():
    """Dictionary Comprehension"""
    result = { (var,vari): var+vari for var in range(2) for vari in range(2)}
    assert 4 == len(result)
    assert {(0,0):0,(0,1):1,(1,0):1,(1,1):2}== result

def test_dc_conditional():
    """Dictionary Comprehension"""
    result = { var : var**2 for var in range (5) if var % 2 == 1}
    assert 2 == len(result)
    assert {1:1,3:9} == result

# set comprehensions are very similar to dict comprehensions except that
# they deal a single value and create set objects
def test_sc_basic():
    """Dictionary Comprehension"""
    result = { var*2 for var in range (4)}
    assert 4 == len(result)
    assert {0,2,4,6}== result

def test_sc_nested():
    """Dictionary Comprehension"""
    result = { var+vari for var in range(3) for vari in range(3)}
    assert 5 == len(result)
    assert {0,1,2,3,4}== result

def test_sc_conditional():
    """Dictionary Comprehension"""
    result = { var**2 for var in range (5) if var % 2 == 1}
    assert 2 == len(result)
    assert {1,9} == result

def test_sc_filtering():
    """Dictionary Comprehension"""
    alls = set(range(10))
    evens = {var for var in alls if var%2 == 0}
    assert {0,2,4,6,8} == evens

    odds = {var for var in alls if var%2 == 1}
    assert {1,3,5,7,9} == odds


THREE_THINGS_I_LEARNT = """
-LIst Comprehensions
-Dict Comprehensions
-Set Comprehensions
"""

TIME_TAKEN_MINUTES = 10
