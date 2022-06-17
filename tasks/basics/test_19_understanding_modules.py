"""This is the 19th file of python exercise by medplus"""
__author__ = 'Hari'

import sys
from tasks import placeholders
from tasks import module1

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
#this is a global import, generally you use only these. rarely will you use
# function level imports, but we are doing that
#here for the sake of testing.


from Tasks.placeholders import *

def test_module_without_import():
    """basic docstring for pylint purpose"""
    try:
        module1.greet("jack")
    except NameError as error_ie :
        print(error_ie)
    assert True

# def test_module_usage_needs_import():
#     """basic docstring for pylint purpose"""
#     from basics.module1 import greet
#     assert "module1 says hi to jack" == greet("jack")

# def test_module_usage_multiple():
#     """basic docstring for pylint purpose"""
#     import basics.module1
#     import basics.module2

#     assert "module1 says hi to jack" == basics.module1.greet("jack")
#     assert "module2 says hi to jack" == basics.module2.greet("jack")

# def test_module_import_affects_current_namespace():
#     """basic docstring for pylint purpose"""
#     from basics import module1

#     def inner_func():
#         """basic docstring for pylint purpose"""
#         from basics import module2
#         assert True is ('module2' in locals())
#         return module2.greet("jack")

#     assert "module1 says hi to jack" == module1.greet("jack")
#     assert "module2 says hi to jack" == inner_func()

#     assert False is ('placeholders' in locals())
#     assert True is ('placeholders' in globals())

#     assert True is ('module1' in locals())
#     assert True is ('module1' in globals())

#     assert False is ('module2' in locals())
#     assert False is ('module2' in globals())

# def test_module_type():
#     """basic docstring for pylint purpose"""
#     assert 'module' == type(placeholders).__name__

# def test_module_is_an_object():
#     """basic docstring for pylint purpose"""
#     assert 12 == len(dir(placeholders))
#     assert 'Tasks.placeholders' == placeholders.__name__
#     assert None is placeholders.__doc__

# def test_module_from_import():
#     """basic docstring for pylint purpose"""
#     from basics.module1 import greet

#     assert False is ('module1' in locals())
#     assert True is ('greet' in locals())

#     try:
#         module1.greet()
#     except TypeError :
#         pass

#     assert "module1 says hi to jack" == greet("jack")

# def test_module_why_from_import_is_a_bad_idea():
#     """basic docstring for pylint purpose"""
#     from basics import module1
#     from basics import module2

#     assert "module2 says hi to jack" == module2.greet("jack")

# def test_modules_are_cached():
#     """basic docstring for pylint purpose"""
#     from basics import module1
#     import basics.module1 as new_name
#     def inner():
#         import basics.module1 as bm
#         return bm.some_attr

#     try:
#         inner()
#     except AttributeError: # what exception do you get here?
#         pass

#     module1.some_attr = 10
#     assert 10 == inner()

#     def inner2():
#         """basic docstring for pylint purpose"""
#         import basics.module1 as module1
#         return module1.some_attr

#     assert 10 == inner2()

#     assert "dict" == type(sys.modules).__name__
#     assert True is (module1 is sys.modules['basics.module1'])
#     assert False is ('new_name' in sys.modules)
#     assert True is (new_name is module1)
#     assert True is (new_name is sys.modules['basics.module1'])

#     # doubts

# s_1 = set()
# s_2 = set()
# s_3 = set()

# s_1 = set(dir())
# import basics.module3
# s_2 = set(dir())
# import basics.module4
# s_3 = set(dir())

# def test_module_star_import():
#     """basic docstring for pylint purpose"""
#     # * imports are not allowed within functions,
#     # so we had to do it at global scope
#     assert {'Tasks'} == (s_2 - s_1)  # what did module3 import bring in.
#     assert set() == (s_3 - s_2)  # what did module4 import bring in.

NOTES_2 = '''
http://effbot.org/zone/import-confusion.htm
'''

THREE_THINGS_I_LEARNT = """
modules,usability,functionality
"""

TIME_TAKEN_MINUTES = 35
