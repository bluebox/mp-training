"""Parking Lot"""

from datetime import datetime, timedelta
import datetime as dt
from vehicle import *


COUNT = 1
series = {}
print("\n")
width_1 = input("Enter the Width of the Parking Lot: ")
depth_1 = input("Enter the Depth of the Parking Lot: ")
total_area = float(width_1) * float(depth_1)
print("\nTotal Area of Parking Lot: ", total_area, "sq mtrs\n")


class ParkingLot:
    """Parking Lot"""
    def __init__(self, width, depth, number, series2, status, out_time):
        self.width = width
        self.depth = depth
        self.number = number
        self.series2 = series2
        self.status = status
        self.out_time = out_time

    series = {"A": {"Car": [[total_area * 0.6]],
    "Bike": [[total_area * 0.3]],
    "Bus": [[total_area * 0.1]]},
    "B": {"Car": [[total_area * 0.6]],
    "Bike": [[total_area * 0.3]],
    "Bus": [[total_area * 0.1]]},
    "C": {"Car": [[total_area * 0.6]],
    "Bike": [[total_area * 0.3]],
    "Bus": [[total_area * 0.1]]},
    "D": {"Car": [[total_area * 0.6]],
    "Bike": [[total_area * 0.3]],
    "Bus": [[total_area * 0.1]]}}


    def add_vehicle(self, object):
        """Allocating Slot for Vehicle"""
        i = 1
        c = self.series2
        while i < 4:
            print(ParkingLot.series[c][type(object).__name__][0][0] - object.width * object.depth)
            if ParkingLot.series[c][type(object).__name__][0][0] - object.width * object.depth >= 0:
                ParkingLot.series[c][type(object).__name__][0][0] -= object.width * object.depth
                ParkingLot.series[c][type(object).__name__].insert(self.number,
                [object.number, object.width * object.depth,object.in_time,
                object.name, object.manufacturer,object.capacity])
                self.status = "Occupied"
                print("Series Lot is " + c, "with vehicle type is", type(object).__name__)
                print(ParkingLot.series[c][type(object).__name__])
                print(f"slot allocated to the vehicle at {c}")
                break
            else:
                c = chr(ord(c) + 1)
                i += 1
        else:
            print("No free slots in the parking")
        return object.width * object.depth

    @staticmethod
    def charge_calculation(time):
        """Calculating Charges"""
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
    """Vehicle"""
    if command == "Car":
        mileage = input("Enter Car's mileage: ")
        name = input("Enter Car's name: ")
        capacity = input("Enter Car's Capacity: ")
        width = input("Enter Car's width: ")
        depth = input("Enter Car's Depth: ")
        number = input("Enter Car's Number : ")
        manufacturer = input("Enter Car's Manufacturer: ")
        print("\n")
        in_time = str(datetime.now().time())[0:8]
        car_object = Car(float(mileage), name, int(capacity),
        int(width), int(depth), number, manufacturer, in_time)
        return car_object,"\n"
    elif command == "Bus":
        mileage = input("Enter Bus's mileage: ")
        name = input("Enter Bus's name: ")
        capacity = input("Enter Bus's Capacity: ")
        width = input("Enter Bus's width: ")
        depth = input("Enter Bus's Depth: ")
        number = input("Enter Bus's Number : ")
        manufacturer = input("Enter Bus's Manufacturer: ")
        print("\n")
        in_time = str(datetime.now().time())[0:8]
        bus_object = Bus(float(mileage), name, int(capacity),
        int(width), int(depth), number, manufacturer, in_time)
        return bus_object,"\n"
    elif command == "Bike":
        mileage = input("Enter Bike's mileage: ")
        name = input("Enter Bike's name: ")
        capacity = input("Enter Bike's Capacity: ")
        width = input("Enter Bike's width: ")
        depth = input("Enter Bike's Depth: ")
        number = input("Enter Bike's Number : ")
        manufacturer = input("Enter Bike's Manufacturer: ")
        print("\n")
        in_time = str(datetime.now().time())[0:8]
        bike_object = Bike(float(mileage), name, int(capacity),
        int(width), int(depth), number, manufacturer, in_time)
        return bike_object,"\n"


def park():
    """Menu"""
    command = ""
    while command != "Q":
        print("Please Select An Option:\n"
            "\n"
              "P - Park a Vehicle\n"
              "E - Exit from the Lot\n"
              "V - View a Parked Vehicle\n"
              "R - Display Vehicle rate  Details for lot Parking\n"
              "Q - Quit Application\n")

        command = input("Enter: ")
        print("\n")
        command_handle(command)


def command_handle(command):
    """Command"""
    global COUNT
    if command == "P":
        width = width_1
        depth = depth_1

        series2 = "A"
        status = "free"
        now = datetime.now()
        current_time = str(now.strftime("%H:%M:%S"))
        timestamp = str(timedelta(hours=1, minutes=25, seconds=50))

        t_1 = dt.datetime.strptime(current_time, '%H:%M:%S')
        t_2 = dt.datetime.strptime(timestamp, '%H:%M:%S')
        time_zero = dt.datetime.strptime('00:00:00', '%H:%M:%S')
        timeout = (t_1 - time_zero + t_2).time()

        slot_1 = ParkingLot(int(width), int(depth), COUNT, series2, status, timeout)
        typ = input("Enter Vehicle Type: \n"
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
        slot_1.add_vehicle(object_121)
        COUNT += 1

    elif command == "E":
        print(ParkingLot.series)
        ch = input("Enter the series A/B/C/D : ")
        vehicle_type = input("Enter Vehicle Type Car/Bus/Bike : ")
        plate_number = input("Enter the Vehicle Number to exit the Lot : ")
        a = 0
        b = 0
        flag = 0
        if flag == 0:
            for x in range(len(ParkingLot.series[ch][vehicle_type])):
                for y in range(len(ParkingLot.series[ch][vehicle_type][x])):
                    if plate_number == ParkingLot.series[ch][vehicle_type][x][y]:
                        a = x
                        b = y
                        flag = 1
                        break
        if flag == 0:
            print("not found")
            park()
        temp = ParkingLot.series[ch][vehicle_type][a][b + 1]
        curr_time = ParkingLot.series[ch][vehicle_type][a][b + 2]
        print("poped vechile is: " + str(ParkingLot.series[ch][vehicle_type].pop(a)))
        ParkingLot.series[ch][vehicle_type][0][0] += float(temp)
        h = input("Enter the random timeout hour: \n")
        m = input("Enter the random timeout minutes: \n")
        s = input("Enter the random timeout seconds: \n")
        timestampout = str(timedelta(hours=int(h), minutes=int(m), seconds=int(s)))
        current_time = curr_time

        t_1 = dt.datetime.strptime(current_time, '%H:%M:%S')
        t_2 = dt.datetime.strptime(timestampout, '%H:%M:%S')
        time_zero = dt.datetime.strptime('00:00:00', '%H:%M:%S')
        timeout = (t_1 - time_zero + t_2).time()
        print("Vehicle withdrawn at : " + str(timeout))
        print("Parked for : " + str(timestampout))
        print("Amount to be paid :" + str(ParkingLot.charge_calculation(str(timestampout)))+"/RS")


    elif command == "R":
        print("First Hour - 20$\n"
              "next nine hours - 10$/hour\n"
              "after ten hours - 5$/hour\n"
              "time > 30 minutes of an hour - full charges collected of that hour \n")
    elif command == "V":
        print(ParkingLot.series)

park()
