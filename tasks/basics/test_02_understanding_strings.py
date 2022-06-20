'''program file'''
__author__ = 'Hari'
#from tasks.placeholders import *
NOTES = """strng is one of the most commonly used data types, it has different
behavior than a char* in C."""

def test_string_type():
    '''function'''
    assert 'str'== type("Hello World").__name__
    assert True is isinstance("Hello World", str)

def test_single_quoted_strings_are_strings():
    '''function'''
    assert True is isinstance('Hello World', str)

def test_double_quoted_strings_are_strings():
    '''function'''
    assert True is isinstance("Hello World", str)

def test_triple_quoted_strings_are_strings():
    '''function'''
    assert True is isinstance("""Hello World""", str)

def test_triple_single_quoted_strings_are_strings():
    '''function'''
    assert True is isinstance('''Hello World''', str)

def test_raw_strings_are_strings():
    '''function'''
    assert True is isinstance(r"Hello World", str)

def test_single_quoted_strings_can_have_double_quotes():
    '''function'''
    first = 'The pilot said "Jump"'
    second = "The pilot said \"Jump\""  #note back slash escaping of "
    are_equal = (first == second)
    assert True is are_equal

def test_double_quoted_strings_can_have_single_quotes():
    '''function'''
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
    '''function'''
    tq_str = """Hello
World"""
    dq_str = "Hello\nWorld"   # what is the double quoted form of tq_str
    assert (tq_str == dq_str)

def test_string_len():
    '''function'''
    assert 13 == len("Hello 'world'")
    assert 13 == len('Hello \'world\'')

def test_triple_quoted_strings_can_span_lines():
    '''function'''
    strng = """Hello World"""
    assert True is isinstance(strng, str)
    assert 15 == len(strng)

def test_strings_can_be_indexed():
    '''function'''
    strng = "Hello"
    assert 'H'== strng[0]
    assert 'e'== strng[1]
    assert 'l' == strng[2]
    assert 'l'== strng[3]
    assert 'o' == strng[4]
    assert 'o' == strng[-1]  # solves the common use case to iterate from end
    assert 'l' == strng[-2]
    assert 'l' == strng[-3]
    assert 'e' == strng[-4]
    assert 'H' == strng[-5]
    assert 'H' == strng[-0]  # hint -0 is 0
    assert  5 == len(strng)
    try:
        out_of_bounds = strng[5] #raises an error, we will revisit exceptions later
    except IndexError as ie_1 :
        print (ie_1)   #strng index out of range
        assert True  #make this True to proceed.

def test_chars_are_strings_too():
    '''function'''
    strng = "Hello"
    first_char = strng[0]
    assert 'str' == type(first_char).__name__
    assert 'str' == type('a').__name__
    assert 'str'== type("a").__name__

def test_strings_are_immutable():
    '''function'''
    """ strings in python cannot be modified unlike in C """
    strng = "Hello"
    try:
        strng[0] = "M"
    except TypeError as te_1:
        print (te_1)
        assert True

def test_string_concat():
    '''function'''
    assert "Hello  world"== "Hello " + " world"
    assert 'Hello world'== """Hello """ + 'world'
    assert 'Hello world'== 'Hello ' + "world"

def test_string_slicing():
    """ Slicing creates new strings """
    strng = "Hello world"
    #with begin : end
    assert ''== strng[0:0]
    assert 'He' == strng[0:2]
    assert 'ello'== strng[1:5]
    assert 'ello worl'== strng[1:-1]
    assert 'llo wor'== strng[2:-2]
    #with :end
    assert ''== strng[:0]
    assert 'Hell'== strng[:4]
    assert 'Hello worl'== strng[:-1]
    #with begin:
    assert 'Hello world'== strng[0:]
    assert 'o world'== strng[4:]
    assert 'd'== strng[-1:]
    #observe the invariant
    assert 'Hello world' == strng[:0] + strng[0:]
    assert 'Hello world' == strng[:1] + strng[1:]
    assert 'Hello world'== strng[:2] + strng[2:]
    assert 'Hello world'== strng[:3] + strng[3:]

def test_string_repeat():
    '''function'''
    assert 'HelloHelloHello' == "Hello" * 3
    assert 12 == len("Hello " * 2)

def test_string_combine():
    '''function'''
    """
    Use slicing to pass the assert.
    """
    hello = "Hello World"
    bye = "Goodbye moon"
    assert  bye[0:8] + hello[6:]  == "Goodbye World"

def test_string_formatting():
    '''function'''
    greeting = "Hello '{0}'".format("learner")
    assert "Hello 'learner'"== greeting
    truth = "{1} plus {1} makes {0}".format("two","one")
    assert truth == 'one plus one makes two'
    stmt = "{name} is {age} years old".format(name="Ravi", age=25)
    assert 'Ravi is 25 years old'== stmt

def test_string_membership():
    '''function'''
    assert False is 'c' in 'apple'  #is there a precedence issue here?
    assert True is 'a' in 'apple'
    assert  True is ('app' in 'apple')
# '==' and 'in' operators have same precedence are interpreted from left to right in the expression

THREE_THINGS_I_LEARNT = """
-strings are immutable
-strings can be accessed through indexing
-strings can be sliced
"""
TIME_TAKEN_MINUTES = 20
