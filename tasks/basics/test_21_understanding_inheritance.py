__author__ = 'Hari'
from tasks.placeholders import __author__

NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''




def test_inheritance_basic():
    class ClsA(object): # A inherits from object.
        def mtd_f(self):
            pass

    class ClsB(ClsA):      #B inherits from A or B derives A
        def mtd_g(self):
            pass

    assert True is issubclass(ClsA, object)
    assert True is issubclass(ClsA, ClsA)
    assert False is issubclass(ClsA, ClsB)

    assert True is issubclass(ClsB, ClsA)
    assert True is issubclass(ClsB, ClsB)
    assert True is issubclass(ClsB, object)

# base class methods are available for derived class objects
def test_inheritance_methods():
    class ClsA(object): # A inherits from object.
        def mtd_f(self):
            return "A:f()"

    class ClsB(ClsA):      #B inherits A's behavior (attributes)
        def mtd_g(self):
            return "B:g()"

    var_b = ClsB()
    assert 'A:f()' == var_b.mtd_f()
    assert 'B:g()' == var_b.mtd_g()

    var_a = ClsA()
    assert 'A:f()' == var_a.mtd_f()
    try:
        assert var_b.l() == var_a.g()
    except AttributeError:
        #print ex  #uncomment this line after filling up
        pass

def test_inheritance_overrides():
    class ClsA(object): # A inherits from object.
        def mtd_f(self):
            return "A:f()"

        def mtd_g(self):
            return "A:g()"

    class ClsB(ClsA):      #B can override A's methods
        def mtd_g(self):
            return "B:g()"

    var_a = ClsA()
    assert 'A:f()' == var_a.mtd_f()
    assert 'A:g()' == var_a.mtd_g()

    var_b = ClsB()
    assert 'A:f()' == var_b.mtd_f()
    assert 'B:g()' == var_b.mtd_g()

def test_inheritance_init():
    class ClsA(object):
        def __init__(self):
            self.var_a1 = []

        def append(self, obj):
            self.var_a1.append(obj)

    class ClsB(ClsA):
        def __init__(self):
            self.var_b1 = []

    var_a = ClsA()
    assert [] == getattr(var_a, "var_a1", None)
    assert None is getattr(var_a, "var_b1", None)

    var_b = ClsB()
    assert None is getattr(var_b, "var_a1", None)
    assert [] == getattr(var_b, "var_b1", None)

    try:
        var_b.append("orange")
    except AttributeError :  #what happened here?
        pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    #lets redefine B now, to chain the inits to the base class.
    class ClsB(ClsA):
        def __init__(self):
            ClsA.__init__(self)
            self.var_b1 = "var_b1"

    var_b = ClsB()
    assert [] == getattr(var_b, "var_a1", None)
    assert 'var_b1' == getattr(var_b, "var_b1", None)
    var_b.append("orange")
    assert ['orange'] == var_b.var_a1

def test_inheritance_invoking_using_super():
    #super can be used instead of explicitly invoking base.
    class ClsA(object): # A inherits from object.
        def mtd_f(self):
            return "A:f()"

        def mtd_g(self):
            return "A:g()"

    class ClsB(ClsA):      #B can override A's methods
        def mtd_g(self):
            return super(ClsB, self).mtd_g() + ":"+ "B:g()"

    var_b = ClsB()
    assert 'A:g():B:g()' == var_b.mtd_g()


NOTES2 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

THREE_THINGS_I_LEARNT = """
-child methods are not accesseble to parents
-parent methods can be accessed by child
-super keyword gives parent class
"""
NOT="""HJGUJGF"""
NOT1='''KJDFATERJKH'''
NOT3="""HDSGTRFIUWEFRHFERUHTIT"""
OT="""HJGUJGF"""
NOT1='''KJDFATERJKH'''
NOT3="""HDSGTRFIUWEFRHFERUHTIT"""
OT="""HJGUJGF"""
NOT1='''KJDFATERJKH'''
NOT3="""HDSGTRFIUWEFRHFERUHTIT"""
OT="""HJGUJGF"""
NOT1='''KJDFATERJKH'''
NOT3="""HDSGTRFIUWEFRHFERUHTI"""
OT="""HJGUJGF"""
NOT1='''KJDFATERJKH'''
NOT3="""HDSGTRFIUWEFRHFERUHTIT"""
OT="""HJGUJGF"""
NOT1='''KJDFATERJKH'''
NOT3="""HDSGTRFIUWEFRHFERUHTIT"""
OT="""HJGUJGF"""
NOT1='''KJDFATERJKH'''
NOT3="""HDSGTRFIUWEFRHFERUHTIT  """
TIME_TAKEN_IN_MINUTES = 35