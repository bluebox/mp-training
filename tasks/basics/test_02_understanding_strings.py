"""__author__ = 'Hari'
2nd Exercise"""

NOTES = """string is one of the most commonly used data types, it has different
behavior than a char* in C."""


def test_string_type():
    """Learning about string"""
    assert type("Hello World").__name__ is 'str'
    assert isinstance("Hello World", str) is True


def test_single_quoted_strings_are_strings():
    """Learning about string"""
    assert isinstance('Hello World', str) is True


def test_double_quoted_strings_are_strings():
    """Learning about string"""
    assert isinstance("Hello World", str) is True


def test_triple_quoted_strings_are_strings():
    """Learning about string"""
    assert isinstance("""Hello World""", str) is True


def test_triple_single_quoted_strings_are_strings():
    """Learning about string"""
    assert isinstance('''Hello World''', str) is True


def test_raw_strings_are_strings():
    """Learning about string"""
    assert isinstance(r"Hello World", str) is True


def test_single_quoted_strings_can_have_double_quotes():
    """Learning about string"""
    first = 'The pilot said "Jump"'
    second = "The pilot said \"Jump\""  # note back slash escaping of "
    are_equal = (first == second)
    assert are_equal is False


def test_double_quoted_strings_can_have_single_quotes():
    """Learning about string"""
    first = "The pilot said 'Jump'"
    second = 'The pilot said \'Jump\''  # note back slash escaping of '
    are_equal = (first == second)
    assert are_equal is True


def test_triple_quoted_strings_can_have_both_single_and_double_quotes():
    """ Edit tq_str to make are_equal True """
    tq_str = """ Isn't the "Hobbit" great? """
    dq_str = "Isn't the \"Hobbit\" great?"
    are_equal = (tq_str == dq_str)
    assert are_equal is False

def test_triple_quoted_strings_can_span_lines():
    """Learning about string"""
    tq_str = """Hello
World"""
    dq_str = "Hello\nWorld"  # what is the double quoted form of tq_str
    assert (tq_str == dq_str)


def test_string_len():
    """Learning about string"""
    assert 13 == len("Hello 'world'")
    assert 13 == len('Hello \'world\'')


def test_triple_quoted_strings_can_span_lines():
    """Learning about string"""
    string = """Hello
    World"""
    assert isinstance(string, str) is True
    assert 15 == len(string)


def test_strings_can_be_indexed():
    """Learning about string"""
    string = "Hello"
    assert 'H' == string[0]
    assert 'e' == string[1]
    assert 'l' == string[2]
    assert 'l' == string[3]
    assert 'o' == string[4]
    assert 'o' == string[-1]  # solves the common use case to iterate from end
    assert 'l' == string[-2]
    assert 'l' == string[-3]
    assert 'e' == string[-4]
    assert 'H' == string[-5]
    assert 'H' == string[-0]  # hint -0 is 0
    assert 5 == len(string)
    try:
        out_of_bounds = string[5]  # raises an error, we will revisit exceptions later
    except IndexError as ie:
        print(ie)  # string index out of range
        assert True  # make this True to proceed.


def test_chars_are_strings_too():
    """Learning about string"""
    string = "Hello"
    first_char = string[0]
    assert 'str' == type(first_char).__name__
    assert type('a').__name__ == 'str'
    assert type("a").__name__ == 'str'


def test_strings_are_immutable():
    """Learning about string"""
    """ strings in python cannot be modified unlike in C """
    string = "Hello"
    try:
        string[0] = "M"
    except TypeError as te:
        print(te)
        assert True


def test_string_concat():
    """Learning about string"""
    assert "Hello  world" == "Hello " + " world"
    assert 'Hello world' == """Hello """ + 'world'
    assert 'Hello world' == 'Hello ' + "world"


def test_string_slicing():
    """Learning about string"""
    """ Slicing creates new strings """
    string = "Hello world"
    # with begin : end
    assert '' == string[0:0]

    assert 'He' == string[0:2]
    assert 'ello' == string[1:5]
    assert 'ello worl' == string[1:-1]
    assert 'llo wor' == string[2:-2]

    # with :end
    assert '' == string[:0]
    assert 'Hell' == string[:4]
    assert 'Hello worl' == string[:-1]

    # with begin:
    assert 'Hello world' == string[0:]
    assert 'o world' == string[4:]
    assert 'd' == string[-1:]

    # observe the invariant
    assert 'Hello world' == string[:0] + string[0:]
    assert 'Hello world' == string[:1] + string[1:]
    assert 'Hello world' == string[:2] + string[2:]
    assert 'Hello world' == string[:3] + string[3:]


def test_string_repeat():
    """Learning about string"""
    assert 'HelloHelloHello' == "Hello" * 3
    assert 12 == len("Hello " * 2)


def test_string_combine():

    """
    Use slicing to pass the assert.
    """
    hello = "Hello World"
    bye = "Goodbye moon"
    assert bye[0:8] + hello[6:] == "Goodbye World"


def test_string_formatting():
    """Learning about string"""
    greeting = "Hello '{0}'".format("learner")
    assert "Hello 'learner'" == greeting

    truth = "{1} plus {1} makes {0}".format("two", "one")
    assert truth == 'one plus one makes two'

    stmt = "{name} is {age} years old".format(name="Ravi", age=25)
    assert 'Ravi is 25 years old' == stmt


def test_string_membership():
    """Learning about string"""
    c_c = 'c'
    assert (c_c in 'apple') is False
    # is there a precedence issue here?
    a_a = 'a'
    assert (a_a in 'apple') is True
    app_a = 'app'
    assert (app_a in 'apple') is True
    # '==' and 'in' operators have same precedence are interpreted from left to right in the expression


# three_things_i_learnt = """
# -strings are immutable
# -strings can be accessed through indexing
# -strings can be sliced
# """
#
# time_taken_minutes = 20
