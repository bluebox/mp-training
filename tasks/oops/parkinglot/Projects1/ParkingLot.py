# to create parking locality and its functionality
import time
import random
import Vehicle
li1 = []
li1.append(Vehicle.Bus("Tata Starbus", 120, "Tata"))
li1.append(Vehicle.Bus("Tata Elxsi", 121, "Tata"))
li1.append(Vehicle.Bus("Volvo Duplex", 121, "Volvo"))
#
li2 = []
li2.append(Vehicle.Car("Tata Nexon", 220, "Tata"))
li2.append(Vehicle.Bus("Toyota Fortuner", 221, "Toyota"))
li2.append(Vehicle.Bus("Thar", 221, "Mahindra"))
#
li3 = []
li3.append(Vehicle.Car("Hero Honda", 320, "Hero"))
li3.append(Vehicle.Bus("Unicorn", 321, "Honda"))
li3.append(Vehicle.Bike("R15", 321, "Yamaha"))
#

class ParkLot:
    def __init__(self, width, depth, series):
        self.width = width
        self.depth = depth
        self.number = dict()
        self.maxbus = 0
        self.maxcar = 0
        self.maxbike = 0
        self.curbus = 0
        self.curcar = 0
        self.curbike = 0
        self.numbus = []
        self.numcar = []
        self.numbike = []
        self.series = series
        self.status = "free"
        self.intime = 0
    def display(self):
        print(self.width)
        print(self.depth)
        print(self.number)
        print(self.series)
        print(self.status)
        print(self.intime)
    def Parkarea(self):
        area = self.width * self.depth
        # print(self.width)
        self.maxbus = area // 570
        self.maxcar = self.maxbus * 3
        self.maxbike = self.maxcar * 2
        # print(self.maxbus)
        # print(self.maxcar)
        # print(self.maxbike)
    def allocate(self, type , n):
            # print(type)
            if type == 'bus':
                # print("c")
                if self.curbus < self.maxbus:
                   print(self.maxbus)
                   self.number[n] = time.time()
                   self.curbus += 1
                   self.numbus.append(n)
                   print(self.curbus)
                   return 1
            if type == 'car':
                if self.curcar < self.maxcar:
                    self.number[n] = time.time()
                    self.curcar += 1
                    self.numcar.append(n)
                    return 1
            if type == 'bike':
                if self.curbike < self.maxbike:
                    self.number[n] = time.time()
                    self.curbike += 1
                    self.numbike.append(n)
                    return 1
    def dealloc(self):
        n = random.randrange(1, 4)
        if n == 1:
            n1 = random.randrange(0, len(self.numbus))
            del self.number[self.numbus[n1]]
            self.curbus -= 1
            return self.numbus.pop(n1)
        if n == 2:
            n1 = random.randrange(0, len(self.numcar))
            del self.number[self.numcar[n1]]
            self.curcar -= 1
            return self.numcar.pop(n1)
        if n == 3:
            n1 = random.randrange(0, len(self.numbike))
            del self.number[self.numbike[n1]]
            self.curbike -= 1
            return self.numbike.pop(n1)
    def availability(self, type, n):
        n1 = self.allocate(self, type, n)
        if n1 == 0:
            print("It is not allocated")
        else:
            print("It is allocated")
    def charge(self):
        n2 = self.dealloc()
        print(n2)
li4 = []
li4.append(ParkLot(57, 10, "A"))
li4.append(ParkLot(114, 10, "B"))
li4.append(ParkLot(171, 10, "C"))
li4.append(ParkLot(228, 10, "D"))
# for i in li4:
#     i.Parkarea()
# for i in li4:
#     # print(i.display())
#     i.allocate("bus", 123)
#main function
B = ParkLot
B.allocate("Bus", 123)