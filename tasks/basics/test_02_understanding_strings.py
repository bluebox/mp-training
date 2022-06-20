'''Strings'''
__author__ = 'Hari'

# from tasks.placeholders import *

# from tasks.basics.test_00_understanding_assert
#  import NOTES_1, THREE_THINGS_I_LEARNT, TIME_TAKEN_MINUTES

NOTES_1 = """string is one of the most commonly used data types, it has different
behavior than a char* in C."""

def test_string_type():
    '''Strings'''
    assert type("Hello World").__name__ == 'str'
    assert isinstance("Hello World", str) is True

def test_single_quoted_strings_are_strings():
    '''Strings'''
    assert isinstance('Hello World', str) is True

def test_double_quoted_strings_are_strings():
    '''Strings'''
    assert isinstance("Hello World", str) is True

def test_triple_quoted_strings_are_strings():
    '''Strings'''
    assert isinstance("""Hello World""", str) is True

def test_triple_single_quoted_strings_are_strings():
    '''Strings'''
    assert isinstance('''Hello World''', str) is True

def test_raw_strings_are_strings():
    '''Strings'''
    assert isinstance(r"Hello World", str) is True

def test_single_quoted_strings_can_have_double_quotes():
    '''Strings'''
    first = 'The pilot said "Jump"'
    second = "The pilot said \"Jump\""  #note back slash escaping of "
    are_equal = (first == second)
    assert are_equal is True

def test_double_quoted_strings_can_have_single_quotes():
    '''Strings'''
    first = "The pilot said 'Jump'"
    second = 'The pilot said \'Jump\''  #note back slash escaping of '
    are_equal = (first == second)
    assert are_equal is True

def test_triple_quoted_strings_can_have_both_single_and_double_quotes():
    """ Edit tq_str to make are_equal True """
    tq_str = """ Isn't the "Hobbit" great? """
    dq_str = "Isn't the \"Hobbit\" great?"
    are_equal = (tq_str == dq_str)
    assert are_equal is False

def test_triple_quoted_strings_can_span_lines():
    '''Strings'''
    tq_str = """Hello
World"""
    dq_str = "Hello\nWorld"   # what is the double quoted form of tq_str
    assert tq_str == dq_str

def test_string_len():
    '''Strings'''
    assert len("Hello 'world'") == 13
    assert len('Hello \'world\'') == 13

def test_triple_quoted_strings_can_spans_lines():
    '''Strings'''
    string = """Hello
    World"""
    assert isinstance(string, str) is True
    assert len(string) == 15

def test_strings_can_be_indexed():
    '''Strings'''
    string = "Hello"
    assert string[0] == 'H'
    assert string[1] == 'e'
    assert string[2] == 'l'
    assert string[3] == 'l'
    assert string[4] == 'o'
    assert string[-1] == 'o' # solves the common use case to iterate from end
    assert string[-2] == 'l'
    assert string[-3] == 'l'
    assert string[-4] == 'e'
    assert string[-5] == 'H'
    assert string[-0] == 'H' # hint -0 is 0
    assert len(string) == 5
    try:
        out_of_bounds = string[5]
        print(out_of_bounds) #raises an error, we will revisit exceptions later
    except IndexError as ie_1:
        print(ie_1)   #string index out of range
        assert True  #make this True to proceed.

def test_chars_are_strings_too():
    '''Strings'''
    string = "Hello"
    first_char = string[0]
    assert type(first_char).__name__ == 'str'
    assert type('a').__name__ == 'str'
    assert type("a").__name__ == 'str'

def test_strings_are_immutable():
    """ strings in python cannot be modified unlike in C """
    string = "Hello"
    try:
        string[0] = "M"
    except TypeError as te_1:
        print(te_1)
        assert True

def test_string_concat():
    '''Strings'''
    assert "Hello " + " world" == "Hello  world"
    assert """Hello """ + 'world' == "Hello world"
    assert 'Hello ' + "world" == "Hello world"


def test_string_slicing():
    """ Slicing creates new strings """
    string = "Hello world"
    #with begin : end
    assert string[0:0] == ""

    assert string[0:2] == "He"
    assert string[1:5] == "ello"
    assert string[1:-1] == "ello worl"
    assert string[2:-2] == "llo wor"

    #with :end
    assert string[:0] == ""
    assert string[:4] == "Hell"
    assert string[:-1] == "Hello worl"

    #with begin:
    assert string[0:] == "Hello world"
    assert string[4:] == "o world"
    assert string[-1:] == "d"

    #observe the invariant
    assert string[:0] + string[0:] == "Hello world"
    assert string[:1] + string[1:] == "Hello world"
    assert string[:2] + string[2:] == "Hello world"
    assert string[:3] + string[3:] == "Hello world"


def test_string_repeat():
    '''Strings'''
    assert "Hello" * 3 == "HelloHelloHello"
    assert len("Hello " * 2) == 12

def test_string_combine():
    """
    Use slicing to pass the assert.
    """
    hello = "Hello World"
    bye = "Goodbye moon"
    assert  bye[0:8] + hello[6:] == "Goodbye World"

def test_string_formatting():
    '''Strings'''
    greeting = "Hello '{0}'".format("learner")
    assert greeting == "Hello 'learner'"

    truth = "{1} plus {1} makes {0}".format("two", "one")
    assert truth == "one plus one makes two"

    stmt = "{name} is {age} years old".format(name="Ravi", age=25)
    assert stmt == "Ravi is 25 years old"

def test_string_membership():
    '''Strings'''
    assert ('c' in 'apple') is False #is there a precedence issue here?
    assert ('a' in 'apple') is True
    assert ('app' in 'apple') is True
    # '==' and 'in' operators have same precedence
    #  are interpreted from left to right in the expression


THREE_THINGS_I_LEARNT = """
1. Slicing of string
2. '==' and 'in' operators have same precedence are interpreted from left to right in the expression
3. concatination of two sliced strings
-
"""

TIME_TAKEN_MINUTES = 10
