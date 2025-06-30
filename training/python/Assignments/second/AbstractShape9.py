from abc import ABC,abstractmethod
import math
class Shape(ABC):
    @abstractmethod
    def area(self):
        pass
    @classmethod
    def describe_shapes(cls):
        return "Shapes are fundamental geometric figures"

    @staticmethod
    def get_pi():
        return math.pi

class Circle(Shape):
    def area(self,radius):
        return self.get_pi()*radius*radius
class Rectangle(Shape):
    def area(self,width,height):
        return width*height
    
c=Circle()
r=Rectangle()
print(c.area(7))
print(Shape.get_pi())
print(Shape.describe_shapes())
    


