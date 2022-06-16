"""This is the 22nd file of python exercise by medplus"""
__author__ = 'Hari'

# from tkinter.messagebox import NO
# from tasks.placeholders import *


NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''


def test_inheritance_basic():
    """basic docstring for pylint testing"""
    class A(object):
        """basic docstring for pylint testing""" 
        
        # A inherits from object.
        def f(self):
            """basic docstring for pylint testing"""
            pass

    class B(A):
        """basic docstring for pylint testing"""      #B inherits from A or B derives A
        def g(self):
            """basic docstring for pylint testing"""
            pass

    assert True == issubclass(A, object)
    assert True == issubclass(A, A)
    assert False == issubclass(A, B)

    assert True == issubclass(B, A)
    assert True == issubclass(B, B)
    assert True == issubclass(B, object)

# base class methods are available for derived class objects
def test_inheritance_methods():
    """basic docstring for pylint testing"""
    class A(object):
        """basic docstring for pylint testing"""
        # A inherits from object.
        def f(self):
            """basic docstring for pylint testing"""
            return "A:f()"

    class B(A):
        """basic docstring for pylint testing"""      #B inherits A's behavior (attributes)
        def g(self):
            """basic docstring for pylint testing"""
            return "B:g()"

    b = B()
    assert "A:f()" == b.f()
    assert "B:g()" == b.g()

    a = A()
    assert "A:f()" == a.f()
    try:
        assert "B:g()" == a.g()
    except AttributeError:
        #print ex  #uncomment this line after filling up
        pass

def test_inheritance_overrides():
    """basic docstring for pylint testing"""
    class A(object):
        """basic docstring for pylint testing""" # A inherits from object.
        def f(self):
            """basic docstring for pylint testing"""
            return "A:f()"

        def g(self):
            """basic docstring for pylint testing"""
            return "A:g()"

    class B(A):
        """basic docstring for pylint testing"""
        #B can override A's methods
        def g(self):
            """basic docstring for pylint testing"""
            return "B:g()"

    a = A()
    assert "A:f()" == a.f()
    assert "A:g()" == a.g()

    b = B()
    assert "A:f()" == b.f()
    assert "B:g()" == b.g()

def test_inheritance_init():
    """basic docstring for pylint testing"""
    class A(object):
        """basic docstring for pylint testing"""
        def __init__(self):
            """basic docstring for pylint testing"""
            self.a1 = []

        def append(self, obj):
            """basic docstring for pylint testing"""
            self.a1.append(obj)

    class B(A):
        """basic docstring for pylint testing"""
        def __init__(self):
            """basic docstring for pylint testing"""
            self.b1 = []

    a = A()
    assert [] == getattr(a, "a1", None)
    assert None == getattr(a, "b1", None)

    b = B()
    assert None == getattr(b, "a1", None)
    assert [] == getattr(b, "b1", None)

    try:
        b.append("orange")
    except AttributeError :
        """basic docstring for pylint testing"""
        #what happened here?
        pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    #lets redefine B now, to chain the inits to the base class.
    class B(A):
        """basic docstring for pylint testing"""
        def __init__(self):
            A.__init__(self)
            self.b1 = "b1"

    b = B()
    assert [] == getattr(b, "a1", None)
    assert "b1" == getattr(b, "b1", None)
    b.append("orange")
    assert ["orange"] == b.a1

def test_inheritance_invoking_using_super():
    """basic docstring for pylint testing"""
    #super can be used instead of explicitly invoking base.
    class A(object):
        """basic docstring for pylint testing"""
        # A inherits from object.
        def f(self):
            """basic docstring for pylint testing"""
            return "A:f()"

        def g(self):
            """basic docstring for pylint testing"""
            return "A:g()"

    class B(A):
        """basic docstring for pylint testing"""
        #B can override A's methods
        def g(self):
            """basic docstring for pylint testing"""
            return super(B, self).g() + ":"+ "B:g()"

    b = B()
    assert "A:g():B:g()" == b.g()


NOTES_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

THREE_THINGS_I_LEARNT = """
classes, inheritance,oops
"""

TIME_TAKEN_MINUTES = 25