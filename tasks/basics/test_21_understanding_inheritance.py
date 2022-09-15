__author__ = 'Hari'

from tasks.placeholders import __author__

NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''



def test_inheritance_basic():
    class Aclass(): # A inherits from object.
        def f_1(self):
            pass

    class Bc1ass(Aclass):      #B inherits from A or B derives A
        def g_1(self):
            pass

    assert True is issubclass(Aclass, object)
    assert True is issubclass(Aclass, Aclass)
    assert False is issubclass(Aclass, Bc1ass)

    assert True is issubclass(Bc1ass, Aclass)
    assert True is issubclass(Bc1ass, Bc1ass)
    assert True is issubclass(Bc1ass, object)

# base class methods are available for derived class objects
def test_inheritance_methods():
    class Aclass(): # A inherits from object.
        def f_1(self):
            return "A:f()"

    class Bclass(Aclass):      #B inherits A's behavior (attributes)
        def g_1(self):
            return "B:g()"

    b_1 = Bclass()
    assert "A:f()" == b_1.f_1()
    assert "B:g()" == b_1.g_1()

    a_1 = Aclass()
    assert "A:f()" == a_1.f_1()
    try:
        assert "B:g()" == a_1.g_1()
    except AttributeError:
        #print ex  #uncomment this line after filling up
        pass

def test_inheritance_overrides():
    class Aclass(): # A inherits from object.
        def f_1(self):
            return "A:f()"

        def g_1(self):
            return "A:g()"

    class Bclass(Aclass):      #B can override A's methods
        def g_1(self):
            return "B:g()"

    a_1 = Aclass()
    assert "A:f()" == a_1.f_1()
    assert "A:g()" == a_1.g_1()

    b_1 = Bclass()
    assert "A:f()" == b_1.f_1()
    assert "B:g()" == b_1.g_1()

def test_inheritance_init():
    class Aclass():
        def __init__(self):
            self.a1 = []

        def append(self, obj):
            pass

    class Bclass(Aclass):
        def __init__(self):
            self.b1 = []

    a_1 = Aclass()
    assert [] == getattr(a_1, "a1", None)
    assert None is getattr(a_1, "b1", None)

    b_1 = Bclass()
    assert None is getattr(b_1, "a1", None)
    assert [] == getattr(b_1, "b1", None)

    try:
        b_1.append("orange")
    except NameError:  #what happened here?
        pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    #lets redefine B now, to chain the inits to the base class.
    class Bclass(Aclass):
        def __init__(self):
            Aclass.__init__(self)
            self.b1 = "b1"

    b_1 = Bclass()
    assert [] == getattr(b_1, "a1", None)
    assert "b1" == getattr(b_1, "b1", None)
    b_1.append("orange")
    assert [] == b_1.a1

def test_inheritance_invoking_using_super():
    #super can be used instead of explicitly invoking base.
    class Aclass(): # A inherits from object.
        def f_1(self):
            return "A:f()"

        def g_1(self):
            return "A:g()"

    class Bclass(Aclass):      #B can override A's methods
        def g_1(self):
            return super(Bclass, self).g_1() + ":"+ "B:g()"

    b_1 = Bclass()
    assert "A:g():B:g()" == b_1.g_1()


NOTES_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

THREE_THINGS_I_LEARNT = """
-
-
-
"""

TIME_TAKEN_MINUTES = 1
