__author__ = 'Hari'

notes = '''
nested functions underlie many advanced features of python. So a basic understanding of this
feature is essential to mastering python.

nested functions are defined in the scope of a function, behave exactly the same except
that they have a read only access to variables in the outer function.
'''

from tasks.placeholders import *

""" Program to understand nesting """
def outer_func(outer_var):
    """ demo outer function """
    def inner_func(inner_var):
        """ demo inner function """
        return outer_var + inner_var
    return inner_func

def test_inner_func_scope():
    """ to test scope of inner function """
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
test_inner_func_scope()
def test_inner_func_can_be_returned():
    """ test what inner function returns """
    f1 = outer_func(10)
    assert "function" == type(f1).__name__
    assert 30 == f1(20)
test_inner_func_can_be_returned()
def test_each_invocation_returns_a_new_func():
    """ test invocation of function """
    f1 = outer_func(10)
    f2 = outer_func(10)

    assert False is (f1 is f2)
    assert False is (f1 == f2)

    f3 = f2
    assert True == (f3 is f2)
    assert True == (f3 == f2)
test_each_invocation_returns_a_new_func()
def test_inner_func_has_access_to_outer_variables_after_return():
    """ test inner function access to outer variables """
    f1 = outer_func(20)
    f2 = outer_func(50)

    assert 50 == f1(30)
    assert 60 == f1(40)

    assert 80 == f2(30)
    assert 90 == f2(40)
test_inner_func_has_access_to_outer_variables_after_return()
def print_attributes(obj):
    """ for printing the attributes """
    for x in dir(obj):
        print("attribute: {0}".format(x))
        print(getattr(obj, x))

def test_inner_func_attributes():
    """ function to test the attributes of inner function """
    f1 = outer_func(10)
    assert 35 == len(dir(f1)) #how many attributes does f1 have

    # use the print_attributes function to explore the properties
    # fill up the attribute name that you think holds a reference to the
    # function scope variables
    ref_to_outer_scope = "__global__"
test_inner_func_attributes()

# if you understand this, you have understood nested funcs :)
def test_inner_func_scoping():
    """ function to test inner function scoping """
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
test_inner_func_scoping()
# generally you should not write code like this :), this is only to learn
def test_outer_scope_is_read_only():
    """ function to test outer scope """
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
    """ function to test definition of statement """
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
test_def_is_a_statement()


three_things_i_learnt = """
-concept of nested class in python 
-workflow of nested class
-calling a inner function
"""

time_taken_minutes = 70
