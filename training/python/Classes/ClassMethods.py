import math

#class methods are mainly used for factory methods
class circle:
    def __init__(self, radius):
        self.radius = radius
    @classmethod
    def classFromDiameter(cls,dia):
        rad = dia/2
        return cls(rad)

    def area(self):
        return math.pi*(self.radius**2)

    def perimeter(self):
        return 2*math.pi*(self.radius)

c1 = circle.classFromDiameter(10)
print(c1.area())
print(c1.perimeter())