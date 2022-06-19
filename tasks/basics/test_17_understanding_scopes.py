'''scopes'''
__author__ = 'Hari'

import inspect
import symtable
#from tasks.basics.test_00_understanding_assert import THREE_THINGS_I_LEARNT, TIME_TAKEN_MINUTES

#from tasks.placeholders import *

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

count = 10

#used to by pass any local shadow variables.
def get_global_count():
    '''scopes'''
    return count

def test_scope_basic():
    '''scopes'''
    local_names = get_locals(test_scope_basic)

    value = count

    assert True is ('value' in local_names)
    assert False is ('value' in global_names)

    assert False is ('count' in local_names)
    assert True is ('count' in global_names)

    assert 10 == value


def test_scope_undefined_variable():
    '''scopes'''
    local_names = get_locals(test_scope_undefined_variable)

    try:
        my_name = name   #name variable is not in local or  global scope
    except NameError: # fill up the exception
        pass

    assert True is ('my_name' in local_names)
    assert False is ('name' in local_names)
    assert False is ('name' in global_names)

def test_variable_shadow():
    '''scopes'''
    local_names = get_locals(test_variable_shadow)
    count = 20

    assert True is ('count' in local_names)
    assert True is ('count' in global_names)

    assert 20== count
    assert 10 == get_global_count()

def test_global_write():
    '''scopes'''
    local_names = get_locals(test_global_write)

    global count # declare that we want to use the read/write to global count
    count = 30

    try:
        assert False is ('count' in local_names)
        assert True is ('count' in global_names)

        assert 30 == count
        assert 30 == get_global_count()
    finally:
        count = 10 #reset to original value

def test_scope_is_bound_at_definition_time():
    '''scopes'''
    local_names = get_locals(test_scope_is_bound_at_definition_time)

    assert ('count' in ('local_names', 'value', 'count', 'ne')) == ('count' in local_names)
    assert True is ('count' in global_names)

    try:
        value = count
        count = 30
    except NameError : # what happens when you read a variable before initializing it?
        #print ex #uncomment after you fill up above
        assert True
    finally:
        count = 20

    assert 20 == count
    assert 10 == get_global_count()


def test_scope_writing_globals():
    '''scopes'''
    local_names = get_locals(test_scope_writing_globals)

    assert False is ('count' in local_names)
    assert True is ('count' in global_names)

    global count

    try:
        count = 40
        assert 40 == count
        assert 40 == get_global_count()
    finally:
        count = 10

    assert 10== get_global_count()



THREE_THINGS_I_LEARNT = """
-
-
-
"""

TIME_TAKEN_MINUTES = 30


#helper functions which get the variables in locals and globals using the compiler's symbol tables.
def get_locals(func):
    '''scopes'''
    source = inspect.getsource(func)
    top = symtable.symtable(source, "<string>", "exec")
    func = top.get_children()[0]  #since we are passing only the func code.
    return func.get_locals()

def get_globals():
    '''scopes'''
    module = inspect.getmodule(get_globals)
    source = inspect.getsource(module)
    top = symtable.symtable(source, "<string>", "exec")
    return top.get_identifiers()

global_names = get_globals()
