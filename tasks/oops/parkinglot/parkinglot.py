from datetime import datetime, timedelta
import datetime as dt



COUNT = 1
series = {}
TOTALAREA = 0


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


class LotProject:
    def __init__(self, width, depth, number, series2, status, outtime):
        self.width = width
        self.depth = depth
        self.number = number
        self.series2 = series2
        self.status = status
        self.outtime = outtime
        

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



    def addvehicle(self, object):
        i = 1
        c_1 = self.series2
        while i < 4:
            print(LotProject.series[c_1][type(object).__name__][0][0] - object.width * object.depth)
            if LotProject.series[c_1][type(object).__name__][0][0] - object.width * object.depth >= 0:
                LotProject.series[c_1][type(object).__name__][0][0] -= object.width * object.depth
                LotProject.series[c_1][type(object).__name__].insert(self.number,
                [object.number, object.width * object.depth,object.in_time,
                object.name, object.manufacturer,object.capacity])
                self.status = "occupied"
                print("Series Lot is " + c_1, "with vehicle type is", type(object).__name__)
                print(LotProject.series[c_1][type(object).__name__])
                print("slot allocated to the vehicle at {}".format(c_1))
                # print(LotProject.series)
                break
            else:
                c_1 = chr(ord(c_1) + 1)
                i += 1
        else:
            print("No free space in the parking")
        return object.width * object.depth

    @staticmethod
    def chargecalculation(time):
        # outtime = str(datetime.now().time())[0:8]
        # time1 = datetime.strptime(outtime, "%H:%M:%S")
        # time2 = datetime.strptime(intime, "%H:%M:%S")
        # time_spend = time1 - time2
        # time_spend1 = str(time_spend)
        h_1, m_1, s_1 = time.split(":")
        hours = int(h_1)
        minutes = int(m_1)
        second = int(s_1)
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
        name = input("enter the name of car : ")
        capacity = input("enter the car capacity : ")
        width = input("enter the car width : ")
        depth = input("enter the depth of car : ")
        number = input("enter the car plate number : ")
        manufacturer = input("enter the Manufacturer : ")
        in_time = str(datetime.now().time())[0:8]
        object_12 = Car(float(milage), name, int(capacity),
        int(width), int(depth), number, manufacturer, in_time)
        return object_12
    if command == "Bus":
        milage = input("enter the millage of Bus : ")
        name = input("enter the name of Bus : ")
        capacity = input("enter the Bus capacity : ")
        width = input("enter the Bus width : ")
        depth = input("enter the depth of bus : ")
        number = input("enter the Bus plate number : ")
        manufacturer = input("enter bus Manufacturer : ")
        in_time = str(datetime.now().time())[0:8]
        object_12 = Bus(float(milage), name, int(capacity),
        int(width), int(depth), number, manufacturer, in_time)
        return object_12
    if command == "Bike":
        milage = input("enter the millage of Bike : ")
        name = input("enter the name of Bike : ")
        capacity = input("enter the Bike capacity : ")
        width = input("enter the Bike width : ")
        depth = input("enter the depth of Bike : ")
        number = input("enter the Bike plate number : ")
        manufacturer = input("enter Bike Manufacturer : ")
        in_time = str(datetime.now().time())[0:8]
        object_12 = Bike(float(milage), name, int(capacity),
        int(width), int(depth), number, manufacturer, in_time)
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
    global COUNT
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

        slot_1 = LotProject(int(width), int(depth), COUNT, series2, status, timeout)
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
        slot_1.addvehicle(object_121)
        COUNT += 1

    elif command == "E":
        print(LotProject.series)
        c_h = input("enter the series A/B/C/D : ")
        vehicle_type = input("enter  vehicle Type Car/Bus/Bike : ")
        plate_number = input("enter the plate number to exit the Lot : ")
        a_1 = 0
        b_1 = 0
        flag = 0
        if flag == 0:
            for x_1 in range(len(LotProject.series[c_h][vehicle_type])):
                for y_1 in range(len(LotProject.series[c_h][vehicle_type][x_1])):
                    if plate_number == LotProject.series[c_h][vehicle_type][x_1][y_1]:
                        a_1 = x_1
                        # print("a= " + str(a))
                        b_1 = y_1
                        # print("b= " + str(b))
                        flag = 1
                        break
        if flag == 0:
            print("not found")
            park()
        temp = LotProject.series[c_h][vehicle_type][a_1][b_1 + 1]
        curr_time = LotProject.series[c_h][vehicle_type][a_1][b_1 + 2]
        # print(temp)
        print("poped vechile is: " + str(LotProject.series[c_h][vehicle_type].pop(a_1)))
        LotProject.series[c_h][vehicle_type][0][0] += float(temp)
        # print(LotProject.series[ch][vehicle_Type][a][b + 2])
        h_1 = input("enter the random timeout hour: ")
        m_1 = input("enter the random timeout minutes: ")
        s_1 = input("enter the random timeout seconds: ")
        timestampout = str(timedelta(hours=int(h_1), minutes=int(m_1), seconds=int(s_1)))
        # print(timestamp)
        current_time = curr_time

        t_1 = dt.datetime.strptime(current_time, '%H:%M:%S')
        t_2 = dt.datetime.strptime(timestampout, '%H:%M:%S')
        time_zero = dt.datetime.strptime('00:00:00', '%H:%M:%S')
        # print((t_1 - time_zero + t_2).time())
        timeout = (t_1 - time_zero + t_2).time()
        print("time out at : " + str(timeout))
        print("parked for : " + str(timestampout))
        print("charges will be :" + str(LotProject.chargecalculation(str(timestampout)))+"/RS")

        # print(LotProject.series)

    elif command == "R":
        print("First Hour - 20$\n"
              "next nine hours - 10$/hour\n"
              "after ten hours - 5$/hour\n"
              "time exceeds more than 30 minutes of an hour - "
              "full charges collected of that hour \n")
    elif command == "V":
        print(LotProject.series)


def main():
    # global TOTALAREA
    park()


if __name__ == '__main__':
    main()
