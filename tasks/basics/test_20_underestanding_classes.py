__author__ = 'Hari'
from tasks.placeholders import __author__


NOTES = '''
Python allows users to add user defined types via classes. This allows you to augment builtin types like dict, list, tuple with your own types with their own specific behavior.
Like most common languages like java and c#, python supports objected oriented features like class definitions, inheritance and polymorphism.
However, unlike java and c#, python does not insist that you have to forcibly model your problem domain as classes if it does not make sense. You could use any mix of modules,
functions and classes to model your application. For e.g. if you goal is to code up the fibonacci function or write a routine that sorts a sequence then defining a class
does not make sense.
This assignment only deals with the syntax of classes and its features. You must
look up references to actually learn object oriented programming.
http://c2.com/cgi/wiki?AlanKaysDefinitionOfObjectOriented

https://www.pythontutorial.net/python-oop/python-type-class/

'''
NOTES2 = '''
 We are defining the classes in the function scope so that we can redefine them for every test.
 Generally you would define them at the module scope.
'''


# classes are objects too, they have a type, have attributes, can be passed
# to functions, held in data structures etc.
def test_classes_are_objects():
    class Queue():
        """Queue with push and pop functions."""
        pass

    def get_attr_count(obj):
        return len(dir(obj))

    assert "type" == type(Queue).__name__ #note this.
    assert 'Queue with push and pop functions.' == Queue.__doc__
    assert 26 is get_attr_count(Queue)

def test_classes_are_callable_objects():
    class Queue(object):
        pass

    # classes are callable objects just like function objects
    assert True is callable(Queue)


def test_classes_are_object_factories():
    class Queue():
        pass

    var_q1 = Queue()  # you can 'call' a class to create an instance
    var_q2 = Queue()

    # assert "<class 'type'>" == type(q1).__class__
    # assert "<class 'type'>" == type(q2).__class__

    assert False  is (var_q1 is Queue)
    assert False  is (var_q2 is Queue)
    assert False is (var_q2 is var_q1)

    assert True is isinstance(var_q1, Queue)
    assert True is isinstance(var_q2, Queue)

    assert 26 is len(dir(Queue))
    assert 26 is len(dir(var_q1))
    assert 26 is len(dir(var_q2))


# if an __init__ method exists it is called with the object that is
# being created, so you can initialize it.
def test_classes_init_constructor():
    test_list = []

    class Queue():
        def __init__(self):
            assert True, "Entered here !"
            test_list.append(self)

    var_q1 = Queue()  # fix the assert to pass this.
    self_argument = test_list[0]
    assert True is (self_argument is var_q1)

def test_classes_init_with_args():
    class Queue():
        def __init__(self, name):
            self.name = name

    var_q1 = Queue("q1")
    var_q2 = Queue("q2")

    assert "q1" == var_q1.name
    assert "q2" == var_q2.name

    try:
        q_3 = Queue()
    except TypeError:  # what error do you get?
        pass


# just like def, class is also a runtime statement
# which bounds a class name with the class body code
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
    var_q1 = Q_class()
    assert ">10queue" == var_q1.name

    Q_class = create_class(5)
    var_q1 = Q_class()
    assert "<=10queue" == var_q1.name


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

    var_q1 = Queue("q1")
    var_q1.push(10)  # note that we pass only one argument
    assert 10 is var_q1.pop()

    # above is a equivalent to
    Queue.push(var_q1, 10)
    assert 10 == Queue.pop(var_q1)


def test_classes_bound_and_unbound_methods():
    class Queue():
        def __init__(self, name):
            self.name = name
            self._queue = []

        def push(self, obj):
            self._queue.append(obj)

        def pop(self):
            return self._queue.pop(0)

    var_q1 = Queue("q1")
    var_q1_push = var_q1.push

    assert False is (var_q1.push is Queue.push)

    # assert "<class 'function'>" == Queue.push.__self__   #unbound method
    # assert "<class 'method'>" == q1_push.__self__      #bound method

    # now understand the output of these 2 statements.
    print(var_q1.push)
    print(Queue.push)


def test_classes_can_have_state():
    class Queue():
        COUNT = 0

        def __init__(self, name):
            self.name = name
            self._queue = []
            Queue.COUNT += 1

        def push(self, obj):
            self._queue.append(obj)

        def pop(self):
            return self._queue.pop(0)

    assert 0 is Queue.COUNT
    var_q1 = Queue("q1")
    assert 1 is Queue.COUNT
    var_q2 = Queue("q2")
    assert 2 is Queue.COUNT

    try:
        value = var_q1.COUNT
    except NameError :
        pass


THREE_THINGS_I_LEARNT = """
-to check wether a object an instance of a class
-snake case to in crese score
-global and local scope of class
"""

TIME_TAKEN_MINUTES = 55