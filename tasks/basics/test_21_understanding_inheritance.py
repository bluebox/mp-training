# from Tasks.placeholders import *
__author__ = 'Hari'

NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''


def test_inheritance_basic():
    class Aclass():  # A inherits from object.
        def _f(self):
            pass

    class Bclass(Aclass):  # B inherits from A or B derives A
        def _g(self):
            pass

    assert True is issubclass(Aclass, object)
    assert True is issubclass(Aclass, Aclass)
    assert False is issubclass(Aclass, Bclass)

    assert True is issubclass(Bclass, Aclass)
    assert True is issubclass(Bclass, Bclass)
    assert True is issubclass(Bclass, object)

# base class methods are available for derived class objects


def test_inheritance_methods():
    class Aclass():  # A inherits from object.
        def _f(self):
            return "A:f()"

    class Bclass(Aclass):  # B inherits A's behavior (attributes)
        def _g(self):
            return "B:g()"

    _b = Bclass()
    assert "A:f()" == _b._f()
    assert "B:g()" == _b._g()

    _a = Aclass()
    assert "A:f()" == _a._f()
    try:
        assert "error" == _a._g()
    except AttributeError:
        # print ex  #uncomment this line after filling up
        pass


def test_inheritance_overrides():
    class Aclass():  # A inherits from object.
        def f(self):
            return "A:f()"

        def g(self):
            return "A:g()"

    class Bclass(Aclass):  # B can override A's methods
        def g(self):
            return "B:g()"

    _a = Aclass()
    assert "A:f()" == _a.f()
    assert "A:g()" == _a.g()

    _b = Bclass()
    assert "A:f()" == _b.f()
    assert "B:g()" == _b.g()


def test_inheritance_init():
    class Aclass():
        def __init__(self):
            self._a1 = []

        def append(self, obj):
            self._a1.append(obj)

    class Bclass(Aclass):
        def __init__(self):
            self._b1 = []

    _a = Aclass()
    assert not getattr(_a, "_a1", None)
    assert None is getattr(_a, "_b1", None)

    _b = Bclass()
    assert None is getattr(_b, "_a1", None)
    assert not getattr(_b, "_b1", None)

    try:
        _b.append("orange")
    except AttributeError:  # what happened here?
        pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    # lets redefine B now, to chain the inits to the base class.
    class Bclass1(Aclass):
        def __init__(self):
            Aclass.__init__(self)
            self._b1 = "b1"

    _b = Bclass1()
    assert not getattr(_b, "_a1", None)
    assert "b1" == getattr(_b, "_b1", None)
    _b.append("orange")
    assert ["orange"] == _b._a1


def test_inheritance_invoking_using_super():
    # super can be used instead of explicitly invoking base.
    class Aclass():  # A inherits from object.
        def f(self):
            return "A:f()"

        def g(self):
            return "A:g()"

    class Bclass(Aclass):  # B can override A's methods
        def g(self):
            return super().g() + ":" + "B:g()"

    _b = Bclass()
    assert "A:g():B:g()" == _b.g()


NOTES_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

THREE_THINGS_I_LEARNT = """
-
-
-
"""

TIME_TAKEN_MINUTES = 40
