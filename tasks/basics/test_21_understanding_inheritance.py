"""MODULE TO UNDERSTAND INHERITANCE"""
from tasks.placeholders import *
__author__ = 'Hari'


NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''


def test_inheritance_basic():
    """test inheritance basic"""
    class ClassA:  # A inherits from object.
        """A class"""
        def func_f(self):
            """f function"""

    class ClassB(ClassA):      # B inherits from A or B derives A
        """B Class"""
        def func_g(self):
            """function g"""

    assert True is issubclass(ClassA, object)
    assert True is issubclass(ClassA, ClassA)
    assert False is issubclass(ClassA, ClassB)

    assert True is issubclass(ClassB, ClassA)
    assert True is issubclass(ClassB, ClassB)
    assert True is issubclass(ClassB, object)


# base class methods are available for derived class objects
def test_inheritance_methods():
    """test inheritance methods"""
    class ClassA:  # A inherits from object.
        """class A"""
        def func_f(self):
            """f method"""
            return "A:f()"

    class ClassB(ClassA):      # B inherits A's behavior (attributes)
        """class B inherits A"""
        def func_g(self):
            """g method"""
            return "B:g()"

    var_b = ClassB()
    assert "A.f()" == var_b.func_f()
    assert "B.g()" == var_b.func_g()

    obj_a = ClassA()
    assert "A.f()" == obj_a.func_f()
    try:
        assert "B.g()" == obj_a.g()
    except AttributeError as ex:
        print(ex)


def test_inheritance_overrides():
    """test inheritance overrides"""
    class ClassA(object):  # A inherits from object.
        """Class A"""
        def func_f(self):
            """function f"""
            return "A:f()"

        def func_g(self):
            """function g"""
            return "A:g()"

    class ClassB(ClassA):      # B can override A's methods
        """Class B inherits A"""
        def func_g(self):
            return "B:g()"

    obj_a = ClassA()
    assert "A:f()" == obj_a.func_f()
    assert "A:g()" == obj_a.func_g()

    obj_b = ClassB()
    assert "A:f()" == obj_b.func_f()
    assert "B:g()" == obj_b.func_g()


def test_inheritance_init():
    """test inheritance init"""
    class ClassA(object):
        """A class"""
        def __init__(self):
            """init method"""
            self.list_a1 = []

        def append(self, obj):
            """append method"""
            self.list_a1.append(obj)

    class ClassB(ClassA):
        """class B inherits A"""
        def __init__(self):
            """init method"""
            self.list_b1 = []

    var_a = ClassA()
    assert [] == getattr(var_a, "a1", None)
    assert None is getattr(var_a, "b1", None)

    obj_b = ClassB()
    assert None is getattr(obj_b, "a1", None)
    assert [] == getattr(obj_b, "b1", None)

    try:
        obj_b.append("orange")
    except AttributeError:  # what happened here?
        pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    # lets redefine B now, to chain the inits to the base class.
    class ClassB(ClassA):
        """class B inherits A"""
        def __init__(self):
            """init method"""
            ClassA.__init__(self)
            self.var_b1 = "b1"

    obj_b = ClassB()
    assert [] == getattr(obj_b, "a1", None)
    assert 'b1' == getattr(obj_b, "b1", None)
    obj_b.append("orange")
    assert ['orange'] == obj_b.list_a1


def test_inheritance_invoking_using_super():
    """test inheritance invoking using super"""
    # super can be used instead of explicitly invoking base.
    class ClassA(object):  # A inherits from object.
        """Class A"""
        def func_f(self):
            """function f"""
            return "A:f()"

        def func_g(self):
            """function g"""
            return "A:g()"

    class ClassB(ClassA):      # B can override A's methods
        """B inheriting A"""
        def func_g(self):
            return super(ClassB, self).func_g() + ":" + "B:g()"

    obj_b = ClassB()
    assert "A:g():B:g()" == obj_b.func_g()


NOTES_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

THREE_THINGS_I_LEARNT = """
-Classes and objects
-Inheritance
-object initialization
"""

TIME_TAKEN_MINUTES = 13
