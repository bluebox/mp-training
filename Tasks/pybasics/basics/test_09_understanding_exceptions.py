__author__ = 'Hari'

notes = """
Exceptions are the default runtime error reporting mechanism in python.

Most modern languages like c#, java have a similar exception model, so your
understanding will carry forward if you end up learning those languages.
"""

from placeholders import *

def test_exception_flow_1():
    fruit = "orange"
    result = []
    try:
        fruit = fruit.upper()
        result.append("one")
        fruit.missingmethod() # what happens to the control flow here?
        result.append("two")
    except AttributeError as ae:
        result.append("three")

    assert [__] == result

def test_exception_flow_2():
    fruit = "orange"
    result = []
    try:
        result.append("one")
        value = 1/0 #division by zero.
        result.append("two")
        fruit.missingmethod() #missing attribute
        result.append("three")
    except AttributeError as ae:
        result.append("four")
    except ZeroDivisionError as ze:
        result.append("five")

    assert [__] == result

def test_raise_error():
    result = []
    try:
        result.append("one")
        raise AttributeError("some error here")
    except AttributeError as se:
        result.append("three")

    assert [__] == result

def test_missing_except():
    result = []
    fruit = "orange"

    result.append("one")
    #what happens now? fix it with an appropriate try except
    fruit.missingmethod()
    result.append("two")

    assert ["one", "two"] == result

def function_with_except(result):
    fruit = "orange"
    result.append("f:enter")
    try:
        fruit.missingmethod()
    except AttributeError as ae:
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
    except AttributeError as ae:
        result.append("m:except")
    assert [__] == result

def test_function_call_without_except():
    result = []
    try:
        result.append("m:beforecall")
        function_without_except(result)
        result.append("m:aftercall")
    except AttributeError as ae:
        result.append("m:except")
    assert [__] == result

def test_else_on_exception():
    result = []
    try:
        result.append("m:beforecall")
        function_with_except(result)
        result.append("m:aftercall")
    except AttributeError as ae:
        result.append("m:except")
    else:
        result.append("m:else")

    assert [__] == result


def test_else_on_no_exception():
    result = []
    try:
        result.append("m:beforecall")
        function_without_except(result)
        result.append("m:aftercall")
    except AttributeError as ae:
        result.append("m:except")
    else:
        result.append("m:else")

    assert [__] == result

def test_finally_on_exception():
    result = []
    try:
        result.append("m:beforecall")
        function_with_except(result)
        result.append("m:aftercall")
    except AttributeError as ae:
        result.append("m:except")
    else:
        result.append("m:else")
    finally:
        result.append("m:finally")

    assert [__] == result



def test_finally_on_no_exception():
    result = []
    try:
        result.append("m:beforecall")
        function_without_except(result)
        result.append("m:aftercall")
    except AttributeError as ae:
        result.append("m:except")
    else:
        result.append("m:else")
    finally:
        result.append("m:finally")

    assert [__] == result

notes2 = '''
To understand why exceptions are a good thing for writing applications,
read up the link below after finishing this module.
http://blogs.msdn.com/b/brada/archive/2003/09/30/50403.aspx
'''


three_things_i_learnt = """
-
-
-
"""

time_taken_minutes = ___
