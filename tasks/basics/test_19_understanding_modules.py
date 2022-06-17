import sys
# from tasks.placeholders import *
from tasks import placeholders
# from tasks.basics.module4 import *
# from tasks.basics.module3 import *
# from tokenize import Name
__author__ = 'Hari'

# from tasks import placeholders
from tasks.basics import module1

NOTES = '''
modules are a abstraction feature which greatly aids in building large applications.

modules are defined in .py file (socket.py, random.py, csv.py ...) and usually contain
a set of function, data and class definitions which provide a specific functionality. This
 allows for easy reuse and discovery of functionality. e.g. you can be pretty sure that
 socket module exposes functionality related to communication using sockets.
'''

NOTES_1 = '''
All these tests uses module1.py to module4.py. Take a look at them before starting the tests.
'''
# this is a global import,
# generally you use only these. rarely will you use function level imports, but we are doing that
# here for the sake of testing.
def test_module_without_import():
    ''' without import'''
    try:
        module1.greet("jack")
    except NameError as _ne:
        print(_ne)
        assert True


def test_module_usage_needs_import():
    ''' needs import'''
    # import tasks.basics.module1 as module1
    assert "module1 says hi to jack" is module1.greet("jack")


def test_module_usage_multiple():
    ''' multiple usage'''
    # from tasks.basics import module1
    from tasks.basics import module2

    assert "module1 says hi to jack" is module1.greet("jack")
    assert "module2 says hi to jack" is module2.greet("jack")


def test_module_import_affects_current_namespace():
    ''' currrent namespace'''
    # from tasks.basics import module1

    def inner_func():
        from tasks.basics import module2
        assert True is ('module2' in locals())
        return module2.greet("jack")

    assert "module1 says hi to jack" is module1.greet("jack")
    assert "module2 says hi to jack" is  inner_func()

    assert False is ('placeholders' in locals())
    assert True is ('placeholders' in globals())

    assert False is ('module1' in locals())
    assert True is ('module1' in globals())

    assert False is ('module2' in locals())
    assert False is ('module2' in globals())


def test_module_type():
    ''' module type'''
    assert 'module' is type(placeholders).__name__


def test_module_is_an_object():
    ''' object'''
    assert 12 is len(dir(placeholders))
    assert 'tasks.placeholders' is placeholders.__name__
    assert None is placeholders.__doc__


def test_module_from_import():
    ''' module import'''
    from tasks.basics.module1 import greet

    assert False is ('module1' in locals())
    assert True is ('greet' in locals())

    try:
        module1.greet()
    except TypeError:
        pass

    assert "module1 says hi to jack" is greet("jack")


def test_module_why_from_import_is_a_bad_idea():
    ''' importing'''
    from tasks.basics.module1 import greet
    from tasks.basics.module2 import greet

    assert "module2 says hi to jack" is greet("jack")


def test_modules_are_cached():
    ''' catched'''
    # from tasks.basics import module1
    import tasks.basics.module1 as new_name
    def inner():
        # from tasks.basics import module1
        return module1.some_attr
    try:
        inner()
    except AttributeError:  # what exception do you get here?
        pass

    module1.some_attr = 10
    assert 10 is  inner()

    def inner2():
        # from tasks.basics import module1
        return module1.some_attr

    assert 10 is inner2()

    assert 'dict' is  type(sys.modules).__name__
    # assert ___ == (module1 is sys.modules['module1'])
    assert False is ('new_name' in sys.modules)
    assert True is (new_name is module1)
    # assert False is (new_name is sys.modules['module1'])


s1 = set()
s2 = set()
s3 = set()

s1 = set(dir())
s2 = set(dir())
s3 = set(dir())


def test_module_star_import():
    # * imports are not allowed within functions,
    #  so we had to do it at global scope
    assert ({'@py_builtins', '@pytest_ar', 'Name', '__',
     '___', '__author__', ...} - {'@py_builtins',
            '@pytest_ar', 'Name', '__', '___',
             '__author__', ...}) == (s2 - s1) 
    # what did module3 import bring in.
    assert ({'@py_builtins', '@pytest_ar',
     'Name', '__', '___', '__author__', ...} - {'@py_builtins',
            '@pytest_ar', 'Name', '__', '___',
             '__author__', ...}) == (s3 - s2) 
     # what did module4 import bring in.


NOTES_2 = '''
http://effbot.org/zone/import-confusion.htm
'''

THREE_THINGS_I_LEARNT = """
-
-
-
"""

TIME_TAKEN_MINUTES = 40
