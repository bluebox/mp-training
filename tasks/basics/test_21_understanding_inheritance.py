'''program file'''
__author__ = 'Hari'
NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''
#from tasks.placeholders import *
def test_inheritance_basic():
    '''function'''
    class AaA(object):
        ''' AaA inherits from object.'''
        def f_1(self):
            '''function'''

    class BbB(AaA):
        '''BbB inherits from AaA or BbB derives AaA'''
        def g_1(self):
            '''function'''

    assert  True is issubclass(AaA, object)
    assert True is issubclass(AaA, AaA)
    assert False is issubclass(AaA, BbB)
    assert True is issubclass(BbB, AaA)
    assert True is issubclass(BbB, BbB)
    assert True is issubclass(BbB, object)

# base class methods are available for derived class objects
'''class'''
def test_inheritance_methods():
    '''function'''
    class AaA(object):
        ''' AaA inherits from object.'''
        def f_1(self):
            '''function'''
            return "AaA:f_1()"

    class BbB(AaA):
        '''BbB inherits AaA's behavior (attributes)'''
        def g_1(self):
            '''function'''
            return "BbB:g_1()"

    k_1 = BbB()
    assert "AaA:f_1()" == k_1.f_1()
    assert "BbB:g_1()" == k_1.g_1()
    a_2 = AaA()
    assert "AaA:f_1()"== a_2.f_1()
    try:
        assert "" == a_2.g_1()
    except AttributeError:
        #print ex  #uncomment this line after filling up
        pass

def test_inheritance_overrides():
    '''function'''
    class AaA(object):
        """ AaA inherits from object."""
        def f_1(self):
            '''function'''
            return "AaA:f_1()"

        def g_1(self):
            '''function'''
            return "AaA:g_1()"

    class BbB(AaA):
        """BbB can override AaA's method"""
        def g_1(self):
            '''function'''
            return "BbB:g_1()"
    a_2 = AaA()
    assert "AaA:f_1()" == a_2.f_1()
    assert "AaA:g_1()" == a_2.g_1()
    k_1 = BbB()
    assert "AaA:f_1()" == k_1.f_1()
    assert "BbB:g_1()" == k_1.g_1()

def test_inheritance_init():
    '''function'''
    class AaA(object):
        '''class'''
        def __init__(self):
            '''function'''
            self.a_1 = []

        def append(self, obj):
            '''function'''
            self.a_1.append(obj)

    class BbB(AaA):
        '''class'''
        def __init__(self):
            '''function'''
            self.b_1 = []
    a_2 = AaA()
    assert not getattr(a_2, "a_1", None)
    assert None is getattr(a_2, "b_1", None)
    k_1 = BbB()
    assert None is getattr(k_1, "a_1", None)
    assert not getattr(k_1, "b_1", None)
    try:
        k_1.append("orange")
    except AttributeError:  #what happened here?
        pass

    # Since methods of AaA depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.
    #lets redefine BbB now, to chain the inits to the base class
    class BbB(AaA):
        '''class'''
        def __init__(self):
            '''function'''
            AaA.__init__(self)
            self.b_2 = "b_1"

    k_1 = BbB()
    assert not getattr(k_1, "a_1", None)
    assert "b_1" == getattr(k_1, "b_1", None)
    k_1.append("orange")
    assert ["orange"] == k_1.a_1

def test_inheritance_invoking_using_super():
    '''super can be used instead of explicitly invoking base'''
    class AaA:
        ''' AaA inherits from object.'''
        def f_1(self):
            '''function'''
            return "AaA:f_1()"

        def g_1(self):
            '''function'''
            return "AaA:g_1()"

    class BbB(AaA):
        '''BbB can override AaA's methods'''
        def g_1(self):
            '''function'''
            return super(BbB, self).g_1() + ":"+ "BbB:g_1()"

    k_1 = BbB()
    assert "AaA:g_1():BbB:g_1()" == k_1.g_1()

NOTES_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

THREE_THINGS_I_LEARNT = """
-
-
-
"""

TIME_TAKEN_MINUTES = 60
