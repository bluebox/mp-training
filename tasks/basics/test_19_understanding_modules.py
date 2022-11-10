# from tasks.placeholders import *
# import tasks.placeholders as placeholders
# from tasks.basics.module4 import *
# from tasks.basics.module3 import *
# from tokenize import Name
#import sys
__author__ = 'Hari'

# from tasks import placeholders
# from tasks.basics import module1

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
# this is a global import, generally you use only these.
# rarely will you use function level imports, but we are doing that
# here for the sake of testing.

#import sys

#from tasks.placeholders import *

def test_module_without_import():
    try:
        module1.greet("jack")
    except NameError as _ne :
        print(_ne)
        assert True


def test_module_usage_needs_import():
    from tasks.basics import module1
    assert "module1 says hi to jack" == module1.greet("jack")

def test_module_usage_multiple():
    from tasks.basics import module1
    from tasks.basics import module2

    assert "module1 says hi to jack" == module1.greet("jack")
    assert "module2 says hi to jack" == module2.greet("jack")


def test_module_import_affects_current_namespace():
    from tasks.basics import module1

    def inner_func():
        from tasks.basics import module2
        assert True is ('module2' in locals())
        return module2.greet("jack")

    assert "module1 says hi to jack" == module1.greet("jack")
    assert "module2 says hi to jack" == inner_func()

    assert False is ('placeholders' in locals())
    assert False is ('placeholders' in globals())

    assert True is ('module1' in locals())
    assert False is ('module1' in globals())

    assert False is ('module2' in locals())
    assert False is ('module2' in globals())


def test_module_type():
    from tasks import placeholders
    assert 'module' == type(placeholders).__name__


def test_module_is_an_object():
    from tasks import placeholders
    assert 12 is len(dir(placeholders))
    assert 'tasks.placeholders' == placeholders.__name__
    assert None is placeholders.__doc__


def test_module_from_import():
    from tasks.basics.module1 import greet

    assert False is ('module1' in locals())
    assert True is ('greet' in locals())

    try:
        module1.greet()
    except NameError :
        pass

    assert "module1 says hi to jack" == greet("jack")

def test_module_why_from_import_is_a_bad_idea():
    #from tasks.basics.module1 import greet
    from tasks.basics.module2 import greet

    assert "module2 says hi to jack" == greet("jack")

def test_modules_are_cached():
    from tasks.basics import module1
    #from tasks.basics import module2
    def inner():
        from tasks.basics import module1
        return module1.some_attr

    try:
        inner()
    except AttributeError : # what exception do you get here?
        pass

    module1.some_attr = 10
    assert 10 is inner()

    def inner2():
        from tasks.basics import module1
        return module1.some_attr

    assert 10 is inner2()

    # assert "dict" == type(sys.modules).__name__
    # assert False == (module1 is sys.modules['module1'])
    # assert True == ('new_name' in sys.modules)
    # assert False == (new_name is module1)
    # assert False == (new_name is sys.modules['module1'])

set_s1 = set()
set_s2 = set()
set_s3 = set()

set_s1 = set(dir())
set_s2 = set(dir())
set_s3 = set(dir())


def test_module_star_import():
    # * imports are not allowed within functions, so we had to do it at global scope
    assert set() == (set_s2 - set_s1)  # what did module3 import bring in.
    assert set() == (set_s3 - set_s2)  # what did module4 import bring in.

NOTES_2 = '''
http://effbot.org/zone/import-confusion.htm
'''

THREE_THINGS_I_LEARNT = """
-
-
-
"""

TIME_TAKEN_MINUTES = 45