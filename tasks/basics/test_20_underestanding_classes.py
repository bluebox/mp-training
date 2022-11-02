"""MODULE TO UNDERSTAND CLASSES"""
__author__ = 'Hari'

from tasks.placeholders import *

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

NOTES_1 = '''
 We are defining the classes in the function scope so that we can redefine them for every test.
 Generally you would define them at the module scope.
'''


# classes are objects too, they have a type, have attributes, can be passed
# to functions, held in data structures etc.
def test_classes_are_objects():
    """test classes are objects"""
    class Queue:
        """Queue with push and pop functions."""
        pass

    def get_attr_count(obj):
        """get attribute count"""
        return len(dir(obj))

    assert 'type' == type(Queue).__name__  # note this.
    assert 'Queue with push and pop functions.' == Queue.__doc__
    assert 26 == get_attr_count(Queue)


def test_classes_are_callable_objects():
    """
    test classes are callable objects
    :return:None
    """

    class Queue:
        pass

    # classes are callable objects just like function objects
    assert True is callable(Queue)


def test_classes_are_object_factories():
    """
    test classes are object factories
    :return: None
    """

    class Queue(object):
        pass

    obj_q1 = Queue()  # you can 'call' a class to create an instance
    obj_q2 = Queue()

    assert Queue.__class__ == type(obj_q1).__class__
    assert Queue.__class__ == type(obj_q2).__class__

    assert False is (obj_q1 is Queue)
    assert False is (obj_q2 is Queue)
    assert False is (obj_q2 is obj_q1)

    assert True is isinstance(obj_q1, Queue)
    assert True is isinstance(obj_q2, Queue)

    assert 26 == len(dir(Queue))
    assert 26 == len(dir(obj_q1))
    assert 26 == len(dir(obj_q2))


# if an __init__ method exists it is called with the object that is
# being created, so you can initialize it.
def test_classes_init_constructor():
    """
    test classses init constructor
    :return: None
    """
    test_list = []

    class Queue:
        """Queue"""
        def __init__(self):
            """__init__ function"""
            assert self, "Entered here !"
            test_list.append(self)

    q1 = Queue()  # fix the assert to pass this.
    self_argument = test_list[0]
    assert True == (self_argument is q1)


def test_classes_init_with_args():
    """
    test classes init with args
    :return: None
    """
    class Queue:
        """Queue"""
        def __init__(self, name):
            """__init__"""
            self.name = name

    obj_q1 = Queue("obj_q1")
    obj_q2 = Queue("obj_q2")

    assert 'obj_q1' == obj_q1.name
    assert 'obj_q2' == obj_q2.name

    try:
        queue_q3 = Queue()
    except TypeError:  # what error do you get?
        pass


# just like def, class is also a runtime statement
# which bounds a class name with the class body code
def test_class_is_an_executable_statement():
    """test class is an executable statement"""

    def create_class(value):
        """create class"""
        if value > 10:
            class Queue:
                """Queue"""
                def __init__(self):
                    """__init"""
                    self.name = ">10queue"
        else:
            class Queue:
                """Queue"""
                def __init__(self):
                    """__init__"""
                    self.name = "<=10queue"

        return Queue

    q_class = create_class(20)
    obj_q1 = q_class()
    assert '>10queue' == obj_q1.name

    q_class = create_class(5)
    obj_q1 = q_class()
    assert '<=10queue>' == obj_q1.name


# the self argument name is just a convention but it is
# followed widely, so don't change the name of the first argument
# this is in contrast to other languages where the instance is implicit via
# the 'this' keyword.
def test_classes_methods():
    """test classes methods"""
    class Queue:
        """Queue"""
        def __init__(self, name):
            """init method"""
            self.name = name
            self._queue = []

        def push(self, obj):
            """push method"""
            self._queue.append(obj)

        def pop(self):
            """pop method"""
            return self._queue.pop(0)

    q1_obj = Queue("q1")
    q1_obj.push(10)  # note that we pass only one argument
    assert 10 == q1_obj.pop()

    # above is a equivalent to
    Queue.push(q1_obj, 10)
    assert 10 == Queue.pop(q1_obj)


def test_classes_bound_and_unbound_methods():
    """test classes bound and unbound methods"""
    class Queue:
        """Queue"""
        def __init__(self, name):
            """init method"""
            self.name = name
            self._queue = []

        def push(self, obj):
            """push method"""
            self._queue.append(obj)

        def pop(self):
            """pop method"""
            return self._queue.pop(0)

    obj_q1 = Queue("q1")
    q1_push = obj_q1.push

    assert False is (obj_q1.push is Queue.push)
    assert True is Queue.push.__self__  # unbound method
    assert obj_q1.push.__self__ == q1_push.__self__  # bound method

    # now understand the output of these 2 statements.
    print(obj_q1.push)
    print(Queue.push)


def test_classes_can_have_state():
    """test classes can have state"""
    class Queue:
        """Queue"""
        count = 0

        def __init__(self, name):
            """init method"""
            self.name = name
            self._queue = []
            Queue.count += 1

        def push(self, obj):
            """push method"""
            self._queue.append(obj)

        def pop(self):
            """pop method"""
            return self._queue.pop(0)

    assert 0 == Queue.count
    obj_q1 = Queue("q1")
    assert 1 == Queue.count
    obj_q2 = Queue("q2")
    assert 2 == Queue.count

    try:
        value = obj_q1.count
    except AttributeError:
        pass


THREE_THINGS_I_LEARNT = """
-classes need to inherit object in python 2
-bound and unbound methods
-variables defined outside __init__ does not belong to object
"""

TIME_TAKEN_MINUTES = 20
