from abc import ABC,abstractmethod
import math
class Shape(ABC):
    @abstractmethod
    def area(self)-> float:
        pass
    @classmethod
    def describe_shapes(cls):
        print("Shapes are fundamental geometric figures.")
    @staticmethod
    def get_pi():
        return 22/7

class Circle(Shape):
    def __init__(self,radius):
        self.radius = radius
    def area(self):
        return 2*self.get_pi()*self.radius

class Rectangle(Shape):
    def __init__(self,length,breadth):
        self.length = length
        self.breadth = breadth
    def area(self):
        return self.length*self.breadth