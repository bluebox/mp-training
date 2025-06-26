from abc import ABC,abstractmethod
from modifiers import Student,Abhi
class Animal(ABC):
    @abstractmethod
    def jump(self):
        pass
    @abstractmethod
    def run(self):
        pass
class Dog(Animal):
    def jump(self):
        print("Dog is jumping")
    def run(self):
        print("Dog is running")

a=Dog()
a.jump()
a.run()

a=Student("anand")
a.display()
a=Abhi()
a.printVal()