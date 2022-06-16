__author__ = 'Hari'
from placeholders import __author__
NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''


def test_inheritance_basic():
    class A(object): # A inherits from object.
        def f(self):
            pass

    class B(A):      #B inherits from A or B derives A
        def g(self):
            pass

    assert True is issubclass(A, object)
    assert True is issubclass(A, A)
    assert False is issubclass(A, B)

    assert True  is issubclass(B, A)
    assert True is issubclass(B, B)
    assert  True is issubclass(B, object)

# base class methods are available for derived class objects
def test_inheritance_methods():
    class A(object): # A inherits from object.
        def f(self):
            return "A:f()"

    class B(A):      #B inherits A's behavior (attributes)
        def g(self):
            return "B:g()"

    b = B()
    assert 'A:f()' == b.f()
    assert 'B:g()'== b.g()

    a = A()
    assert 'A:f()'== a.f()
    try:
        assert AttributeError == a.g()
    except AttributeError:
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
    assert "A:f()" == a.f()
    assert "A:g()" == a.g()

    b = B()
    assert "A:f()" == b.f()
    assert "B:g()" == b.g()

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
    assert [] == getattr(a, "a1", None)
    assert None == getattr(a, "b1", None)

    b = B()
    assert None== getattr(b, "a1", None)
    assert [] == getattr(b, "b1", None)

    try:
        b.append("orange")
    except AttributeError:  #what happened here?
        pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    #lets redefine B now, to chain the inits to the base class.
    class B(A):
        def __init__(self):
            A.__init__(self)
            self.b1 = "b1"

    b = B()
    assert [] == getattr(b, "a1", None)
    assert 'b1' == getattr(b, "b1", None)
    b.append("orange")
    assert ['orange'] == b.a1

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
    assert "A:g():B:g()" == b.g()


NOTES1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

THREE_THINGS_I_LEARNT = """
-methods of parent can be accessed by their child 
-but methods of child canmot nbe accessed by their parent 
-super keyword is used to access methods of their parent 
"""

TIME_TAKEN_MINUTES= 40