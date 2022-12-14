import lotFunctionality
from datetime import datetime
from Classes.vehicle import Car, Bus, Bike

parking = lotFunctionality.define_lot()
while True:
    choice = int(input('Enter your choice (1: park, 2: retrieve, 3: Exit): '))
    if choice == 1:
        vehicle = lotFunctionality.define_vehicle()
        i = 0
        for i in range(len(parking)):
            if parking[i].allocate_slot(vehicle):
                break
            parking[i].status = False
        if i == len(parking):
            print('No Parking space.')
        for i in parking:
            print(f'\nCars in Series {i.series} are:')
            for j in i.car_in_lot:
                print(j.manufacturer, j.name)
    elif choice == 2:
        name = input('Enter name: ')
        manufacturer = input('Enter manufacturer: ')
        number = input('Enter number: ')
        choice = int(input('Enter your choice of vehicle (1. Car, 2. Bike, 3. Bike): '))
        if choice == 1:
            vehicle = Car(number, manufacturer, name)
        elif choice == 2:
            vehicle = Bike(number, manufacturer, name)
        else:
            vehicle = Bus(number, manufacturer, name)
        out_time = datetime.now()
        charge = 0
        for i in range(len(parking)):

            charge = parking[i].deallocate_slot(vehicle, out_time)
            print('Total Cost of parking: ', charge)
            if charge > 0:
                break
        for i in parking:
            print(f'\nCars in Series {i.series} are:')
            for j in i.car_in_lot:
                print(j.manufacturer, j.name)
            for j in i.bike_in_lot:
                print(j.manufacturer, j.name)
            for j in i.bus_in_lot:
                print(j.manufacturer, j.name)
    else:
        break
