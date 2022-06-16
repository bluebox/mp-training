"""This is the 19th file of python exercise by medplus"""
__author__ = 'Hari'

from Tasks import placeholders
from Tasks.basics import module1
from re import T
import sys

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
#this is a global import, generally you use only these. rarely will you use function level imports, but we are doing that
#here for the sake of testing.


# from Tasks.placeholders import *

def test_module_without_import():
    """basic docstring for pylint purpose"""
    try:
        module1.greet("jack")
    except NameError as IE :
        print(IE)
        assert True

def test_module_usage_needs_import():
    """basic docstring for pylint purpose"""
    from Tasks.basics import module1
    assert "module1 says hi to jack" == module1.greet("jack")

def test_module_usage_multiple():
    """basic docstring for pylint purpose"""
    from Tasks.basics import module1
    from Tasks.basics import module2

    assert "module1 says hi to jack" == module1.greet("jack")
    assert "module2 says hi to jack" == module2.greet("jack")

def test_module_import_affects_current_namespace():
    """basic docstring for pylint purpose"""
    from Tasks.basics import module1

    def inner_func():
        """basic docstring for pylint purpose"""
        from Tasks.basics import module2
        assert True == ('module2' in locals())
        return module2.greet("jack")

    assert "module1 says hi to jack" == module1.greet("jack")
    assert "module2 says hi to jack" == inner_func()

    assert False == ('placeholders' in locals())
    assert True == ('placeholders' in globals())

    assert True == ('module1' in locals())
    assert True == ('module1' in globals())

    assert False == ('module2' in locals())
    assert False == ('module2' in globals())

def test_module_type():
    """basic docstring for pylint purpose"""
    assert 'module' == type(placeholders).__name__

def test_module_is_an_object():
    """basic docstring for pylint purpose"""
    assert 12 == len(dir(placeholders))
    assert 'Tasks.placeholders' == placeholders.__name__
    assert None == placeholders.__doc__

def test_module_from_import():
    """basic docstring for pylint purpose"""
    from Tasks.basics.module1 import greet

    assert False == ('module1' in locals())
    assert True == ('greet' in locals())

    try:
        module1.greet()
    except TypeError :
        pass

    assert "module1 says hi to jack" == greet("jack")

def test_module_why_from_import_is_a_bad_idea():
    """basic docstring for pylint purpose"""
    from Tasks.basics.module1 import greet
    from Tasks.basics.module2 import greet

    assert "module2 says hi to jack" == greet("jack")

def test_modules_are_cached():
    """basic docstring for pylint purpose"""
    from Tasks.basics import module1
    import Tasks.basics.module1 as new_name
    def inner():
        import Tasks.basics.module1 as bm
        return bm.some_attr

    try:
        inner()
    except AttributeError: # what exception do you get here?
        pass

    module1.some_attr = 10
    assert 10 == inner()

    def inner2():
        """basic docstring for pylint purpose"""
        import Tasks.basics.module1 as module1
        return module1.some_attr

    assert 10 == inner2()

    assert "dict" == type(sys.modules).__name__
    assert True == (module1 is sys.modules['Tasks.basics.module1'])
    assert False == ('new_name' in sys.modules)
    assert True == (new_name is module1)
    assert True == (new_name is sys.modules['Tasks.basics.module1'])

    # doubts

s1 = set()
s2 = set()
s3 = set()

s1 = set(dir())
from Tasks.basics.module3 import *
s2 = set(dir())
from Tasks.basics.module4 import *
s3 = set(dir())

def test_module_star_import():
    """basic docstring for pylint purpose"""
    # * imports are not allowed within functions, so we had to do it at global scope
    assert {"m3_func1","m3_func2"} == (s2 - s1)  # what did module3 import bring in.
    assert {"_m4_func3","m4_func1"} == (s3 - s2)  # what did module4 import bring in.

notes_2 = '''
http://effbot.org/zone/import-confusion.htm
'''

THREE_THINGS_I_LEARNT = """
modules,usability,functionality
"""

TIME_TAKEN_MINUTES = 35
