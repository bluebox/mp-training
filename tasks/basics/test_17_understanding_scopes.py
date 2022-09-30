'''program file'''
__author__ = 'Hari'
import inspect
import symtable
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
#from tasks.placeholders import *
c_1 = 10
#used to by pass any local shadow variables.
def get_global_count():
    '''function'''
    return c_1

def test_scope_basic():
    '''function'''
    local_names = get_locals(test_scope_basic)
    value = c_1
    assert True is ('value' in local_names)
    assert False is ('value' in global_names)
    assert False is ('c_1' in local_names)
    assert True is ('c_1' in global_names)
    assert 10 == value

def test_scope_undefined_variable():
    '''function'''
    local_names = get_locals(test_scope_undefined_variable)
    try:
        my_name = name  #name variable is not in local or  global scope
    except NameError: # fill up the exception
        pass
    assert True is ('my_name' in local_names)
    assert False is ('name' in local_names)
    assert False is ('name' in global_names)

def test_variable_shadow():
    '''function'''
    local_names = get_locals(test_variable_shadow)
    c_1 = 20
    assert True is ('c_1' in local_names)
    assert True is ('c_1' in global_names)
    assert 20 == c_1
    assert 10 == get_global_count()

def test_global_write():
    '''function'''
    local_names = get_locals(test_global_write)
    global c_1 # declare that we want to use the read/write to global c_1
    c_1 = 30
    try:
        assert False is ('c_1' in local_names)
        assert True is ('c_1' in global_names)
        assert 30 == c_1
        assert 30 == get_global_count()
    finally:
        c_1 = 10 #reset to original value

def test_scope_is_bound_at_definition_time():
    '''function'''
    local_names = get_locals(test_scope_is_bound_at_definition_time)
    assert True is ('c_1' in local_names)
    assert True is ('c_1' in global_names)
    try:
        value = c_1
        c_1 = 30
    except NameError:# what happens when you read a variable before initializing it?
        #print ex #uncomment after you fill up above
        assert True
    finally:
        c_1 = 20
    assert 20 == c_1
    assert 10 == get_global_count()

def test_scope_writing_globals():
    '''function'''
    local_names = get_locals(test_scope_writing_globals)
    assert False is ('c_1' in local_names)
    assert True is ('c_1' in global_names)
    global c_1
    try:
        c_1 = 40
        assert 40 == c_1
        assert 40 == get_global_count()
    finally:
        c_1 = 10
    assert 10 == get_global_count()

THREE_THINGS_I_LEARNT = """
-
-
-
"""
TIME_TAKEN_MINUTES = 60

#helper functions which get the variables in locals and globals using the compiler's symbol tables.
def get_locals(func):
    '''function'''
    source = inspect.getsource(func)
    top = symtable.symtable(source, "<string>", "exec")
    func = top.get_children()[0]  #since we are passing only the func code.
    return func.get_locals()

def get_globals():
    '''function'''
    module = inspect.getmodule(get_globals)
    source = inspect.getsource(module)
    top = symtable.symtable(source, "<string>", "exec")
    return top.get_identifiers()

global_names = get_globals()
