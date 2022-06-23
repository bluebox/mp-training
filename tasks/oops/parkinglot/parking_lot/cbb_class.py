"""This is a Class file inhertiting the vechile properties"""
import vechile_class

class Car(vechile_class.Vechile):
    """car class with vechile inherit properties"""
    def __init__(self, numberplate, Milage, Name, Capacity, Manufacturer):
        super().__init__(numberplate, Milage, Name, Capacity, Manufacturer)

class Bike(vechile_class.Vechile):
    """Bike class with vechile inherit properties"""
    def __init__(self, numberplate, Milage, Name, Capacity, Manufacturer):
        super().__init__(numberplate, Milage, Name, Capacity, Manufacturer)

class Bus(vechile_class.Vechile):
    """Bus class with vechile inherit properties"""
    def __init__(self, numberplate, Milage, Name, Capacity, Manufacturer):
        super().__init__(numberplate, Milage, Name, Capacity, Manufacturer)
