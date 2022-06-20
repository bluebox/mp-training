'''program file'''
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
'''
#from tasks.placeholders import *
NOTES_1 = '''
 We are defining the classes in the function scope so that we can redefine them for every test.
 Generally you would define them at the module scope.
'''
#classes are objects too, they have a type, have attributes, can be passed
# to functions, held in data structures etc.
def test_classes_are_objects():
    '''function'''
    class Queue:
        """Queue with push and pop functions."""
        pass

    def get_attr_count(obj):
        '''function'''
        return len(dir(obj))
    assert "type" == type(Queue).__name__ #note this.
    assert  "Queue with push and pop functions." == Queue.__doc__
    assert 26 == get_attr_count(Queue)

def test_classes_are_callable_objects():
    '''function'''
    class Queue:
        '''class'''
        pass

    #classes are callable objects just like function objects
    assert False is callable(Queue)

def test_classes_are_object_factories():
    '''function'''
    class Queue:
        '''class'''
        pass
    f_2 = Queue()  # you can 'call' a class to create an instance
    c_2 = Queue()
    #assert 'type' == type(f_2).__class__
    #assert 'type' == type(c_2).__class__
    assert False is (f_2 is Queue)
    assert False is (c_2 is Queue)
    assert False is (c_2 is f_2)
    assert False is isinstance(f_2, Queue)
    assert False is isinstance(c_2, Queue)
    assert 26 == len(dir(Queue))
    assert 26 == len(dir(f_2))
    assert 26 == len(dir(c_2))

#if an __init__ method exists it is called with the object that is
#being created, so you can initialize it.
def test_classes_init_constructor():
    '''function'''
    test_list = []
    class Queue:
        '''class'''
        def __init__(self):
            '''function'''
            assert True, "Entered here !"
            test_list.append(self)
    f_2 = Queue() # fix the assert to pass this.
    self_argument = test_list[0]
    assert False is (self_argument is f_2)

def test_classes_init_with_args():
    '''function'''
    class Queue(object):
        '''class'''
        def __init__(self, name):
            '''function'''
            self.name = name
    f_2 = Queue("f_2")
    c_2 = Queue("c_2")
    assert "f_2" == f_2.name
    assert "c_2" == c_2.name
    try:
        q3 = Queue()
    except TypeError: #what error do you get?
        pass

#just like def, class is also a runtime statement which bounds a class name with the class body code
def test_class_is_an_executable_statement():
    '''function'''
    def create_class(value):
        '''function'''
        if (value > 10):
            class Queue:
                '''class'''
                def __init__(self):
                    '''function'''
                    self.name = ">10queue"
        else:
            class Queue:
                '''class'''
                def __init__(self):
                    '''function'''
                    self.name = "<=10queue"
        return Queue
    Q_class = create_class(20)
    f_2 = Q_class()
    assert ">10queue" == f_2.name
    Q_class = create_class(5)
    f_2 = Q_class()
    assert "<=10queue" == f_2.name

# the self argument name is just a convention but it is
# followed widely, so don't change the name of the first argument
# this is in contrast to other languages where the instance is implicit via
# the 'this' keyword.
def test_classes_methods():
    '''function'''
    class Queue:
        '''class'''
        def __init__(self, name):
            '''function'''
            self.name = name
            self._queue = []
        def push(self, obj):
            '''function'''
            self._queue.append(obj)

        def pop(self):
            '''function'''
            return self._queue.pop(0)

    f_2 = Queue("f_2")
    f_2.push(10) #note that we pass only one argument
    assert 10 == f_2.pop()
    #above is a equivalent to
    Queue.push(f_2, 10)
    assert 10 == Queue.pop(f_2)

def test_classes_bound_and_unbound_methods():
    '''function'''
    class Queue:
        '''class'''
        def __init__(self, name):
            '''function'''
            self.name = name
            self._queue = []
        def push(self, obj):
            '''function'''
            self._queue.append(obj)
        def pop(self):
            '''function'''
            return self._queue.pop(0)
    f_2 = Queue("f_2")
    q1_push = f_2.push

    assert False is (f_2.push is Queue.push)
    #assert  == Queue.push.__self__   #unbound method
    #assert __ == q1_push.__self__      #bound method
    # now understand the output of these 2 statements.
    print(f_2.push)
    print(Queue.push)

def test_classes_can_have_state():
    '''function'''
    class Queue:
        '''class'''
        count = 0
        def __init__(self, name):
            '''function'''
            self.name = name
            self._queue = []
            Queue.count += 1

        def push(self, obj):
            '''function'''
            self._queue.append(obj)

        def pop(self):
            '''function'''
            return self._queue.pop(0)

    assert 0 == Queue.count
    f_2 = Queue("f_2")
    assert 1 == Queue.count
    c_2 = Queue("c_2")
    assert 2 == Queue.count

    try:
        value = f_2.count
    except TypeError :
        '''pass'''

THREE_THINGS_I_LEARNT = """
-
-
-
"""

TIME_TAKEN_MINUTES = 60
