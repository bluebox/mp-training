__author__ = 'Hari'

NOTES = """
Exceptions are the default runtime error reporting mechanism in python.

Most modern languages like c#, java have a similar exception model, so your
understanding will carry forward if you end up learning those languages.
"""

from tasks.placeholders import *

def test_exception_flow_1():
    fruit = "orange"
    result = []
    try:
        fruit = fruit.upper()
        result.append("one")
        fruit.missingmethod() # what happens to the control flow here?
        # once an exception occurs, compiler will either stop execution or searches for an 
        # handling code
        # once it finds handling code, no other code will be executed in the block where 
        # exception has occured
        result.append("two")
    except AttributeError:
        result.append("three")

    assert ["one", "three"] == result

def test_exception_flow_2():
    fruit = "orange"
    result = []
    try:
        result.append("one")
        value = 1/0 #division by zero.
        result.append("two")
        fruit.missingmethod() #missing attribute
        result.append("three")
    except AttributeError:
        result.append("four")
    except ZeroDivisionError:
        result.append("five")

    assert ["one", "five"] == result

def test_raise_error():
    result = []
    try:
        result.append("one")
        raise AttributeError("some error here")
        # even this code doesnt work if we wantedly raise error
        result.append("two")
    except AttributeError:
        result.append("three")

    assert ["one", "three"] == result

def test_missing_except():
    result = []
    fruit = "orange"

    result.append("one")
    #what happens now? fix it with an appropriate try except
    try:
        fruit.missingmethod()
    except AttributeError as a_e:
        print(a_e)
    result.append("two")

    assert ["one", "two"] == result

def function_with_except(result):
    fruit = "orange"
    result.append("f:enter")
    try:
        fruit.missingmethod()
    except AttributeError:
        result.append("f:except")

    result.append("f:return")

def function_without_except(result):
    fruit = "orange"
    result.append("f:enter")
    fruit.missingmethod()
    result.append("f:return")

def test_function_call_with_except():
    result = []
    try:
        result.append("m:beforecall")
        function_with_except(result)
        result.append("m:aftercall")
    except AttributeError:
        result.append("m:except")
    assert ["m:beforecall", "f:enter", "f:except", "f:return", "m:aftercall"] == result

def test_function_call_without_except():
    result = []
    try:
        result.append("m:beforecall")
        function_without_except(result)
        result.append("m:aftercall")
    except AttributeError:
        result.append("m:except")
    assert ["m:beforecall", "f:enter", "m:except"] == result

def test_else_on_exception():
    result = []
    try:
        result.append("m:beforecall")
        function_with_except(result)
        result.append("m:aftercall")
    except AttributeError:
        result.append("m:except")
    else:
        result.append("m:else")

    assert ["m:beforecall", "f:enter", "f:except", "f:return", "m:aftercall", "m:else"] == result


def test_else_on_no_exception():
    result = []
    try:
        result.append("m:beforecall")
        function_without_except(result)
        result.append("m:aftercall")
    except AttributeError:
        result.append("m:except")
    else:
        result.append("m:else")

    assert ["m:beforecall", "f:enter", "m:except"] == result

def test_finally_on_exception():
    result = []
    try:
        result.append("m:beforecall")
        function_with_except(result)
        result.append("m:aftercall")
    except AttributeError:
        result.append("m:except")
    else:
        result.append("m:else")
    finally:
        result.append("m:finally")

    assert ["m:beforecall", "f:enter", "f:except", "f:return", "m:aftercall", "m:else", "m:finally"] == result



def test_finally_on_no_exception():
    result = []
    try:
        result.append("m:beforecall")
        function_without_except(result)
        result.append("m:aftercall")
    except AttributeError:
        result.append("m:except")
    else:
        result.append("m:else")
    finally:
        result.append("m:finally")

    assert ["m:beforecall", "f:enter", "m:except", "m:finally"] == result

NOTES2 = '''
To understand why exceptions are a good thing for writing applications,
read up the link below after finishing this module.
http://blogs.msdn.com/b/brada/archive/2003/09/30/50403.aspx
'''


THREE_THINGS_I_LEARNT = """
1. if there is an error and if that block handles error, then the next code 
within the same block wont execute
2. if there is an error and if that block doesnt handles error, then control 
flow jumps to outer block and searches handling code in the outer block
3. if there is an error, then else statement wont execute
4. if there is no error, then else block doesnt execute
5. finally always execute even if there is an error or not 
"""

TIME_TAKEN_MINUTES = 30
