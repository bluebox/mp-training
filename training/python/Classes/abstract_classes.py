from abc import ABC, abstractmethod, abstractproperty


class Vehicle(ABC):
    @abstractmethod
    def start(self):
        pass

    @property
    @abstractmethod
    def no_of_wheels(self):
        pass

    @no_of_wheels.setter
    @abstractmethod
    def no_of_wheels(self,value):
        pass

class bike(Vehicle):
    def __init__(self, val = None):
        if val != None:
            self.__no_of_wheels=val
        else:
            self.__no_of_wheels= 0
    @property
    def no_of_wheels(self):
        return self._no_of_wheels
    @no_of_wheels.setter
    def no_of_wheels(self,val):
        self._no_of_wheels = val

    def start(self):
        print("bike started")
def func():
    return "hello world"

bike1 = bike()
print(bike1.start())
bike1.no_of_wheels = 2
print(bike1.no_of_wheels)
try:
    bike1.no_of_wheels = func# Raises TypeError
except TypeError as e:
    print(f"Error: {e}")
print(bike1.no_of_wheels)