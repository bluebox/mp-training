__author__ = 'Hari'

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

import sys

import placeholders
from placeholders import *

def test_module_without_import():
    try:
        module1.greet("jack")
    except ___ :
        print
        assert ___

def test_module_usage_needs_import():
    import module1
    assert __ == module1.greet("jack")

def test_module_usage_multiple():
    import module1
    import module2

    assert __ == module1.greet("jack")
    assert __ == module2.greet("jack")

def test_module_import_affects_current_namespace():
    import module1

    def inner_func():
        import module2
        assert __ == ('module2' in locals())
        return module2.greet("jack")

    assert __ == module1.greet("jack")
    assert __ == inner_func()

    assert __ == ('placeholders' in locals())
    assert __ == ('placeholders' in globals())

    assert __ == ('module1' in locals())
    assert __ == ('module1' in globals())

    assert __ == ('module2' in locals())
    assert __ == ('module2' in globals())

def test_module_type():
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
        return module1.some_attr

    try:
        inner()
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

s1 = set()
s2 = set()
s3 = set()

s1 = set(dir())
from module3 import *
s2 = set(dir())
from module4 import *
s3 = set(dir())

def test_module_star_import():
    # * imports are not allowed within functions, so we had to do it at global scope
    assert __ == (s2 - s1)  # what did module3 import bring in.
    assert __ == (s3 - s2)  # what did module4 import bring in.

notes_2 = '''
http://effbot.org/zone/import-confusion.htm
'''

three_things_i_learnt = """
-
-
-
"""

time_taken_minutes = ___
