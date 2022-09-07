__author__ = 'Hari'
from tasks.placeholders import __author__
NOTES = '''
nested functions underlie many advanced features of python. So a basic understanding of this
feature is essential to mastering python.

nested functions are defined in the scope of a function, behave exactly the same except
that they have a read only access to variables in the outer function.
'''

def outer_func(outer_var):
    '''method'''
    def inner_func(inner_var):
        '''method'''
        return outer_var + inner_var
    return inner_func

def test_inner_func_scope():
    '''method'''
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
    '''method'''
    f1 = outer_func(10)
    assert 'function' == type(f1).__name__
    assert 30 == f1(20)

def test_each_invocation_returns_a_new_func():
    '''method'''
    f1 = outer_func(10)
    f2 = outer_func(10)

    assert False is (f1 is f2)
    assert False is (f1 == f2)

    f3 = f2
    assert True is (f3 is f2)
    assert True is (f3 == f2)

def test_inner_func_has_access_to_outer_variables_after_return():
    '''method'''
    f1 = outer_func(20)
    f2 = outer_func(50)

    assert 50 == f1(30)
    assert 60 == f1(40)

    assert 80 == f2(30)
    assert 90 == f2(40)

def print_attributes(obj):
    '''method'''
    for x in dir(obj):
        print("attribute: {0}".format(x))
        print(getattr(obj, x))


def test_inner_func_attributes():
    '''method'''
    f_1 = outer_func(10)
    assert 35 == len(dir(f_1)) #how many attributes does f1 have

    # use the print_attributes function to explore the properties
    # fill up the attribute name that you think holds a reference to the
    # function scope variables
    ref_to_outer_scope = "global" 


# if you understand this, you have understood nested funcs :)
def test_inner_func_scoping():
    '''method'''
    def outer():
        '''method'''
        funcs = []
        for x in range(10):
            def inner():
                '''method'''
                return x
            funcs.append(inner)
        result = []
        for func in funcs:
            '''method'''
            result.append(func())
        return result

    assert [9,9,9,9,9,9,9,9,9,9] == outer()

# generally you should not write code like this :), this is only to learn
def test_outer_scope_is_read_only():
    '''method'''
    y = 30
    def outer(x):
        '''method'''
        def inner1():
            '''method'''
            x = 30
            return x
        def inner2():
            '''METHOD'''
            return x + y
        def inner3():
            '''METHOD'''
            y = 10
            return x + y
        return [inner1(), inner2(), inner3(), x, y]

    assert [30,50,30,20,30] == outer(20)

# def is an executable statement. the function name is nothing more than a name
# binding to a code object! So same scope rules as variables apply to function names.
# read up more at http://effbot.org/zone/default-values.htm
def test_def_is_a_statement():
    '''method'''
    def outer(x_1):
        '''method'''
        if x_1 > 10:
            def f():
                '''method'''
                return x_1 * 2
        else:
            def f():
                '''method'''
                return x_1 * 3
        return f

    assert 40 == outer(20)()
    assert 15 == outer(5)()


THREE = """
-
-
-
"""

RRI = 15
