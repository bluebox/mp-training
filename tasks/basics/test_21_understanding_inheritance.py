__author__ = 'Hari'

from tasks.placeholders import *

NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''


def test_inheritance_basic():
    class Aclass(): # A inherits from object.
        def var_f(self):
            pass

    class Bclass(Aclass):      #B inherits from A or B derives A
        def var_g(self):
            pass

    assert True is issubclass(Aclass, object)
    assert True is issubclass(Aclass, Aclass)
    assert False is issubclass(Aclass, Bclass)

    assert True is issubclass(Bclass, Aclass)
    assert True is issubclass(Bclass, Bclass)
    assert True is issubclass(Bclass, object)

# base class methods are available for derived class objects
def test_inheritance_methods():
    class Aclass(): # A inherits from object.
        def var_f(self):
            return "A:f()"

    class Bclass(Aclass):      #B inherits A's behavior (attributes)
        def var_g(self):
            return "B:g()"

    var_b = Bclass()
    assert "A:f()" == var_b.var_f()
    assert "B:g()" == var_b.var_g()

    var_a = Aclass()
    assert "A:f()" == var_a.var_f()
    try:
        assert True is var_a.var_g()
    except AttributeError:
        #print ex  #uncomment this line after filling up
        pass

def test_inheritance_overrides():
    class Aclass(): # A inherits from object.
        def var_f(self):
            return "A:f()"

        def var_g(self):
            return "A:g()"

    class Bclass(Aclass):      #B can override A's methods
        def var_g(self):
            return "B:g()"

    var_a = Aclass()
    assert "A:f()" == var_a.var_f()
    assert "A:g()" == var_a.var_g()

    var_b = Bclass()
    assert "A:f()" == var_b.var_f()
    assert "B:g()" == var_b.var_g()

def test_inheritance_init():
    class Aclass():
        def __init__(self):
            self.a_1 = []

        def append(self, obj):
            self.a_1.append(obj)

    class Bclass(Aclass):
        def __init__(self):
            self.b_1 = []

    var_a = Aclass()
    assert [] == getattr(var_a, "a_1", None)
    assert None is getattr(var_a, "b_1", None)

    var_b = Bclass()
    assert None is getattr(var_b, "a_1", None)
    assert [] == getattr(var_b, "b_1", None)

    try:
        var_b.append("orange")
    except AttributeError :  #what happened here? 'B' object has no attribute 'a1'
        pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    #lets redefine B now, to chain the inits to the base class.
    class Bclass(Aclass):
        def __init__(self):
            Aclass.__init__(self)
            self.b_1 = "b1"

    var_b = Bclass()
    assert [] == getattr(var_b, "a_1", None)
    assert "b1" == getattr(var_b, "b_1", None)
    var_b.append("orange")
    assert ["orange"] == var_b.a_1

def test_inheritance_invoking_using_super():
    #super can be used instead of explicitly invoking base.
    class Aclass(): # A inherits from object.
        def var_f(self):
            return "A:f()"

        def var_g(self):
            return "A:g()"

    class Bclass(Aclass):      #B can override A's methods
        def var_g(self):
            return super(Bclass, self).var_g() + ":"+ "B:g()"

    var_b = Bclass()
    assert "A:g():B:g()" == var_b.var_g()


NOTES_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

THREE_THINGS_I_LEARNT= """
-inheritance
-parent and child classes
-super keyword
"""

TIME_TAKEN_MINUTES = 20
