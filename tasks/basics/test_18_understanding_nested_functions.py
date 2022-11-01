"""__author__ = 'Hari'
18th Exercise"""

NOTES = '''
nested functions underlie many advanced features of python. So a basic understanding of this
feature is essential to mastering python.

nested functions are defined in the scope of a function, behave exactly the same except
that they have a read only access to variables in the outer function.
'''


def outer_func(outer_var):
    """nested functions"""
    def inner_func(inner_var):
        return outer_var + inner_var

    return inner_func


def test_inner_func_scope():
    """nested functions"""
    # inner_func not accessible by default
    # try:
    #     inner_func()
    # except NameError:  # fill up the exception
    #     pass

    # this syntax does not work either, it is not just static scoping.
    try:
        outer_func.inner_func()
    except AttributeError:  # fillup the exception
        pass


def test_inner_func_can_be_returned():
    """nested functions"""
    f_1 = outer_func(10)
    assert 'function' == type(f_1).__name__
    assert 30 == f_1(20)


def test_each_invocation_returns_a_new_func():
    """nested functions"""
    f_1 = outer_func(10)
    f_2 = outer_func(10)

    assert False is (f_1 is f_2)
    assert False is (f_1 == f_2)

    f_3 = f_2
    assert True is (f_3 is f_2)
    assert True is (f_3 == f_2)


def test_inner_func_has_access_to_outer_variables_after_return():
    """nested functions"""
    f_1 = outer_func(20)
    f_2 = outer_func(50)

    assert 50 == f_1(30)
    assert 60 == f_1(40)

    assert 80 == f_2(30)
    assert 90 == f_2(40)


def print_attributes(obj):
    """nested functions"""
    for x in dir(obj):
        print("attribute: {0}".format(x))
        print(getattr(obj, x))


def test_inner_func_attributes():
    """nested functions"""
    f_1 = outer_func(10)
    assert 35 == len(dir(f_1))  # how many attributes does f_1 have

    # use the print_attributes function to explore the properties
    # fill up the attribute name that you think holds a reference to the
    # function scope variables :- maybe getattribute
    ref_to_outer_scope = "__global__"


# if you understand this, you have understood nested funcs :)
# Ans->Understood with difficulty but worth it<(*_*)>
def test_inner_func_scoping():
    """nested functions"""
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
    """nested functions"""
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
    """nested functions"""
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


# three_things_i_learnt = """
# working of nested function
# how we can assing function to a variable and how it behaves
# def keyword act as a executable statement
# """
#
# time_taken_minutes = 60
