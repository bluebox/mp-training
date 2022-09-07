__author__ = 'Hari'

from tasks.placeholders import __author__
NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''

# from re import T

def test_inheritance_basic():
    '''module'''
    class Aa(object): # A inherits from object.
        '''CLASS'''
        def f_1(self):
            '''CLASS'''
            pass

    class Ba(Aa):      #B inherits from A or B derives A
        '''CLASS'''
        def g_1(self):
            '''CLASS'''
            pass

    assert True is issubclass(Aa, object)
    assert True is issubclass(Aa, Aa)
    assert False is issubclass(Aa, Ba)

    assert True is issubclass(Ba, Aa)
    assert True is issubclass(Ba, Ba)
    assert True is issubclass(Ba, object)

# base class methods are available for derived class objects
def test_inheritance_methods():
    '''module'''
    class Aa(object): # A inherits from object.
        '''CLASS'''
        def f_1(self):
            '''CLASS'''
            return "A:f()"

    class Ba(Aa):      #B inherits A's behavior (attributes)
        '''CLASS'''
        def g_1(self):
            '''CLASS'''
            return "B:g()"

    b_1 = Ba()
    assert "A:f()" == b_1.f_1()
    assert "B:g()" == b_1.g_1()

    a_1 = Aa()
    assert "A:f()" == a_1.f_1()
    try:
        assert "B:g()" == a_1.g_1()
    except AttributeError:
        #print ex  #uncomment this line after filling up
        pass

def test_inheritance_overrides():
    '''module'''
    class Aa(object): # A inherits from object.
        '''CLASS'''
        def f_1(self):
            '''CLASS'''
            return "A:f()"

        def g_1(self):
            '''CLASS'''
            return "A:g()"

    class Ba(Aa):      #B can override A's methods
        '''CLASS'''
        def g_1(self):
            '''CLASS'''
            return "B:g()"

    a_1 = Aa()
    assert "A:f()" == a_1.f_1()
    assert "A:g()" == a_1.g_1()

    b_1 = Ba()
    assert "A:f()" == b_1.f_1()
    assert "B:g()" == b_1.g_1()

def test_inheritance_init():
    '''CLASS'''
    class Aa(object):
        '''CLASS'''
        def __init__(self):
            '''CLASS'''
            self.a1 = []

        def append(self, obj):
            '''CLASS'''
            self.a1.append(obj)

    class Ba(Aa):
        '''CLASS'''
        def __init__(self):
            '''CLASS'''
            self.b1 = []

    a_1 = Aa()
    assert [] == getattr(a_1, "a1", None)
    assert None is getattr(a_1, "b1", None)

    b_1 = Ba()
    assert None is getattr(b_1, "a1", None)
    assert [] == getattr(b_1, "b1", None)

    try:
        b_1.append("orange")
    except AttributeError :  #what happened here?
        pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    #lets redefine B now, to chain the inits to the base class.
    class Ba(Aa):
        '''CLASS'''
        def __init__(self):
            '''CLASS'''
            Aa.__init__(self)
            self.b1 = "b1"

    b_1 = Ba()
    assert [] == getattr(b_1, "a1", None)
    assert 'b1' == getattr(b_1, "b1", None)
    b_1.append("orange")
    assert ['orange'] == b_1.a1

def test_inheritance_invoking_using_super():
    '''module'''
    #super can be used instead of explicitly invoking base.
    class Aa(): # A inherits from object.
        '''CLASS'''
        def f_1(self):
            '''CLASS'''
            return "A:f()"

        def g_1(self):
            '''CLASS'''
            return "A:g()"

    class Ba(Aa):      #B can override A's methods
        '''CLASS'''
        def g_1(self):
            '''CLASS'''
            return super(Ba, self).g_1() + ":"+ "B:g()"

    b_1 = Ba()
    assert "A:g():B:g()" == b_1.g_1()


NOTES_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

RESG = """
-
-
-
"""

TTM1 = 25