"""This is the 9th file of python exercise by medplus"""
__author__ = 'Hari'

NOTES = """
Exceptions are the default runtime error reporting mechanism in python.

Most modern languages like c#, java have a similar exception model, so your
understanding will carry forward if you end up learning those languages.
"""

# from tasks.placeholders import *

def test_exception_flow_1():
    """basic docstring for pylint testing"""
    str_fruit = "orange"
    list_result = []
    try:
        str_fruit = str_fruit.upper()
        list_result.append("one")
        str_fruit.missingmethod() # what happens to the control flow here?
        list_result.append("two")
    except AttributeError:
        list_result.append("three")

    assert ["one","three"] == list_result

def test_exception_flow_2():
    """basic docstring for pylint testing"""
    str_fruit = "orange"
    list_result = []
    try:
        list_result.append("one")
        test_value = 1/0 #division by zero.
        print(test_value) #printing for pylint
        list_result.append("two")
        str_fruit.missingmethod() #missing attribute
        list_result.append("three")
    except AttributeError:
        list_result.append("four")
    except ZeroDivisionError:
        list_result.append("five")

    assert ["one","five"] == list_result

def test_raise_error():
    """basic docstring for pylint testing"""
    list_result = []
    try:
        list_result.append("one")
        raise AttributeError("some error here")
    except AttributeError:
        list_result.append("three")

    assert ["one","three"] == list_result

def test_missing_except():
    """basic docstring for pylint testing"""
    list_result = []
    str_fruit = "orange"
    try:
        list_result.append("one")
        #what happens now? fix it with an appropriate try except
        str_fruit.missingmethod()
    except AttributeError:
        list_result.append("two")


    assert ["one", "two"] == list_result

def function_with_except(list_result):
    """basic docstring for pylint testing"""
    str_fruit = "orange"
    list_result.append("f:enter")
    try:
        str_fruit.missingmethod()
    except AttributeError:
        list_result.append("f:except")

    list_result.append("f:return")

def function_without_except(list_result):
    """basic docstring for pylint testing"""
    str_fruit = "orange"
    print(str_fruit)
    list_result.append("f:enter")
    str_fruit.missingmethod()
    list_result.append("f:return")

def test_function_call_with_except():
    """basic docstring for pylint testing"""
    list_result = []
    try:
        list_result.append("m:beforecall")
        function_with_except(list_result)
        list_result.append("m:aftercall")
    except AttributeError:
        list_result.append("m:except")
    assert ["m:beforecall","f:enter","f:except","f:return","m:aftercall"] == list_result

def test_function_call_without_except():
    """basic docstring for pylint testing"""
    list_result = []
    try:
        list_result.append("m:beforecall")
        function_without_except(list_result)
        list_result.append("m:aftercall")
    except AttributeError:
        list_result.append("m:except")
    assert ["m:beforecall","f:enter","m:except"] == list_result

def test_else_on_exception():
    """basic docstring for pylint testing"""
    list_result = []
    try:
        list_result.append("m:beforecall")
        function_with_except(list_result)
        list_result.append("m:aftercall")
    except AttributeError:
        list_result.append("m:except")
    else:
        list_result.append("m:else")

    assert ["m:beforecall","f:enter","f:except","f:return","m:aftercall","m:else"] == list_result


def test_else_on_no_exception():
    """basic docstring for pylint testing"""
    list_result = []
    try:
        list_result.append("m:beforecall")
        function_without_except(list_result)
        list_result.append("m:aftercall")
    except AttributeError:
        list_result.append("m:except")
    else:
        list_result.append("m:else")

    assert ["m:beforecall","f:enter","m:except"] == list_result

def test_finally_on_exception():
    """basic docstring for pylint testing"""
    list_result = []
    try:
        list_result.append("m:beforecall")
        function_with_except(list_result)
        list_result.append("m:aftercall")
    except AttributeError:
        list_result.append("m:except")
    else:
        list_result.append("m:else")
    finally:
        list_result.append("m:finally")

    assert ["m:beforecall","f:enter","f:except","f:return","m:aftercall"
    ,"m:else","m:finally"] == list_result



def test_finally_on_no_exception():
    """basic docstring for pylint testing"""
    list_result = []
    try:
        list_result.append("m:beforecall")
        function_without_except(list_result)
        list_result.append("m:aftercall")
    except AttributeError:
        list_result.append("m:except")
    else:
        list_result.append("m:else")
    finally:
        list_result.append("m:finally")

    assert ["m:beforecall","f:enter","m:except","m:finally"]  == list_result

NOTES2 = '''
To understand why exceptions are a good thing for writing applications,
read up the link below after finishing this module.
http://blogs.msdn.com/b/brada/archive/2003/09/30/50403.aspx
'''


THREE_THINGS_I_LEARNT = """
control flow, exception model, types of exceptions
"""

TIME_TAKEN_MINUTES = 30
