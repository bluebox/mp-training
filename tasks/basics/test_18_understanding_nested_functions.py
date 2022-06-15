__author__ = 'Hari'

notes = '''
nested functions underlie many advanced features of python. So a basic understanding of this
feature is essential to mastering python.

nested functions are defined in the scope of a function, behave exactly the same except
that they have a read only access to variables in the outer function.
'''

from placeholders import *

def outer_func(outer_var):
    def inner_func(inner_var):
        return outer_var + inner_var
    return inner_func

def test_inner_func_scope():
    # inner_func not accessible by default
    try:
        inner_func()
    except __:  # fill up the exception
        pass

    # this syntax does not work either, it is not just static scoping.
    try:
        outer_func.inner_func()
    except __ : # fillup the exception
        pass

def test_inner_func_can_be_returned():
    f1 = outer_func(10)
    assert __ == type(f1).__name__
    assert __ == f1(20)

def test_each_invocation_returns_a_new_func():
    f1 = outer_func(10)
    f2 = outer_func(10)

    assert __ == (f1 is f2)
    assert __ == (f1 == f2)

    f3 = f2
    assert __ == (f3 is f2)
    assert __ == (f3 == f2)

def test_inner_func_has_access_to_outer_variables_after_return():
    f1 = outer_func(20)
    f2 = outer_func(50)

    assert __ == f1(30)
    assert __ == f1(40)

    assert __ == f2(30)
    assert __ == f2(40)

def print_attributes(obj):
    for x in dir(obj):
        print "attribute: " + x
        print getattr(obj, x)

def test_inner_func_attributes():
    f1 = outer_func(10)
    assert __ == len(dir(f1)) #how many attributes does f1 have

    # use the print_attributes function to explore the properties
    # fill up the attribute name that you think holds a reference to the
    # function scope variables
    ref_to_outer_scope = __


# if you understand this, you have understood nested funcs :)
def test_inner_func_scoping():
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

    assert [__] == outer()

# generally you should not write code like this :), this is only to learn
def test_outer_scope_is_read_only():
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

    assert [__] == outer(20)

# def is an executable statement. the function name is nothing more than a name
# binding to a code object! So same scope rules as variables apply to function names.
# read up more at http://effbot.org/zone/default-values.htm
def test_def_is_a_statement():
    def outer(x):
        if x > 10:
            def f():
                return x * 2
        else:
            def f():
                return x * 3
        return f

    assert __ == outer(20)()
    assert __ == outer(5)()


three_things_i_learnt = """
-
-
-
"""

time_taken_minutes = ___
