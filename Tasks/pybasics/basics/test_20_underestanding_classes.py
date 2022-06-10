__author__ = 'Hari'

notes = '''
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
'''

from placeholders import *

notes_1 = '''
 We are defining the classes in the function scope so that we can redefine them for every test.
 Generally you would define them at the module scope.
'''


#classes are objects too, they have a type, have attributes, can be passed
# to functions, held in data structures etc.
def test_classes_are_objects():
    class Queue(object):
        """Queue with push and pop functions."""
        pass

    def get_attr_count(obj):
        return len(dir(obj))

    assert __ == type(Queue).__name__ #note this.
    assert __ == Queue.__doc__
    assert __ == get_attr_count(Queue)

def test_classes_are_callable_objects():
    class Queue(object):
        pass

    #classes are callable objects just like function objects
    assert __ == callable(Queue)


def test_classes_are_object_factories():
    class Queue(object):
        pass

    q1 = Queue()  # you can 'call' a class to create an instance
    q2 = Queue()

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


#if an __init__ method exists it is called with the object that is
#being created, so you can initialize it.
def test_classes_init_constructor():
    test_list = []

    class Queue(object):
        def __init__(self):
            assert ___, "Entered here !"
            test_list.append(self)

    q1 = Queue() # fix the assert to pass this.
    self_argument = test_list[0]
    assert __ == (self_argument is q1)

def test_classes_init_with_args():
    class Queue(object):
        def __init__(self, name):
            self.name = name

    q1 = Queue("q1")
    q2 = Queue("q2")

    assert __ == q1.name
    assert __ == q2.name

    try:
        q3 = Queue()
    except __: #what error do you get?
        pass


#just like def, class is also a runtime statement which bounds a class name with the class body code
def test_class_is_an_executable_statement():
    def create_class(value):
        if (value > 10):
            class Queue(object):
                def __init__(self):
                    self.name = ">10queue"
        else:
            class Queue(object):
                def __init__(self):
                    self.name = "<=10queue"

        return Queue

    Q_class = create_class(20)
    q1 = Q_class()
    assert __ == q1.name

    Q_class = create_class(5)
    q1 = Q_class()
    assert __ == q1.name


# the self argument name is just a convention but it is
# followed widely, so don't change the name of the first argument
# this is in contrast to other languages where the instance is implicit via
# the 'this' keyword.
def test_classes_methods():
    class Queue(object):
        def __init__(self, name):
            self.name = name
            self._queue = []

        def push(self, obj):
            self._queue.append(obj)

        def pop(self):
            return self._queue.pop(0)

    q1 = Queue("q1")
    q1.push(10) #note that we pass only one argument
    assert __ == q1.pop()

    #above is a equivalent to
    Queue.push(q1, 10)
    assert __ == Queue.pop(q1)


def test_classes_bound_and_unbound_methods():
    class Queue(object):
        def __init__(self, name):
            self.name = name
            self._queue = []

        def push(self, obj):
            self._queue.append(obj)

        def pop(self):
            return self._queue.pop(0)

    q1 = Queue("q1")
    q1_push = q1.push

    assert __ == (q1.push is Queue.push)

    assert __ == Queue.push.__self__   #unbound method
    assert __ == q1_push.__self__      #bound method

    # now understand the output of these 2 statements.
    print q1.push
    print Queue.push

def test_classes_can_have_state():
    class Queue(object):
        count = 0
        def __init__(self, name):
            self.name = name
            self._queue = []
            Queue.count += 1

        def push(self, obj):
            self._queue.append(obj)

        def pop(self):
            return self._queue.pop(0)

    assert __ == Queue.count
    q1 = Queue("q1")
    assert __ == Queue.count
    q2 = Queue("q2")
    assert __ == Queue.count

    try:
        value = q1.count
    except __ :
        pass


three_things_i_learnt = """
-
-
-
"""

time_taken_minutes = ___
