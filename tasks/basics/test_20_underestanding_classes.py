'''classes'''
__author__ = 'Hari'

NOTES = '''
Python allows users to add user defined types via classes
This allows you to augment
builtin types like dict, list, tuple with your
own types with their own specific behavior.

Like most common languages like java and c#,
python supports objected oriented features
like class definitions, inheritance and polymorphism.

However, unlike java and c#, python does not
insist that you have to forcibly model your
problem domain as classes if it does not make
sense. You could use any mix of modules,
functions and classes to model your application
For e.g. if you goal is to code up the
fibonacci function or write a routine that sorts
a sequence then defining a class
does not make sense.

This assignment only deals with the syntax of
classes and its features. You must
look up references to actually learn object
oriented programming.

http://c2.com/cgi/wiki?AlanKaysDefinitionOfObjectOriented

https://www.pythontutorial.net/python-oop/python-type-class/

'''

# from tasks.placeholders import *

NOTES_1 = '''
 We are defining the classes in the function scope so
 that we can redefine them for every test.
 Generally you would define them at the module scope.
'''


#classes are objects too, they have a type, have attributes, can be passed
# to functions, held in data structures etc.
def test_classes_are_objects():
    '''classes'''
    class Queue(object):
        """Queue with push and pop functions."""

    def get_attr_count(obj):
        '''classes'''
        return len(dir(obj))

    assert type(Queue).__name__ == "type" #note this.
    assert Queue.__doc__ == 'Queue with push and pop functions.'
    assert get_attr_count(Queue) == 26

def test_classes_are_callable_objects():
    '''classes'''
    class Queue(object):
        '''classes'''

    #classes are callable objects just like function objects
    assert callable(Queue) is True


def test_classes_are_object_factories():
    '''classes'''
    class Queue(object):
        '''classes'''

    q1_1 = Queue()  # you can 'call' a class to create an instance
    q2_1 = Queue()

    # assert 'type' == type(q1).__class__
    # assert 'type' == type(q2).__class__

    assert (q1_1 is Queue) is False
    assert (q2_1 is Queue) is False
    assert (q2_1 is q1_1) is False

    assert isinstance(q1_1, Queue) is True
    assert isinstance(q2_1, Queue) is True

    assert len(dir(Queue)) == 26
    assert len(dir(q1_1)) == 26
    assert len(dir(q2_1)) == 26


#if an __init__ method exists it is called with the object that is
#being created, so you can initialize it.
def test_classes_init_constructor():
    '''classes'''
    test_list = []

    class Queue(object):
        '''classes'''
        def __init__(self):
            '''classes'''
            assert self, "Entered here !"
            test_list.append(self)

    q1_1 = Queue() # fix the assert to pass this.
    self_argument = test_list[0]
    assert (self_argument is q1_1) is True

def test_classes_init_with_args():
    '''classes'''
    class Queue(object):
        '''classes'''
        def __init__(self, name):
            '''classes'''
            self.name = name

    q_1 = Queue("q1")
    q_2 = Queue("q2")

    assert q_1.name == "q1"
    assert q_2.name == "q2"

    try:
        q_3 = Queue()
        print(q_3)
    except TypeError: #what error do you get?
        pass


#just like def, class is also a runtime statement which bounds a class name with the class body code
def test_class_is_an_executable_statement():
    '''classes'''
    def create_class(value):
        '''classes'''
        if value > 10:
            class Queue(object):
                '''classes'''
                def __init__(self):
                    self.name = ">10queue"
        else:
            class Queue(object):
                '''classes'''
                def __init__(self):
                    self.name = "<=10queue"

        return Queue

    q_class = create_class(20)
    q_1 = q_class()
    assert q_1.name == ">10queue"

    q_class = create_class(5)
    q_1 = q_class()
    assert q_1.name == "<=10queue"


# the self argument name is just a convention but it is
# followed widely, so don't change the name of the first argument
# this is in contrast to other languages where the instance is implicit via
# the 'this' keyword.
def test_classes_methods():
    '''classes'''
    class Queue(object):
        '''classes'''
        def __init__(self, name):
            '''classes'''
            self.name = name
            self._queue = []

        def push(self, obj):
            '''classes'''
            self._queue.append(obj)

        def pop(self):
            '''classes'''
            return self._queue.pop(0)

    q_1 = Queue("q1")
    q_1.push(10) #note that we pass only one argument
    assert q_1.pop() == 10

    #above is a equivalent to
    Queue.push(q_1, 10)
    assert Queue.pop(q_1) == 10

def test_classes_bound_and_unbound_methods():
    '''classes'''
    class Queue(object):
        '''classes'''
        def __init__(self, name):
            '''classes'''
            self.name = name
            self._queue = []

        def push(self, obj):
            '''classes'''
            self._queue.append(obj)

        def pop(self):
            '''classes'''
            return self._queue.pop(0)

    q_1 = Queue("q1")
    q1_push = q_1.push

    assert (q1_push is Queue.push) is False

    # assert None == Queue.push.__self__   #unbound method
    # assert __ == q1_push.__self__      #bound method

    # now understand the output of these 2 statements.
    print(q_1.push)
    print(Queue.push)

def test_classes_can_have_state():
    '''classes'''
    class Queue(object):
        '''classes'''
        count = 0
        def __init__(self, name):
            '''classes'''
            self.name = name
            self._queue = []
            Queue.count += 1

        def push(self, obj):
            '''classes'''
            self._queue.append(obj)

        def pop(self):
            '''classes'''
            return self._queue.pop(0)

    assert Queue.count == 0
    q_1 = Queue("q1")
    assert Queue.count == 1
    # q_2 = Queue("q2")
    assert Queue.count == 1

    try:
        value = q_1.count
        print(value)
    except AssertionError:
        pass


THREE_THINGS_I_LEARNT = """
- A class is a user-defined blueprint or prototype
from which objects are created.
- Attributes are the variables that belong to a class.
- Attributes are always public and can be accessed
using the dot (.) operator.
"""

TIME_TAKEN_MINUTES = 10
