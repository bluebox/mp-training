'''program file'''
__author__ = 'Hari'

NOTES = """
Exceptions are the default runtime error reporting mechanism in python.

Most modern languages like c#, java have a similar exception model, so your
understanding will carry forward if you end up learning those languages.
"""

#from tasks.placeholders import *

def test_exception_flow_1():
    '''function'''
    fruit = "orange"
    result = []
    try:
        fruit = fruit.upper()
        result.append("one")
        fruit.missingmethod()
        # what happens to the control flow here? throws error as no such function is there
        result.append("two")
    except AttributeError :
        result.append("three")
    assert ["one","three"] == result

def test_exception_flow_2():
    '''function'''
    fruit = "orange"
    result = []
    try:
        result.append("one")
        value = 1/0 #division by zero.
        result.append("two")
        fruit.missingmethod() #missing attribute
        result.append("three")
    except AttributeError :
        result.append("four")
    except ZeroDivisionError:
        result.append("five")
    assert ["one","five"] == result

def test_raise_error():
    '''function'''
    result = []
    try:
        result.append("one")
        raise AttributeError("some error here")
    except AttributeError :
        result.append("three")
    assert ["one","three"] == result

def test_missing_except():
    '''function'''
    result = []
    fruit = "orange"
    try:
        result.append("one")
        #what happens now? fix it with an appropriate try except
        fruit.missingmethod()
    except AttributeError :
        result.append("two")
    assert ["one", "two"] == result

def function_with_except(result):
    '''function'''
    fruit = "orange"
    result.append("f:enter")
    try:
        fruit.missingmethod()
    except AttributeError :
        result.append("f:except")
    result.append("f:return")

def function_without_except(result):
    '''function'''
    fruit = "orange"
    result.append("f:enter")
    fruit.missingmethod()
    result.append("f:return")

def test_function_call_with_except():
    '''function'''
    result = []
    try:
        result.append("m:beforecall")
        function_with_except(result)
        result.append("m:aftercall")
    except AttributeError :
        result.append("m:except")
    assert ["m:beforecall","f:enter","f:except","f:return","m:aftercall"] == result

def test_function_call_without_except():
    '''function'''
    result = []
    try:
        result.append("m:beforecall")
        function_without_except(result)
        result.append("m:aftercall")
    except AttributeError :
        result.append("m:except")
    assert ["m:beforecall","f:enter","m:except"] == result

def test_else_on_exception():
    '''function'''
    result = []
    try:
        result.append("m:beforecall")
        function_with_except(result)
        result.append("m:aftercall")
    except AttributeError :
        result.append("m:except")
    else:
        result.append("m:else")

    assert  ['m:beforecall', 'f:enter', 'f:except', 'f:return', 'm:aftercall', 'm:else'] == result


def test_else_on_no_exception():
    '''function'''
    result = []
    try:
        result.append("m:beforecall")
        function_without_except(result)
        result.append("m:aftercall")
    except AttributeError :
        result.append("m:except")
    else:
        result.append("m:else")

    assert ['m:beforecall', 'f:enter', 'm:except'] == result

def test_finally_on_exception():
    '''function'''
    result = []
    try:
        result.append("m:beforecall")
        function_with_except(result)
        result.append("m:aftercall")
    except AttributeError :
        result.append("m:except")
    else:
        result.append("m:else")
    finally:
        result.append("m:finally")
    assert['m:beforecall','f:enter','f:except','f:return','m:aftercall','m:else','m:finally']==result

def test_finally_on_no_exception():
    '''function'''
    result = []
    try:
        result.append("m:beforecall")
        function_without_except(result)
        result.append("m:aftercall")
    except AttributeError :
        result.append("m:except")
    else:
        result.append("m:else")
    finally:
        result.append("m:finally")
    assert ['m:beforecall', 'f:enter', 'm:except', 'm:finally'] == result

NOTES2 = '''
To understand why exceptions are a good thing for writing applications,
read up the link below after finishing this module.
http://blogs.msdn.com/b/brada/archive/2003/09/30/50403.aspx
'''
THREE_THINGS_I_LEARNT = """
-
-
-
"""

TIME_TAKEN_MINUTES = 92
