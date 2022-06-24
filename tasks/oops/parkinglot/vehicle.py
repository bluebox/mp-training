class Vehicle:

    def __init__(self,milage,name,capacity,width,depth,number,manufacturer,time):
        self.name=name
        self.milage=milage
        self.capacity=capacity
        self.width=width
        self.manufacturer=manufacturer
        self.depth=depth
        self.number=number
        self.time=time
    def __str__(self):
        return self.name