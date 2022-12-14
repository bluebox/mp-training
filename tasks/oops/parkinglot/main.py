"""OOPS assignment"""
from parking_lot import seriesA, seriesB, seriesC, seriesD
import parking_lot
from vehicle import Vehicle
import vehicle_type
import parking_lot
from parking_lot import ParkingLot
import datetime
import math

bike1 = vehicle_type.Bike('50 km/l', 'Himalayan R9', 2, 1, 2, 'HP21 T2352', 'Royal Enfield')
car1 = vehicle_type.Car('60 km/l', 'Super Splendor', 2, 4, 4, 'U31 T5919', 'Hero')


while True:
    s = int(input("Choose from options given below:\n 1. Allocate space to vehicle in "
                  "parking lot \n 2. Deallocate space from parking lot and calculate charge \n 3. Exit \n"))
    # name = input("Enter name of the object: \n")
    # name = vehicle_type.Bike(input("Enter milage: "), input("Enter name: "), int(input("Enter capacity: ")),
    #                          int(input("Enter width: ")), int(input("Enter depth: ")), input("Enter number: "),
    #                          input("Enter manufacturer name: "))
    if s == 1:
        vehicle_name = input("Please enter name of the vehicle: ")
        series_name = input("Enter the series of the parking lot: ")
        series_nam = f'series{series_name}'

        if vehicle_name in locals():
            if ParkingLot.check_availability(locals()[vehicle_name], locals()[series_nam]):
                ParkingLot.allocate(locals()[vehicle_name], locals()[series_nam])
                print("Space allocated!")
            else:
                print("This parking lot is full. Try another series instead.")
        else:
            print("Vehicle not found")
    elif s == 2:
        vehicle_name = input("Please enter name of the vehicle: ")
        series = input("Enter the series of the parking lot: ")
        series_name = f'series{series}'
        if vehicle_name in locals():
            t2 = ParkingLot.deallocate(locals()[vehicle_name], locals()[series_name])
            print("Space deallocated!")
        else:
            print("Vehicle not found")
        t = t2 - locals()[vehicle_name].intime
        t = t.total_seconds()
        print(f'Time vehicle was parked in the parking lot: {round(t)}')
        charge = 0
        if round(math.fmod(t, 1.0), 1) < 0.5:
            t += 1
        else:
            t = round(t)
        charge += 20
        t -= 1
        if t > 0:
            if t < 9:
                charge += 10 * t
                t = 0
            else:
                charge += 10 * 9
                t -= 9
        charge += 5 * t
        print(f'Parking charge: {int(charge)}')
    elif s == 3:
        break
