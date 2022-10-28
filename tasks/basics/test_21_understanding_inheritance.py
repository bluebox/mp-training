"""Exercise 21 python basics"""
__author__ = 'Hari'

# from tkinter.messagebox import NO
# from tasks.placeholders import *

NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using 
 inheritance in Python.
'''

def test_inheritance_basic():
    """basic docstring for pylint testing"""
    class ClassA():
        """basic docstring for pylint testing"""

        # A inherits from object.
        def obj_f(self):
            """basic docstring for pylint testing"""
#            pass

    class ClassB(ClassA):
        """basic docstring for pylint testing"""      #B inherits from A or B derives A
        def obj_g(self):
            """basic docstring for pylint testing"""
#            pass

    assert True is issubclass(ClassA, object)
    assert True is issubclass(ClassA, ClassA)
    assert False is issubclass(ClassA, ClassB)
    assert True is issubclass(ClassB, ClassA)
    assert True is issubclass(ClassB, ClassB)
    assert True is issubclass(ClassB, object)

# base class methods are available for derived class objects
def test_inheritance_methods():
    """basic docstring for pylint testing"""
    class ClassA():
        """basic docstring for pylint testing"""
        # A inherits from object.
        def obj_f(self):
            """basic docstring for pylint testing"""
            return "A:f()"
    class ClassB(ClassA):
        """basic docstring for pylint testing"""      #B inherits A's behavior (attributes)
        def obj_g(self):
            """basic docstring for pylint testing"""
            return "B:g()"
    ins_b = ClassB()
    assert "A:f()" == ins_b.obj_f()
    assert "B:g()" == ins_b.obj_g()
    ins_a = ClassA()
    assert "A:f()" == ins_a.obj_f()
    try:
        assert "B:g()" == ins_a.obj_g()
    except AttributeError:
        #print ex  #uncomment this line after filling up
        pass

def test_inheritance_overrides():
    """basic docstring for pylint testing"""
    class ClassA():
        """basic docstring for pylint testing""" # A inherits from object.
        def obj_f(self):
            """basic docstring for pylint testing"""
            return "A:f()"

        def obj_g(self):
            """basic docstring for pylint testing"""
            return "A:g()"
    class ClassB(ClassA):
        """basic docstring for pylint testing"""
        #B can override A's methods
        def obj_g(self):
            """basic docstring for pylint testing"""
            return "B:g()"

    ins_a = ClassA()
    assert "A:f()" == ins_a.obj_f()
    assert "A:g()" == ins_a.obj_g()

    ins_b = ClassB()
    assert "A:f()" == ins_b.obj_f()
    assert "B:g()" == ins_b.obj_g()

def test_inheritance_init():
    """basic docstring for pylint testing"""
    class ClassA():
        """basic docstring for pylint testing"""
        def __init__(self):
            """basic docstring for pylint testing"""
            self.ans = []

        def append(self, obj):
            """basic docstring for pylint testing"""
            self.ans.append(obj)

    class ClassB(ClassA):
        """basic docstring for pylint testing"""
        def __init__(self):
            """basic docstring for pylint testing"""
            self.bns = []

    ins_a = ClassA()
    assert [] == getattr(ins_a, "ans", None)
    assert None is getattr(ins_a, "bns", None)

    ins_b = ClassB()
    assert None is getattr(ins_b, "ans", None)
    assert [] == getattr(ins_b, "bns", None)

    try:
        ins_b.append("orange")
    except AttributeError:
        """basic docstring for pylint testing"""
        print("AttributeError")
        #what happened here?
        # pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    #lets redefine B now, to chain the inits to the base class.
    class ClassB(ClassA):
        """basic docstring for pylint testing"""
        def __init__(self):
            ClassA.__init__(self)
            self.bns = "bns"

    ins_b = ClassB()
    assert [] == getattr(ins_b, "ans", None)
    assert "bns" == getattr(ins_b, "bns", None)
    ins_b.append("orange")
    assert ["orange"] == ins_b.ans

def test_inheritance_invoking_using_super():
    """basic docstring for pylint testing"""
    #super can be used instead of explicitly invoking base.
    class ClassA():
        """basic docstring for pylint testing"""
        # A inherits from object.
        def obj_f(self):
            """basic docstring for pylint testing"""
            return "A:f()"

        def obj_g(self):
            """basic docstring for pylint testing"""
            return "A:g()"

    class ClassB(ClassA):
        """basic docstring for pylint testing"""
        #B can override A's methods
        def obj_g(self):
            """basic docstring for pylint testing"""
            return super(ClassB, self).obj_g() + ":"+ "B:g()"

    ins_b = ClassB()
    assert "A:g():B:g()" == ins_b.obj_g()

NOTES_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''
THREE_THINGS_I_LEARNT = """
classes, inheritance,oops
"""
TIME_TAKEN_MINUTES = 40
