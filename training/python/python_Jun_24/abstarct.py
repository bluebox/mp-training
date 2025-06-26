from abc import ABC, abstractmethod

class Car(ABC):
    @property
    @abstractmethod
    def sound(self):
        pass  
    def start(self):
        return "concrete function car is starting"

class kia(Car):
    def display(self):
        pass
class suzuki(Car):
    
    def sound(self):
        return "its suzuki"  

class tata(Car):

    def sound(self):
        return "its tata"
    def start(self):
        return "tata car started"
    
car = tata()
car2=suzuki()
# c=Car()
print(car.sound())
print(car2.sound())
# print(c.start())