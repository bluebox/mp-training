'''modules'''
__author__ = 'Hari'

from tasks import placeholders
from tasks.basics import module1
# from tasks.placeholders import *

NOTES = '''
Modules are a abstraction feature which greatly aids
in building large applications.

Modules are defined in .py file (socket.py, random.py, csv.py ...)
and usually contain
A set of function, data and class definitions which provide a
specific functionality. This
Allows for easy reuse and discovery of functionality. e.g.
you can be pretty sure that
Socket module exposes functionality related to
communication using sockets.
'''

NOTES_1 = '''
All these tests uses module1.py to module4.py. Take a look
at them before starting the tests.
'''
#this is a global import, generally you use only these. rarely will
# you use function level imports, but we are doing that
#here for the sake of testing.

def test_module_without_import():
    '''modules'''
    try:
        module1.greet("jack")
    except NameError:
        print()
        assert AssertionError

def test_module_usage_needs_import():
    '''modules'''
    assert module1.greet("jack") == "module1 says hi to jack"

# def test_module_usage_multiple():
#     '''modules'''
#     from tasks.basics import module1
#     from tasks.basics import module2

#     assert module1.greet("jack") == "module1 says hi to jack"
#     assert module2.greet("jack") == "module2 says hi to jack"

# def test_module_import_affects_current_namespace():
#     from tasks.basics import module1

#     def inner_func():
#         import module2
#         assert True == ('module2' in locals())
#         return module2.greet("jack")

#     assert "module1 says hi to jack" == module1.greet("jack")
#     assert "module2 says hi to jack" == inner_func()

#     assert False == ('placeholders' in locals())
#     assert True == ('placeholders' in globals())

#     assert True == ('module1' in locals())
#     assert True == ('module1' in globals())

#     assert False == ('module2' in locals())
#     assert False == ('module2' in globals())

def test_module_type():
    '''modules'''
    assert type(placeholders).__name__ == 'module'

def test_module_is_an_object():
    '''modules'''
    assert len(dir(placeholders)) == 12
    assert placeholders.__name__ == 'tasks.placeholders'
    assert placeholders.__doc__ is None

def test_module_from_import():
    '''modules'''
    from tasks.basics.module1 import greet

    assert ('module1' in locals()) is False
    assert ('greet' in locals()) is True

    try:
        module1.greet()
    except TypeError:
        pass

    assert greet("jack") == 'module1 says hi to jack'

def test_module_why_from_import_is_a_bad_idea():
    '''modules'''
    # from tasks.basics.module1 import greet
    from tasks.basics.module2 import greet

    assert greet("jack") == "module2 says hi to jack"

# def test_modules_are_cached():
#     import tasks.basics.module1
#     import module1 as new_name
#     def inner():
#         import tasks.basics.module1
#         return module1.some_attr

#     try:
#         inner()
#     except AttributeError: # what exception do you get here?
#         pass

#     module1.some_attr = 10
#     assert 10 == inner()

#     def inner2():
#         from tasks.basics import module1
#         return module1.some_attr

#     assert 10 == inner2()

#     assert 'dict' == type(sys.modules).__name__
#     assert True == (module1 is sys.modules['tasks.basics.module1'])
#     assert False == ('new_name' in sys.modules)
#     assert True == (new_name is module1)
#     assert True == (new_name is sys.modules['tasks.basics.module1'])

S_1 = set()
S_2 = set()
S_3 = set()

S_1 = set(dir())
# from tasks.basics.module3 import *
S_2 = set(dir())
# from tasks.basics.module4 import *
S_3 = set(dir())

def test_module_star_import():
    '''modules'''
    # * imports are not allowed within functions, so we had to do it at global scope
    assert (S_2 - S_1) == set(['m3_func1', 'm3_func2']) # what did module3 import bring in.
    assert (S_3 - S_2) == set(['_m4_func3', 'm4_func1']) # what did module4 import bring in.

NOTES_2 = '''
http://effbot.org/zone/import-confusion.htm
'''

THREE_THINGS_I_LEARNT = """
- modules are a abstraction feature which greatly
aids in building large applications.

- modules usually contains a set of function,
data and class definitions which provide a specific functionality.
- modules  allows for easy reuse and discovery of functionality
"""

TIME_TAKEN_MINUTES = 15
