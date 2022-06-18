"""Code Author"""
__author__ = 'Hari'

from tasks.placeholders import __author__

NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using inheritance in Python.
'''

def test_inheritance_basic():
    """Inheritance"""
    class Aclass(): # A inherits from object.
        """Class A"""
        def fun(self):
            """Function f"""

    class Bclass(Aclass):      #B inherits from A or B derives A
        """Class B"""
        def gun(self):
            """Function g"""

    assert True is issubclass(Aclass, object)
    assert True is issubclass(Aclass, Aclass)
    assert False is issubclass(Aclass, Bclass)

    assert True is issubclass(Bclass, Aclass)
    assert True is issubclass(Bclass, Bclass)
    assert True is issubclass(Bclass, object)

# base class methods are available for derived class objects
def test_inheritance_methods():
    """Inheritance methods"""
    class Aclass(): # A inherits from object.
        """Class A"""
        def fun(self):
            """function f"""
            return "A:f()"

    class Bclass(Aclass):      #B inherits A's behavior (attributes)
        """Class B"""
        def gun(self):
            """Function g"""
            return "B:g()"

    vari = Bclass()
    assert "A:f()" == vari.fun()
    assert "B:g()" == vari.gun()

    var = Aclass()
    assert "A:f()" == var.fun()
    try:
        assert "AttributeError" == var.gun()
    except AttributeError as e_x :
        print(e_x)

def test_inheritance_overrides():
    """Inheritance overrides"""
    class Aclass(): # A inherits from object.
        """Class A"""
        def fun(self):
            """Function f"""
            return "A:f()"

        def gun(self):
            """Function g"""
            return "A:g()"

    class Bclass(Aclass):      #B can override A's methods
        """Class B"""
        def gun(self):
            """Function g"""
            return "B:g()"

    var = Aclass()
    assert "A:f()" == var.fun()
    assert "A:g()" == var.gun()

    vari = Bclass()
    assert "A:f()" == vari.fun()
    assert "B:g()" == vari.gun()

def test_inheritance_init():
    """Inheritance init"""
    class Aclass():
        """Class A"""
        def __init__(self):
            """init"""
            self.a_1 = []

        def append(self, obj):
            """Append"""
            self.a_1.append(obj)

    class Bclass(Aclass):
        """Class B"""
        def __init__(self):
            """init"""
            self.b_1 = []

    var = Aclass()
    assert [] == getattr(var, "a_1", None)
    assert None is getattr(var, "b_1", None)

    vari = Bclass()
    assert None is getattr(vari, "a_1", None)
    assert [] == getattr(vari, "b_1", None)

    try:
        vari.append("orange")
    except AttributeError :  #what happened here?
        pass

    # Since methods of A depend on init being called, we must always
    # chain __init__ to the base class if the derived class overrides it.

    #lets redefine B now, to chain the inits to the base class.
    class Bclass(Aclass):
        """Class B"""
        def __init__(self):
            """init"""
            Aclass.__init__(self)
            self.b_1 = "b_1"

    vari = Bclass()
    assert [] == getattr(vari, "a_1", None)
    assert "b_1" == getattr(vari, "b_1", None)
    vari.append("orange")
    assert ["orange"] == vari.a_1

def test_inheritance_invoking_using_super():
    """inheritance invoking using super"""
    #super can be used instead of explicitly invoking base.
    class Aclass(): # A inherits from object.
        """Class A"""
        def fun(self):
            """Function f"""
            return "A:f()"

        def gun(self):
            """Function g"""
            return "A:g()"

    class Bclass(Aclass):      #B can override A's methods
        """Class B"""
        def gun(self):
            """Function g"""
            return super(Bclass, self).gun() + ":"+ "B:g()"

    vari = Bclass()
    assert "A:g():B:g()" == vari.gun()


NOTES_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

THREE_THINGS_I_LEARNT = """
-Inheritance
-Overrides
-Super
"""

TIME_TAKEN_MINUTES = 5
