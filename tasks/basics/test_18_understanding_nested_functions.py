"""understanding nested functions"""

__author__ = 'Hari'

notes = '''
nested functions underlie many advanced features of python. So a basic understanding of this
feature is essential to mastering python.

nested functions are defined in the scope of a function, behave exactly the same except
that they have a read only access to variables in the outer function.
'''

from tasks.placeholders import *

def outer_func(outer_var):
    """outer function"""
    def inner_func(inner_var):
        return outer_var + inner_var
    return inner_func

def test_inner_func_scope():
    """inner function scope"""
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
    """inner function can be returned"""
    f_1 = outer_func(10)
    assert 'function' == type(f_1).__name__
    assert 30 == f_1(20)

def test_each_invocation_returns_a_new_func():
    """each invocation returns a new funtion"""
    f_1 = outer_func(10)
    f_2 = outer_func(10)

    assert False is (f_1 is f_2)
    assert False is (f_1 == f_2)

    f_3 = f_2
    assert True is (f_3 is f_2)
    assert True is (f_3 == f_2)

def test_inner_func_has_access_to_outer_variables_after_return():
    """inner function has access to outer variables after return"""
    f_1 = outer_func(20)
    f_2 = outer_func(50)

    assert 50 == f_1(30)
    assert 60 == f_1(40)

    assert 80 == f_2(30)
    assert 90 == f_2(40)

def print_attributes(obj):
    """print attributes"""
    for item in dir(obj):
        print(f'attribute: {item}')
        print(getattr(obj, item))


def test_inner_func_attributes():
    """inner function attributes"""
    f_1 = outer_func(10)
    assert 35 == len(dir(f_1)) #how many attributes does f1 have

    # use the print_attributes function to explore the properties
    # fill up the attribute name that you think holds a reference to the
    # function scope variables
    # ref_to_outer_scope = '__global__'


# if you understand this, you have understood nested funcs :)
def test_inner_func_scoping():
    """inner function scoping"""
    def outer():
        funcs = []
        for item in range(10):
            def inner():
                return item
            funcs.append(inner)
        result = []
        for func in funcs:
            result.append(func())
        return result

    assert [9, 9, 9, 9, 9, 9, 9, 9, 9, 9] == outer()

# generally you should not write code like this :), this is only to learn
def test_outer_scope_is_read_only():
    """outer scope is read only"""
    _y = 30
    def outer(_x):
        def inner1():
            _x = 30
            return _x
        def inner2():
            return _x + _y
        def inner3():
            _y = 10
            return _x + _y
        return [inner1(), inner2(), inner3(), _x, _y]

    assert [30, 50, 30, 20, 30] == outer(20)

# def is an executable statement. the function name is nothing more than a name
# binding to a code object! So same scope rules as variables apply to function names.
# read up more at http://effbot.org/zone/default-values.htm
def test_def_is_a_statement():
    """def is a statement"""
    def outer(_x):
        if _x > 10:
            def _f():
                return _x * 2
        else:
            def _f():
                return _x * 3
        return _f

    assert 40 == outer(20)()
    assert 15 == outer(5)()


THREE_THINGS_I_LEARNT = """
- nested functions
- outer scope
- inner scope
"""

TIME_TAKEN_MINUTES = 20
