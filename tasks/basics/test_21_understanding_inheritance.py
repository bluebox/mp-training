__author__ = 'Hari'

NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''

# from re import T
from tasks.placeholders import __author__

def test_inheritance_basic():
    class A(object): # A inherits from object.
        '''CLASS'''
        def f(self):
            '''CLASS'''
            pass

    class B(A):      #B inherits from A or B derives A
        '''CLASS'''
        def g(self):
            '''CLASS'''
            pass

    assert True is issubclass(A, object)
    assert True is issubclass(A, A)
    assert False is issubclass(A, B)

    assert True is issubclass(B, A)
    assert True is issubclass(B, B)
    assert True is issubclass(B, object)

# base class methods are available for derived class objects
def test_inheritance_methods():
    class A(object): # A inherits from object.
        '''CLASS'''
        def f(self):
            '''CLASS'''
            return "A:f()"

    class B(A):      #B inherits A's behavior (attributes)
        '''CLASS'''
        def g(self):
            '''CLASS'''
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
    class A(object): # A inherits from object.
        '''CLASS'''
        def f(self):
            '''CLASS'''
            return "A:f()"

        def g(self):
            '''CLASS'''
            return "A:g()"

    class B(A):      #B can override A's methods
        '''CLASS'''
        def g(self):
            '''CLASS'''
            return "B:g()"

    a = A()
    assert "A:f()" == a.f()
    assert "A:g()" == a.g()

    b = B()
    assert "A:f()" == b.f()
    assert "B:g()" == b.g()

def test_inheritance_init():
    '''CLASS'''
    class A(object):
        '''CLASS'''
        def __init__(self):
            '''CLASS'''
            self.a1 = []

        def append(self, obj):
            '''CLASS'''
            self.a1.append(obj)

    class B(A):
        '''CLASS'''
        def __init__(self):
            '''CLASS'''
            self.b1 = []

    a = A()
    assert [] == getattr(a, "a1", None)
    assert None == getattr(a, "b1", None)

    b = B()
    assert None == getattr(b, "a1", None)
    assert [] == getattr(b, "b1", None)

    try:
        b.append("orange")
    except AttributeError :  #what happened here?
        pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    #lets redefine B now, to chain the inits to the base class.
    class B(A):
        '''CLASS'''
        def __init__(self):
            '''CLASS'''
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
        '''CLASS'''
        def f(self):
            '''CLASS'''
            return "A:f()"

        def g(self):
            '''CLASS'''
            return "A:g()"

    class B(A):      #B can override A's methods
        '''CLASS'''
        def g(self):
            '''CLASS'''
            return super(B, self).g() + ":"+ "B:g()"

    b = B()
    assert "A:g():B:g()" == b.g()


NOTES_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

RESG = """
-
-
-
"""

TTM1 = 25