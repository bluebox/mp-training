from abc import ABC, abstractmethod


class Shape(ABC):
    @abstractmethod
    def area(self):
        pass

    @classmethod
    def describe_shapes(cls):
        print("Shapes are fundamental geometric figures.")
        return cls

    @staticmethod
    def get_Pi():
        return 3.143


class Circle(Shape):
    def __init__(self, radius):
        self.radius = radius

    def area(self):
        return self.get_Pi() * self.radius * self.radius


class Rectangle(Shape):
    def __init__(self, width, height):
        self.width = width
        self.height = height

    def area(self):
        return self.height * self.width


c = Circle(3.4)
r = Rectangle(2, 5)
p = r.describe_shapes()
p.describe_shapes()
print(c.area())
print(r.area())