from tasks.placeholders import __author__

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

NOTES_1 = '''
 We are defining the classes in the function scope so that we can redefine them for every test.
 Generally you would define them at the module scope.
'''


#classes are objects too, they have a type, have attributes, can be passed
# to functions, held in data structures etc.
def test_classes_are_objects():
    '''METHOD'''
    class Queue(object):
        """Queue with push and pop functions."""
        pass

    def get_attr_count(obj):
        '''METHOD'''
        return len(dir(obj))

    assert "type" == type(Queue).__name__ #note this.
    assert "Queue with push and pop functions." == Queue.__doc__
    assert 26 == get_attr_count(Queue)

def test_classes_are_callable_objects():
    '''METHOD'''
    class Queue(object):
        '''METHOD'''
        pass

    #classes are callable objects just like function objects
    assert True is callable(Queue)


def test_classes_are_object_factories():
    '''METHOD'''
    class Queue(object):
        '''METHOD'''
        pass

    q1 = Queue()  # you can 'call' a class to create an instance
    q2 = Queue()

    # assert "<class 'type'>" == type(q1).__class__
    # assert "<class 'type'>" == type(q2).__class__

    assert False  is (q1 is Queue)
    assert False  is (q2 is Queue)
    assert False  is (q2 is q1)

    assert True is isinstance(q1, Queue)
    assert True is isinstance(q2, Queue)

    assert 26 == len(dir(Queue))
    assert 26 == len(dir(q1))
    assert 26 == len(dir(q2))


#if an __init__ method exists it is called with the object that is
#being created, so you can initialize it.
def test_classes_init_constructor():
    '''METHOD'''
    test_list = []

    class Queue(object):
        '''METHOD'''
        def __init__(self):
            '''METHOD'''
            assert self, "Entered here !"
            test_list.append(self)

    q_1 = Queue() # fix the assert to pass this.
    self_argument = test_list[0]
    assert True is (self_argument is q_1)

def test_classes_init_with_args():
    '''METHOD'''
    class Queue(object):
        '''METHOD'''
        def __init__(self, name):
            '''METHOD'''
            self.name = name

    q1 = Queue("q1")
    q2 = Queue("q2")

    assert 'q1' == q1.name
    assert 'q2' == q2.name

    try:
        q3 = Queue()
    except TypeError: #what error do you get?
        pass


#just like def, class is also a runtime statement which bounds a class name with the class body code
def test_class_is_an_executable_statement():
    '''METHOD'''
    def create_class(value):
        '''METHOD'''
        if value > 10:
            class Queue(object):
                '''METHOD'''
                def __init__(self):
                    '''methid'''
                    self.name = ">10queue"
        else:
            class Queue(object):
                '''METHOD'''
                def __init__(self):
                    '''METHOD'''
                    self.name = "<=10queue"

        return Queue

    Q_class = create_class(20)
    q1 = Q_class()
    assert ">10queue" == q1.name

    Q_class = create_class(5)
    q1 = Q_class()
    assert "<=10queue" == q1.name


# the self argument name is just a convention but it is
# followed widely, so don't change the name of the first argument
# this is in contrast to other languages where the instance is implicit via
# the 'this' keyword.
def test_classes_methods():
    '''METHOD'''
    class Queue(object):
        '''METHOD'''
        def __init__(self, name):
            '''METHOD'''
            self.name = name
            self._queue = []

        def push(self, obj):
            '''METHOD'''
            self._queue.append(obj)

        def pop(self):
            '''METHOD'''
            return self._queue.pop(0)

    q_1 = Queue("q1")
    q_1.push(10) #note that we pass only one argument
    assert 10 == q_1.pop()

    #above is a equivalent to
    Queue.push(q_1, 10)
    assert 10 == Queue.pop(q_1)


def test_classes_bound_and_unbound_methods():
    '''METHOD'''
    class Queue(object):
        '''METHOD'''
        def __init__(self, name):
            '''METHOD'''
            self.name = name
            self._queue = []

        def push(self, obj):
            '''METHOD'''
            self._queue.append(obj)

        def pop(self):
            '''METHOD'''
            return self._queue.pop(0)

    q_1 = Queue("q1")
    q1_push = q_1.push

    assert False is (q_1.push is Queue.push)
    try:
       assert AttributeError == Queue.push.__self__   #unbound method
    except AttributeError as a_e:
       print(a_e)
    assert q1_push.__self__ == q1_push.__self__      #bound method

    # now understand the output of these 2 statements.
    print(q_1.push)
    print(Queue.push)


def test_classes_can_have_state():
    '''METHOD'''
    class Queue(object):
        '''METHOD'''
        count = 0
        def __init__(self, name):
            '''METHOD'''
            self.name = name
            self._queue = []
            Queue.count += 1

        def push(self, obj):
            '''METHOD'''
            self._queue.append(obj)

        def pop(self):
            '''METHOD'''
            return self._queue.pop(0)

    assert 0 == Queue.count
    q_1 = Queue("q1")
    assert 1 == Queue.count
    q_2 = Queue("q2")
    print(q_2)
    assert 2 == Queue.count

    try:
        value = q_1.count
        print(value)
    except NameError :
        pass


TTIL = """
-
-
-
"""

TTM = 10
