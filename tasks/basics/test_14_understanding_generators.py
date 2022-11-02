""" Understanding Generators """


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

from tasks.placeholders import *


# The state of the function is saved between yields and re-invoked on call to next.
def demo_generator():
    """ Demo Generator Function """
    yield "how"
    yield "are"
    yield "you?"


def test_generator_type():
    """type of generator"""
    assert 'function' == type(demo_generator).__name__   # definition is a function
    assert 'generator' == type(demo_generator()).__name__  # once you invoke it, you get a generator


def test_generator_is_an_iterator1():
    """generator is an iterator1"""
    assert False == hasattr(demo_generator, "next")
    assert True == hasattr(demo_generator(), "__next__")


def test_generator_is_an_iterator2():
    """generator is an iterator2 """
    result = demo_generator()
    try:
        assert 'how' == next(result)  # builtin which calls the iterator.next()
        assert 'are' == next(result)
        assert 'you?' == next(result)
        assert False == next(result)
    except StopIteration:
        assert True

    assert 'how.are.you?' == ".".join(demo_generator())  # join takes an iterable but iterables should be strings


# Note that this function takes any sequence, and returns a reversed form
# element by element, so at any point is a new reversed sequence object
# created though to the consumer it appears like a sequence.
def demo_reverse(sequence):
    """ Reverse Function """
    for index in range(len(sequence)-1, -1, -1):
        yield sequence[index]


def test_generator_reverse():
    """ Generator reverse Function using demo reverse function """
    result = []
    for item in demo_reverse("Hello World"):
        result.append(item)
    assert ['d', 'l', 'r', 'o', 'W', ' ', 'o', 'l', 'l', 'e', 'H'] == result


# range using a generator (xrange does something similar)
def demo_range(limit):
    """ range function """
    value = 0
    while value < limit:
        yield value
        value = value + 1


def test_generator_range_does_not_allocate_memory():
    """ Generator using demo_range function and it does not allocate memory"""
    for item in demo_range(1000 * (10**6)):
        if item % 5 == 1:
            break
    assert True  # did you reach here without any memory exception? No


# write a statement that can collect all results from the generator into a list
def demo_generator_to_list(generator):
    """ Using genrator to create a List """
    result = []
    for i in generator:
        result.append(i)
    return result


def test_collapse_generator():
    """ Collapse Generator """
    assert [0, 1, 2, 3] == demo_generator_to_list(demo_range(4))
    assert ['how', 'are', 'you?'] == demo_generator_to_list(demo_generator())


def test_generator_return():
    """ return generator function """
    def func():
        yield 1
        yield 2
        return
        yield 3
    assert [1, 2] == demo_generator_to_list(func())


def test_generator_control_flow():
    """ generator control flow function """
    def func():
        for x in range(5):
            yield x
        yield 10
    assert [0, 1, 2, 3, 4, 10] == demo_generator_to_list(func())


def test_generator_exception():
    """ generator Exception """
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

    assert [10, 40, 50, 30] == demo_generator_to_list(func())


THREE_THINGS_I_LEARNT = """
- Generator is a function that returns an iterator and which can be used with next()
- Generator does not allocate any memory as it does not store in a list rather it pauses the function when it hits yield and exceutes the remaining
- Generators are best incase of memory management rather than comprehensions where they utilize the memory
"""

TIME_TAKEN_MINUTES = 20
