__author__ = 'Hari'

from placeholders import *

notes = """string is one of the most commonly used data types, it has different
behavior than a char* in C."""

def test_string_type():
    assert __ == type("Hello World").__name__
    assert __ == isinstance("Hello World", str)

def test_single_quoted_strings_are_strings():
    assert __ == isinstance('Hello World', str)

def test_double_quoted_strings_are_strings():
    assert __ == isinstance("Hello World", str)

def test_triple_quoted_strings_are_strings():
    assert __ == isinstance("""Hello World""", str)

def test_triple_single_quoted_strings_are_strings():
    assert __ == isinstance('''Hello World''', str)

def test_raw_strings_are_strings():
    assert __ == isinstance(r"Hello World", str)

def test_single_quoted_strings_can_have_double_quotes():
    first = 'The pilot said "Jump"'
    second = "The pilot said \"Jump\""  #note back slash escaping of "
    are_equal = (first == second)
    assert __ == are_equal

def test_double_quoted_strings_can_have_single_quotes():
    first = "The pilot said 'Jump'"
    second = 'The pilot said \'Jump\''  #note back slash escaping of '
    are_equal = (first == second)
    assert __ == are_equal

def test_triple_quoted_strings_can_have_both_single_and_double_quotes():
    """ Edit tq_str to make are_equal True """
    tq_str = """ Isn't the "Hobbit" great? """
    dq_str = "Isn't the \"Hobbit\" great?"
    are_equal = (tq_str == dq_str)
    assert __ == are_equal

def test_triple_quoted_strings_can_span_lines():
    tq_str = """Hello
World"""
    dq_str = "Hello\nWorld"   # what is the double quoted form of tq_str
    assert (tq_str == dq_str)

def test_string_len():
    assert __ == len("Hello 'world'")
    assert __ == len('Hello \'world\'')

def test_triple_quoted_strings_can_span_lines():
    string = """Hello
    World"""
    assert __ == isinstance(string, str)
    assert __ == len(string)

def test_strings_can_be_indexed():
    string = "Hello"
    assert __ == string[0]
    assert __ == string[1]
    assert __ == string[2]
    assert __ == string[3]
    assert __ == string[4]
    assert __ == string[-1]  # solves the common use case to iterate from end
    assert __ == string[-2]
    assert __ == string[-3]
    assert __ == string[-4]
    assert __ == string[-5]
    assert __ == string[-0]  # hint -0 is 0
    assert __ == len(string)
    try:
        out_of_bounds = string[5] #raises an error, we will revisit exceptions later
    except IndexError as ie:
        print (ie)   #string index out of range
        assert True  #make this True to proceed.

def test_chars_are_strings_too():
    string = "Hello"
    first_char = string[0]
    assert __ == type(first_char).__name__
    assert __ == type('a').__name__
    assert __ == type("a").__name__

def test_strings_are_immutable():
    """ strings in python cannot be modified unlike in C """
    string = "Hello"
    try:
        string[0] = "M"
    except TypeError as te:
        print (te)
        assert True

def test_string_concat():
    assert __ == "Hello " + " world"
    assert __ == """Hello """ + 'world'
    assert __ == 'Hello ' + "world"


def test_string_slicing():
    """ Slicing creates new strings """
    string = "Hello world"
    #with begin : end
    assert __ == string[0:0]

    assert __ == string[0:2]
    assert __ == string[1:5]
    assert __ == string[1:-1]
    assert __ == string[2:-2]

    #with :end
    assert __ == string[:0]
    assert __ == string[:4]
    assert __ == string[:-1]

    #with begin:
    assert __ == string[0:]
    assert __ == string[4:]
    assert __ == string[-1:]

    #observe the invariant
    assert __ == string[:0] + string[0:]
    assert __ == string[:1] + string[1:]
    assert __ == string[:2] + string[2:]
    assert __ == string[:3] + string[3:]


def test_string_repeat():
    assert __ == "Hello" * 3
    assert __ == len("Hello " * 2)

def test_string_combine():
    """
    Use slicing to pass the assert.
    """
    hello = "Hello World"
    bye = "Goodbye moon"
    assert  bye[0:8] + hello[6:]  == __

def test_string_formatting():
    greeting = "Hello '{0}'".format("learner")
    assert __ == greeting

    truth = "{1} plus {1} makes {0}".format("two","one")
    assert truth == __

    stmt = "{name} is {age} years old".format(name="Ravi", age=25)
    assert __ == stmt

def test_string_membership():
    assert __ == ('c' in 'apple')  #is there a precedence issue here?
    assert __ == ('a' in 'apple')
    assert __ == ('app' in 'apple')  # '==' and 'in' operators have same precedence are interpreted from left to right in the expression


three_things_i_learnt = """
-
-
-
"""

time_taken_minutes = __
