__author__ = 'Hari'

<<<<<<< HEAD
#from tasks.placeholders import *
=======
from tasks.placeholders import *
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


NOTES = """string is one of the most commonly used data types, it has different
behavior than a char* in C."""

def test_string_type():
<<<<<<< HEAD
    assert 'str' == type("Hello World").__name__
    assert True is isinstance("Hello World", str)


def test_single_quoted_strings_are_strings():
    assert True is isinstance('Hello World', str)


def test_double_quoted_strings_are_strings():
    assert True is isinstance("Hello World", str)


def test_triple_quoted_strings_are_strings():
    assert True is isinstance("""Hello World""", str)


def test_triple_single_quoted_strings_are_strings():
    assert True is isinstance('''Hello World''', str)

=======
    assert 'str'== type("Hello World").__name__
    assert True== isinstance("Hello World", str)

def test_single_quoted_strings_are_strings():
    assert True== isinstance('Hello World', str)

def test_double_quoted_strings_are_strings():
    assert True== isinstance("Hello World", str)

def test_triple_quoted_strings_are_strings():
    assert True== isinstance("""Hello World""", str)

def test_triple_single_quoted_strings_are_strings():
    assert True== isinstance('''Hello World''', str)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_raw_strings_are_strings():
    assert True is isinstance(r"Hello World", str)

def test_single_quoted_strings_can_have_double_quotes():
    first = 'The pilot said "Jump"'
    second = "The pilot said \"Jump\""  #note back slash escaping of "
    are_equal = (first == second)
<<<<<<< HEAD
    assert True is are_equal

=======
    assert True== are_equal
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_double_quoted_strings_can_have_single_quotes():
    first = "The pilot said 'Jump'"
    second = 'The pilot said \'Jump\''  #note back slash escaping of '
    are_equal = (first == second)
<<<<<<< HEAD
    assert True is are_equal

=======
    assert True== are_equal
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_triple_quoted_strings_can_have_both_single_and_double_quotes():
    """ Edit tq_str to make are_equal True """
    tq_str = """ Isn't the "Hobbit" great? """
    dq_str = "Isn't the \"Hobbit\" great?"
    are_equal = (tq_str == dq_str)
<<<<<<< HEAD
    assert False is are_equal

=======
    assert  False == are_equal
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_triple_quoted_strings_can_span_lines():
    tq_str = """Hello
    World"""
    dq_str = "Hello\nWorld"   # what is the double quoted form of tq_str
<<<<<<< HEAD
    assert (tq_str == dq_str) is False

=======
    assert (tq_str == dq_str)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_string_len():
    assert 13 is len("Hello 'world'")
    assert 13 is len('Hello \'world\'')

<<<<<<< HEAD

def test_triple_quoted_strings_can_span_lines_2():
=======
def test_triple_quoted_strings_can_span_lines():
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
    string = """Hello
    World"""
    assert True is isinstance(string, str)
    assert 15 is len(string)

def test_strings_can_be_indexed():
    string = "Hello"
    assert 'H'== string[0]
    assert 'e'== string[1]
    assert 'l' == string[2]
    assert 'l'== string[3]
    assert 'o' == string[4]
    assert 'o' == string[-1]  # solves the common use case to iterate from end
    assert 'l' == string[-2]
    assert 'l' == string[-3]
    assert 'e' == string[-4]
    assert 'H' == string[-5]
    assert 'H' == string[-0]  # hint -0 is 0
    assert  5 == len(string)
    try:
<<<<<<< HEAD
        # raises an error, we will revisit exceptions later
        out_of_bounds = string[5]
    except IndexError as _ie:
        print(_ie)  # string index out of range
        assert True  # make this True to proceed.

=======
        out_of_bounds = string[5] #raises an error, we will revisit exceptions later
    except IndexError as ie:
        print (ie)   #string index out of range
        assert True  #make this True to proceed.
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_chars_are_strings_too():
    string = "Hello"
    first_char = string[0]
    assert 'str' == type(first_char).__name__
    assert 'str' == type('a').__name__
    assert 'str'== type("a").__name__

def test_strings_are_immutable():
    """ strings in python cannot be modified unlike in C """
    string = "Hello"
    try:
        string[0] = "M"
<<<<<<< HEAD
    except TypeError as _te:
        print(_te)
=======
    except TypeError as te:
        print (te)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
        assert True

def test_string_concat():
    assert "Hello  world"== "Hello " + " world"
    assert 'Hello world'== """Hello """ + 'world'
    assert 'Hello world'== 'Hello ' + "world"


def test_string_slicing():
    """ Slicing creates new strings """
    string = "Hello world"
    #with begin : end
    assert ''== string[0:0]

    assert 'He' == string[0:2]
    assert 'ello'== string[1:5]
    assert 'ello worl'== string[1:-1]
    assert 'llo wor'== string[2:-2]

    #with :end
    assert ''== string[:0]
    assert 'Hell'== string[:4]
    assert 'Hello worl'== string[:-1]

    #with begin:
    assert 'Hello world'== string[0:]
    assert 'o world'== string[4:]
    assert 'd'== string[-1:]

    #observe the invariant
    assert 'Hello world' == string[:0] + string[0:]
    assert 'Hello world' == string[:1] + string[1:]
    assert 'Hello world'== string[:2] + string[2:]
    assert 'Hello world'== string[:3] + string[3:]


def test_string_repeat():
    assert 'HelloHelloHello' == "Hello" * 3
    assert 12 == len("Hello " * 2)

def test_string_combine():
    """
    Use slicing to pass the assert.
    """
    hello = "Hello World"
    bye = "Goodbye moon"
    assert  bye[0:8] + hello[6:]  == "Goodbye World"

def test_string_formatting():
    greeting = "Hello '{0}'".format("learner")
    assert "Hello 'learner'"== greeting

    truth = "{1} plus {1} makes {0}".format("two","one")
    assert truth == 'one plus one makes two'


    stmt = "{name} is {age} years old".format(name="Ravi", age=25)
    assert 'Ravi is 25 years old'== stmt

def test_string_membership():
<<<<<<< HEAD
    assert False is ('c' in 'apple')  # is there a precedence issue here?
    assert True is ('a' in 'apple')
    # '=='and'in'operators have same precedence are interpreted from left to right in the expression
    assert True is ('app' in 'apple')


THREE_THINGS_LERNT = """
=======
    assert False== ('c' in 'apple')  #is there a precedence issue here?
    assert True == ('a' in 'apple')
    assert  True== ('app' in 'apple')  # '==' and 'in' operators have same precedence are interpreted from left to right in the expression


three_things_i_learnt = """
-strings are immutable
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
-strings can be accessed through indexing
-strings can be sliced
"""

<<<<<<< HEAD
TIME_TAKEN_MINUTES = 25
=======
time_taken_minutes = 20
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
