""" Understanding How inheritance works and what is the use of it and how it imporves code
reusability and may more """

__author__ = 'Hari'

NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''


from tasks.placeholders import *


def test_inheritance_basic():
    """ Inheritance basic """
    class Aclass(object):  # A will inherits from object.
        """ class A """
        def func(self):
            """ function  f """
            pass

    class Bclass(Aclass):      # B inherits from A or B derives A
        """ class B """
        def gunc(self):
            """ fucntion g """
            pass

    assert True == issubclass(Aclass, object)
    assert True == issubclass(Aclass, Aclass)
    assert False == issubclass(Aclass, Bclass)

    assert True == issubclass(Bclass, Aclass)
    assert True == issubclass(Bclass, Bclass)
    assert True == issubclass(Bclass, object)


# base class methods are available for derived class objects
def test_inheritance_methods():
    """ inheritance methods """
    class Aclass(object):  # A will inherits from object.
        """ class A """
        def func(self):
            """ function f """
            return "A:f()"

    class Bclass(Aclass):      # B inherits A's behavior (attributes)
        """ class B """
        def gunc(self):
            """ function g """
            return "B:g()"

    temp = B()
    assert 'A:f()' == temp.func()
    assert 'B:g()' == temp.gunc()

    var = Aclass()
    assert 'A:f()' == var.func()
    try:
        assert None == var.gunc()
    except AttributeError as ex:
        print(ex)  # uncomment this line after filling up
        pass


def test_inheritance_overrides():
    """ Inheritance overriding """
    class Aclass(object): # A will inherits from object.
        """ class A """
        def func(self):
            """ function f """
            return "A:f()"

        def gunc(self):
            """ function g """
            return "A:g()"

    class Bclass(Aclass):      # B can override A's methods
        """ class B """
        def gunc(self):
            """ function g """
            return "B:g()"

    var = Aclass()
    assert 'A:f()' == var.func()
    assert 'A:g()' == var.gunc()

    temp = Bclass()
    assert 'A:f()' == temp.func()
    assert 'B:g()' == temp.gunc()


def test_inheritance_init():
    """ inheritance init """
    class Aclass(object):
        """ class A """
        def __init__(self):
            """ init """
            self.a1 = []

        def append(self, obj):
            """ append """
            self.a1.append(obj)

    class Bclass(Aclass):
        def __init__(self):
            """ init """
            self.b1 = []

    var = Aclass()
    assert [] == getattr(var, "a_1", None)
    assert None == getattr(var, "b_1", None)

    temp = Bclass()
    assert None == getattr(temp, "a_1", None)
    assert [] == getattr(temp, "b_1", None)

    try:
        temp.append("orange")
    except AttributeError:  # what happened here?
        pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    # let's redefine B now, to chain init to the base class.
    class Bclass(Aclass):
        """ class B """
        def __init__(self):
            """ init 1 """
            A.__init__(self) 
            self.b1 = "b_1"

    temp = Bclass()
    assert [] == getattr(temp, "a_1", None)
    assert 'b_1' == getattr(temp, "b_1", None)
    temp.append("orange")
    assert ['orange'] == temp.a_1


def test_inheritance_invoking_using_super():
    """ inheritance invoking using super """
    # super can be used instead of explicitly invoking base.
    class Aclass(object):  # A will inherits from object.
        """ class A """
        def func(self):
            """ function f """
            return "A:f()"

        def g(self):
            """ function g """
            return "A:g()"

    class Bclass(Aclass):      # B can override A's methods
        def gunc(self):
            """ function g """
            return super(Bclass, self).gunc() + ":" + "B:g()"

    temp = Bclass()
    assert 'A:g():B:g()' == temp.g()


NOTES_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

THREE_THINGS_I_LEARNT = """
- Inheritance and it's working
- Derived class cannot use Base class's method unless super keyword is used
- Inheritance syntax
"""

TIME_TAKEN_MINUTES = 30
