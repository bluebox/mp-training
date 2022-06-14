__author__ = 'Hari'

notes = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''

from placeholders import *

def test_inheritance_basic():
    class A(object): # A inherits from object.
        def f(self):
            pass

    class B(A):      #B inherits from A or B derives A
        def g(self):
            pass

    assert __ == issubclass(A, object)
    assert __ == issubclass(A, A)
    assert __ == issubclass(A, B)

    assert __ == issubclass(B, A)
    assert __ == issubclass(B, B)
    assert __ == issubclass(B, object)

# base class methods are available for derived class objects
def test_inheritance_methods():
    class A(object): # A inherits from object.
        def f(self):
            return "A:f()"

    class B(A):      #B inherits A's behavior (attributes)
        def g(self):
            return "B:g()"

    b = B()
    assert __ == b.f()
    assert __ == b.g()

    a = A()
    assert __ == a.f()
    try:
        assert __ == a.g()
    except __:
        #print ex  #uncomment this line after filling up
        pass

def test_inheritance_overrides():
    class A(object): # A inherits from object.
        def f(self):
            return "A:f()"

        def g(self):
            return "A:g()"

    class B(A):      #B can override A's methods
        def g(self):
            return "B:g()"

    a = A()
    assert __ == a.f()
    assert __ == a.g()

    b = B()
    assert __ == b.f()
    assert __ == b.g()

def test_inheritance_init():
    class A(object):
        def __init__(self):
            self.a1 = []

        def append(self, obj):
            self.a1.append(obj)

    class B(A):
        def __init__(self):
            self.b1 = []

    a = A()
    assert __ == getattr(a, "a1", None)
    assert __ == getattr(a, "b1", None)

    b = B()
    assert __ == getattr(b, "a1", None)
    assert __ == getattr(b, "b1", None)

    try:
        b.append("orange")
    except __ :  #what happened here?
        pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    #lets redefine B now, to chain the inits to the base class.
    class B(A):
        def __init__(self):
            A.__init__(self)
            self.b1 = "b1"

    b = B()
    assert __ == getattr(b, "a1", None)
    assert __ == getattr(b, "b1", None)
    b.append("orange")
    assert __ == b.a1

def test_inheritance_invoking_using_super():
    #super can be used instead of explicitly invoking base.
    class A(object): # A inherits from object.
        def f(self):
            return "A:f()"

        def g(self):
            return "A:g()"

    class B(A):      #B can override A's methods
        def g(self):
            return super(B, self).g() + ":"+ "B:g()"

    b = B()
    assert __ == b.g()


notes_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

three_things_i_learnt = """
-
-
-
"""

time_taken_minutes = ___