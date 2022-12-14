from datetime import datetime

series = {}
WIDTH = 70
DEPTH = 20
TOTAL_LOT_AREA = float(WIDTH) * float(DEPTH)


class Vehicle:
    def __init__(self, mileage, name, capacity, width, depth, number, manufacturer, in_time):
        self.mileage = mileage
        self.name = name
        self.capacity = capacity
        self.width = width
        self.depth = depth
        self.number = number
        self.manufacturer = manufacturer
        self.in_time = in_time
        self.timestamp = 0


class Bus(Vehicle):
    def __init__(self, mileage, name, capacity, width, depth, number, manufacturer, in_time):
        super().__init__(mileage, name, capacity, width, depth, number, manufacturer, in_time)


class Car(Vehicle):
    def __init__(self, mileage, name, capacity, width, depth, number, manufacturer, in_time):
        super().__init__(mileage, name, capacity, width, depth, number, manufacturer, in_time)


class Bike(Vehicle):
    def __init__(self, mileage, name, capacity, width, depth, number, manufacturer, in_time):
        super().__init__(mileage, name, capacity, width, depth, number, manufacturer, in_time)


class ParkingLot:
    def __init__(self, width, depth, number, status):
        self.width = width
        self.depth = depth
        self.number = number
        self.status = status

    series = {'A': {'Area_for_cars': TOTAL_LOT_AREA * 0.6, 'Car_objects': [], 'Area_of_bikes': TOTAL_LOT_AREA * 0.3, 'Bike_objects': [], 'Area_of_buses': TOTAL_LOT_AREA * 0.1, 'Bus_objects': [], 'Available_area': [TOTAL_LOT_AREA * 0.6, TOTAL_LOT_AREA * 0.3, TOTAL_LOT_AREA * 0.1]},
              'B': {'Area_for_cars': TOTAL_LOT_AREA * 0.6, 'Car_objects': [], 'Area_of_bikes': TOTAL_LOT_AREA * 0.3, 'Bike_objects': [], 'Area_of_buses': TOTAL_LOT_AREA * 0.1, 'Bus_objects': [], 'Available_area': [TOTAL_LOT_AREA * 0.6, TOTAL_LOT_AREA * 0.3, TOTAL_LOT_AREA * 0.1]},
              'C': {'Area_for_cars': TOTAL_LOT_AREA * 0.6, 'Car_objects': [], 'Area_of_bikes': TOTAL_LOT_AREA * 0.3, 'Bike_objects': [], 'Area_of_buses': TOTAL_LOT_AREA * 0.1, 'Bus_objects': [], 'Available_area': [TOTAL_LOT_AREA * 0.6, TOTAL_LOT_AREA * 0.3, TOTAL_LOT_AREA * 0.1]},
              'D': {'Area_for_cars': TOTAL_LOT_AREA * 0.6, 'Car_objects': [], 'Area_of_bikes': TOTAL_LOT_AREA * 0.3, 'Bike_objects': [], 'Area_of_buses': TOTAL_LOT_AREA * 0.1, 'Bus_objects': [], 'Available_area': [TOTAL_LOT_AREA * 0.6, TOTAL_LOT_AREA * 0.3, TOTAL_LOT_AREA * 0.1]}
              }


# Function used to return the vehicle object which we need to exit
def exit_vehicle(parking_lot_vehicles):
    vehicle_number = input("Enter your vehicle Number")
    vehicle_type = input("enter your vehicle type i.e car, bike or bus")
    for parking_series in parking_lot_vehicles.keys():
        parking_series_dict = parking_lot_vehicles[parking_series]
        if vehicle_type == 'car':
            car_objects = parking_series_dict['Car_objects']
            for obj in car_objects:
                if vehicle_number == obj.number:
                    return obj
            else:
                print("Please enter correct vehicle number")
                return None
        elif vehicle_type == 'bike':
            bike_objects = parking_series_dict['Bike_objects']
            for obj in bike_objects:
                if vehicle_number == obj.number:
                    return obj
            else:
                print("Please enter correct vehicle number")
                return None
        else:
            bus_objects = parking_series_dict['Car_objects']
            for obj in bus_objects:
                if vehicle_number == obj.number:
                    return obj
            else:
                print("Please enter correct vehicle number")
                return None


# function used to calculate the price based on the time parked
def price_calculation(vehicle):
    if None == vehicle:
        return
    entered_time = vehicle.timestamp
    exit_time = creating_time_stamp(datetime.now())
    time_stayed_in_hrs = abs(exit_time - entered_time)/3600
    charge = 0
    if time_stayed_in_hrs < 1:
        charge = 20
    elif 10 >= time_stayed_in_hrs >= 1:
        charge = time_stayed_in_hrs * 10
    else:
        charge = time_stayed_in_hrs * 5
    return charge


# Function used to create a timestamp which is used for both in and out time of a vehicle
def creating_time_stamp(present_time):
    a = datetime(present_time.year, present_time.month, present_time.day, present_time.hour, present_time.minute,
                 present_time.second)
    return a.timestamp()


# Creating the vehicle object based on the type of vehicle and returning the vehicle object
def park():
    print("Now enter your Type of vehicle")
    vehicle_type = input(''' choose the option below based on the type of vehicle you need to park
                             press "1" -> "CAR"
                             press "2" -> "BIKE"
                             press "3" -> "BUS"''')
    if vehicle_type == '1':
        mileage = input("enter the millage of car : ")
        name = input("enter the name of car : ")
        capacity = input("enter the car capacity : ")
        width = input("enter the car width : ")
        depth = input("enter the depth of car : ")
        number = input("enter the car plate number : ")
        manufacturer = input("enter the Manufacturer : ")
        in_time = str(datetime.now().time())[0:8]
        timestamp = creating_time_stamp(datetime.now())
        car_object = Car(float(mileage), name, int(capacity), int(width), int(depth), number, manufacturer, in_time)
        car_object.timestamp = timestamp
        return car_object
    elif vehicle_type == '2':
        mileage = input("enter the millage of bike : ")
        name = input("enter the name of bike : ")
        capacity = input("enter the bike capacity : ")
        width = input("enter the bike width : ")
        depth = input("enter the depth of bike : ")
        number = input("enter the bike plate number : ")
        manufacturer = input("enter the Manufacturer : ")
        in_time = str(datetime.now().time())[0:8]
        timestamp = creating_time_stamp(datetime.now())
        bike_object = Car(float(mileage), name, int(capacity), int(width), int(depth), number, manufacturer, in_time)
        bike_object.timestamp = timestamp
        return bike_object
    elif vehicle_type == '3':
        mileage = input("enter the millage of bus : ")
        name = input("enter the name of bus : ")
        capacity = input("enter the bus capacity : ")
        width = input("enter the bus width : ")
        depth = input("enter the depth of bus : ")
        number = input("enter the bus plate number : ")
        manufacturer = input("enter the Manufacturer : ")
        in_time = str(datetime.now().time())[0:8]
        timestamp = creating_time_stamp(datetime.now())
        bus_object = Car(float(mileage), name, int(capacity), int(width), int(depth), number, manufacturer, in_time)
        bus_object.timestamp = timestamp
        return bus_object
    else:
        print("enter the valid option Or your required service is not available")
        return


# checking the availability of parking space for the current vehicle obj and if available we park them
def checking_the_availability(parking_lot_series, vehicle_obj):
    type_of_vehicle = type(vehicle_obj).__name__
    for lot_series in parking_lot_series.keys():
        series_dict = parking_lot_series[lot_series]
        if type_of_vehicle == 'Car':
            available_areas = series_dict['Available_area']
            car_available_area = available_areas[0]
            current_car_area = vehicle_obj.depth * vehicle_obj.width
            if car_available_area >= current_car_area:
                car_objects = series_dict['Car_objects']
                car_objects.append(vehicle_obj)
                updated_car_available_area = car_available_area - current_car_area
                available_areas[0] = updated_car_available_area
                print(f'Your car with Number {vehicle_obj.number} is parked successfully on {lot_series} series '
                      f'{vehicle_obj.in_time}')
                return
            else:
                continue
        if type_of_vehicle == 'Bike':
            available_areas = series_dict['Available_area']
            bike_available_area = available_areas[1]
            current_bike_area = vehicle_obj.depth * vehicle_obj.width
            if bike_available_area >= current_bike_area:
                bike_objects = series_dict['Bike_objects']
                bike_objects.append(vehicle_obj)
                updated_bike_available_area = bike_available_area - current_bike_area
                available_areas[1] = updated_bike_available_area
                print(f'Your car with Number {vehicle_obj.number} is parked successfully on {lot_series} series '
                      f'{vehicle_obj.in_time}')
                return
            else:
                continue
        if type_of_vehicle == 'Bus':
            available_areas = series_dict['Available_area']
            bus_available_area = available_areas[2]
            current_bus_area = vehicle_obj.depth * vehicle_obj.width
            if bus_available_area >= current_bus_area:
                bus_objects = series_dict['Bus_objects']
                bus_objects.append(vehicle_obj)
                updated_bus_available_area = bus_available_area - current_bus_area
                available_areas[2] = updated_bus_available_area
                print(f'Your car with Number {vehicle_obj.number} is parked successfully on {lot_series} series '
                      f'{vehicle_obj.in_time}')
                return
            else:
                continue
    print('SORRY!, Currently there is no space available in the parking lot')


# main function which is used to park or exit the vehicle based on the request of the customer
def main():
    print('Hii, Welcome to parking lot services')
    print('Go through following commands')
    parking_lot_object = ParkingLot(WIDTH, DEPTH, 1, False)

    while True:
        option_chosen = input('''Enter the option according to your required service
                                   press "1" if you need to park your Vehicle
                                   press "2" if you want to exit from the parking lot
                                   press "3" if you want to exit''')
        if option_chosen == '1':
            vehicle_object = park()
            checking_the_availability(parking_lot_object.series, vehicle_object)
        elif option_chosen == '2':
            object_to_exited = exit_vehicle(parking_lot_object.series)
            total_price = price_calculation(object_to_exited)
            print(f"Your parking charge for the time being is as {total_price} Rupees")
        elif option_chosen == '3':
            return
        else:
            print('choose the above two options or enter the valid option')


if __name__ == '__main__':
    main()
