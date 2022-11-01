"""Understanding modules"""
__author__ = 'Hari'

from tasks import placeholders
from tasks.basics import module1

NOTES= '''
modules are an abstraction feature which greatly aids in building large applications.
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

import sys

from tasks.placeholders import *

def test_module_without_import():
    """function"""
    try:
        module1.greet("jack")
    except NameError as ne :
        print (ne)
        assert True

def test_module_usage_needs_import():
    """import to done to use the modules"""
    import module1
    assert 'module1 says hi to jack' == module1.greet("jack")

def test_module_usage_multiple():
    """just importing"""
    import module1
    import module2

    assert 'module1 says hi to jack' is module1.greet("jack")
    assert 'module2 says hi to jack' is module2.greet("jack")

def test_module_import_affects_current_namespace():
    """namespace retriever"""
    import module1

    def inner_func():
        """inner functions"""
        import module2
        assert True is ('module2' in locals())
        return module2.greet("jack")

    assert 'module1 says hi to jack' is module1.greet("jack")
    assert 'module2 says hi to jack' is inner_func()

    assert False is ('placeholders' in locals())
    assert True is ('placeholders' in globals())

    assert True is ('module1' in locals())
    assert True is ('module1' in globals())

    assert False is ('module2' in locals())
    assert False is ('module2' in globals())

def test_module_type():
    """gives the type of module is"""
    assert 'module' is type(placeholders).__name__

def test_module_is_an_object():
    """module is object"""
    assert 12 == len(dir(placeholders))
    assert 'module' is placeholders.__name__
    assert None is placeholders.__doc__

def test_module_from_import():
    """from import statement"""
    from tasks.basics.module1 import greet

    assert False is ('module1' in locals())
    assert True is ('greet' in locals())

    try:
        module1.greet()
    except TypeError :
        pass

    assert 'module1 says hi to jack' is greet("jack")

def test_module_why_from_import_is_a_bad_idea():
    """from import is a messful thing to do"""
    
    from module1 import greet
    from module2 import greet

    assert 'module2 says hi to jack' == greet("jack")

def test_modules_are_cached():
    """modules cached"""
    from tasks.basics import module1
    from tasks.basics import module1 as new_name
    def inner():
        import module1
        return module1.some_attr

    try:
        inner()
    except AttributeError : # what exception do you get here?
        pass

    module1.some_attr = 10
    assert 10 == inner()

    def inner2():
        import module1
        return module1.some_attr

    assert 10 == inner2()

    assert 'dict' is type(sys.modules).__name__
    assert True is (module1 is sys.modules['module1'])
    assert False is ('new_name' in sys.modules)
    assert True is (new_name is module1)
    assert True is (new_name is sys.modules['module1'])

s1 = set()
s2 = set()
s3 = set()

s1 = set(dir())
from tasks.basics.module3 import *
s2 = set(dir())
from tasks.basics.module4 import *
s3 = set(dir())

def test_module_star_import():
    """ checking for scope """
    # * imports are not allowed within functions, so we had to do it at global scope
    assert {'m3_func2', 'm3_func1'} == (s2 - s1)  # what did module3 import bring in.
    assert {'m4_func1', '_m4_func3'} == (s3 - s2)  # what did module4 import bring in.

NOTES_2 = '''
http://effbot.org/zone/import-confusion.htm
'''

THREE_THINGS_I_LEARNT = """
-modules
-scopes
-from import is a big confusion
"""

TIME_TAKEN = 60
