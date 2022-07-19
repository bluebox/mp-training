<<<<<<< HEAD
#from tasks.placeholders import *
=======
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
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

NOTES_2 = '''
 We are defining the classes in the function scope so that we can redefine them for every test.
 Generally you would define them at the module scope.
'''


#classes are objects too, they have a type, have attributes, can be passed
# to functions, held in data structures etc.
def test_classes_are_objects():
    class Queue():
        """Queue with push and pop functions."""
        pass

    def get_attr_count(obj):
        return len(dir(obj))

<<<<<<< HEAD
    assert "type" == type(Queue).__name__ #note this.
    assert 'Queue with push and pop functions.' == Queue.__doc__
    assert 26 is get_attr_count(Queue)
=======
    assert __ == type(Queue).__name__ #note this.
    assert __ == Queue.__doc__
    assert __ == get_attr_count(Queue)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_classes_are_callable_objects():
    class Queue(object):
        pass

<<<<<<< HEAD
    # classes are callable objects just like function objects
    assert True is callable(Queue)
=======
    #classes are callable objects just like function objects
    assert __ == callable(Queue)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


def test_classes_are_object_factories():
    class Queue():
        pass

    q_1 = Queue()  # you can 'call' a class to create an instance
    q_2 = Queue()

<<<<<<< HEAD
    # assert "<class 'type'>" == type(q1).__class__
    # assert "<class 'type'>" == type(q2).__class__

    assert False  is (q_1 is Queue)
    assert False  is (q_2 is Queue)
    assert False is (q_2 is q_1)

    assert True is isinstance(q_1, Queue)
    assert True is isinstance(q_2, Queue)

    assert 26 is len(dir(Queue))
    assert 26 is len(dir(q_1))
    assert 26 is len(dir(q_2))
=======
    assert __ == type(q1).__class__
    assert __ == type(q2).__class__

    assert __  == (q1 is Queue)
    assert __  == (q2 is Queue)
    assert __  == (q2 is q1)

    assert __ == isinstance(q1, Queue)
    assert __ == isinstance(q2, Queue)

    assert __ == len(dir(Queue))
    assert __ == len(dir(q1))
    assert __ == len(dir(q2))
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


#if an __init__ method exists it is called with the object that is
#being created, so you can initialize it.
def test_classes_init_constructor():
    test_list = []

    class Queue():
        def __init__(self):
            assert ___, "Entered here !"
            test_list.append(self)

<<<<<<< HEAD
    q_1 = Queue()  # fix the assert to pass this.
    self_argument = test_list[0]
    assert True is (self_argument is q_1)
=======
    q1 = Queue() # fix the assert to pass this.
    self_argument = test_list[0]
    assert __ == (self_argument is q1)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_classes_init_with_args():
    class Queue():
        def __init__(self, name):
            self.name = name

    q_1 = Queue("q1")
    q_2 = Queue("q2")

<<<<<<< HEAD
    assert "q1" == q_1.name
    assert "q2" == q_2.name

    try:
        q_3 = Queue()
    except TypeError:  # what error do you get?
        pass


# just like def, class is also a runtime statement
# which bounds a class name with the class body code
=======
    assert __ == q1.name
    assert __ == q2.name

    try:
        q3 = Queue()
    except __: #what error do you get?
        pass


#just like def, class is also a runtime statement which bounds a class name with the class body code
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
def test_class_is_an_executable_statement():
    def create_class(value):
        if (value > 10):
            class Queue():
                def __init__(self):
                    self.name = ">10queue"
        else:
            class Queue():
                def __init__(self):
                    self.name = "<=10queue"

        return Queue

    Q_class = create_class(20)
<<<<<<< HEAD
    q_1 = Q_class()
    assert ">10queue" == q_1.name

    Q_class = create_class(5)
    q_1 = Q_class()
    assert "<=10queue" == q_1.name
=======
    q1 = Q_class()
    assert __ == q1.name

    Q_class = create_class(5)
    q1 = Q_class()
    assert __ == q1.name
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


# the self argument name is just a convention but it is
# followed widely, so don't change the name of the first argument
# this is in contrast to other languages where the instance is implicit via
# the 'this' keyword.
def test_classes_methods():
    class Queue():
        def __init__(self, name):
            self.name = name
            self._queue = []

        def push(self, obj):
            self._queue.append(obj)

        def pop(self):
            return self._queue.pop(0)

<<<<<<< HEAD
    q_1 = Queue("q1")
    q_1.push(10)  # note that we pass only one argument
    assert 10 is q_1.pop()

    # above is a equivalent to
    Queue.push(q_1, 10)
    assert 10 == Queue.pop(q_1)
=======
    q1 = Queue("q1")
    q1.push(10) #note that we pass only one argument
    assert __ == q1.pop()

    #above is a equivalent to
    Queue.push(q1, 10)
    assert __ == Queue.pop(q1)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


def test_classes_bound_and_unbound_methods():
    class Queue():
        def __init__(self, name):
            self.name = name
            self._queue = []

        def push(self, obj):
            self._queue.append(obj)

        def pop(self):
            return self._queue.pop(0)

    q_1 = Queue("q1")
    q_1_push = q_1.push

<<<<<<< HEAD
    assert False is (q_1.push is Queue.push)

    # assert "<class 'function'>" == Queue.push.__self__   #unbound method
    # assert "<class 'method'>" == q1_push.__self__      #bound method
=======
    assert __ == (q1.push is Queue.push)

    assert __ == Queue.push.__self__   #unbound method
    assert __ == q1_push.__self__      #bound method
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

    # now understand the output of these 2 statements.
    print(q_1.push)
    print(Queue.push)


def test_classes_can_have_state():
<<<<<<< HEAD
    class Queue():
        COUNT = 0

=======
    class Queue(object):
        count = 0
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
        def __init__(self, name):
            self.name = name
            self._queue = []
            Queue.COUNT += 1

        def push(self, obj):
            self._queue.append(obj)

        def pop(self):
            return self._queue.pop(0)

<<<<<<< HEAD
    assert 0 is Queue.COUNT
    q_1 = Queue("q1")
    assert 1 is Queue.COUNT
    q_2 = Queue("q2")
    assert 2 is Queue.COUNT

    try:
        value = q_1.COUNT
    except NameError :
=======
    assert __ == Queue.count
    q1 = Queue("q1")
    assert __ == Queue.count
    q2 = Queue("q2")
    assert __ == Queue.count

    try:
        value = q1.count
    except __ :
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
        pass


THREE_THINGS_I_LEARNT = """
CLASSES USING
-
-
"""

<<<<<<< HEAD
TIME_TAKEN_MINUTES = 120
=======
time_taken_minutes = ___
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
