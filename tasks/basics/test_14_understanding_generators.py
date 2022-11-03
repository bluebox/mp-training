__author__ = 'Hari'

notes = '''
Generators are a easy way to create your own custom iterators. They look like
functions but do a lot of heavy lifting under the covers.

There are also useful when you want to 'generate' data on demand rather than
create all data at one shot - typically in memory constrained scenarios.

You can also think of generators as resumable functions. The caller needs to keep
calling next() to keep moving the function forward and at every stop point where you
have a yield or return the function can return something new.
'''

from tasks.placeholders import *

# The state of the function is saved between yields and re-invoked on call to next.
""" Program to check generator function in python"""
def demo_generator():
    """ demo generator function"""
    yield "how"
    yield "are"
    yield "you?"

def test_generator_type():
    """ demo test generatro type"""
    assert "function" == type(demo_generator).__name__  #definition is a function
    assert "generator" == type(demo_generator()).__name__ #once you invoke it, you get a generator
test_generator_type()
def test_generator_is_an_iterator1():
    """ demo test generator as an iterator"""
    assert False == hasattr(demo_generator, "next")
    assert False == hasattr(demo_generator(), "next")
test_generator_is_an_iterator1()
def test_generator_is_an_iterator2():
    """ function to test generator as an iterator"""
    result = demo_generator()
    try:
        assert "how" == next(result)  # builtin which calls the iterator.next()
        assert "are" == next(result)
        assert "you?" == next(result)
        assert "" == next(result)
    except StopIteration:
        assert True

    assert "how.are.you?" == ".".join(demo_generator()) #join takes a iterable
test_generator_is_an_iterator2()
# Note that this function takes any sequence, and returns a reversed form
# element by element, so at no point is a new reversed sequence object
# created though to the consumer it appears like a sequence.
def demo_reverse(sequence):
    """ function to reverse the sequence"""
    for index in range(len(sequence)-1, -1, -1):
        yield sequence[index]


def test_generator_reverse():
    """ function to test the generator reverse"""
    result = []
    for item in demo_reverse("Hello World"):
        result.append(item)
    assert ['d', 'l', 'r', 'o', 'W', ' ', 'o', 'l', 'l', 'e', 'H'] == result
test_generator_reverse()

# range using a generator (xrange does something similar)
def demo_range(limit):
    """ function to demo the range"""
    value = 0
    while value < limit:
        yield value
        value = value + 1

def test_generator_range_does_not_allocate_memory():
    """ function to test generator range"""
    for item in demo_range(1000 * (10**6)):
        if item % 5 ==1:
            break
    assert True # did you reach here without any memory exception?
test_generator_range_does_not_allocate_memory()


#write a statement that can collect all results from the generator into a list
def demo_generator_to_list(generator):
    """ function to demo generator to list"""
    result=[]
    for i in generator: # fill code here.
        result.append(i)
    return result

def test_collapse_generator():
    """ function to test generator"""
    assert [0, 1, 2, 3] == demo_generator_to_list(demo_range(4))
    assert ["how", "are", "you?"] == demo_generator_to_list(demo_generator())
test_collapse_generator()

def test_generator_return():
    """ function to return"""
    def func():
        yield 1
        yield 2
        return
        yield 3
    assert [1, 2] == demo_generator_to_list(func())
test_generator_return()
def test_generator_control_flow():
    """ function to check contro flow"""
    def func():
        for x in range(5):
            yield x
        yield 10
    assert [0, 1, 2, 3, 4, 10] == demo_generator_to_list(func())
test_generator_control_flow()

def test_generator_exception():
    """ function to test exception"""
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

    assert [10, 20, 50, 30] == demo_generator_to_list(func())
test_generator_exception()


three_things_i_learnt = """
- generator in python
-use of yield
-usability of generator
"""

time_taken_minutes = 70
