from new3 import lot
import random

class Vehicle:
    def __init__(self, Milage = "", Name = "", Capacity = "", Width = "", Depth = "", Number = "", Manufacturer = ""):
        self.Milage = Milage
        self.Name = Name
        self.Capacity = Capacity
        self.Width = Width
        self.Depth = Depth
        self.Number = Number
        self.Manufacturer = Manufacturer

class Bus(Vehicle):
    def __init__(self, Milage, Name, Capacity, Width, Depth, Number, Manufacturer):
        Vehicle.__init__(self, Milage, Name, Capacity, Width, Depth, Number, Manufacturer)
        self.Wheels = 8
        self.size = "large"
        
        if self.Depth > 20 or self.Width > 60:
            del self
            raise Exception("it isnt a bus")
        
class Car(Vehicle):
    def __init__(self, Milage, Name, Capacity, Width, Depth, Number, Manufacturer):
        Vehicle.__init__(self, Milage, Name, Capacity, Width, Depth, Number, Manufacturer)
        self.Wheels = 4
        self.size = "medium"
        if self.Depth > 15 or self.Width > 30:
            del self
            raise Exception("it isnt a car")
            
class Bike(Vehicle):
    def __init__(self, Milage, Name, Capacity, Width, Depth, Number, Manufacturer):
        super().__init__(Milage, Name, Capacity, Width, Depth, Number, Manufacturer)
        self.Wheels = 2
        self.size = "small"
        if self.Depth > 5 or self.Width > 15:
            del self
            raise Exception("it isnt a bike")

bus = Bus(12, "volvo", 100, 60, 20, 2, "benz")
bus1 = Bus(12, "volvo", 100, 60, 20, 3, "benz")
bus2 = Bus(12, "volvo", 100, 60, 20, 4, "benz")
bus3 = Bus(12, "volvo", 100, 60, 20, 5, "benz")
parkingLot = lot(100, 50, 1)
parkingLot.addVehicleToLot(bus, 10, "11:AM")
parkingLot.addVehicleToLot(bus1, 10, "11:AM")
parkingLot.addVehicleToLot(bus2, 20, "11:AM")
parkingLot.addVehicleToLot(bus3, 6, "11:AM")
print(parkingLot.randomExit('bus'))
print(parkingLot.getLotDetails())