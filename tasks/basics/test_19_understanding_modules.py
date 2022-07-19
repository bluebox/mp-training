<<<<<<< HEAD

# from tasks.placeholders import *
# import tasks.placeholders as placeholders
# from tasks.basics.module4 import *
# from tasks.basics.module3 import *
# from tokenize import Name
#import sys
__author__ = 'Hari'

# from tasks import placeholders
# from tasks.basics import module1
=======
__author__ = 'Hari'

from tasks import placeholders
from tasks.basics import module1
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

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
<<<<<<< HEAD
# this is a global import, generally you use only these.
# rarely will you use function level imports, but we are doing that
# here for the sake of testing.

#import sys

#from tasks.placeholders import *
=======
#this is a global import, generally you use only these. rarely will you use function level imports, but we are doing that
#here for the sake of testing.

import sys

from tasks.placeholders import *
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_module_without_import():
    try:
        module1.greet("jack")
<<<<<<< HEAD
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
=======
    except ___ :
        print
        assert ___

def test_module_usage_needs_import():
    import module1
    assert __ == module1.greet("jack")

def test_module_usage_multiple():
    import module1
    import module2
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

    assert __ == module1.greet("jack")
    assert __ == module2.greet("jack")

def test_module_import_affects_current_namespace():
<<<<<<< HEAD
    from tasks.basics import module1

    def inner_func():
        from tasks.basics import module2
        assert True is ('module2' in locals())
=======
    import module1

    def inner_func():
        import module2
        assert __ == ('module2' in locals())
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
        return module2.greet("jack")

    assert __ == module1.greet("jack")
    assert __ == inner_func()

<<<<<<< HEAD
    assert False is ('placeholders' in locals())
    assert False is ('placeholders' in globals())

    assert True is ('module1' in locals())
    assert False is ('module1' in globals())

    assert False is ('module2' in locals())
    assert False is ('module2' in globals())
=======
    assert __ == ('placeholders' in locals())
    assert __ == ('placeholders' in globals())

    assert __ == ('module1' in locals())
    assert __ == ('module1' in globals())
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

    assert __ == ('module2' in locals())
    assert __ == ('module2' in globals())

def test_module_type():
<<<<<<< HEAD
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
=======
    assert __ == type(placeholders).__name__

def test_module_is_an_object():
    assert __ == len(dir(placeholders))
    assert __ == placeholders.__name__
    assert __ == placeholders.__doc__

def test_module_from_import():
    from module1 import greet

    assert __ == ('module1' in locals())
    assert __ == ('greet' in locals())

    try:
        module1.greet()
    except __ :
        pass

    assert __ == greet("jack")

def test_module_why_from_import_is_a_bad_idea():
    from module1 import greet
    from module2 import greet

    assert __ == greet("jack")

def test_modules_are_cached():
    import module1
    import module1 as new_name
    def inner():
        import module1
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
        return module1.some_attr

    try:
        inner()
<<<<<<< HEAD
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
=======
    except __: # what exception do you get here?
        pass

    module1.some_attr = 10
    assert __ == inner()

    def inner2():
        import module1
        return module1.some_attr

    assert __ == inner2()

    assert __ == type(sys.modules).__name__
    assert __ == (module1 is sys.modules['module1'])
    assert __ == ('new_name' in sys.modules)
    assert __ == (new_name is module1)
    assert __ == (new_name is sys.modules['module1'])
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

set_s1 = set()
set_s2 = set()
set_s3 = set()

<<<<<<< HEAD
set_s1 = set(dir())
set_s2 = set(dir())
set_s3 = set(dir())
=======
s1 = set(dir())
from tasks.basics.module3 import *
s2 = set(dir())
from tasks.basics.module4 import *
s3 = set(dir())
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_module_star_import():
    # * imports are not allowed within functions, so we had to do it at global scope
<<<<<<< HEAD
    assert set() == (set_s2 - set_s1)  # what did module3 import bring in.
    assert set() == (set_s3 - set_s2)  # what did module4 import bring in.

NOTES_2 = '''
=======
    assert __ == (s2 - s1)  # what did module3 import bring in.
    assert __ == (s3 - s2)  # what did module4 import bring in.

notes_2 = '''
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
http://effbot.org/zone/import-confusion.htm
'''

THREE_THINGS_I_LEARNT = """
IMPORTING MODULES
-
-
"""

<<<<<<< HEAD
TIME_TAKEN_MINUTES = 120
=======
time_taken_minutes = ___
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
