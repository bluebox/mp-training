'''Inheritance'''

__author__ = 'Hari'

NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''

# from tasks.placeholders import *

def test_inheritance_basic():
    '''Inheritance'''
    class Aa(): # A inherits from object.
        '''Inheritance'''
        def f_1(self):
            '''Inheritance'''

    class Bb(Aa):      #B inherits from A or B derives A
        '''Inheritance'''
        def g_1(self):
            '''Inheritance'''

    assert issubclass(Aa, object) is True
    assert issubclass(Aa, Aa) is True
    assert issubclass(Aa, Bb) is False

    assert issubclass(Bb, Aa) is True
    assert issubclass(Bb, Bb) is True
    assert issubclass(Bb, object) is True

# base class methods are available for derived class objects
def test_inheritance_methods():
    '''Inheritance'''
    class Ab(): # A inherits from object.
        '''Inheritance'''
        def f_1(self):
            '''Inheritance'''
            return "A:f()"

    class Bb(Ab):      #B inherits A's behavior (attributes)
        '''Inheritance'''
        def g_1(self):
            '''Inheritance'''
            return "B:g()"

    b_1 = Bb()
    assert b_1.f_1() == "A:f()"
    assert b_1.g_1() == "B:g()"

    a_1 = Ab()
    assert a_1.f_1() == "A:f()"
    try:
        assert a_1.g_1() is None
    except AttributeError:
        #print ex  #uncomment this line after filling up
        pass

def test_inheritance_overrides():
    '''Inheritance'''
    class Aa(): # A inherits from object.
        '''Inheritance'''
        def f_1(self):
            '''Inheritance'''
            return "A:f()"

        def g_1(self):
            '''Inheritance'''
            return "A:g()"

    class Bb(Aa):      #B can override A's methods
        '''Inheritance'''
        def g_1(self):
            '''Inheritance'''
            return "B:g()"

    a_1 = Aa()
    assert a_1.f_1() == "A:f()"
    assert a_1.g_1() == "A:g()"

    b_1 = Bb()
    assert b_1.f_1() == "A:f()"
    assert b_1.g_1() == "B:g()"

def test_inheritance_init():
    '''Inheritance'''
    class Aa():
        '''Inheritance'''
        def __init__(self):
            self.a_1 = []

        def append(self, obj):
            '''Inheritance'''
            self.a_1.append(obj)

    class Bb(Aa):
        '''Inheritance'''
        def __init__(self):
            '''Inheritance'''
            self.b_1 = []

    a_1 = Aa()
    assert getattr(a_1, "a1", None) is None
    assert getattr(a_1, "b1", None) is None

    b_1 = Bb()
    assert  getattr(b_1, "a1", None) is None
    assert getattr(b_1, "b1", None) is None

    try:
        b_1.append("orange")
    except AttributeError:  #what happened here?
        pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    #lets redefine B now, to chain the inits to the base class.
    class Bb(Aa):
        '''Inheritance'''
        def __init__(self):
            '''Inheritance'''
            Aa.__init__(self)
            self.b_1 = "b1"

    b_1 = Bb()
    assert getattr(b_1, "a1", None) is None
    assert getattr(b_1, "b1", None) is None
    b_1.append("orange")
    assert b_1.a_1 == ['orange']

def test_inheritance_invoking_using_super():
    '''Inheritance'''
    #super can be used instead of explicitly invoking base.
    class Aa(): # A inherits from object.
        '''Inheritance'''
        def f_1(self):
            '''Inheritance'''
            return "A:f()"

        def g_1(self):
            '''Inheritance'''
            return "A:g()"

    class Bb(Aa):      #B can override A's methods
        '''Inheritance'''

        def g_1(self):
            '''Inheritance'''
            return super(Bb, self).g_1() + ":"+ "B:g()"

    b_1 = Bb()
    assert b_1.g_1() == "A:g():B:g()"


NOTES_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

THREE_THINGS_I_LEARNT = """
- Inheritance enables us to define a class that takes all the functionality from a parent class and allows us to add more
- The new class is called derived (or child) class and the one from which it inherits is called the base (or parent) class.
- Derived class inherits features from the base class where new features can be added to it. This results in re-usability of code.
"""

TIME_TAKEN_MINUTES = 12
