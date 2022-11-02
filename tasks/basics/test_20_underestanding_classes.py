""" understanding what are classes and what is its usage and advantages in python """

__author__ = 'Hari'

NOTES = '''
Python allows users to add user defined types via classes. This allows you to augment
builtin types like dict, list, tuple with your own types with their own specific behavior.

Like most common languages like java and c#, python supports objected oriented features
like class definitions, inheritance and polymorphism.

However, unlike java and c#, python does not insist that you have to forcibly model your
problem domain as classes if it does not make sense. You could use any mix of modules,
functions and classes to model your application. For e.g. if you goal is to code up the
fibonacci function or write a routine that sorts a sequence then defining a class
does not make sense.

This assignment only deals with the syntax of classes and its features. You must
look up references to actually learn object oriented programming.

http://c2.com/cgi/wiki?AlanKaysDefinitionOfObjectOriented

https://www.pythontutorial.net/python-oop/python-type-class/

'''

from tasks.placeholders import *

NOTES_1 = '''
 We are defining the classes in the function scope so that we can redefine them for every test.
 Generally you would define them at the module scope.
'''


# classes are objects too, they have a type, have attributes, can be passed
# to functions, held in data structures etc.
def test_classes_are_objects():
    """ classes are Objects """
    """ Checking whether classes are objects or not"""
    class Queue(object):
        """Queue with push and pop functions."""
        pass

    def get_attr_count(obj):
        return len(dir(obj))

    assert 'type' == type(Queue).__name__  # note this.
    assert 'Queue with push and pop functions.' == Queue.__doc__
    assert 26 == get_attr_count(Queue)


def test_classes_are_callable_objects():
    """ Classes are callable objects """
    class Queue(object):
        """ Queue class """
        pass

    # classes are callable objects just like function objects
    assert True == (callable(Queue))


def test_classes_are_object_factories():
    """ classes are object factories i.e they help to create a new object
    up on calling the class """
    class Queue(object):
        """ Queue """
        pass

    q_1 = Queue()  # you can 'call' a class to create an instance
    q_2 = Queue()
    print(type(q1).__class__)
    # assert "<class 'type'>" == type(q1).__class__
    # assert "<class 'type'>" == type(q2).__class__

    assert False == (q1 is Queue)
    assert False == (q2 is Queue)
    assert False == (q2 is q1)

    assert True == (isinstance(q1, Queue))
    assert True == (isinstance(q2, Queue))

    assert 26 == len(dir(Queue))
    assert 26 == len(dir(q1))
    assert 26 == len(dir(q2))


# if an __init__ method exists it is called with the object that is
# being created, so you can initialize it.
def test_classes_init_constructor():
    """ creating a constructor using __init__ method """
    test_list = []

    class Queue(object):
        """ queue class """
        def __init__(self):
            assert True, "Entered here !"
            test_list.append(self)

    q1 = Queue()  # fix the-assert to pass this.
    self_argument = test_list[0]
    assert True == (self_argument is q1)


def test_classes_init_with_args():
    """ creating a constructor with the arguments """
    class Queue(object):
        """ Queue"""
        def __init__(self, name):
            self.name = name

    q1 = Queue("q1")
    q2 = Queue("q2")

    assert 'q1' == q1.name
    assert 'q2' == q2.name

    try:
        q3 = Queue()
    except TypeError:  # what error do you get?
        pass


# just like def, class is also a runtime statement which bounds a class name with the class body code
def test_class_is_an_executable_statement():
    """ class is also an executable statement """
    def create_class(value):
        if value > 10:
            class Queue(object):
                def __init__(self):
                    self.name = ">10queue"
        else:
            class Queue(object):
                def __init__(self):
                    self.name = "<=10queue"

        return Queue

    q_class = create_class(20)
    q1 = q_class()
    assert '>10queue' == q1.name

    queue_class = create_class(5)
    q1 = queue_class()
    assert '<=10queue' == q1.name


# the self argument name is just a convention, but it is
# followed widely, so don't change the name of the first argument
# this is in contrast to other languages where the instance is implicit via
# the 'this' keyword.
def test_classes_methods():
    """ creating a class with some methods """
    class Queue(object):
        """ queue class along with some methods with constructor """
        def __init__(self, name):
            self.name = name
            self._queue = []

        def push(self, obj):
            """ push """
            self._queue.append(obj)

        def pop(self):
            """ pop """
            return self._queue.pop(0)

    q1 = Queue("q1")
    q1.push(10)  # note that we pass only one argument
    assert 10 == q1.pop()

    # above is also equivalent to
    Queue.push(q1, 10)
    assert 10 == Queue.pop(q1)


def test_classes_bound_and_unbound_methods():
    """ bound and unbound methods """
    class Queue(object):
        """ Queue """
        def __init__(self, name):
            self.name = name
            self._queue = []

        def push(self, obj):
            """ push """
            self._queue.append(obj)

        def pop(self):
            """ pop """
            return self._queue.pop(0)

    q1 = Queue("q1")
    q1_push = q1.push

    assert False == (q1.push is Queue.push)

    try:
        Queue.push.__self__   # unbound method
    except AttributeError:
        assert True
    print(q1.push.__self__)
    assert True == bool(q1_push.__self__)      # bound method

    # now understand the output of these 2 statements.
    print(q1.push)
    print(Queue.push)


def test_classes_can_have_state():
    """ classes can have state """
    class Queue(object):
        """ Queue class """
        count = 0
        def __init__(self, name):
            self.name = name
            self._queue = []
            Queue.count += 1

        def push(self, obj):
            """ push method appends element to last in Queue """
            self._queue.append(obj)

        def pop(self):
            """ returns the front i.e 1st element in the queue """
            return self._queue.pop(0)

    assert 0 == Queue.count
    q_1 = Queue("q1")
    assert 1 == Queue.count
    q_2 = Queue("q2")
    assert 2 == Queue.count

    try:
        value = q1.count
    except NameError:
        pass


THREE_THINGS_I_LEARNT = """
- Learned about Objects and classes
- How Objects behave and interact
- Bound and Unbound Methods
"""

TIME_TAKEN_MINUTES = 30
