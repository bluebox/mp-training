__author__ = 'Hari'

NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''

from tasks.placeholders import __author__

def test_inheritance_basic():
    class Aa(object): # A inherits from object.
        def f_1(self):
            pass

    class Bb(Aa):      #B inherits from A or B derives A
        def g_1(self):
            pass

    assert True is issubclass(Aa, object)
    assert True is issubclass(Aa, Aa)
    assert False is issubclass(Aa, Bb)

    assert True is issubclass(Bb, Aa)
    assert True is issubclass(Bb, Bb)
    assert True is issubclass(Bb, object)

# base class methods are available for derived class objects
def test_inheritance_methods():
    class Aa(object): # A inherits from object.
        def f_1(self):
            return "Aa:f_1()"

    class Bb(Aa):      #B inherits A's behavior (attributes)
        def g_1(self):
            return "Bb:g_1()"

    b_1 = Bb()
    assert "Aa:f_1()" == b_1.f_1()
    assert "Bb:g_1()" == b_1.g_1()

    a_1 = Aa()
    assert "Aa:f_1()" == a_1.f_1()
    try:
        assert "Bb:g_1()" == a_1.g_1()
    except AttributeError as a_1e:
        print(a_1e)

def test_inheritance_overrides():
    class Aa(object): # A inherits from object.
        def f_1(self):
            return "Aa:f_1()"

        def g_1(self):
            return "Aa:g_1()"

    class Bb(Aa):      #B can override A's methods
        def g_1(self):
            return "Bb:g_1()"

    a_1 = Aa()
    assert "Aa:f_1()" == a_1.f_1()
    assert "Aa:g_1()" == a_1.g_1()

    b_1 = Bb()
    assert "Aa:f_1()" == b_1.f_1()
    assert "Bb:g_1()" == b_1.g_1()

def test_inheritance_init():
    class Aa(object):
        def __init__(self):
            self.a1 = []

        def append(self, obj):
            self.a1.append(obj)

    class Bb(Aa):
        def __init__(self):
            self.b1 = []

    a_1 = Aa()
    assert [] == getattr(a_1, "a1", None)
    assert None is getattr(a_1, "b1", None)

    b_1 = Bb()
    assert None is getattr(b_1, "a1", None)
    assert [] == getattr(b_1, "b1", None)

    try:
        b_1.append("orange")
    except AttributeError as a_e:  #what happened here?
        print(a_e)

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    #lets redefine B now, to chain the inits to the base class.
    class Bb(Aa):
        def __init__(self):
            Aa.__init__(self)
            self.b1 = "b1"

    b_1 = Bb()
    assert [] == getattr(b_1, "a1", None)
    assert "b1" == getattr(b_1, "b1", None)
    b_1.append("orange")
    assert ["orange"] == b_1.a1

def test_inheritance_invoking_using_super():
    #super can be used instead of explicitly invoking base.
    class Aa(object): # A inherits from object.
        def f_1(self):
            return "Aa:f_1()"

        def g_1(self):
            return "Aa:g_1()"

    class Bb(Aa):      #B can override A's methods
        def g_1(self):
            return super(Bb, self).g_1() + ":"+ "Bb:g_1()"

    b_1 = Bb()
    assert "Aa:g_1():Bb:g_1()" == b_1.g_1()


NOTES_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

THREE_THINGS_I_LEARNT = """
learnt what classes are and how to use inheritance in python classes
"""

TIME_TAKEN_MINUTES = 20