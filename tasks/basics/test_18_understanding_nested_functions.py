__author__ = 'Hari'

NOTES = '''
nested functions underlie many advanced features of python. So a basic understanding of this
feature is essential to mastering python.

nested functions are defined in the scope of a function, behave exactly the same except
that they have a read only access to variables in the outer function.
'''

from tasks.placeholders import *

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
    f1 = outer_func(10)
    assert 'function' == type(f1).__name__
    assert  30 == f1(20)

def test_each_invocation_returns_a_new_func():
    f1 = outer_func(10)
    f2 = outer_func(10)

    assert False is (f1 is f2)
    assert False is (f1 == f2)

    f3 = f2
    assert True is (f3 is f2)
    assert True is (f3 == f2)

def test_inner_func_has_access_to_outer_variables_after_return():
    f1 = outer_func(20)
    f2 = outer_func(50)

    assert 50 == f1(30)
    assert 60 == f1(40)

    assert 80 == f2(30)
    assert 90 == f2(40)

def print_attributes(obj):
    for x in dir(obj):
        print("attribute: {0} ".format(x))
        print (getattr(obj, x))

def test_inner_func_attributes():
    f1 = outer_func(10)
    assert 35 == len(dir(f1)) #how many attributes does f1 have

    # use the print_attributes function to explore the properties
    # fill up the attribute name that you think holds a reference to the
    # function scope variables
    ref_to_outer_scope = "globally"


# if you understand this, you have understood nested funcs :)
def test_inner_func_scoping():
    def outer():
        funcs = []
        for var_x in range(10):
            def inner():
                return var_x
            funcs.append(inner)
        result = []
        for func in funcs:
            result.append(func())
        return result

    assert [9,9,9,9,9,9,9,9,9,9] == outer()

# generally you should not write code like this :), this is only to learn
def test_outer_scope_is_read_only():
    var_y = 30
    def outer(var_x):
        def inner1():
            var_x = 30
            return var_x
        def inner2():
            return var_x + var_y
        def inner3():
            var_y = 10
            return var_x + var_y
        return [inner1(), inner2(), inner3(), var_x, var_y]

    assert [30,50,30,20,30] == outer(20)

# def is an executable statement. the function name is nothing more than a name
# binding to a code object! So same scope rules as variables apply to function names.
# read up more at http://effbot.org/zone/default-values.htm
def test_def_is_a_statement():
    def outer(var_x):
        if var_x > 10:
            def f_n():
                return var_x * 2
        else:
            def f_n():
                return var_x * 3
        return f_n

    assert 40 == outer(20)()
    assert 15 == outer(5)()


THREE_THINGS_I_LEARNT = """
-functions
-nested functions
-passing arguments in inner and outer functions
"""

TIME_TAKEN__MINUTES = 2
