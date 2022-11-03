__author__ = 'Hari'

notes = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''

from tasks.placeholders import *
""" To learn about inheritance in python """
def test_inheritance_basic():
    """ function to test basics of inheritance """

    class Aa():  # A inherits from object.
        """ parent class """

        def f_1(self):
            """ learning inheritance """

    class Bb(Aa):  # B inherits from A or B derives A
        """ child class """

        def g_1(self):
            """learning Inheritance"""

    assert issubclass(Aa, object) is True
    assert issubclass(Aa, Aa) is True
    assert issubclass(Aa, Bb) is False

    assert issubclass(Bb, Aa) is True
    assert issubclass(Bb, Bb) is True
    assert issubclass(Bb, object) is True


# base class methods are available for derived class objects
def test_inheritance_methods():
    """ function to verify class methods """

    class Ab():  # A inherits from object.
        """learning Inheritance"""

        def f_1(self):
            """function to test self """
            return "A:f()"

    class Bb(Ab):  # B inherits A's behavior (attributes)
        """ class to inherit behaviour """

        def g_1(self):
            """learning Inheritance"""
            return "B:g()"

    b_1 = Bb()
    assert b_1.f_1() == "A:f()"
    assert b_1.g_1() == "B:g()"

    a_1 = Ab()
    assert a_1.f_1() == "A:f()"
    try:
        assert a_1.g_1() is None
    except AttributeError:
        # print ex  #uncomment this line after filling up
        pass


def test_inheritance_overrides():
    """learning Inheritance"""

    class Aa():  # A inherits from object.
        """learning Inheritance"""

        def f_1(self):
            """learning Inheritance"""
            return "A:f()"

        def g_1(self):
            """learning Inheritance"""
            return "A:g()"

    class Bb(Aa):  # B can override A's methods
        """learning Inheritance"""

        def g_1(self):
            """learning Inheritance"""
            return "B:g()"

    a_1 = Aa()
    assert a_1.f_1() == "A:f()"
    assert a_1.g_1() == "A:g()"

    b_1 = Bb()
    assert b_1.f_1() == "A:f()"
    assert b_1.g_1() == "B:g()"


def test_inheritance_init():
    """learning Inheritance"""

    class Aa():
        """learning Inheritance"""

        def __init__(self):
            self.a_1 = []

        def append(self, obj):
            """learning Inheritance"""
            self.a_1.append(obj)

    class Bb(Aa):
        """learning Inheritance"""

        def __init__(self):
            """learning Inheritance"""
            self.b_1 = []

    a_1 = Aa()
    assert getattr(a_1, "a1", None) is None
    assert getattr(a_1, "b1", None) is None

    b_1 = Bb()
    assert getattr(b_1, "a1", None) is None
    assert getattr(b_1, "b1", None) is None

    try:
        b_1.append("orange")
    except AttributeError:  # what happened here?
        pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    # lets redefine B now, to chain the inits to the base class.
    class Bb(Aa):
        """learning Inheritance"""

        def __init__(self):
            """learning Inheritance"""
            Aa.__init__(self)
            self.b_1 = "b1"

    b_1 = Bb()
    assert getattr(b_1, "a1", None) is None
    assert getattr(b_1, "b1", None) is None
    b_1.append("orange")
    assert b_1.a_1 == ['orange']


def test_inheritance_invoking_using_super():
    """learning Inheritance"""

    # super can be used instead of explicitly invoking base.
    class Aa():  # A inherits from object.
        """learning Inheritance"""

        def f_1(self):
            """learning Inheritance"""
            return "A:f()"

        def g_1(self):
            """learning Inheritance"""
            return "A:g()"

    class Bb(Aa):  # B can override A's methods
        """learning Inheritance"""

        def g_1(self):
            """learning Inheritance"""
            return super(Bb, self).g_1() + ":" + "B:g()"

    b_1 = Bb()
    assert b_1.g_1() == "A:g():B:g()"



notes_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

three_things_i_learnt = """
-inheritance concept in python
-uses
-benefits
"""

time_taken_minutes = 70
