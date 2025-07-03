from abc import ABC, abstractmethod

class NonAbs:
    @staticmethod
    def greet():
        print("hello")

obj = NonAbs()
obj.greet()

class Abs(ABC):

    @abstractmethod
    def greet(self):
        pass

class B(Abs):

    def greet(self):
        print("Hello")

b = B()

print(globals())