from datetime import datetime, timedelta
import datetime as dt



COUNT = 1
series = {}
TOTALAREA = 1000


class Vehicle:
    def __init__(self, milage, name, capacity, width, depth, number, manufacturer, in_time):
        self.milage = milage
        self.name = name
        self.capacity = capacity
        self.width = width
        self.depth = depth
        self.number = number
        self.manufacturer = manufacturer
        self.in_time = in_time


class Bus(Vehicle):
    def __init__(self, milage, name, capacity, width, depth, number, manufacturer, in_time):
        super().__init__(milage, name, capacity, width, depth, number, manufacturer, in_time)


class Car(Vehicle):
    def __init__(self, milage, name, capacity, width, depth, number, manufacturer, in_time):
        super().__init__(milage, name, capacity, width, depth, number, manufacturer, in_time)


class Bike(Vehicle):
    def __init__(self, milage, name, capacity, width, depth, number, manufacturer, in_time):
        super().__init__(milage, name, capacity, width, depth, number, manufacturer, in_time)


object_2 = Bus(10, "bmw", 12, 10, 12, "TS31F4775", "tata", "10:30")
object3 = Car(10, "bmw", 12, 10, 12, "TS31F4775", "tata", "10:30")
object_1 = Bike(10, "pulsar", 15, 10, 21, "TS11A1234", "tvs", "11:45")


def area1(length, breadth):
    return length * breadth


WID = input("enter the width of the lot: ")
DEP = input("enter the  depth of the lot: ")
TOTALAREA = float(WID) * float(DEP)
print("total area ", TOTALAREA, "sq mtrs")


class lotProject:
    def __init__(self, width, depth, number, series2, status, outTime):
        self.width = width
        self.depth = depth
        self.number = number
        self.series2 = series2
        self.status = status
        self.outTime = outTime
        self.T = area1(width, depth)

    series = {"A": {"Car": [[TOTALAREA * 0.6]],
    "Bike": [[TOTALAREA * 0.3]],
    "Bus": [[TOTALAREA * 0.1]]},
    "B": {"Car": [[TOTALAREA * 0.6]],
    "Bike": [[TOTALAREA * 0.3]],
    "Bus": [[TOTALAREA * 0.1]]},
    "C": {"Car": [[TOTALAREA * 0.6]],
    "Bike": [[TOTALAREA * 0.3]],
    "Bus": [[TOTALAREA * 0.1]]},
    "D": {"Car": [[TOTALAREA * 0.6]],
    "Bike": [[TOTALAREA * 0.3]],
    "Bus": [[TOTALAREA * 0.1]]}}

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
                [object.number, object.width * object.depth,object.in_time,
                object.name, object.manufacturer,object.capacity])
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
        in_time = str(datetime.now().time())[0:8]
        object_12 = Car(float(milage), Name, int(Capacity),
        int(width), int(Depth), Number, Manufacturer, in_time)
        return object_12
    elif command == "Bus":
        milage = input("enter the millage of Bus : ")
        Name = input("enter the name of Bus : ")
        Capacity = input("enter the Bus capacity : ")
        width = input("enter the Bus width : ")
        Depth = input("enter the depth of bus : ")
        Number = input("enter the Bus plate number : ")
        Manufacturer = input("enter bus Manufacturer : ")
        in_time = str(datetime.now().time())[0:8]
        object_12 = Bus(float(milage), Name, int(Capacity),
        int(width), int(Depth), Number, Manufacturer, in_time)
        return object_12
    elif command == "Bike":
        milage = input("enter the millage of Bike : ")
        Name = input("enter the name of Bike : ")
        Capacity = input("enter the Bike capacity : ")
        width = input("enter the Bike width : ")
        Depth = input("enter the depth of Bike : ")
        Number = input("enter the Bike plate number : ")
        Manufacturer = input("enter Bike Manufacturer : ")
        in_time = str(datetime.now().time())[0:8]
        object_12 = Bike(float(milage), Name, int(Capacity),
        int(width), int(Depth), Number, Manufacturer, in_time)
        return object_12


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
    global COUNT, WID, DEP
    if command == "P":
        width = WID
        depth = DEP

        series2 = "A"
        status = "free"
        now = datetime.now()
        current_time = str(now.strftime("%H:%M:%S"))
        # print(current_time)
        timestamp = str(timedelta(hours=1, minutes=25, seconds=50))
        # print(timestamp)

        t_1 = dt.datetime.strptime(current_time, '%H:%M:%S')
        t_2 = dt.datetime.strptime(timestamp, '%H:%M:%S')
        time_zero = dt.datetime.strptime('00:00:00', '%H:%M:%S')
        # print((t_1 - time_zero + t_2).time())
        timeout = (t_1 - time_zero + t_2).time()

        slot_1 = lotProject(int(width), int(depth), COUNT, series2, status, timeout)
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
        object_121 = command_handler(new_type)
        slot_1.addVehicle(object_121)
        COUNT += 1

    elif command == "E":
        print(lotProject.series)
        ch = input("enter the series A/B/C/D : ")
        vehicle_type = input("enter  vehicle Type Car/Bus/Bike : ")
        plate_number = input("enter the plate number to exit the Lot : ")
        a = 0
        b = 0
        flag = 0
        if flag == 0:
            for x in range(len(lotProject.series[ch][vehicle_type])):
                for y in range(len(lotProject.series[ch][vehicle_type][x])):
                    if plate_number == lotProject.series[ch][vehicle_type][x][y]:
                        a = x
                        # print("a= " + str(a))
                        b = y
                        # print("b= " + str(b))
                        flag = 1
                        break
        if flag == 0:
            print("not found")
            park()
        temp = lotProject.series[ch][vehicle_type][a][b + 1]
        curr_time = lotProject.series[ch][vehicle_type][a][b + 2]
        # print(temp)
        print("poped vechile is: " + str(lotProject.series[ch][vehicle_type].pop(a)))
        lotProject.series[ch][vehicle_type][0][0] += float(temp)
        # print(lotProject.series[ch][vehicle_Type][a][b + 2])
        h = input("enter the random timeout hour: ")
        m = input("enter the random timeout minutes: ")
        s = input("enter the random timeout seconds: ")
        timestampout = str(timedelta(hours=int(h), minutes=int(m), seconds=int(s)))
        # print(timestamp)
        current_time = curr_time

        t_1 = dt.datetime.strptime(current_time, '%H:%M:%S')
        t_2 = dt.datetime.strptime(timestampout, '%H:%M:%S')
        time_zero = dt.datetime.strptime('00:00:00', '%H:%M:%S')
        # print((t_1 - time_zero + t_2).time())
        timeout = (t_1 - time_zero + t_2).time()
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
    # global TOTALAREA
    park()


if __name__ == '__main__':
    main()
