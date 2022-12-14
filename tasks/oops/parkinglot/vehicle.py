"""defines vehicle"""


class Vehicle:
    """class vehicle"""

    def __init__(self, milage, name, capacity, width, depth, number, manufacturer, intime=0):
        self.milage = milage
        self.name = name
        self.capacity = capacity
        self.width = width
        self.depth = depth
        self.number = number
        self.manufacturer = manufacturer
        self.intime = intime
