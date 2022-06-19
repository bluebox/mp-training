'''inheritance'''
__author__ = 'Hari'

#from tkinter import NO
# pylint: disable=unused-variable
# pylint: disable=too-few-public-methods
#from tasks.basics.test_08_understanding_truth import THREE_THINGS_I_LEARNT, TIME_TAKEN_MINUTES


NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''

#from tasks.placeholders import *

def test_inheritance_basic():
    '''inheritance'''
    class _A(object): # A inherits from object.
        '''inheritance'''
        def _f(self):
            '''inheritance'''
            pass

    class _B(_A):      #B inherits from A or B derives A
        '''inheritance'''
        def _g(self):
            '''inheritance'''
            pass

    assert True is issubclass(_A, object)
    assert True is issubclass(_A, _A)
    assert False is issubclass(_A, _B)

    assert True is issubclass(_B, _A)
    assert True is issubclass(_B, _B)
    assert True is issubclass(_B, object)

# base class methods are available for derived class objects
def test_inheritance_methods():
    '''inheritance'''
    class _A(object): # A inherits from object.
        '''inheritance'''
        def _f(self):
            '''inheritance'''
            return "_A:_f()"

    class _B(_A):      #B inherits A's behavior (attributes)
        '''inheritance'''
        def _g(self):
            '''inheritance'''
            return "_B:_g()"

    _b = _B()
    assert "_A:_f()"== _b._f()
    assert "_B:_g()" == _b._g()

    _a = _A()
    assert "_A:_f()"== _a._f()
    try:
        assert ''== _a._g()
    except AttributeError:
        #print ex  #uncomment this line after filling up
        pass

def test_inheritance_overrides():
    '''inheritance'''
    class _A(object): # A inherits from object.
        '''inheritance'''
        def _f(self):
            '''inheritance'''
            return "_A:_f()"

        def _g(self):
            '''inheritance'''
            return "_A:_g()"

    class _B(_A):      #B can override A's methods
        '''inheritance'''
        def _g(self):
            '''inheritance'''
            return "_B:_g()"

    _a = _A()
    assert "_A:_f()" == _a._f()
    assert "_A:_g()"== _a._g()

    _b = _B()
    assert "_A:_f()"== _b._f()
    assert "_B:_g()" == _b._g()

def test_inheritance_init():
    '''inheritance'''
    class _A(object):
        '''inheritance'''
        def __init__(self):
            '''inheritance'''
            self.a_1 = []

        def append(self, obj):
            '''inheritance'''
            self.a_1.append(obj)

    class _B(_A):
        '''inheritance'''
        def __init__(self):
            '''inheritance'''
            self.b_1 = []

    _a = _A()
    assert [] == getattr(_a, "a_1", None)
    assert None is getattr(_a, "b_1", None)

    _b = _B()
    assert None is getattr(_b, "a_1", None)
    assert [] == getattr(_b, "b_1", None)

    try:
        _b.append("orange")
    except AttributeError :  #what happened here?
        pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    #lets redefine B now, to chain the inits to the base class.
    class _B(_A):
        '''inheritance'''
        def __init__(self):
            '''inheritance'''
            _A.__init__(self)
            self.b_1 = "b_1"

    _b = _B()
    assert [] == getattr(_b, "a_1", None)
    assert 'b_1'== getattr(_b, "b_1", None)
    _b.append("orange")
    assert ['orange'] == _b.a_1

def test_inheritance_invoking_using_super():
    '''inheritance'''
    #super can be used instead of explicitly invoking base.
    class _A(object): # A inherits from object.
        '''inheritance'''
        def _f(self):
            '''inheritance'''
            return "_A:_f()"

        def _g(self):
            '''inheritance'''
            return "_A:_g()"

    class _B(_A):      #B can override A's methods
        '''inheritance'''
        def _g(self):
            '''inheritance'''
            return super(_B, self)._g() + ":"+ "_B:_g()"

    _b = _B()
    assert "_A:_g():_B:_g()" == _b._g()


NOTES_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

THREE_THINGS_I_LEARNT = """
-
-
-
"""

TIME_TAKEN_MINUTES = 30