__author__ = 'Hari'

from tasks import placeholders
from tasks.basics import module1

notes = '''
modules are a abstraction feature which greatly aids in building large applications.

modules are defined in .py file (socket.py, random.py, csv.py ...) and usually contain
a set of function, data and class definitions which provide a specific functionality. This
 allows for easy reuse and discovery of functionality. e.g. you can be pretty sure that
 socket module exposes functionality related to communication using sockets.
'''

notes_1 = '''
All these tests uses module1.py to module4.py. Take a look at them before starting the tests.
'''
#this is a global import, generally you use only these. rarely will you use function level imports, but we are doing that
#here for the sake of testing.
""" understanding of modules in python """
import sys

from tasks.placeholders import *

def test_module_without_import():
    """ function definition """
    try:
        module1.greet("jack")
    except NameError as error_ie:
        print(error_ie)
    assert True


def test_module_usage_needs_import():
    """ function definition """
    from tasks.basics.module1 import greet
    assert "module1 says hi to jack" == greet("jack")


def test_module_usage_multiple():
    """ function definition """
    import tasks.basics.module1
    import tasks.basics.module2

    assert "module1 says hi to jack" == tasks.basics.module1.greet("jack")
    assert "module2 says hi to jack" == tasks.basics.module2.greet("jack")


def test_module_import_affects_current_namespace():
    """ function definition """
    from tasks.basics import module1

    def inner_func():
        """ function definition """
        from tasks.basics import module2
        assert True is ('module2' in locals())
        return module2.greet("jack")

    assert "module1 says hi to jack" == module1.greet("jack")
    assert "module2 says hi to jack" == inner_func()

    assert False is ('placeholders' in locals())
    assert True is ('placeholders' in globals())

    assert True is ('module1' in locals())
    assert True is ('module1' in globals())

    assert False is ('module2' in locals())
    assert False is ('module2' in globals())


def test_module_type():
    """ function definition """
    assert 'module' == type(placeholders).__name__


def test_module_is_an_object():
    """ function definition """
    assert 12 == len(dir(placeholders))
    assert 'tasks.placeholders' == placeholders.__name__
    assert None is placeholders.__doc__


def test_module_from_import():
    """ function definition """
    from tasks.basics.module1 import greet

    assert False is ('module1' in locals())
    assert True is ('greet' in locals())

    try:
        module1.greet()
    except TypeError:
        pass

    assert "module1 says hi to jack" == greet("jack")


def test_module_why_from_import_is_a_bad_idea():
    """ function definition """
    from tasks.basics import module1
    from tasks.basics import module2

    assert "module2 says hi to jack" == module2.greet("jack")


def test_modules_are_cached():
    """ function definition """
    from tasks.basics import module1
    import tasks.basics.module1 as new_name
    def inner():
        import tasks.basics.module1 as bm
        return bm.some_attr

    try:
        inner()
    except AttributeError:  # what exception do you get here?
        pass

    module1.some_attr = 10
    assert 10 == inner()

    def inner2():
        """ function definition """
        import tasks.basics.module1 as module1
        return module1.some_attr

    assert 10 == inner2()

    assert "dict" == type(sys.modules).__name__
    assert True is (module1 is sys.modules['tasks.basics.module1'])
    assert False is ('new_name' in sys.modules)
    assert True is (new_name is module1)
    assert True is (new_name is sys.modules['tasks.basics.module1'])


s_1 = set()
s_2 = set()
s_3 = set()

s_1 = set(dir())
import tasks.basics.module3

s_2 = set(dir())
import tasks.basics.module4

s_3 = set(dir())


def test_module_star_import():
    """ function definition """
    # * imports are not allowed within functions,
    # so we had to do it at global scope
    assert {'tasks'} == (s_2 - s_1)  # what did module3 import bring in.
    assert set() == (s_3 - s_2)  # what did module4 import bring in.
notes_2 = '''
http://effbot.org/zone/import-confusion.htm
'''

three_things_i_learnt = """
-concept of modules in python
-its implementation
-its benefits
"""

time_taken_minutes = 60
