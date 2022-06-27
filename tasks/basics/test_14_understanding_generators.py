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
def demo_generator():
    '''method'''
    yield "how"
    yield "are"
    yield "you?"

def test_generator_type():
    '''method'''
    assert "function" == type(demo_generator).__name__  #definition is a function
    assert 'generator' == type(demo_generator()).__name__ #once you invoke it, you get a generator

def test_generator_is_an_iterator1():
    '''method'''
    assert False is hasattr(demo_generator, "next")
    assert False is hasattr(demo_generator(), "next")

def test_generator_is_an_iterator2():
    '''method'''
    result = demo_generator()
    try:
        assert "how" == next(result)  # builtin which calls the iterator.next()
        assert "are" == next(result)
        assert "you?" == next(result)
        assert False is next(result)
    except StopIteration:
        assert True

    assert "how.are.you?" == ".".join(demo_generator()) #join takes a iterable

# Note that this function takes any sequence, and returns a reversed form
# element by element, so at no point is a new reversed sequence object
# created though to the consumer it appears like a sequence.
def demo_reverse(sequence):
    '''method'''
    for index in range(len(sequence)-1, -1, -1):
        yield sequence[index]


def test_generator_reverse():
    '''method'''
    result = []
    for item in demo_reverse("Hello World"):
        result.append(item)
    assert ['d','l','r','o','W',' ','o','l','l','e','H'] == result

# range using a generator (xrange does something similar)
def demo_range(limit):
    '''method'''
    value = 0
    while value < limit:
        yield value
        value = value + 1

def test_generator_range_does_not_allocate_memory():
    '''method'''
    for item in demo_range(1000 * (10**6)):
        if item%5 ==1:
            break
    assert AssertionError # did you reach here without any memory exception?


#write a statement that can collect all results from the generator into a list
def demo_generator_to_list(generator):
    pass
    __ # fill code here.


def test_collapse_generator():
    '''method'''
    assert None == demo_generator_to_list(demo_range(4))
    assert None == demo_generator_to_list(demo_generator())

def test_generator_return():
    '''method'''
    def func():
        yield 1
        yield 2
        return
        yield 3
    assert None == demo_generator_to_list(func())

def test_generator_control_flow():
    '''method'''
    def func():
        for x in range(5):
            yield x
        yield 10
    assert None == demo_generator_to_list(func())

def test_generator_exception():
    '''method'''
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

    assert None == demo_generator_to_list(func())


TTIL = """
-
-
-
"""

RRM = 20
