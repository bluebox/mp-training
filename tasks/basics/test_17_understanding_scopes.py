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

from tasks.placeholders import *

COUNT = 10

#used to by pass any local shadow variables.
def get_global_count():
    return COUNT 


def test_scope_basic():
    local_names = get_locals(test_scope_basic)

    value = COUNT 


    assert True is ('value' in local_names)
    assert False is ('value' in global_names)

    assert False is ('COUNT' in local_names)
    assert True is ('COUNT' in global_names)

    assert 10 == value


def test_scope_undefined_variable():
    local_names = get_locals(test_scope_undefined_variable)

    try:
        my_name = name  #name variable is not in local or  global scope
    except NameError : # fill up the exception
        pass

    assert True is ('my_name' in local_names)
    assert False is ('name' in local_names)
    assert False is ('name' in global_names)

def test_variable_shadow():
    local_names = get_locals(test_variable_shadow)
    COUNT = 20

    assert True is ('COUNT' in local_names)
    assert True is ('COUNT' in global_names)

    assert 20 == COUNT
    assert 10 == get_global_count()

def test_global_write():
    local_names = get_locals(test_global_write)

    global COUNT # declare that we want to use the read/write to global count
    COUNT = 30

    try:
        assert False is ('COUNT' in local_names)
        assert True is ('COUNT' in global_names)

        assert 30 == COUNT
        assert 30== get_global_count()
    finally:
        COUNT = 10 #reset to original value

def test_scope_is_bound_at_definition_time():
    local_names = get_locals(test_scope_is_bound_at_definition_time)

    assert True is ('COUNT' in local_names)
    assert True is ('COUNT' in global_names)

    try:
        value = COUNT
        COUNT = 30
    except UnboundLocalError: # what happens when you read a variable before initializing it?
        #print ex #uncomment after you fill up above
        assert True
    finally:
        COUNT = 20

    assert 20 == COUNT
    assert 10 == get_global_count()


def test_scope_writing_globals():
    local_names = get_locals(test_scope_writing_globals)

    assert False is ('COUNT' in local_names)
    assert True is ('COUNT' in global_names)

    global COUNT

    try:
        COUNT = 40
        assert 40 == COUNT
        assert 40 == get_global_count()
    finally:
        COUNT = 10

    assert 10 == get_global_count()



THREE_THINGS_I_LEARNT = """
-
-
-
"""

TIME_TAKEN_MINUTES = 40


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

