# module to define the class vehicle

class vehicle:
    def __init__(self):
        self.Milage = 1
        self.Name = ""
        self.Capacity = 0
        self.width = 0.0
        self.depth = 0.0
        self.number = ""
        self.manufacturer = ""
class Bus(vehicle):
    def __init__(self, Name, number, manufacturer):
        vehicle.__init__(self)
        self.Name = Name
        self.width = 10
        self.depth = 30
        self.Milage = 8
        self.Capacity = 50
        self.number = number
        self.manufacturer = manufacturer
    def display(self):
        print(self.Name)
        print(self.Milage)
        print(self.Capacity)
        print(self.width)
        print(self.depth)
        print(self.number)
        print(self.manufacturer)
li1 = []
li1.append(Bus("Tata Starbus", 120, "Tata"))
li1.append(Bus("Tata Elxsi", 121, "Tata"))
li1.append(Bus("Volvo Duplex", 121, "Volvo"))
# for i in li1:
#     print(i.display())
class Car(vehicle):
    def __init__(self, Name, number, manufacturer):
        vehicle.__init__(self)
        self.Name = Name
        self.width = 10
        self.depth = 5
        self.Milage = 20
        self.Capacity = 5
        self.number = number
        self.manufacturer = manufacturer
    def display(self):
        print(self.Name)
        print(self.Milage)
        print(self.Capacity)
        print(self.width)
        print(self.depth)
        print(self.number)
        print(self.manufacturer)
li2 = []
li2.append(Car("Tata Nexon", 220, "Tata"))
li2.append(Bus("Toyota Fortuner", 221, "Toyota"))
li2.append(Bus("Thar", 221, "Mahindra"))
# for i in li2:
#     print(i.display())
class Bike(vehicle):
    def __init__(self, Name, number, manufacturer):
        vehicle.__init__(self)
        self.Name = Name
        self.width = 4
        self.depth = 5
        self.Milage = 35
        self.Capacity = 2
        self.number = number
        self.manufacturer = manufacturer
    def display(self):
        print(self.Name)
        print(self.Milage)
        print(self.Capacity)
        print(self.width)
        print(self.depth)
        print(self.number)
        print(self.manufacturer)
li3 = []
li3.append(Car("Hero Honda", 320, "Hero"))
li3.append(Bus("Unicorn", 321, "Honda"))
li3.append(Bike("R15", 321, "Yamaha"))
# for i in li3:
#     print(i.display())
# for i in li4:
#     # print(i.display())
#     print(i.Parkarea())



