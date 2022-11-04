#from Tasks.placeholders import *

"""inheritance"""
__author__ = 'Hari'

NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''



def test_inheritance_basic():
    """main function calling"""
    class CallingA(): # A inherits from object.
        "A inherits from object."
        def method_f(self):
            """methodF"""
            #pass

    class CallingB(CallingA):      #B inherits from A or B derives A
        """B inherits from A or B derives A"""
        def method_g(self):
            """methodG"""
            #pass

    assert True is issubclass(CallingA, object)
    assert True is issubclass(CallingA, CallingA)
    assert False is issubclass(CallingA, CallingB)

    assert True is issubclass(CallingB, CallingA)
    assert True is issubclass(CallingB, CallingB)
    assert True is issubclass(CallingB, object)

#base class methods are available for derived class objects
def test_inheritance_methods():
    """main function calling"""
    class CallingA(): # A inherits from object.
        """A inherits from object."""
        def method_f(self):
            """methodF"""
            return "A:f()"

    class CallingB(CallingA):      #B inherits A's behavior (attributes)
        """B inherits A's behavior"""
        def method_g(self):
            """method of classB"""
            return "B:g()"

    _b = CallingB()
    assert "A:f()" == _b.method_f()
    assert "B:g()" == _b.method_g()

    _a = CallingA()
    assert "A:f()" == _a.method_f()
    try:
        assert False is _a.method_g()
    except AttributeError:
        #print ex  #uncomment this line after filling up
        pass

def test_inheritance_overrides():
    """function defining"""
    class CallingA(): # A inherits from object.
        """main class"""
        def method_f(self):
            """methodA"""
            return "A:f()"

        def method_g(self):
            """methodG"""
            return "A:g()"

    class CallingB(CallingA):      #B can override A's methods
        """B can override A's methods"""
        def method_g(self):
            return "B:g()"

    _a = CallingA()
    assert "A:f()" == _a.method_f()
    assert "A:g()" == _a.method_g()

    _b = CallingB()
    assert "A:f()" == _b.method_f()
    assert "B:g()" == _b.method_g()

def test_inheritance_init():
    """inheritance function"""
    class CallingA():
        """callingA"""
        def __init__(self):
            self.a_1 = []

        def append(self, obj):
            """inheritance"""
            self.a_1.append(obj)

    class CallingB(CallingA):
        """CallingB"""
        def __init__(self):
            self.b_1 = []
        def method_f3(self):
            """method"""
            pass

    _a = CallingA()
    assert [] == getattr(_a, "a_1", None)
    assert None is getattr(_a, "b_1", None)

    _b = CallingB()
    assert None is getattr(_b, "a_1", None)
    assert [] == getattr(_b, "b_1", None)

    try:
        _b.append("orange")
    except AttributeError :  #what happened here?
        pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    #lets redefine B now, to chain the inits to the base class.
    class CallingB2(CallingA):
        """Again defined same class"""
        def __init__(self):
            CallingA.__init__(self)
            self.b_1 = "b1"
        def method_f1(self):
            """methods"""
            #pass

    _b = CallingB2()
    assert [] == getattr(_b, "a_1", None)
    assert "b1" == getattr(_b, "b_1", None)
    _b.append("orange")
    assert ["orange"] == _b.a_1

def test_inheritance_invoking_using_super():
    """super can be used instead of explicitly invoking base."""
    #super can be used instead of explicitly invoking base.
    class CallingA(): # A inherits from object.
        """A inherits from object"""
        def method_f(self):
            """class methods"""
            return "A:f()"

        def method_g(self):
            """class methods"""
            return "A:g()"

    class CallingB(CallingA):      #B can override A's methods
        """superclass inheritance"""
        def method_g(self):
            return super().method_g() + ":"+ "B:g()"
        def method_f2(self):
            """methods"""
            #pass

    _b = CallingB()
    assert "A:g():B:g()" == _b.method_g()


NOTES_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

THREE_THINGS_I_LEARNT = """
-
-
-
"""

TIME_TAKEN_IN_MINUTES = 40
