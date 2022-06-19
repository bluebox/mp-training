'''classes'''
__author__ = 'Hari'

#from tasks.basics.test_08_understanding_truth import THREE_THINGS_I_LEARNT, TIME_TAKEN_MINUTES
# pylint: disable=unused-variable
# pylint: disable=too-few-public-methods

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
'''

#from tasks.placeholders import *

NOTES_1 = '''
 We are defining the classes in the function scope so that we can redefine them for every test.
 Generally you would define them at the module scope.
'''


#classes are objects too, they have a type, have attributes, can be passed
# to functions, held in data structures etc.
def test_classes_are_objects():
    '''classes'''
    class Queue(object):
        """Queue with push and pop functions."""
        pass

    def get_attr_count(obj):
        '''classes'''
        return len(dir(obj))

    assert 'type' == type(Queue).__name__ #note this.
    assert 'Queue with push and pop functions.' == Queue.__doc__
    assert 26 == get_attr_count(Queue)

def test_classes_are_callable_objects():
    '''classes'''
    class Queue(object):
        '''classes'''
        pass

    #classes are callable objects just like function objects
    assert True is callable(Queue)


def test_classes_are_object_factories():
    '''classes'''
    class Queue(object):
        '''classes'''
        pass

    q_1 = Queue()  # you can 'call' a class to create an instance
    q_2 = Queue()

    # assert 'type'== type(q1).__class__
    # assert 'type'== type(q2).__class__

    assert False is (q_1 is Queue)
    assert False is (q_2 is Queue)
    assert False is (q_2 is q_1)

    assert True is isinstance(q_1, Queue)
    assert True is isinstance(q_2, Queue)

    assert 26 == len(dir(Queue))
    assert 26 == len(dir(q_1))
    assert 26 == len(dir(q_2))


#if an __init__ method exists it is called with the object that is
#being created, so you can initialize it.
def test_classes_init_constructor():
    '''classes'''
    test_list = []

    class Queue(object):
        '''classes'''
        def __init__(self):
            assert True, "Entered here !"
            test_list.append(self)

    q_1 = Queue() # fix the assert to pass this.
    self_argument = test_list[0]
    assert True is (self_argument is q_1)

def test_classes_init_with_args():
    '''classes'''
    class Queue(object):
        '''classes'''
        def __init__(self, name):
            '''classes'''
            self.name = name

    q_1 = Queue("q_1")
    q_2 = Queue("q_2")

    assert  "q_1"== q_1.name
    assert "q_2"== q_2.name

    try:
        q_3 = Queue()
    except TypeError: #what error do you get?
        pass


#just like def, class is also a runtime statement which bounds a class name with the class body code
def test_class_is_an_executable_statement():
    '''classes'''
    def create_class(value):
        '''classes'''
        if (value > 10):
            class Queue(object):
                '''classes'''
                def __init__(self):
                    '''classes'''
                    self.name = ">10queue"
        else:
            class Queue(object):
                '''classes'''
                def __init__(self):
                    '''classes'''
                    self.name = "<=10queue"

        return Queue

    Q_class = create_class(20)
    q_1 = Q_class()
    assert '>10queue' == q_1.name

    Q_class = create_class(5)
    q_1 = Q_class()
    assert '<=10queue'== q_1.name


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

    q_1 = Queue("q_1")
    q_1.push(10) #note that we pass only one argument
    assert 10 == q_1.pop()

    #above is a equivalent to
    Queue.push(q_1, 10)
    assert 10 == Queue.pop(q_1)


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

    q_1 = Queue("q_1")
    q1_push = q_1.push

    assert False is (q_1.push is Queue.push)

   # assert 'q1' == Queue.push.__self__   #unbound method
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

    assert 0 == Queue.count
    q_1 = Queue("q_1")
    assert 1 == Queue.count
    q_2 = Queue("q_2")
    assert 2 == Queue.count

    try:
        value = q_1.count
    except TypeError :
        pass


THREE_THINGS_I_LEARNT = """
-
-
-
"""

TIME_TAKEN_MINUTES = 40
