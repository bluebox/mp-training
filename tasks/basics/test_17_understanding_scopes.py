<<<<<<< HEAD

import symtable
import inspect

#from tasks.placeholders import *
=======
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
__author__ = 'Hari'

NOTES = '''
 Scopes and namespaces govern the accessibility rules and lifetime of python variables.

 Namespaces is a mapping of names to objects. Each python block creates a new namespace. The 3 python blocks are
 modules (files), functions and classes.

 An object can have many names in the same namespace
 An object can have names in different namespaces.

 Scope is a textual area in which a variable can be directly accessible by its name.

 Variable which are bound (created) in a block are called local variables in that block
 Variables which are scoped to the the whole file (module) are called global
 Variables which are scoped to outer functions (in case of nested functions) are called non-local or free.
'''

import inspect
import symtable

<<<<<<< HEAD
COUNT = 10

# used to by pass any local shadow variables.
=======
from tasks.placeholders import *
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

count = 10

#used to by pass any local shadow variables.
def get_global_count():
    return COUNT

def test_scope_basic():
    local_names = get_locals(test_scope_basic)

    value = COUNT

<<<<<<< HEAD
    assert True is ('value' in local_names)
    assert False is ('value' in global_names)

    assert False is ('COUNT' in local_names)
    assert True is ('COUNT' in global_names)

    assert 10 is value
=======
    assert __ == ('value' in local_names)
    assert __ == ('value' in global_names)

    assert __ == ('count' in local_names)
    assert __ == ('count' in global_names)

    assert __ == value
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


def test_scope_undefined_variable():
    local_names = get_locals(test_scope_undefined_variable)

    try:
        my_name = name  #name variable is not in local or  global scope
    except __ : # fill up the exception
        pass

<<<<<<< HEAD
    assert True is ('my_name' in local_names)
    assert False is ('name' in local_names)
    assert False is ('name' in global_names)
=======
    assert __ == ('my_name' in local_names)
    assert __ == ('name' in local_names)
    assert __ == ('name' in global_names)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_variable_shadow():
    local_names = get_locals(test_variable_shadow)
    COUNT = 20

<<<<<<< HEAD
    assert True is ('COUNT' in local_names)
    assert True is ('COUNT' in global_names)

    assert 20 is COUNT
    assert 10 is get_global_count()
=======
    assert __ == ('count' in local_names)
    assert __ == ('count' in global_names)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

    assert __ == count
    assert __ == get_global_count()

def test_global_write():
    local_names = get_locals(test_global_write)

<<<<<<< HEAD
    global COUNT  # declare that we want to use the read/write to global count
    COUNT = 30

    try:
        assert False is ('COUNT' in local_names)
        assert True is ('COUNT' in global_names)

        assert 30 is COUNT
        assert 30 is get_global_count()
    finally:
        COUNT = 10  # reset to original value

=======
    global count # declare that we want to use the read/write to global count
    count = 30

    try:
        assert __ == ('count' in local_names)
        assert __ == ('count' in global_names)

        assert __ == count
        assert __ == get_global_count()
    finally:
        count = 10 #reset to original value
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_scope_is_bound_at_definition_time():
    local_names = get_locals(test_scope_is_bound_at_definition_time)

<<<<<<< HEAD
    assert True is ('COUNT' in local_names)
    assert True is ('COUNT' in global_names)

    try:
        value = COUNT
        COUNT = 30
    except NameError as _ex:  # what happens when you read a variable before initializing it?
        print (_ex) #uncomment after you fill up above
        assert True
=======
    assert __ == ('count' in local_names)
    assert __ == ('count' in global_names)

    try:
        value = count
        count = 30
    except __: # what happens when you read a variable before initializing it?
        #print ex #uncomment after you fill up above
        assert __
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
    finally:
        COUNT = 20

<<<<<<< HEAD
    assert 20 is COUNT
    assert 10 is get_global_count()
=======
    assert __ == count
    assert __ == get_global_count()
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


def test_scope_writing_globals():
    local_names = get_locals(test_scope_writing_globals)

<<<<<<< HEAD
    assert False is ('COUNT' in local_names)
    assert True is ('COUNT' in global_names)
=======
    assert __ == ('count' in local_names)
    assert __ == ('count' in global_names)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

    global COUNT

    try:
<<<<<<< HEAD
        COUNT = 40
        assert 40 is COUNT
        assert 40 is get_global_count()
=======
        count = 40
        assert __ == count
        assert __ == get_global_count()
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
    finally:
        COUNT = 10

<<<<<<< HEAD
    assert 10 is get_global_count()
=======
    assert __ == get_global_count()

>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


THREE_THINGS_I_LERNT = """
glooby difining
local difining
use of global key word
"""

<<<<<<< HEAD
TIME_TAKEN_MINUTES = 120
=======
time_taken_minutes = ___
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


#helper functions which get the variables in locals and globals using the compiler's symbol tables.
def get_locals(func):
    source = inspect.getsource(func)
    top = symtable.symtable(source, "<string>", "exec")
    func = top.get_children()[0]  #since we are passing only the func code.
    return func.get_locals()

def get_globals():
    module = inspect.getmodule(get_globals)
    source = inspect.getsource(module)
    top = symtable.symtable(source, "<string>", "exec")
    return top.get_identifiers()

global_names = get_globals()

