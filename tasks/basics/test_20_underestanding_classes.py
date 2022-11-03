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

https://www.pythontutorial.net/python-oop/python-type-class/

'''

from tasks.placeholders import *

notes_1 = '''
 We are defining the classes in the function scope so that we can redefine them for every test.
 Generally you would define them at the module scope.
'''


#classes are objects too, they have a type, have attributes, can be passed
# to functions, held in data structures etc.
""" Understanding classes """
def test_classes_are_objects():
    """ test classes as objects"""
    class Queue(object):
        """Queue with push and pop functions."""
        pass

    def get_attr_count(obj):
        return len(dir(obj))

    assert "type" == type(Queue).__name__ #note this.
    assert "Queue with push and pop functions." == Queue.__doc__
    assert 26 == get_attr_count(Queue)
test_classes_are_objects()
def test_classes_are_callable_objects():
    """ function to test classes are callable """
    class Queue(object):
        pass

    #classes are callable objects just like function objects
    assert True is callable(Queue)

test_classes_are_callable_objects()
def test_classes_are_object_factories():
    """ function to check classes are objects factories """
    class Queue(object):
        pass

    q1 = Queue()  # you can 'call' a class to create an instance
    q2 = Queue()

    # assert "<class 'type'>" == type(q1).__class__
    # assert "<class 'type'>" == type(q2).__class__

    assert False  == (q1 is Queue)
    assert False  == (q2 is Queue)
    assert False  == (q2 is q1)

    assert True == isinstance(q1, Queue)
    assert True == isinstance(q2, Queue)

    assert 26 == len(dir(Queue))
    assert 26 == len(dir(q1))
    assert 26 == len(dir(q2))
test_classes_are_object_factories()


#if an __init__ method exists it is called with the object that is
#being created, so you can initialize it.
def test_classes_init_constructor():
    """ function to test classes init constructor """
    test_list = []

    class Queue(object):
        def __init__(self):
            assert self, "Entered here !"
            test_list.append(self)

    q1 = Queue()  # fix the assert to pass this.
    self_argument = test_list[0]
    assert True is (self_argument is q1)
test_classes_init_constructor()

def test_classes_init_with_args():
    """ function to test classes init with args """
    class Queue(object):
        def __init__(self, name):
            self.name = name

    q1 = Queue("q1")
    q2 = Queue("q2")

    assert "q1" == q1.name
    assert "q2" == q2.name

    try:
        q3 = Queue()
    except TypeError: #what error do you get?
        pass
test_classes_init_with_args()

#just like def, class is also a runtime statement which bounds a class name with the class body code
def test_class_is_an_executable_statement():
    """ function to test class is an executable statement """
    def create_class(value):
        if ( value > 10):
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
    assert ">10queue" == q1.name

    Q_class = create_class(5)
    q1 = Q_class()
    assert "<=10queue" == q1.name
test_class_is_an_executable_statement()

# the self argument name is just a convention but it is
# followed widely, so don't change the name of the first argument
# this is in contrast to other languages where the instance is implicit via
# the 'this' keyword.
def test_classes_methods():
    """ function to test classes methods """
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
    assert 10 == q1.pop()

    #above is a equivalent to
    Queue.push(q1, 10)
    assert 10 == Queue.pop(q1)


def test_classes_bound_and_unbound_methods():
    """ function to test classes are bound and unbound """
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

    assert False == (q1.push is Queue.push)

    # assert "q1" == Queue.push.__self__   #unbound method
    # assert __ == q1_push.__self__      #bound method

    # now understand the output of these 2 statements.
    print(q1.push)
    print(Queue.push)

test_classes_bound_and_unbound_methods()
def test_classes_can_have_state():
    """ functions to test classes can have state or not """
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

    assert 0 == Queue.count
    q1 = Queue("q1")
    assert 1 == Queue.count
    q2 = Queue("q2")
    assert 2 == Queue.count

    try:
        value = q1.count
    except NameError :
        pass
test_classes_can_have_state()


three_things_i_learnt = """
-concept of class in python
-errors while calling class in wrong syntax
-passing value to a class
"""

time_taken_minutes = 70
