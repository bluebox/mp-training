from Classes.ParkingLot import Lot
from datetime import datetime
from Classes.vehicle import Car
from Classes.vehicle import Bus
from Classes.vehicle import Bike


def define_vehicle():
    number = input('Enter vehicle number: ')
    manufacturer = input('Enter vehicle manufacturer: ')
    name = input("Enter name of the vehicle: ")
    time = datetime(2022, 11, 10, 10, 5, 2)
    car_type = int(input('Enter type of Vehicle 1: Car, 2: Bike, 3: Bus: '))
    if car_type == 1:
        return Car(number, manufacturer,  name, in_time=time)
    elif car_type == 2:
        return Bike(number, manufacturer,  name, in_time=time)
    else:
        return Bus(number, manufacturer,  name, in_time=time)


def define_lot():
    return [Lot(200, 400, 'A', True), Lot(250, 300, 'B', True), Lot(150, 450, 'C', True), Lot(100, 500, 'D', True)]


def percentage_of_area(depth, width, percentage):
    area = depth * width
    return area * percentage / 100


def init_lot(depth, width):
    percentage_car = 60
    percentage_bike = 30
    percentage_bus = 10
    car_area = percentage_of_area(depth, width, percentage_car)
    bus_area = percentage_of_area(depth, width, percentage_bus)
    bike_area = percentage_of_area(depth, width, percentage_bike)
    lot = {"car": car_area, "bus": bus_area, "bike": bike_area}
    return lot


def calculate_charge(in_time: datetime, out_time: datetime):
    diff = out_time - in_time
    total_minutes = diff.total_seconds()//60
    hours = total_minutes // 60
    minutes = total_minutes % 60
    charge = 0
    if minutes > 30:
        hours += 1
    if hours >= 1:
        charge += 20
        hours -= 1
    if hours >= 9:
        if hours > 9:
            charge += 10 * 9
            hours -= 9
        else:
            charge += 10 * hours
            hours -= hours
    if hours > 0:
        charge += 5 * hours
    return charge


# Linear Search
def match_vehicle(vehicle, vehicle_list):
    for i in vehicle_list:
        if i.name == vehicle.name and i.number == vehicle.number and i.manufacturer == vehicle.manufacturer:
            return True, i
        else:
            return False, -1
