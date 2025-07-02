"""
 Problem Statement:
Create an abstract Shape class using ABC and abstractmethod from the abc module (though for simplicity, you can initially just define a base class without strict ABC enforcement, and later modify it).
Define a base Shape class with an area method (which should raise NotImplementedError or be an abstract method if using ABC).
Create concrete subclasses Circle and Rectangle that inherit from Shape.
Circle should have a radius and implement the area method.
Rectangle should have width and height and implement the area method.
Add a @classmethod to the Shape class (or one of its subclasses) called describe_shapes that prints a general statement about shapes (e.g., "Shapes are fundamental geometric figures.").
Add a @staticmethod to the Shape class (or one of its subclasses) called get_pi that returns the value of PI (3.14159).
"""
from abc import ABC, abstractmethod
import math

class Shape(ABC):
    @abstractmethod
    def area(self):
        pass

    @classmethod
    def describe_shapes(cls):
        print("Shapes are geometric figures")

    @staticmethod
    def get_pi():
        return math.pi
  
class Circle(Shape):
    def __init__(self, radius):
        self.radius = radius

    def area(self):
        return Shape.get_pi() * (self.radius ** 2)

    @staticmethod
    def calling_meth():
        print("it is calling")
class Rectangle(Shape):
    def __init__(self, width, height, circle):
        self.width = width
        self.height = height
        self.circle = circle

    def area(self):
        return self.width * self.height

    def dispplay(self):
        self.circle.calling_meth()


Shape.describe_shapes() 
# print("Value of PI:", Shape.get_pi())
#
circle = Circle(5)
# print(f"Circle area: {circle.area()}")
rectangle = Rectangle(4, 6, circle)
rectangle.dispplay()
# print(f"Rectangle area: {rectangle.area()}")

