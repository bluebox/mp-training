'''generators'''
__author__ = 'Hari'

NOTES_1 = '''
Generators are a easy way to create your
own custom iterators. They look like
functions but do a lot of heavy lifting
under the covers.

There are also useful when you want to
'generate' data on demand rather than
create all data at one shot - typically
in memory constrained scenarios.

You can also think of generators as resumable
functions. The caller needs to keep
calling next() to keep moving the function
forward and at every stop point where you
have a yield or return the function can return something new.
'''

# from tasks.placeholders import *

# The state of the function is saved between yields and re-invoked on call to next.
def demo_generator():
    '''generators'''
    yield "how"
    yield "are"
    yield "you?"

def test_generator_type():
    '''generators'''
    assert type(demo_generator).__name__ == 'function' #definition is a function
    assert type(demo_generator()).__name__ == 'generator'#once you invoke it, you get a generator

def test_generator_is_an_iterator1():
    '''generators'''
    assert hasattr(demo_generator, "next") is False
    assert hasattr(demo_generator(), "next") is False

def test_generator_is_an_iterator2():
    '''generators'''
    result = demo_generator()
    try:
        assert next(result) == "how" # builtin which calls the iterator.next()
        assert next(result) == "are"
        assert next(result) == "you?"
        assert next(result) == ""
    except StopIteration:
        assert True

    assert ".".join(demo_generator()) == "how.are.you?"#join takes a iterable

# Note that this function takes any sequence, and returns a reversed form
# element by element, so at no point is a new reversed sequence object
# created though to the consumer it appears like a sequence.
def demo_reverse(sequence):
    '''generators'''
    for index in range(len(sequence)-1, -1, -1):
        yield sequence[index]


def test_generator_reverse():
    '''generators'''
    result = []
    for item in demo_reverse("Hello World"):
        result.append(item)
    assert result == ['d', 'l', 'r', 'o', 'W', ' ', 'o', 'l', 'l', 'e', 'H']

# range using a generator (xrange does something similar)
def demo_range(limit):
    '''generators'''
    value = 0
    while value < limit:
        yield value
        value = value + 1

def test_generator_range_does_not_allocate_memory():
    '''generators'''
    for item in demo_range(1000 * (10**6)):
        if item%5 == 1:
            break
    assert AssertionError # did you reach here without any memory exception?


#write a statement that can collect all results from the generator into a list
def demo_generator_to_list(generator):
    '''generators'''
    result = [] # fill code here.
    for item in generator:
        result.append(item)


def test_collapse_generator():
    '''generators'''
    assert demo_generator_to_list(demo_range(4)) is None
    assert demo_generator_to_list(demo_generator()) is None

def test_generator_return():
    '''generators'''
    def func():
        '''generators'''
        yield 1
        yield 2
        return
        yield 3
    assert demo_generator_to_list(func()) is None

def test_generator_control_flow():
    '''generators'''
    def func():
        '''generators'''
        for x_1 in range(5):
            yield x_1
        yield 10
    assert demo_generator_to_list(func()) is None

def test_generator_exception():
    '''generators'''
    def func():
        '''generators'''
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

    assert demo_generator_to_list(func()) is None


THREE_THINGS_I_LEARNT = """
Generators are a easy way to create your own custom
terators. They look like
functions but do a lot of heavy
lifting under the covers.

There are also useful when you want to
'generate' data on demand rather than
create all data at one shot - typically
in memory constrained scenarios.

You can also think of generators as resumable
functions. The caller needs to keep
calling next() to keep moving the function
forward and at every stop point where you
have a yield or return the function can return something new.
"""

TIME_TAKEN_MINUTES = 20
