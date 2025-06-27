# Task 5: Inheritance, super(), and Polymorphism
#
# Goal: Design an inheritance hierarchy and use super().
#
#     Create a base class Employee with attributes like name, id, salary.
#
#     Create subclasses Developer and Manager that inherit from Employee.
#
#     Use super() to initialize parent class attributes.
#
#     Add a method calculate_bonus() in each subclass with different implementations.
#
#     Create a list of employees of mixed types and print their bonuses using polymorphism.
"""Task 5: Inheritance, super(), and Polymorphism"""
class Employee:
    """Base class Employee with attributes like name, id, salary."""
    def __init__(self,name,id,salary):
        self.name = name
        self.id = id
        self.salary = salary

    def calculate_bonus(self):
        """No bonus"""
        return 0

class Developer(Employee):
    """subclass Developer that inherit from Employee"""
    def __init__(self,name,id,salary,department,hours_worked):
        super().__init__(name,id,salary)
        self.department = department
        self.hours_worked = hours_worked

    def calculate_bonus(self):
        """calculate bonus based on number of hours worked"""
        return self.hours_worked * 1000

class Manager(Employee):
    """subclass Manager that inherit from Employee"""
    def __init__(self,name,id,salary,department,projects_deployed):
        super().__init__(name,id,salary)
        self.department = department
        self.projects_deployed = projects_deployed

    def calculate_bonus(self):
        """calculate bonus based on number of projects successfully deployed"""
        return self.projects_deployed * 2000

dev = Developer("Alice", 1, 50000, "Engineering", 40)
mgr = Manager("Bob", 2, 70000, "Engineering", 5)

print(f"{dev.name}'s bonus is {dev.calculate_bonus()}")
print(f"{mgr.name}'s bonus is {mgr.calculate_bonus()}")
