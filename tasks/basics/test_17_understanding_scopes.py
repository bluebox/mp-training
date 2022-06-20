'''scopes'''
__author__ = 'Hari'

import inspect
import symtable

NOTES_1 = '''
Scopes and namespaces govern the accessibility rules
and lifetime of python variables.

Namespaces is a mapping of names to objects. Each
python block creates a new namespace. The 3 python blocks are
modules (files), functions and classes.

An object can have many names in the same namespace
An object can have names in different namespaces.

Scope is a textual area in which a variable can be directly
accessible by its name.

Variable which are bound (created) in a block are
called local variables in that block
Variables which are scoped to the the whole
file (module) are called global
Variables which are scoped to outer functions
(in case of nested functions) are called non-local or free.
'''

COUNT = 10

#used to by pass any local shadow variables.
def get_global_count():
    '''scopes'''
    return COUNT

def test_scope_basic():
    '''scopes'''
    local_names = get_locals(test_scope_basic)

    value = COUNT

    assert ('value' in local_names) is True
    assert ('value' in GLOBAL_NAMES) is False

    assert ('count' in local_names) is False
    assert ('count' in GLOBAL_NAMES) is False

    assert value == 10


def test_scope_undefined_variable():
    '''scopes'''
    local_names = get_locals(test_scope_undefined_variable)

    try:
        my_name = name  #name variable is not in local or  global scope
    except NameError: # fill up the exception
        pass

    assert ('my_name' in local_names) is True
    assert ('name' in local_names) is False
    assert ('name' in GLOBAL_NAMES) is False

def test_variable_shadow():
    '''scopes'''
    local_names = get_locals(test_variable_shadow)
    COUNT = 20

    assert ('COUNT' in local_names) is True
    assert ('COUNT' in GLOBAL_NAMES) is True

    assert COUNT == 20
    assert get_global_count() == 10

def test_global_write():
    '''scopes'''
    local_names = get_locals(test_global_write)

    global COUNT # declare that we want to use the read/write to global count
    COUNT = 30

    try:
        assert ('COUNT' in local_names) is False
        assert ('COUNT' in GLOBAL_NAMES) is True

        assert COUNT == 30
        assert get_global_count() == 30
    finally:
        COUNT = 10 #reset to original value

def test_scope_is_bound_at_definition_time():
    '''scopes'''
    local_names = get_locals(test_scope_is_bound_at_definition_time)

    assert ('COUNT' in local_names) is True
    assert ('COUNT' in GLOBAL_NAMES) is True

    try:
        # value = COUNT
        COUNT = 30
    except UnboundLocalError: # what happens when you read a variable before initializing it?
        #print ex #uncomment after you fill up above
        assert __
    finally:
        COUNT = 20

    assert COUNT == 20
    assert get_global_count() == 10


def test_scope_writing_globals():
    '''scopes'''
    local_names = get_locals(test_scope_writing_globals)

    assert ('COUNT' in local_names) is False
    assert ('COUNT' in GLOBAL_NAMES) is True

    global COUNT

    try:
        COUNT = 40
        assert COUNT == 40
        assert get_global_count() == 40
    finally:
        COUNT = 10

    assert get_global_count() == 10

THREE_THINGS_I_LEARNT = """
- Scopes and namespaces govern the accessibility rules
and lifetime of python variables.
- Namespaces is a mapping of names to objects. Each python
block creates a new namespace. The 3 python blocks are
 modules (files), functions and classes.
- An object can have many names in the same namespace
 An object can have names in different namespaces.
"""

TIME_TAKEN_MINUTES = 15


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

GLOBAL_NAMES = get_globals()
