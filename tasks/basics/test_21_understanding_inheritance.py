"""understanding inheritance"""

__author__ = 'Hari'

from tasks.placeholders import __author__

NOTES = '''
 Inheritance is another standard feature of object oriented programming.
 This exercise illustrates the syntax and language features for using 
 inheritance in Python.
'''

def test_inheritance_basic():
    """Inheritance"""
    class Aclass(): # A inherits from object.
        """Class A"""
        def func(self):
            """Function f"""

    class Bclass(Aclass):      #B inherits from A or B derives A
        """Class B"""
        def gunc(self):
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
        def func(self):
            """function f"""
            return "A:f()"

    class Bclass(Aclass):      #B inherits A's behavior (attributes)
        """Class B"""
        def gunc(self):
            """Function g"""
            return "B:g()"

    temp = Bclass()
    assert "A:f()" == temp.func()
    assert "B:g()" == temp.gunc()

    var = Aclass()
    assert "A:f()" == var.func()
    try:
        assert "AttributeError" == var.gunc()
    except AttributeError as e_x :
        print(e_x)

def test_inheritance_overrides():
    """Inheritance overrides"""
    class Aclass(): # A inherits from object.
        """Class A"""
        def func(self):
            """Function f"""
            return "A:f()"

        def gunc(self):
            """Function g"""
            return "A:g()"

    class Bclass(Aclass):      #B can override A's methods
        """Class B"""
        def gunc(self):
            """Function g"""
            return "B:g()"

    var = Aclass()
    assert "A:f()" == var.func()
    assert "A:g()" == var.gunc()

    temp = Bclass()
    assert "A:f()" == temp.func()
    assert "B:g()" == temp.gunc()

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

    temp = Bclass()
    assert None is getattr(temp, "a_1", None)
    assert [] == getattr(temp, "b_1", None)

    try:
        temp.append("orange")
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

    temp = Bclass()
    assert [] == getattr(temp, "a_1", None)
    assert "b_1" == getattr(temp, "b_1", None)
    temp.append("orange")
    assert ["orange"] == temp.a_1

def test_inheritance_invoking_using_super():
    """inheritance invoking using super"""
    #super can be used instead of explicitly invoking base.
    class Aclass(): # A inherits from object.
        """Class A"""
        def func(self):
            """Function f"""
            return "A:f()"

        def gunc(self):
            """Function g"""
            return "A:g()"

    class Bclass(Aclass):      #B can override A's methods
        """Class B"""
        def gunc(self):
            """Function g"""
            return super(Bclass, self).gunc() + ":" + "B:g()"

    temp = Bclass()
    assert "A:g():B:g()" == temp.gunc()


NOTES_1 = '''
 Inheritance if one of the most abused features of object oriented programming especially by novices.
 Think carefully before using it :). We will cover usage in assignments.
'''

THREE_THINGS_I_LEARNT = """
- inheritance
- overriding parent class' method
- invoking parent class' method after overriding it using 'super' keyword
"""

TIME_TAKEN_MINUTES = 20
