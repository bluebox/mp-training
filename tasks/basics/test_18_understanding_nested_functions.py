<<<<<<< HEAD
#from tasks.placeholders import *
=======
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
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
    except __:  # fill up the exception
        pass

    # this syntax does not work either, it is not just static scoping.
    try:
        outer_func.inner_func()
    except __ : # fillup the exception
        pass

def test_inner_func_can_be_returned():
<<<<<<< HEAD
    f_1 = outer_func(10)
    assert 'function' == type(f_1).__name__
    assert 30 is f_1(20)


def test_each_invocation_returns_a_new_func():
    f_1 = outer_func(10)
    f_2 = outer_func(10)

    assert False is (f_1 is f_2)
    assert False is (f_1 == f_2)

    f_3 = f_2
    assert True is (f_3 is f_2)
    assert True is (f_3 == f_2)
=======
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
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_inner_func_has_access_to_outer_variables_after_return():
    f_1 = outer_func(20)
    f_2 = outer_func(50)

<<<<<<< HEAD
    assert 50 is f_1(30)
    assert 60 is f_1(40)

    assert 80 is f_2(30)
    assert 90 is f_2(40)
=======
    assert __ == f1(30)
    assert __ == f1(40)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

    assert __ == f2(30)
    assert __ == f2(40)

def print_attributes(obj):
    for _x in dir(obj):
        print("attribute: {0}".format(_x))
        print(getattr(obj, _x))


def test_inner_func_attributes():
<<<<<<< HEAD
    f_1 = outer_func(10)
    assert 35 is len(dir(f_1)) #how many attributes does f1 have
=======
    f1 = outer_func(10)
    assert __ == len(dir(f1)) #how many attributes does f1 have
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

    # use the print_attributes function to explore the properties
    # fill up the attribute name that you think holds a reference to the
    # function scope variables
    ref_to_outer_scope = __


# if you understand this, you have understood nested funcs :)
def test_inner_func_scoping():
    def outer():
        funcs = []
        for _x in range(10):
            def inner():
                return _x
            funcs.append(inner)
        result = []
        for func in funcs:
            result.append(func())
        return result

    assert [__] == outer()

# generally you should not write code like this :), this is only to learn
def test_outer_scope_is_read_only():
<<<<<<< HEAD
    _y = 30

    def outer(_x):
        def inner1():
            _x = 30
            return _x

        def inner2():
            return _x +_y

=======
    y = 30
    def outer(x):
        def inner1():
            x = 30
            return x
        def inner2():
            return x + y
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
        def inner3():
            _y = 10
            return _x + _y
        return [inner1(), inner2(), inner3(), _x, _y]

    assert [__] == outer(20)

# def is an executable statement. the function name is nothing more than a name
# binding to a code object! So same scope rules as variables apply to function names.
# read up more at http://effbot.org/zone/default-values.htm
def test_def_is_a_statement():
    def outer(_x):
        if _x > 10:
            def method_f():
                return _x * 2
        else:
            def method_f():
                return _x * 3
        return method_f

<<<<<<< HEAD
    assert 40 is outer(20)()
    assert 15 is outer(5)()
=======
    assert __ == outer(20)()
    assert __ == outer(5)()
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


THREE_THINGS_I_LEARNT = """
nested functions
"""

<<<<<<< HEAD
TIME_TAKEN_MINUTES = 120
=======
time_taken_minutes = ___
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
