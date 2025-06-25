from abc import ABC,abstractmethod
class Animal(ABC):
    @abstractmethod
    def start(self):
        pass
class Dog(Animal):
    def start(self):
        print("started")
a=Dog()
a.start()