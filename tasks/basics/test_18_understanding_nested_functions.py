"""MODULE FOR UNDERSTANDING NESTED FUNCTIONS"""

__author__ = 'Hari'

NOTES = '''
nested functions underlie many advanced features of python. So a basic understanding of this
feature is essential to mastering python.

nested functions are defined in the scope of a function, behave exactly the same except
that they have a read only access to variables in the outer function.
'''

from tasks.placeholders import *


def outer_func(outer_var):
    """
    outer function definition
    :param outer_var:
    :return: return inner function
    """
    def inner_func(inner_var):
        return outer_var + inner_var
    return inner_func


def test_inner_func_scope():
    """
    test inner function scope
    :return: None
    """
    # inner_func not accessible by default
    try:
        inner_func()
    except NameError:  # fill up the exception
        pass

    # this syntax does not work either, it is not just static scoping.
    try:
        outer_func.inner_func()
    except AttributeError:  # fill up the exception
        pass


def test_inner_func_can_be_returned():
    """
    test inner functions can be returned
    :return:
    """
    func_f1 = outer_func(10)
    assert 'function' == type(func_f1).__name__
    assert 30 == func_f1(20)


def test_each_invocation_returns_a_new_func():
    """
    test each invocation returns a new function
    :return:
    """
    func_f1 = outer_func(10)
    func_f2 = outer_func(10)

    assert False is (func_f1 is func_f2)
    assert False is (func_f1 == func_f2)

    func_f3 = func_f2
    assert True is (func_f3 is func_f2)
    assert True is (func_f3 == func_f2)


def test_inner_func_has_access_to_outer_variables_after_return():
    """
    test inner function has access to outer variables after return
    :return: None
    """
    func_f1 = outer_func(20)
    func_f2 = outer_func(50)

    assert 50 == func_f1(30)
    assert 60 == func_f1(40)

    assert 80 == func_f2(30)
    assert 90 == func_f2(40)


def print_attributes(obj):
    """
    print attribute helper function
    :param obj:
    :return: None
    prints object attributes
    """
    for x in dir(obj):
        print("attribute: {0}".format(x))
        print(getattr(obj, x))


def test_inner_func_attributes():
    """
    test inner function attributes
    :return: None
    """
    f1 = outer_func(10)
    assert 35 == len(dir(f1))  # how many attributes does f1 have

    # use the print_attributes function to explore the properties
    # fill up the attribute name that you think holds a reference to the
    # function scope variables
    # ref_to_outer_scope = '__global__'


# if you understand this, you have understood nested funcs :)
def test_inner_func_scoping():
    """
    test inner function scoping
    :return: None
    """
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
    """test outer scope is read only"""
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
# read up more at http://effbot.org/zone/default-values.html
def test_def_is_a_statement():
    """
    test def is a statement
    :return: None
    """
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
-scope of variables inside nested functions
-dir() function
-def is an executable statement
"""


TIME_TAKEN_MINUTES = 25
