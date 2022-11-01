"""MODULE FOR UNDERSTANDING SCOPE OF A VARIABLE"""
import inspect
import symtable
from tasks.placeholders import *

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


count = 10


# used to by-pass any local shadow variables.
def get_global_count():
    """
    get-global count helper function
    :return: count
    """
    return count


def test_scope_basic():
    """test scope basic"""
    local_names = get_locals(test_scope_basic)

    value = count

    assert True is ('value' in local_names)
    assert False is ('value' in global_names)

    assert False is ('count' in local_names)
    assert True is ('count' in global_names)

    assert 10 == value


def test_scope_undefined_variable():
    """
    test scope undefined variables
    :return: None
    """
    local_names = get_locals(test_scope_undefined_variable)

    try:
        my_name = name  # name variable is not in local or  global scope
    except NameError:  # fill up the exception
        pass

    assert True is ('my_name' in local_names)
    assert False is ('name' in local_names)
    assert False is ('name' in global_names)


def test_variable_shadow():
    """test variables shadow"""
    local_names = get_locals(test_variable_shadow)
    count = 20

    assert True is ('count' in local_names)
    assert True is ('count' in global_names)

    assert 20 == count
    assert 10 == get_global_count()


def test_global_write():
    """
    test global write
    :return: None
    """
    local_names = get_locals(test_global_write)

    global count  # declare that we want to use the read/write to global count
    count = 30

    try:
        assert False is ('count' in local_names)
        assert True is ('count' in global_names)

        assert 30 == count
        assert 30 == get_global_count()
    finally:
        count = 10  # reset to original value


def test_scope_is_bound_at_definition_time():
    """
    test scope is bound at definition time
    :return: None
    """
    local_names = get_locals(test_scope_is_bound_at_definition_time)

    assert True is ('count' in local_names)
    assert True is ('count' in global_names)

    try:
        value = count
        count = 30
    except NameError as ex:  # what happens when you read a variable before initializing it?
        # NameError
        print(ex)
        assert True
    finally:
        count = 20

    assert 20 == count
    assert 10 == get_global_count()


def test_scope_writing_globals():
    """
    test scope writing globals
    :return: None
    """
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

    assert 10 == get_global_count()


THREE_THINGS_I_LEARNT = """
-scope of a function
-global keyword gives access to global variables
-finally block has access to variables defined in try block
"""

TIME_TAKEN_MINUTES = 15


# helper functions which get the variables in locals and globals using the compiler's symbol tables.
def get_locals(func):
    """
    helper function get-locals
    :param func:
    :return: calls get-locals function
    """
    source = inspect.getsource(func)
    top = symtable.symtable(source, "<string>", "exec")
    func = top.get_children()[0]  # since we are passing only the func code.
    return func.get_locals()


def get_globals():
    """
    helper function get-globals
    :return: list of strings
    """
    module = inspect.getmodule(get_globals)
    source = inspect.getsource(module)
    top = symtable.symtable(source, "<string>", "exec")
    return top.get_identifiers()


global_names = get_globals()
