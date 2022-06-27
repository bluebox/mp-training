from datetime import datetime, timedelta
import datetime as dt



count = 1
series = {}
totalArea = 1000


class Vehicle:
    def __init__(self, milage, name, capacity, width, depth, number, manufacturer, inTime):
        self.milage = milage
        self.name = name
        self.capacity = capacity
        self.width = width
        self.depth = depth
        self.number = number
        self.manufacturer = manufacturer
        self.inTime = inTime


class Bus(Vehicle):
    def __init__(self, milage, name, capacity, width, depth, number, manufacturer, inTime):
        super().__init__(milage, name, capacity, width, depth, number, manufacturer, inTime)


class Car(Vehicle):
    def __init__(self, milage, name, capacity, width, depth, number, manufacturer, inTime):
        super().__init__(milage, name, capacity, width, depth, number, manufacturer, inTime)


class Bike(Vehicle):
    def __init__(self, milage, name, capacity, width, depth, number, manufacturer, inTime):
        super().__init__(milage, name, capacity, width, depth, number, manufacturer, inTime)


object2 = Bus(10, "bmw", 12, 10, 12, "TS31F4775", "tata", "10:30")
object3 = Car(10, "bmw", 12, 10, 12, "TS31F4775", "tata", "10:30")
object1 = Bike(10, "pulsar", 15, 10, 21, "TS11A1234", "tvs", "11:45")


def area1(length, breadth):
    return length * breadth


wid = input("enter the width of the lot: ")
dep = input("enter the  depth of the lot: ")
totalArea = float(wid) * float(dep)
print("total area ", totalArea, "sq mtrs")


class lotProject:
    T = 0

    series1 = {"A": {"Buses": [[area1(0.1, T)]], "Car": [[area1(0.6, T)]], "Bike": [[area1(0.3, T)]]},
               "B": {"Buses": [[area1(0.1, T)]], "Car": [[area1(0.6, T)]], "Bike": [[area1(0.3, T)]]},
               "C": {"Buses": [[area1(0.1, T)]], "Car": [[area1(0.6, T)]], "Bike": [[area1(0.3, T)]]},
               "D": {"Buses": [[area1(0.1, T)]], "Car": [[area1(0.6, T)]], "Bike": [[area1(0.3, T)]]}, }

    def __init__(self, width, depth, number, series2, status, outTime):
        self.width = width
        self.depth = depth
        self.number = number
        self.series2 = series2
        self.status = status
        self.outTime = outTime
        self.T = area1(width, depth)

    series = {"A": {"Car": [[totalArea * 0.6]], "Bike": [[totalArea * 0.3]], "Bus": [[totalArea * 0.1]]},
              "B": {"Car": [[totalArea * 0.6]], "Bike": [[totalArea * 0.3]], "Bus": [[totalArea * 0.1]]},
              "C": {"Car": [[totalArea * 0.6]], "Bike": [[totalArea * 0.3]], "Bus": [[totalArea * 0.1]]},
              "D": {"Car": [[totalArea * 0.6]], "Bike": [[totalArea * 0.3]], "Bus": [[totalArea * 0.1]]}}

    def fun(self):
        print(self.width)
        print(self.depth)
        print(self.number)
        print(self.series)
        print(self.status)
        print(self.outTime)

    def addVehicle(self, object):
        i = 1
        c = self.series2
        while i < 4:
            print(lotProject.series[c][type(object).__name__][0][0] - object.width * object.depth)
            if lotProject.series[c][type(object).__name__][0][0] - object.width * object.depth >= 0:
                lotProject.series[c][type(object).__name__][0][0] -= object.width * object.depth
                lotProject.series[c][type(object).__name__].insert(self.number,
                                                                   [object.number, object.width * object.depth,
                                                                    object.inTime, object.name, object.manufacturer,
                                                                    object.capacity])
                self.status = "occupied"
                print("Series Lot is " + c, "with vehicle type is", type(object).__name__)
                print(lotProject.series[c][type(object).__name__])
                print("slot allocated to the vehicle at {}".format(c))
                # print(lotProject.series)
                break
            else:
                c = chr(ord(c) + 1)
                i += 1
        else:
            print("No free space in the parking")
        return object.width * object.depth

    @staticmethod
    def chargeCalculation(time):
        # outtime = str(datetime.now().time())[0:8]
        # time1 = datetime.strptime(outtime, "%H:%M:%S")
        # time2 = datetime.strptime(intime, "%H:%M:%S")
        # time_spend = time1 - time2
        # time_spend1 = str(time_spend)
        h, m, s = time.split(":")
        hours = int(h)
        minutes = int(m)

        charge = 0
        if hours == 0 and minutes > 30:
            charge += 20
        elif hours == 10 and minutes > 30:
            hours -= 1
            charge += 20
            charge += hours * 10
            if minutes > 30:
                charge += 5
        elif hours > 0 and hours <= 10:
            hours -= 1
            charge += 20
            charge += hours * 10
            if minutes > 30:
                charge += 10
        elif hours > 10:
            hours -= 1
            charge += 20
            charge += 9 * 10
            hours -= 9
            charge += hours * 5
            if minutes > 30:
                charge += 5
        return charge


def command_handler(command):
    if command == "Car":
        milage = input("enter the millage of car : ")
        Name = input("enter the name of car : ")
        Capacity = input("enter the car capacity : ")
        width = input("enter the car width : ")
        Depth = input("enter the depth of car : ")
        Number = input("enter the car plate number : ")
        Manufacturer = input("enter the Manufacturer : ")
        inTime = str(datetime.now().time())[0:8]
        object12 = Car(float(milage), Name, int(Capacity), int(width), int(Depth), Number, Manufacturer, inTime)
        return object12
    elif command == "Bus":
        milage = input("enter the millage of Bus : ")
        Name = input("enter the name of Bus : ")
        Capacity = input("enter the Bus capacity : ")
        width = input("enter the Bus width : ")
        Depth = input("enter the depth of bus : ")
        Number = input("enter the Bus plate number : ")
        Manufacturer = input("enter bus Manufacturer : ")
        inTime = str(datetime.now().time())[0:8]
        object12 = Bus(float(milage), Name, int(Capacity), int(width), int(Depth), Number, Manufacturer, inTime)
        return object12
    elif command == "Bike":
        milage = input("enter the millage of Bike : ")
        Name = input("enter the name of Bike : ")
        Capacity = input("enter the Bike capacity : ")
        width = input("enter the Bike width : ")
        Depth = input("enter the depth of Bike : ")
        Number = input("enter the Bike plate number : ")
        Manufacturer = input("enter Bike Manufacturer : ")
        inTime = str(datetime.now().time())[0:8]
        object12 = Bike(float(milage), Name, int(Capacity), int(width), int(Depth), Number, Manufacturer, inTime)
        return object12


def park():
    command = ""
    while command != "Q":
        print("Please Select An Option:\n"
              "P - Park a Vehicle\n"
              "E - Exit from the Lot\n"
              "V - View a Parked Vehicle\n"
              "R - Display Vehicle rate  Details for lot Parking\n"
              "Q - Quit Application\n")

        command = input("Enter:")
        command_handle(command)


def command_handle(command):
    global count, wid, dep
    if command == "P":
        Width = wid
        Depth = dep

        series2 = "A"
        status = "free"
        now = datetime.now()
        current_time = str(now.strftime("%H:%M:%S"))
        # print(current_time)
        timestamp = str(timedelta(hours=1, minutes=25, seconds=50))
        # print(timestamp)

        t1 = dt.datetime.strptime(current_time, '%H:%M:%S')
        t2 = dt.datetime.strptime(timestamp, '%H:%M:%S')
        time_zero = dt.datetime.strptime('00:00:00', '%H:%M:%S')
        # print((t1 - time_zero + t2).time())
        timeout = (t1 - time_zero + t2).time()

        slot1 = lotProject(int(Width), int(Depth), count, series2, status, timeout)
        typ = input("Enter Vehicle Type:\n"
                    "1. Car\n"
                    "2. Bus\n"
                    "3. Bike\n"
                    ">")
        if typ == "1":
            new_type = "Car"
        elif typ == "2":
            new_type = "Bus"
        elif typ == "3":
            new_type = "Bike"
        print(new_type)
        object121 = command_handler(new_type)
        slot1.addVehicle(object121)
        count += 1

    elif command == "E":
        print(lotProject.series)
        ch = input("enter the series A/B/C/D : ")
        vehicle_Type = input("enter  vehicle Type Car/Bus/Bike : ")
        plate_Number = input("enter the plate number to exit the Lot : ")
        a = 0
        b = 0
        flag = 0
        if flag == 0:
            for x in range(len(lotProject.series[ch][vehicle_Type])):
                for y in range(len(lotProject.series[ch][vehicle_Type][x])):
                    if plate_Number == lotProject.series[ch][vehicle_Type][x][y]:
                        a = x
                        # print("a= " + str(a))
                        b = y
                        # print("b= " + str(b))
                        flag = 1
                        break
        if flag == 0:
            print("not found")
            park()
        temp = lotProject.series[ch][vehicle_Type][a][b + 1]
        currentTime = lotProject.series[ch][vehicle_Type][a][b + 2]
        # print(temp)
        print("poped vechile is: " + str(lotProject.series[ch][vehicle_Type].pop(a)))
        lotProject.series[ch][vehicle_Type][0][0] += float(temp)
        # print(lotProject.series[ch][vehicle_Type][a][b + 2])
        h = input("enter the random timeout hour: ")
        m = input("enter the random timeout minutes: ")
        s = input("enter the random timeout seconds: ")
        timestampout = str(timedelta(hours=int(h), minutes=int(m), seconds=int(s)))
        # print(timestamp)
        current_time = currentTime

        t1 = dt.datetime.strptime(current_time, '%H:%M:%S')
        t2 = dt.datetime.strptime(timestampout, '%H:%M:%S')
        time_zero = dt.datetime.strptime('00:00:00', '%H:%M:%S')
        # print((t1 - time_zero + t2).time())
        timeout = (t1 - time_zero + t2).time()
        print("time out at : " + str(timeout))
        print("parked for : " + str(timestampout))
        print("charges will be :" + str(lotProject.chargeCalculation(str(timestampout)))+"/RS")

        # print(lotProject.series)

    elif command == "R":
        print("First Hour - 20$\n"
              "next nine hours - 10$/hour\n"
              "after ten hours - 5$/hour\n"
              "time exceeds more than 30 minutes of an hour - full charges collected of that hour \n")
    elif command == "V":
        print(lotProject.series)


def main():
    global totalArea
    park()


if __name__ == '__main__':
    main()
