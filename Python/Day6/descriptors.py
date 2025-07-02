class Yes:
    def __get__(self, instance, owner):
        return "sljfslfjdlfsldfl"

class TestClass:
    a=Yes()
b=TestClass()
print(b.a)


class PositiveInteger:
    def __set_name__(self, owner, name):
        self.name = name
        print(f"Descriptor created for attribute: {name}")

    def __get__(self, instance, owner):
        return instance.__dict__.get(self.name)

    def __set__(self, instance, value):
        if not isinstance(value, int):
            raise TypeError(f"{self.name} must be an integer")
        if value < 0:
            raise ValueError(f"{self.name} must be >= 0")
        instance.__dict__[self.name] = value
class Person:
    age = PositiveInteger()
    height = PositiveInteger()

    def __init__(self, age, height):
        self.age = age
        self.height = height
a=Person(20,30)
print(a.age)
a.age=10


