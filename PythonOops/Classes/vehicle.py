from datetime import datetime


class Vehicle:
    def __init__(self, number, manufacturer,  name, mileage, in_time, capacity, width, depth):
        self.mileage = mileage
        self.name = name
        self.capacity = capacity
        self.width = width
        self.depth = depth
        self.number = number
        self.manufacturer = manufacturer
        self.in_time = in_time


class Bus (Vehicle):
    def __init__(self, number, manufacturer,  name, mileage=10, in_time=datetime(2022, 11, 10, 10, 10, 10), capacity=4, width=3, depth=5):
        super().__init__(number, manufacturer,  name, mileage, in_time, capacity, width, depth)


class Car (Vehicle):
    def __init__(self, number, manufacturer,  name, mileage=10, in_time= datetime(2022, 11, 10, 10, 10, 10), capacity=4, width=3, depth=5):
        super().__init__(number, manufacturer,  name, mileage, in_time, capacity, width, depth)


class Bike (Vehicle):
    def __init__(self, number, manufacturer,  name, mileage=10, in_time=datetime(2022, 11, 10, 10, 10, 10), capacity=4, width=3, depth=5):
        super().__init__(number, manufacturer,  name, mileage, in_time, capacity, width, depth)
