"""This is the 14th file of python exercise by medplus"""
__author__ = 'Hari'

NOTES = '''
Generators are a easy way to create your own custom iterators. They look like
functions but do a lot of heavy lifting under the covers.

There are also useful when you want to 'generate' data on demand rather than
create all data at one shot - typically in memory constrained scenarios.

You can also think of generators as resumable functions. The caller needs to keep
calling next() to keep moving the function forward and at every stop point where you
have a yield or return the function can return something new.
'''

# from tasks.placeholders import *

# The state of the function is saved between yields and re-invoked on call to next.
def demo_generator():
    """basic docstring for pylint testing"""
    yield "how"
    yield "are"
    yield "you?"

def test_generator_type():
    """basic docstring for pylint testing"""
    assert "function" == type(demo_generator).__name__
    # definition is a function
    assert "generator" == type(demo_generator()).__name__
    #once you invoke it, you get a generator

def test_generator_is_an_iterator1():
    """basic docstring for pylint testing"""
    assert False is hasattr(demo_generator, "next")
    assert False is hasattr(demo_generator(), "next")

def test_generator_is_an_iterator2():
    """basic docstring for pylint testing"""
    result = demo_generator()
    try:
        assert "how" == next(result)
        # builtin which calls the iterator.next()
        assert "are" == next(result)
        assert "you?" == next(result)
        assert False is  next(result)
    except StopIteration:
        assert True

    assert "how.are.you?" == ".".join(demo_generator())
    #join takes a iterable

# Note that this function takes any sequence, and returns
#  a reversed form
# element by element, so at no point is a new reversed
# sequence object
# created though to the consumer it appears like a
# sequence.
def demo_reverse(sequence):
    """basic docstring for pylint testing"""
    for index in range(len(sequence)-1, -1, -1):
        yield sequence[index]


def test_generator_reverse():
    """basic docstring for pylint testing"""
    result = []
    for item in demo_reverse("Hello World"):
        result.append(item)
    assert ["d","l","r","o","W"," ","o","l","l","e","H"] == result

# range using a generator (xrange does something similar)
def demo_range(limit):
    """basic docstring for pylint testing"""
    value = 0
    while value < limit:
        yield value
        value = value + 1

def test_generator_range_does_not_allocate_memory():
    """basic docstring for pylint testing"""
    for item in demo_range(1000 * (10**6)):
        if item%5 ==1:
            break
    assert True # did you reach here without any memory exception?


#write a statement that can collect all results from the generator into a list
def demo_generator_to_list(generator):
    """basic docstring for pylint testing"""
    list_a=[]
    for index in generator:
        list_a.append(index)
    return list_a
    # fill code here.


def test_collapse_generator():
    """basic docstring for pylint testing"""
    assert [0, 1, 2, 3] == demo_generator_to_list(demo_range(4))
    assert ["how","are","you?"] == demo_generator_to_list(demo_generator())

def test_generator_return():
    """basic docstring for pylint testing"""
    def func():
        yield 1
        yield 2
        return
        yield 3
    assert [1,2] == demo_generator_to_list(func())

def test_generator_control_flow():
    """basic docstring for pylint testing"""
    def func():
        for num_x in range(5):
            yield num_x
        yield 10
    assert [0,1,2,3,4,10] == demo_generator_to_list(func())

def test_generator_exception():
    """basic docstring for pylint testing"""
    def func():
        try:
            yield 10
            raise Exception("some message")
        except:
            yield 20
        else:
            yield 40
        finally:
            yield 50
        yield 30

    assert [10,20,50,30] == demo_generator_to_list(func())


THREE_THINGS_I_LEARNT = """
yield,generator
"""

TIME_TAKEN_MINUTES = 30
