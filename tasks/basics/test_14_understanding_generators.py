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
    yield "how"
    yield "are"
    yield "you?"

def test_generator_type():
    assert __ == type(demo_generator).__name__  #definition is a function
    assert __ == type(demo_generator()).__name__ #once you invoke it, you get a generator

def test_generator_is_an_iterator1():
    assert __ == hasattr(demo_generator, "next")
    assert __ == hasattr(demo_generator(), "next")

def test_generator_is_an_iterator2():
    result = demo_generator()
    try:
        assert __ == next(result)  # builtin which calls the iterator.next()
        assert __ == next(result)
        assert __ == next(result)
        assert __ == next(result)
    except __:
        assert True

    assert __ == ".".join(demo_generator()) #join takes a iterable

# Note that this function takes any sequence, and returns a reversed form
# element by element, so at no point is a new reversed sequence object
# created though to the consumer it appears like a sequence.
def demo_reverse(sequence):
    for index in range(len(sequence)-1, -1, -1):
        yield sequence[index]


def test_generator_reverse():
    result = []
    for item in demo_reverse("Hello World"):
        result.append(item)
    assert __ == result

# range using a generator (xrange does something similar)
def demo_range(limit):
    value = 0
    while value < limit:
        yield value
        value = value + 1

def test_generator_range_does_not_allocate_memory():
    for item in demo_range(1000 * (10**6)):
        if item%5 ==1:
            break
    assert ___ # did you reach here without any memory exception?


#write a statement that can collect all results from the generator into a list
def demo_generator_to_list(generator):
    __ # fill code here.


def test_collapse_generator():
    assert __ == demo_generator_to_list(demo_range(4))
    assert __ == demo_generator_to_list(demo_generator())

def test_generator_return():
    def func():
        yield 1
        yield 2
        return
        yield 3
    assert [__] == demo_generator_to_list(func())

def test_generator_control_flow():
    def func():
        for x in range(5):
            yield x
        yield 10
    assert __ == demo_generator_to_list(func())

def test_generator_exception():
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

    assert [__] == demo_generator_to_list(func())


three_things_i_learnt = """
-
-
-
"""

time_taken_minutes = ___
