__author__ = 'Hari'
from tasks.placeholders import __author__

NOTES = '''
nested functions underlie many advanced features of python. So a basic understanding of this
feature is essential to mastering python.

nested functions are defined in the scope of a function, behave exactly the same except
that they have a read only access to variables in the outer function.
'''


def outer_func(otr_var):
    def inner_func(inner_var):
        return otr_var + inner_var
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
    var_f1 = outer_func(10)
    assert 'function' == type(var_f1).__name__
    assert 30 == var_f1(20)

def test_each_invocation_returns_a_new_func():
    var_f1 = outer_func(10)
    var_f2 = outer_func(10)

    assert False is (var_f1 is var_f2)
    assert False is (var_f1 == var_f2)

    f3 = var_f2
    assert True is (f3 is var_f2)
    assert True is (f3 == var_f2)

def test_inner_func_has_access_to_otr_variables_after_return():
    var_f1 = outer_func(20)
    var_f2 = outer_func(50)

    assert 50 == var_f1(30)
    assert 60 == var_f1(40)

    assert  80== var_f2(30)
    assert  90== var_f2(40)

def print_attributes(obj):
    for x in dir(obj):
        print("attribute: {0}".format(x))
        print(getattr(obj, x))


def test_inner_func_attributes():
    var_f1 = outer_func(10)
    assert 35 == len(dir(var_f1)) #how many attributes does f1 have

    # use the print_attributes function to explore the properties
    # fill up the attribute name that you think holds a reference to the
    # function scope variables
    ref_to_outer_scope = 8


# if you understand thi
# s, you have understood nested funcs :)
def test_inner_func_scoping():
    def outer():
        funcs = []
        for x_1 in range(10):
            def inner():
                return x_1
            funcs.append(inner)
        result = []
        for func in funcs:
            result.append(func())
        return result

    assert [9,9,9,9,9,9,9,9,9,9] == outer()

# generally you should not write code like this :), this is only to learn
def test_outer_scope_is_read_only():
    y_1 = 30
    def outer(x_1):
        def inner1():
            x_1 = 30
            return x_1
        def inner2():
            return x_1 + y_1
        def inner3():
            y_1 = 10
            return x_1 + y_1
        return [inner1(), inner2(), inner3(), x_1, y_1]

    assert [30,50,30,20,30] == outer(20)

# def is an executable statement. the function name is nothing more than a name
# binding to a code object! So same scope rules as variables apply to function names.
# read up more at http://effbot.org/zone/default-values.htm
def test_def_is_a_statement():
    def outer(x_1):
        if x_1 > 10:
            def f():
                return x_1 * 2
        else:
            def f():
                return x_1 * 3
        return f

    assert 40 == outer(20)()
    assert 15 == outer(5)()


THREE_THINGS_I_LEARNT = """
-passing functions in list to sub function
-functions in if else
-passing arguments to sub functions
"""

TIME_TAKEN_MINUTES = 50