__author__ = 'Hari'

from tasks.placeholders import *

notes = """string is one of the most commonly used data types, it has different
behavior than a char* in C."""

""" To understand the use of strings in python """
def test_string_type():
    """ function to check the type of string"""
    assert 'str'== type("Hello World").__name__
    assert True is isinstance("Hello World", str)
test_string_type()
def test_single_quoted_strings_are_strings():
    """ Function to check the single quoted strings are strings or not"""
    assert True is isinstance('Hello World', str)
test_single_quoted_strings_are_strings()
def test_double_quoted_strings_are_strings():
    """ Function to check double quoted strings"""
    assert True is isinstance("Hello World", str)
test_double_quoted_strings_are_strings()
def test_triple_quoted_strings_are_strings():
    """ Function to check triple quoted strings"""
    assert True is isinstance("""Hello World""", str)
test_triple_quoted_strings_are_strings()
def test_triple_single_quoted_strings_are_strings():
    """ function to check three triple single quoted string"""
    assert True is isinstance('''Hello World''', str)
test_triple_single_quoted_strings_are_strings()
def test_raw_strings_are_strings():
    """ function to check raw string"""
    assert True is isinstance(r"Hello World", str)
test_raw_strings_are_strings()
def test_single_quoted_strings_can_have_double_quotes():
    """ function to check """
    first = 'The pilot said "Jump"'
    second = "The pilot said \"Jump\""  #note back slash escaping of "
    are_equal = (first == second)
    assert True is are_equal
test_single_quoted_strings_can_have_double_quotes()
def test_double_quoted_strings_can_have_single_quotes():
    """ function to check"""
    first = "The pilot said 'Jump'"
    second = 'The pilot said \'Jump\''  #note back slash escaping of '
    are_equal = (first == second)
    assert True is are_equal
test_double_quoted_strings_can_have_single_quotes()
def test_triple_quoted_strings_can_have_both_single_and_double_quotes():
    """ Edit tq_str to make are_equal True """
    tq_str = """ Isn't the "Hobbit" great? """
    dq_str = "Isn't the \"Hobbit\" great?"
    are_equal = (tq_str == dq_str)
    assert  False is are_equal
test_triple_quoted_strings_can_have_both_single_and_double_quotes()
def test_triple_quoted_strings_cans_span_lines():
    """ function to check strings can span lines """
    tq_str = """Hello
World"""
    dq_str = "Hello\nWorld"   # what is the double quoted form of tq_str
    assert tq_str == dq_str
test_triple_quoted_strings_cans_span_lines()
def test_string_len():
    """ function to check length of strings """
    assert 13 == len("Hello 'world'")
    assert 13 == len('Hello \'world\'')
test_string_len()
def test_triple_quoted_strings_can_span_lines():
    """ function to check triple quoted strings """
    string = """Hello
    World"""
    assert True is isinstance(string, str)
    assert 15 == len(string)
test_triple_quoted_strings_can_span_lines()
def test_strings_can_be_indexed():
    """ function to check strings can be indexed or not"""
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
    assert 5 == len(string)
    try:
        out_of_bounds = string[5] #raises an error, we will revisit exceptions later
    except IndexError as i_e:
        print(i_e)   #string index out of range
        assert True  #make this True to proceed.
test_strings_can_be_indexed()
def test_chars_are_strings_too():
    """ function to check characters are strings or not"""
    string = "Hello"
    first_char = string[0]
    assert 'str' == type(first_char).__name__
    assert 'str' == type('a').__name__
    assert 'str'== type("a").__name__
test_chars_are_strings_too()
def test_strings_are_immutable():
    """ strings in python cannot be modified unlike in C """
    string = "Hello"
    try:
        string[0] = "M"
    except TypeError as t_e:
        print(t_e)
        assert True
test_strings_are_immutable()
def test_string_concat():
    """ function to test string concatenation"""
    assert "Hello  world" == "Hello " + " world"
    assert 'Hello world' == """Hello """ + 'world'
    assert 'Hello world' == 'Hello ' + "world"
test_string_concat()

def test_string_slicing():
    """ Slicing creates new strings """
    string = "Hello world"
    #with begin : end
    assert '' == string[0:0]

    assert 'He' == string[0:2]
    assert 'ello' == string[1:5]
    assert 'ello worl' == string[1:-1]
    assert 'llo wor' == string[2:-2]

    #with :end
    assert '' == string[:0]
    assert 'Hell' == string[:4]
    assert 'Hello worl' == string[:-1]

    #with begin:
    assert 'Hello world' == string[0:]
    assert 'o world' == string[4:]
    assert 'd' == string[-1:]

    #observe the invariant
    assert 'Hello world' == string[:0] + string[0:]
    assert 'Hello world' == string[:1] + string[1:]
    assert 'Hello world' == string[:2] + string[2:]
    assert 'Hello world' == string[:3] + string[3:]
test_string_slicing()

def test_string_repeat():
    """ function to check string repeatition """
    assert 'HelloHelloHello' == "Hello" * 3
    assert 12 == len("Hello " * 2)
test_string_repeat()
def test_string_combine():
    """
    Use slicing to pass the assert.
    """
    hello = "Hello World"
    bye = "Goodbye moon"
    assert bye[0:8] + hello[6:] == "Goodbye World"
test_string_combine()
def test_string_formatting():
    """ function to check formatting of string"""
    greeting = "Hello '{0}'".format("learner")
    assert "Hello 'learner'" == greeting

    truth = "{1} plus {1} makes {0}".format("two", "one")
    assert truth == 'one plus one makes two'


    stmt = "{name} is {age} years old".format(name="Ravi", age=25)
    assert 'Ravi is 25 years old' == stmt
test_string_formatting()
def test_string_membership():
    """ function to check membership of string """
    assert False is ('c' in 'apple')  #is there a precedence issue here?
    assert True is ('a' in 'apple')
    assert True is ('app' in 'apple')  # '==' and 'in' operators have same precedence are interpreted from left to right in the expression
test_string_membership()


three_things_i_learnt = """
-strings are immutable
-strings can be accessed through indexing
-strings can be sliced
"""

time_taken_minutes = 20
