__author__ = 'Hari'

notes = """
Exceptions are the default runtime error reporting mechanism in python.

Most modern languages like c#, java have a similar exception model, so your
understanding will carry forward if you end up learning those languages.
"""

from tasks.placeholders import *

""" Program to check the Exceptions in python"""
def test_exception_flow_1():
    """ Function to flow in exception """
    fruit = "orange"
    result = []
    try:
        fruit = fruit.upper()
        result.append("one")
        fruit.missingmethod() # what happens to the control flow here?
        result.append("two")
    except AttributeError as a_e:
        result.append("three")

    assert ["one", "three"] == result
test_exception_flow_1()

def test_exception_flow_2():
    """ function to test the exception"""
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
    except ZeroDivisionError :
        result.append("five")

    assert ["one", "five"] == result
test_exception_flow_2()

def test_raise_error():
    """ function to use raise for exception"""
    result = []
    try:
        result.append("one")
        raise AttributeError("some error here")
    except AttributeError :
        result.append("three")

    assert ["one", "three"] == result
test_raise_error()

def test_missing_except():
    """ function to test missing except"""
    result = []
    fruit = "orange"

    result.append("one")
    #what happens now? fix it with an appropriate try except
    try:
        fruit.missingmethod()
    except AttributeError :
        result.append("two")

    assert ["one", "two"] == result
test_missing_except()

def function_with_except(result):
    """ function to check except"""
    fruit = "orange"
    result.append("f:enter")
    try:
        fruit.missingmethod()
    except AttributeError :
        result.append("f:except")

    result.append("f:return")

def function_without_except(result):
    """ function to check function without except"""
    fruit = "orange"
    result.append("f:enter")
    fruit.missingmethod()
    result.append("f:return")

def test_function_call_with_except():
    """ function to call with except """
    result = []
    try:
        result.append("m:beforecall")
        function_with_except(result)
        result.append("m:aftercall")
    except AttributeError :
        result.append("m:except")
    assert ["m:beforecall", "f:enter", "f:except", "f:return", "m:aftercall"] == result
test_function_call_with_except()

def test_function_call_without_except():
    """ function to call without except """
    result = []
    try:
        result.append("m:beforecall")
        function_without_except(result)
        result.append("m:aftercall")
    except AttributeError:
        result.append("m:except")
    assert ["m:beforecall", "f:enter", "m:except"] == result
test_function_call_without_except()

def test_else_on_exception():
    """ function to test without exception """
    result = []
    try:
        result.append("m:beforecall")
        function_with_except(result)
        result.append("m:aftercall")
    except AttributeError :
        result.append("m:except")
    else:
        result.append("m:else")

    assert ["m:beforecall", "f:enter",  "f:except", "f:return", "m:aftercall", "m:else"] == result
test_else_on_exception()


def test_else_on_no_exception():
    """ function to test else on exception """
    result = []
    try:
        result.append("m:beforecall")
        function_without_except(result)
        result.append("m:aftercall")
    except AttributeError :
        result.append("m:except")
    else:
        result.append("m:else")

    assert ["m:beforecall", "f:enter", "m:except"] == result
test_else_on_no_exception()

def test_finally_on_exception():
    """ function to test finally """
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

    assert ["m:beforecall", "f:enter", "f:except", "f:return", "m:aftercall", "m:else", "m:finally"] == result
test_finally_on_exception()



def test_finally_on_no_exception():
    """ function to test finally on no exception """
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

    assert ["m:beforecall", "f:enter", "m:except", "m:finally"] == result
test_finally_on_no_exception()
notes2 = '''
To understand why exceptions are a good thing for writing applications,
read up the link below after finishing this module.
http://blogs.msdn.com/b/brada/archive/2003/09/30/50403.aspx
'''


three_things_i_learnt = """
-functioning of exception in python
-raise keyword to raise exception   
- error can be checked for the function enclosed
"""

time_taken_minutes = 60
