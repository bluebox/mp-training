class Vehicle:
    def __init__(self, mileage, name, capacity, width, depth, number, manufacturer, in_time):
        self.mileage = mileage
        self.name = name
        self.capacity = capacity
        self.width = width
        self.depth = depth
        self.number = number
        self.manufacturer = manufacturer
        self.in_time = in_time

    def display(self):
        return self.mileage, self.name, self.capacity, self.width, self.depth, self.number, self.manufacturer, self.in_time


class Bus(Vehicle):
    def __init__(self, mileage, name, capacity, width, depth, number, manufacturer, in_time):
        super().__init__(mileage, name, capacity, width, depth, number, manufacturer, in_time)


class Car(Vehicle):
    def __init__(self, mileage, name, capacity, width, depth, number, manufacturer, in_time):
        super().__init__(mileage, name, capacity, width, depth, number, manufacturer, in_time)


class Bike(Vehicle):
    def __init__(self, mileage, name, capacity, width, depth, number, manufacturer, in_time):
        super().__init__(mileage, name, capacity, width, depth, number, manufacturer, in_time)