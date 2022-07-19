<<<<<<< HEAD
#from tasks.placeholders import *
=======
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
__author__ = 'Hari'

NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''

from tasks.placeholders import *

def test_inheritance_basic():
<<<<<<< HEAD
    class ClassA():  # A inherits from object.
        def method_f(self):
            pass
        def method_f_2(self):
            pass
    class ClassB(ClassA):  # B inherits from A or B derives A
        def method_g(self):
            pass

    assert True is issubclass(ClassA, object)
    assert True is issubclass(ClassA, ClassA)
    assert False is issubclass(ClassA, ClassB)

    assert True is issubclass(ClassB, ClassA)
    assert True is issubclass(ClassB, ClassB)
    assert True is issubclass(ClassB, object)
=======
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
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

# base class methods are available for derived class objects
def test_inheritance_methods():
<<<<<<< HEAD
    class ClassA():  # A inherits from object.
        def method_f(self):
            return "A:f()"
        def method_f_2(self):
            pass
    class ClassB(ClassA):  # B inherits A's behavior (attributes)
        def method_g(self):
            return "B:g()"

    _b = ClassB()
    assert "A:f()" == _b.method_f()
    assert "B:g()" == _b.method_g()

    _a = ClassA()
    assert "A:f()" == _a.method_f()
    try:
        assert "error" == _a.method_g()
    except AttributeError as _ex:
        print(_ex)  #uncomment this line after filling up
=======
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
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
        pass

def test_inheritance_overrides():
<<<<<<< HEAD
    class ClassA():  # A inherits from object.
        def method_f(self):
=======
    class A(object): # A inherits from object.
        def f(self):
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
            return "A:f()"

        def method_g(self):
            return "A:g()"

<<<<<<< HEAD
    class ClassB(ClassA):  # B can override A's methods
        def method_g(self):
            return "B:g()"

    _a = ClassA()
    assert "A:f()" == _a.method_f()
    assert "A:g()" == _a.method_g()

    _b = ClassB()
    assert "A:f()" == _b.method_f()
    assert "B:g()" == _b.method_g()
=======
    class B(A):      #B can override A's methods
        def g(self):
            return "B:g()"

    a = A()
    assert __ == a.f()
    assert __ == a.g()

    b = B()
    assert __ == b.f()
    assert __ == b.g()
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_inheritance_init():
    class ClassA():
        def __init__(self):
            self.a_1 = []

        def append(self, obj):
            self.a_1.append(obj)
        def method_f_2(self):
            pass
    class ClassB(ClassA):
        def __init__(self):
            self.b_1 = []

<<<<<<< HEAD
    _a = ClassA()
    assert [] == getattr(_a, "a_1", None)
    assert None is getattr(_a, "b_1", None)

    _b = ClassB()
    assert None is getattr(_b, "a_1", None)
    assert [] == getattr(_b, "b_1", None)

    try:
        _b.append("orange")
    except AttributeError:  # what happened here?
=======
    a = A()
    assert __ == getattr(a, "a1", None)
    assert __ == getattr(a, "b1", None)

    b = B()
    assert __ == getattr(b, "a1", None)
    assert __ == getattr(b, "b1", None)

    try:
        b.append("orange")
    except __ :  #what happened here?
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
        pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

<<<<<<< HEAD
    # lets redefine B now, to chain the inits to the base class.
    class ClassB2(ClassA):
        def __init__(self):
            ClassA.__init__(self)
            self.b_1 = "b_1"

    _b = ClassB2()
    assert [] == getattr(_b, "a_1", None)
    assert "b_1" == getattr(_b, "b_1", None)
    _b.append("orange")
    assert ["orange"] == _b.a_1

def test_inheritance_invoking_using_super():
    # super can be used instead of explicitly invoking base.
    class ClassA():  # A inherits from object.
        def method_f(self):
=======
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
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
            return "A:f()"

        def method_g(self):
            return "A:g()"

<<<<<<< HEAD
    class ClassB(ClassA):  # B can override A's methods
        def method_g(self):
            return super().method_g() + ":" + "B:g()"

    _b = ClassB()
    assert "A:g():B:g()" == _b.method_g()
=======
    class B(A):      #B can override A's methods
        def g(self):
            return super(B, self).g() + ":"+ "B:g()"

    b = B()
    assert __ == b.g()
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


NOTES_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

THREE_THINGS_LERNT = """
inheritance of classes
"""

<<<<<<< HEAD
TIME_TAKEN_MINUTES = 120
=======
time_taken_minutes = ___
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
