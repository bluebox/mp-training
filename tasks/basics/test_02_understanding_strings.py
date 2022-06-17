'''string assertion'''

__author__ = 'Hari'

#from tasks.basics.test_00_understanding_assert import TIME_TAKEN_MINUTES
#from stringprep import c22_specials
from tasks.placeholders import __author__, __all__, __, ___

NOTES = """string is one of the most commonly used data types, it has different
behavior than a char* in C."""

def test_string_type():
    '''string assertion'''
    assert 'str'== type("Hello World").__name__
    assert True is isinstance("Hello World", str)

def test_single_quoted_strings_are_strings():
    '''quoted string assertion'''
    assert True is isinstance('Hello World', str)

def test_double_quoted_strings_are_strings():
    '''double quoted string assertion'''
    assert True is isinstance("Hello World", str)

def test_triple_quoted_strings_are_strings():
    '''triple quoted string assertion'''
    assert True is isinstance("""Hello World""", str)

def test_triple_single_quoted_strings_are_strings():
    '''triple quoted string assertion'''
    assert True is isinstance('''Hello World''', str)

def test_raw_strings_are_strings():
    '''raw string assertion'''
    assert True is isinstance(r"Hello World", str)

def test_single_quoted_strings_can_have_double_quotes():
    '''single quote can have double quote'''
    first = 'The pilot said "Jump"'
    second = "The pilot said \"Jump\""  #note back slash escaping of "
    are_equal = (first == second)
    assert True is are_equal

def test_double_quoted_strings_can_have_single_quotes():
    '''double quote can have single quote'''
    first = "The pilot said 'Jump'"
    second = 'The pilot said \'Jump\''  #note back slash escaping of '
    are_equal = (first == second)
    assert True is are_equal

def test_triple_quoted_strings_can_have_both_single_and_double_quotes():
    '''triple quote can have both single and double'''

    tq_str = """ Isn't the "Hobbit" great? """
    dq_str = "Isn't the \"Hobbit\" great?"
    are_equal = (tq_str == dq_str)
    assert  False is are_equal

def test_triple_quoted_strings_can_span_lines():
    '''span line'''
    tq_str = """Hello
World"""
    dq_str = "Hello\nWorld"   # what is the double quoted form of tq_str
    assert True is (tq_str == dq_str)

def test_string_len():
    '''string len'''
    assert 13 == len("Hello 'world'")
    assert 13 == len('Hello \'world\'')

def test_triple_quoted_strings_can_span_lines1():
    '''triple quote can have span line'''
    string = """Hello
    World"""
    assert True is isinstance(string, str)
    assert 15 == len(string)

def test_strings_can_be_indexed():
    '''string can be indexed'''
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
        out_of_bounds = string[5] #raises an error, we will revisit exceptions later
        print(out_of_bounds)
    except IndexError as i_e:
        print (i_e)   #string index out of range
        assert True  #make this True to proceed.

def test_chars_are_strings_too():
    '''chars are also string'''
    string = "Hello"
    first_char = string[0]
    assert 'str' == type(first_char).__name__
    assert 'str' == type('a').__name__
    assert 'str'== type("a").__name__

def test_strings_are_immutable():
    '''strings are immutable'''
    string = "Hello"
    try:
        string[0] = "M"
    except TypeError as t_e:
        print (t_e)
        assert True

def test_string_concat():
    '''string concatenation'''
    assert "Hello  world"== "Hello " + " world"
    assert 'Hello world'== """Hello """ + 'world'
    assert 'Hello world'== 'Hello ' + "world"


def test_string_slicing():
    '''string slicing'''

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
    '''repeatition of string'''
    assert 'HelloHelloHello' == "Hello" * 3
    assert 12 == len("Hello " * 2)

def test_string_combine():
    '''addition of strings'''
    hello = "Hello World"
    bye = "Goodbye moon"
    assert  bye[0:8] + hello[6:]  == "Goodbye World"

def test_string_formatting():
    '''string formatting'''
    greeting = "Hello {0}".format("learner")
    assert "Hello 'learner'"== greeting

    truth = "{1} plus {1} makes {0}".format("two","one")
    assert truth == 'one plus one makes two'

    stmt = "{name} is {age} years old".format(name="Ravi", age=25)
    assert 'Ravi is 25 years old'== stmt

def test_string_membership():
    '''membership in string using in operator'''
    string='apple'
    c_1='c'
    c_2='a'
    c_3='app'
    assert False is (c_1 in string)  #is there a precedence issue here?
    assert True is (c_2 in 'apple')
    assert  True is (c_3 in 'apple')

THREE_THINGS_I_LEARNT = """
-strings are immutable
-strings can be accessed through indexing
-strings can be sliced
"""

TIME_TAKEN_MINUTES = 20
