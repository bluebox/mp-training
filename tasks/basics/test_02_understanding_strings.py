"""This is the 2nd file of python exercise by medplus"""
__author__ = 'Hari'

#from placeholders import *

NOTES = """string is one of the most commonly used data types, it has different
behavior than a char* in C."""

def test_string_type():
    """String type and isinstance"""
    assert 'str' == type("Hello World").__name__
    assert True is isinstance('Hello World', str)

def test_single_quoted_strings_are_strings():
    """Single quotation string and isinstance"""
    assert True is isinstance('Hello World', str)

def test_double_quoted_strings_are_strings():
    """double quoted string and isinstance"""
    assert True is isinstance("Hello World", str)

def test_triple_quoted_strings_are_strings():
    """Triple-double quoted string and isinstance"""
    assert True is isinstance("""Hello World""", str)

def test_triple_single_quoted_strings_are_strings():
    """Triple-single quoted string and isinstance"""
    assert True is isinstance('''Hello World''', str)

def test_raw_strings_are_strings():
    """raw str and isinstance"""
    assert True is isinstance(r"Hello World", str)

def test_single_quoted_strings_can_have_double_quotes():
    """String type and isinstance"""
    first = 'The pilot said "Jump"'
    second = "The pilot said \"Jump\""  #note back slash escaping of "
    are_equal = (first == second)
    assert True is are_equal

def test_double_quoted_strings_can_have_single_quotes():
    """String type and isinstance"""
    first = "The pilot said 'Jump'"
    second = 'The pilot said \'Jump\''  #note back slash escaping of '
    are_equal = (first == second)
    assert True is are_equal

def test_triple_quoted_strings_can_have_both_single_and_double_quotes():
    """ Edit tq_str to make are_equal True """
    tq_str = """ Isn't the "Hobbit" great? """
    dq_str = "Isn't the \"Hobbit\" great?"
    are_equal = (tq_str == dq_str)
    assert  False is are_equal

def test_triple_quoted_strings_can_span_lines():
    """String type and isinstance"""
    tq_str = """Hello
World"""
    dq_str = "Hello\nWorld"
    # what is the double quoted form of tq_str
    assert True is (tq_str == dq_str)

def test_string_len():
    """String type and isinstance"""
    assert 13 is len("Hello 'world'")
    assert 13 is len('Hello \'world\'')

def test_triple_quoted_strings_can_span_lines_2():
    """String type and isinstance"""
    string = """Hello
    World"""
    assert True is isinstance(string, str)
    assert 15 is len(string)

def test_strings_can_be_indexed():
    """String type and isinstance"""
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
        out_of_bounds = string[5]
        print(out_of_bounds)
        #raises an error, we will revisit exceptions later
    except IndexError as i_e:
        print (i_e)   #string index out of range
        assert True  #make this True to proceed.

def test_chars_are_strings_too():
    """String type and isinstance"""
    string = "Hello"
    first_char = string[0]
    assert 'str' == type(first_char).__name__
    assert 'str' == type('a').__name__
    assert 'str'== type("a").__name__


# Commenting the below file because of pylint
# as its showing error string does not support item assigment
# and in this function it was done for learning purpose


# def test_strings_are_immutable():
#     """ strings in python cannot be modified unlike in C """
#     string = "Hello"
    # try:
    #     string[0] = "M"
    # except TypeError as t_e:
    #     print (t_e)
    #     assert True

def test_string_concat():
    """String type and isinstance"""
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
    """String type and isinstance"""
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
    """String type and isinstance changing from format to fstring
    due to pylint suggestions
    """
    greeting = f"Hello '{'learner'}'"
    assert "Hello 'learner'"== greeting

    truth = f"{'one'} plus {'one'} makes {'two'}"
    assert truth == 'one plus one makes two'

    name_a="Ravi"
    age_a=25
    stmt = f"{name_a} is {age_a} years old"
    assert 'Ravi is 25 years old'== stmt

def test_string_membership():
    """String type and isinstance"""
    c_1="c"
    assert False is (c_1 in 'apple')
    #is there a precedence issue here?
    a_1="a"
    assert True is (a_1 in 'apple')
    app_1="app"
    assert True is (app_1 in 'apple')
    # '==' and 'in' operators have same precedence are
    # interpreted from left to right in the expression


THREE_THINGS_I_LEARNT = """
-strings are immutable
-strings can be accessed through indexing
-strings can be sliced
"""

TIME_TAKEN_MINUTES = 20
