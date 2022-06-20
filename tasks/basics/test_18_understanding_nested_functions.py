'''nested_function'''
__author__ = 'Hari'

NOTES_1 = '''
nested functions underlie many advanced features of
python. So a basic understanding of this
feature is essential to mastering python.

nested functions are defined in the scope of a
function, behave exactly the same except
that they have a read only access to variables in the outer function.
'''

# from tasks.placeholders import *

def outer_func(outer_var):
    '''nested_function'''
    def inner_func(inner_var):
        '''nested_function'''
        return outer_var + inner_var
    return inner_func

def test_inner_func_scope():
    '''nested_function'''
    # inner_func not accessible by default
    try:
        inner_func()
    except NameError:  # fill up the exception
        pass

    # this syntax does not work either, it is not just static scoping.
    try:
        outer_func.inner_func()
    except AttributeError: # fillup the exception
        pass

def test_inner_func_can_be_returned():
    '''nested_function'''
    f1_1 = outer_func(10)
    assert type(f1_1).__name__ == 'function'
    assert f1_1(20) == 30
def test_each_invocation_returns_a_new_func():
    '''nested_function'''
    f1_1 = outer_func(10)
    f2_1 = outer_func(10)

    assert (f1_1 is f2_1) is False
    assert (f1_1 == f2_1) is False

    f3_1 = f2_1
    assert (f3_1 is f2_1) is True
    assert (f3_1 == f2_1) is True

def test_inner_func_has_access_to_outer_variables_after_return():
    '''nested_function'''
    f1_1 = outer_func(20)
    f2_1 = outer_func(50)

    assert f1_1(30) == 50
    assert f1_1(40) == 60

    assert f2_1(30) == 80
    assert f2_1(40) == 90

def print_attributes(obj):
    '''nested_function'''
    for x_1 in dir(obj):
        print("attribute: {0}".format(x_1))
        print(getattr(obj, x_1))


def test_inner_func_attributes():
    '''nested_function'''
    f1_1 = outer_func(10)
    assert len(dir(f1_1)) == 35 #how many attributes does f1 have

    # use the print_attributes function to explore the properties
    # fill up the attribute name that you think holds a reference to the
    # function scope variables
    ref_to_outer_scope = __


# if you understand this, you have understood nested funcs :)
def test_inner_func_scoping():
    '''nested_function'''
    def outer():
        '''nested_function'''
        funcs = []
        for x_1 in range(10):
            def inner():
                return x_1
            funcs.append(inner)
        result = []
        for func in funcs:
            result.append(func())
        return result

    assert outer() == [9, 9, 9, 9, 9, 9, 9, 9, 9, 9]

# generally you should not write code like this :), this is only to learn
def test_outer_scope_is_read_only():
    '''nested_function'''
    y_1 = 30
    def outer(x_1):
        '''nested_function'''
        def inner1():
            '''nested_function'''
            x_1 = 30
            return x_1
        def inner2():
            '''nested_function'''
            return x_1 + y_1
        def inner3():
            '''nested_function'''
            y_1 = 10
            return x_1 + y_1
        return [inner1(), inner2(), inner3(), x_1, y_1]

    assert outer(20) == [30, 50, 30, 20, 30]

# def is an executable statement. the function name is nothing more than a name
# binding to a code object! So same scope rules as variables apply to function names.
# read up more at http://effbot.org/zone/default-values.htm
def test_def_is_a_statement():
    '''nested_function'''
    def outer(x_1):
        '''nested_function'''
        if x_1 > 10:
            def f_1():
                '''nested_function'''
                return x_1 * 2
        else:
            def f_1():
                '''nested_function'''
                return x_1 * 3
        return f_1

    assert outer(20)() == 40
    assert outer(5)() == 15


THREE_THINGS_I_LEARNT = """
- nested functions underlie many advanced features of python.
So a basic understanding of this
feature is essential to mastering python.
- nested functions are defined in the scope of a function, 
-  behave exactly the same except that they have a read only
access to variables in the outer function.
"""

TIME_TAKEN_MINUTES = 15
