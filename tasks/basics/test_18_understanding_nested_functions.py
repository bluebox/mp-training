""" Understandig how the functions Nested inside other functions works """

__author__ = 'Hari'


NOTES = '''
nested functions underlie many advanced features of python. So a basic understanding of this
feature is essential to mastering python.

nested functions are defined in the scope of a function, behave exactly the same except
that they have a read only access to variables in the outer function.
'''


from tasks.placeholders import *


def outer_func(outer_var):
    """ Outer function returning innner function"""
    def inner_func(inner_var):
        """ Inner function returning the sum of two variables passed down """
        return outer_var + inner_var
    return inner_func


def test_inner_func_scope():
    """ Inner function scope """
    # inner_func not accessible by default
    try:
        inner_func()
    except NameError:  # fill up the exception
        pass

    # this syntax does not work either, it is not just static scoping.
    try:
        outer_func.inner_func()
    except AttributeError : # fillup the exception
        pass


def test_inner_func_can_be_returned():
    """ Inner function can be returned """
    f_1 = outer_func(10)
    assert 'function' == type(f_1).__name__
    assert 30 == f_1(20)


def test_each_invocation_returns_a_new_func():
    """ Each invoacation will return a new function """
    f_1 = outer_func(10)
    f_2 = outer_func(10)

    assert False == (f_1 is f_2)
    assert False == (f_1 == f_2)

    f_3 = f_2
    assert True == (f_3 is f_2)
    assert True == (f_3 == f_2)


def test_inner_func_has_access_to_outer_variables_after_return():
    """ Inner function has access to outer variables after return """
    f_1 = outer_func(20)
    f_2 = outer_func(50)

    assert 50 == f_1(30)
    assert 60 == f_1(40)

    assert 80 == f_2(30)
    assert 90 == f_2(40)


def print_attributes(obj):
    """ Printing attributes """
    for x in dir(obj):
        print("attribute: {0}".format(x))
        print(getattr(obj, x))


def test_inner_func_attributes():
    """ Inner function Attributes """
    f1 = outer_func(10)
    assert 35 == len(dir(f1))  # how many attributes does f1 have
    # use the print_attributes function to explore the properties
    # fill up the attribute name that you think holds a reference to the
    # function scope variables
    ref_to_outer_scope = 'outer_func().__getattribute__'


# if you understand this, you have understood nested funcs :)
def test_inner_func_scoping():
    """ inner function scoping """
    def outer():
        funcs = []
        for x in range(10):
            def inner():
                return x
            funcs.append(inner)
        result = []
        for func in funcs:
            result.append(func())
        return result

    assert [9, 9, 9, 9, 9, 9, 9, 9, 9, 9] == outer()


# generally you should not write code like this :), this is only to learn
def test_outer_scope_is_read_only():
    """" Outer scope is read only """
    y = 30
    def outer(x):
        def inner1():
            x = 30
            return x
        def inner2():
            return x + y
        def inner3():
            y = 10
            return x + y
        return [inner1(), inner2(), inner3(), x, y]

    assert [30, 50, 30, 20, 30] == outer(20)


# def is an executable statement. the function name is nothing more than a name
# binding to a code object! So same scope rules as variables apply to function names.
# read up more at http://effbot.org/zone/default-values.htm
def test_def_is_a_statement():
    """ def is a statement """
    def outer(x):
        if x > 10:
            def f():
                return x * 2
        else:
            def f():
                return x * 3
        return f

    assert 40 == outer(20)()
    assert 15 == outer(5)()


THREE_THINGS_I_LEARNT = """
- The scoping of functions
- Nested functions
- Inner function has access to outer variables after return i.e Variable access
"""

TIME_TAKEN_MINUTES = 20
