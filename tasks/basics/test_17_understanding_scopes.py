"""Understanding scopes"""
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

count_global = 10

#used to by pass any local shadow variables.
def get_global_count():
    """returns count value stored in global space"""
    return count_global

def test_scope_basic():
    """FUNCTION"""
    local_names = get_locals(test_scope_basic)

    value = count_global

    assert True is ('value' in local_names)
    assert False is ('value' in global_names)

    assert False is ('count' in local_names)
    assert True is ('count' in global_names)

    assert 10 == value


def test_scope_undefined_variable():
    """FUNCTION"""
    local_names = get_locals(test_scope_undefined_variable)

    try:
        my_name = name  #name variable is not in local or  global scope
    except NameError:
        pass

    assert True is ('my_name' in local_names)
    assert False is ('name' in local_names)
    assert False is ('name' in global_names)

def test_variable_shadow():
    """FUNCTION"""
    local_names = get_locals(test_variable_shadow)
    count_global = 20

    assert True is ('count' in local_names)
    assert True is ('count' in global_names)

    assert 20 == count_global
    assert 10 == get_global_count()

def test_global_write():
    """FUNCTION"""
    local_names = get_locals(test_global_write)

    global count_global # declare that we want to use
    # the read/write to global count
    count_global = 30

    try:
        assert False is ('count' in local_names)
        assert True is ('count' in global_names)

        assert 30 == count_global
        assert 30 == get_global_count()
    finally:
        count_global = 10 #reset to original value

def test_scope_is_bound_at_definition_time():
    """FUNCTION"""
    local_names = get_locals(test_scope_is_bound_at_definition_time)

    assert True is ('count' in local_names)
    assert True is ('count' in global_names)

    try:
        value = count_global
        count_global = 30
    except NameError as n_e: # what happens when you read a variable before initializing it?
        #print ex #uncomment after you fill up above
        print(n_e)
        assert True
    finally:
        count_global = 20

    assert 20 == count_global
    assert 10 == get_global_count()


def test_scope_writing_globals():
    """FUNCTION"""
    local_names = get_locals(test_scope_writing_globals)

    assert False is ('count' in local_names)
    assert True is ('count' in global_names)

    global count_global

    try:
        count_global = 40
        assert 40 == count_global
        assert 40 == get_global_count()
    finally:
        count_global = 10

    assert 10 == get_global_count()



THREE_THINGS_I_LEARNT = """
-scopes of variables
-local and global variable
-local variable can be converted into global by declaring it as global inside the local block
"""

TIME_TAKEN = 50


#helper functions which get the variables in locals and globals using the compiler's symbol tables.
def get_locals(func):
    """CHECKS FOR LOCAL SPACE"""
    source = inspect.getsource(func)
    top = symtable.symtable(source, "<string>", "exec")
    func = top.get_children()[0]  #since we are passing only the func code.
    return func.get_locals()

def get_globals():
    """CHECKS FOR GLOBAL SPACE"""
    module = inspect.getmodule(get_globals)
    source = inspect.getsource(module)
    top = symtable.symtable(source, "<string>", "exec")
    return top.get_identifiers()

global_names = get_globals()
