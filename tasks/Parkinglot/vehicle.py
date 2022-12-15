"""vehicle module"""
import datetime


class Vehicle:
    """vehicle class """

    def __init__(self, name, reg_no, manufacturer, mileage, length, width, parking_no=None):
        self.v_name = name
        self.v_reg_no = reg_no
        self.v_manufacturer = manufacturer
        self.v_mileage = mileage
        self.v_parking_no = parking_no
        self.v_width = int(width)
        self.v_length = int(length)
        self.v_in_time = None
        self.v_time_diff = None

    def exit_time(self):
        self.v_time_diff = datetime.datetime.now() - self.v_in_time
        return self.v_time_diff.total_seconds()

    def entry_time(self):
        self.v_in_time = datetime.datetime(2022, 11, 11, 18, 25, 30)
        print(self.v_in_time)


class Car(Vehicle):
    """Car class that inherits the property of Vehicle class"""

    def __init__(self, name, reg_no, manufacturer, mileage, length, width, parking_no=None):
        super().__init__(name, reg_no, manufacturer, mileage, length, width, parking_no)


class Bike(Vehicle):
    """Bike class that inherits the property of Vehicle class"""

    def __init__(self, name, reg_no, manufacturer, mileage, length, width, parking_no=None):
        super().__init__(name, reg_no, manufacturer, mileage, length, width, parking_no)


class Bus(Vehicle):
    """Bus class that inherits the property of Vehicle class"""

    def __init__(self, name, reg_no, manufacturer, mileage, length, width, parking_no=None):
        super().__init__(name, reg_no, manufacturer, mileage, length, width, parking_no)
