""" Understanding What are modules and why they are necessary and uses of these modules in
the code redability and reusability """

__author__ = 'Hari'

import sys
from tasks.placeholders import *
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

#this is a global import, generally you use only these. rarely will you use function level imports, but we are doing that
#here for the sake of testing.


def test_module_without_import():
    """ Module with out import """
    try:
        module1.greet("jack")
    except NameError:
        print
        assert True


def test_module_usage_needs_import():
    """ Module which needs import """
    import module1
    assert "module1 says hi to " + "jack" == module1.greet("jack")


def test_module_usage_multiple():
    """ Multiple module usage """
    import module1
    import module2

    assert "module1 says hi to " + "jack" == module1.greet("jack")
    assert "module2 says hi to " + "jack" == module2.greet("jack")


def test_module_import_affects_current_namespace():
    """ import affects current namespace """
    import module1
    import module2

    def inner_func():
        """ Inner function """
        import module2
        assert True == ('module2' in locals())
        return module2.greet("jack")

    assert "module1 says hi to " + "jack" == module1.greet("jack")
    assert "module2 says hi to " + "jack" == inner_func()

    assert False == ('placeholders' in locals())
    assert True == ('placeholders' in globals())

    assert True == ('module1' in locals())
    assert False == ('module1' in globals())

    assert True == ('module2' in locals())
    assert False == ('module2' in globals())


def test_module_type():
    """ Module Type """
    assert 'module' == type(placeholders).__name__


def test_module_is_an_object():
    """ Module is an Object """
    assert 11 == len(dir(placeholders))
    assert 'placeholders' == placeholders.__name__
    assert None == placeholders.__doc__


def test_module_from_import():
    """ from import """
    from module1 import greet

    assert False == ('module1' in locals())
    assert True == ('greet' in locals())

    try:
        module1.greet()
    except NameError :
        pass

    assert "module1 says hi to " + "jack" == greet("jack")


def test_module_why_from_import_is_a_bad_idea():
    """ Using from import is a bad idea """
    from module1 import greet
    from module2 import greet

    assert "module2 says hi to " + "jack" == greet("jack")


def test_modules_are_cached():
    """ modules are always cached """
    import module1
    import module1 as new_name
    def inner():
        import module1
        return module1.some_attr

    try:
        inner()
    except AttributeError: # what exception do you get here?
        pass

    module1.some_attr = 10
    assert 10 == inner()

    def inner2():
        """ inner funcntion 2 """
        import module1
        return module1.some_attr

    assert 10 == inner2()

    assert 'dict' == type(sys.modules).__name__
    assert True == (module1 is sys.modules['module1'])
    assert False == ('new_name' in sys.modules)
    assert True == (new_name is module1)
    assert True == (new_name is sys.modules['module1'])


s_1 = set()
s_2 = set()
s_3 = set()

s_1 = set(dir())
import tasks.basics.module3
s_2 = set(dir())
 import tasks.basics.module4
s_3 = set(dir())


def test_module_star_import():
    """ Importing using * """
    # * imports are not allowed within functions, so we had to do it at global scope
    assert {'tasks'} == (s_2 - s_1)  # what did module3 import bring in.
    assert {set()} == (s_3 - s_2)  # what did module4 import bring in.


NOTES_2 = '''
http://effbot.org/zone/import-confusion.htm
'''


THREE_THINGS_I_LEARNT = """
- Usage of import and from keywords
- How Modules work
- How does importing working and how can we use for better coding optimiazation
"""


TIME_TAKEN_MINUTES = 30
