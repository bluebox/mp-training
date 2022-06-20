from tasks.placeholders import *

__AUTHOR__ = 'Hari'

NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''


def test_inheritance_basic():
    class ClassA(object): # A inherits from object.
        def method_f(self):
            pass

    class ClassB(ClassA):      #B inherits from A or B derives A
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
    class ClassA(object): # A inherits from object.
        def method_f(self):
            return "A:f()"

    class ClassB(ClassA):      #B inherits A's behavior (attributes)
        def method_g(self):
            return "B:g()"

    obj_b = ClassB()
    assert "A:f()" == obj_b.method_f()
    assert "B:g()" == obj_b.method_g()

    obj_a = ClassA()
    assert "A:f()" == obj_a.method_f()
    try:
        assert "A:f()" == obj_a.method_f()
    except TypeError as T_e:
        print(T_e)
        #print ex  #uncomment this line after filling up
        pass

def test_inheritance_overrides():
    class ClassA(object): # A inherits from object.
        def method_f(self):
            return "A:f()"

        def method_g(self):
            return "A:g()"

    class ClassB(ClassA):      #B can override A's methods
        def method_g(self):
            return "B:g()"

    obj_a = ClassA()
    assert "A:f()" == obj_a.method_f()
    assert "A:g()" == obj_a.method_g()

    obj_b = ClassB()
    assert "A:f()" == obj_b.method_f()
    assert "B:g()" == obj_b.method_g()
CONST = 888
def test_inheritance_init():
    class ClassA(object):
        def __init__(self):
            self.a_1 = []

        def append(self, obj):
            self.a_1.append(obj)

    class ClassB(ClassA):
        def __init__(self):
            self.b_1 = []

    obj_a = ClassA()
    assert None is getattr(obj_a, "a1", None)
    assert None is getattr(obj_a, "b1", None)

    obj_b = ClassB()
    assert None is getattr(obj_b, "a1", None)
    assert None is getattr(obj_b, "b1", None)

    try:
        obj_b.append("orange")
    except AttributeError as a_e :  #what happened here?
        print(a_e)
        pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    #lets redefine B now, to chain the inits to the base class.
    class ClassB(ClassA):
        def __init__(self):
            ClassA.__init__(self)
            self.b_1 = "b1"

    obj_b = ClassB()
    assert None is getattr(obj_b, "a1", None)
    assert None is getattr(obj_b, "b1", None)
    obj_b.append("orange")
    assert ['orange'] == obj_b.a_1

def test_inheritance_invoking_using_super():
    #super can be used instead of explicitly invoking base.
    class ClassA(object): # A inherits from object.
        def method_f(self):
            return "A:f()"

        def method_g(self):
            return "A:g()"

    class ClassB(ClassA):      #B can override A's methods
        def method_g(self):
            return super(ClassB, self).method_g() + ":"+ "B:g()"

    obj_b = ClassB()
    assert "A:g():B:g()" == obj_b.method_g()


NOTES_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

THREE_THINGS_I_LEARNT = """
-Inheritance
-using of inheritance
-
"""

TIMETAKENMINUTES = 20