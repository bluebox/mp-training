#from Tasks.placeholders import *
__author__ = 'Hari'

NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''


def test_inheritance_basic():
    class ClassA():  # A inherits from object.
        def method_f(self):
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

# base class methods are available for derived class objects


def test_inheritance_methods():
    class ClassA():  # A inherits from object.
        def method_f(self):
            return "A:f()"

    class ClassB(ClassA):  # B inherits A's behavior (attributes)
        def method_g(self):
            return "B:g()"

    _b = ClassB()
    assert "A:f()" == _b.method_f()
    assert "B:g()" == _b.method_g()

    _a = ClassA()
    assert "A:f()" == _a.method_f()
    try:
        assert __ == _a.method_g()
    except AttributeError as _ex:
        print(_ex)  #uncomment this line after filling up
        pass


def test_inheritance_overrides():
    class ClassA():  # A inherits from object.
        def method_f(self):
            return "A:f()"

        def method_g(self):
            return "A:g()"

    class ClassB(ClassA):  # B can override A's methods
        def method_g(self):
            return "B:g()"

    _a = ClassA()
    assert "A:f()" == _a.method_f()
    assert "A:g()" == _a.method_g()

    _b = ClassB()
    assert "A:f()" == _b.method_f()
    assert "B:g()" == _b.method_g()

def test_inheritance_init():
    class ClassA():
        def __init__(self):
            self.a1 = []

        def append(self, obj):
            self.a1.append(obj)

    class ClassB(ClassA):
        def __init__(self):
            self.b1 = []

    _a = ClassA()
    assert [] == getattr(_a, "a1", None)
    assert None == getattr(_a, "b1", None)

    _b = ClassB()
    assert None == getattr(_b, "a1", None)
    assert [] == getattr(_b, "b1", None)

    try:
        _b.append("orange")
    except AttributeError:  # what happened here?
        pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    # lets redefine B now, to chain the inits to the base class.
    class ClassB(ClassA):
        def __init__(self):
            ClassA.__init__(self)
            self.b1 = "b1"

    _b = ClassB()
    assert [] == getattr(_b, "a1", None)
    assert "b1" == getattr(_b, "b1", None)
    _b.append("orange")
    assert ["orange"] == _b.a1

def test_inheritance_invoking_using_super():
    # super can be used instead of explicitly invoking base.
    class ClassA():  # A inherits from object.
        def method_f(self):
            return "A:f()"

        def method_g(self):
            return "A:g()"

    class ClassB(ClassA):  # B can override A's methods
        def method_g(self):
            return super(ClassB, self).method_g() + ":" + "B:g()"

    _b = ClassB()
    assert "A:g():B:g()" == _b.method_g()


NOTES_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

THREE_THINGS_LERNT = """
inheritance of classes
"""

TIME_TAKEN_MINUTES = 120
