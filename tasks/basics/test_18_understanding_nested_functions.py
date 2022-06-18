"""Code Author"""
__author__ = 'Hari'

from tasks.placeholders import __author__

NOTES = '''
nested functions underlie many advanced features of python. So a basic understanding of this
feature is essential to mastering python.

nested functions are defined in the scope of a function, behave exactly the same except
that they have a read only access to variables in the outer function.
'''


def outer_func(outer_var):
    def inner_func(inner_var):
        return outer_var + inner_var
    return inner_func

def test_inner_func_scope():
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
    f_1 = outer_func(10)
    assert "function" == type(f_1).__name__
    assert 30 == f_1(20)

def test_each_invocation_returns_a_new_func():
    f_1 = outer_func(10)
    f_2 = outer_func(10)

    assert False is (f_1 is f_2)
    assert False is (f_1 == f_2)

    f_3 = f_2
    assert True is (f_3 is f_2)
    assert True is (f_3 == f_2)

def test_inner_func_has_access_to_outer_variables_after_return():
    f_1 = outer_func(20)
    f_2 = outer_func(50)

    assert 50 == f_1(30)
    assert 60 == f_1(40)

    assert 80 == f_2(30)
    assert 90 == f_2(40)

def print_attributes(obj):
    for var in dir(obj):
        print("attribute: {0}".format(var))
        print(getattr(obj, var))


def test_inner_func_attributes():
    f_1 = outer_func(10)
    assert 36 == len(dir(f_1)) #how many attributes does f1 have

    # use the print_attributes function to explore the properties
    # fill up the attribute name that you think holds a reference to the
    # function scope variables
    ref_to_outer_scope = "__globals__"


# if you understand this, you have understood nested funcs :)
def test_inner_func_scoping():
    def outer():
        funcs = []
        for var in range(10):
            def inner():
                return var
            funcs.append(inner)
        result = []
        for func in funcs:
            result.append(func())
        return result

    assert [9, 9, 9, 9, 9, 9, 9, 9, 9, 9] == outer()

# generally you should not write code like this :), this is only to learn
def test_outer_scope_is_read_only():
    vari = 30
    def outer(var):
        def inner1():
            var = 30
            return var
        def inner2():
            return var + vari
        def inner3():
            vari = 10
            return var + vari
        return [inner1(), inner2(), inner3(), var, vari]

    assert [30,50,30,20,30] == outer(20)

# def is an executable statement. the function name is nothing more than a name
# binding to a code object! So same scope rules as variables apply to function names.
# read up more at http://effbot.org/zone/default-values.htm
def test_def_is_a_statement():
    def outer(var):
        if var > 10:
            def func():
                return var * 2
        else:
            def func():
                return var * 3
        return func

    assert 40 == outer(20)()
    assert 15 == outer(5)()


THREE_THINGS_I_LEARNT = """
-Functions
-Nested Functions
-Function scopes
"""

TIME_TAKEN_MINUTES = 10
