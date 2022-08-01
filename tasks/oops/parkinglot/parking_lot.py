"""parking_lot_functionality"""

import datetime


def cost_function(time_difference):
    """cost function"""
    if 0 <= time_difference <= 1:
        total_cost = 20
    elif 1 < time_difference <= 10:
        total_cost = 10 + 10 * time_difference
    elif 10 < time_difference:
        total_cost = time_difference * 5 + 60
    return total_cost


class ParkingLot:
    """parking lot class"""
    def __init__(self, width_in_meters, depth_in_meters):
        self.width = int(width_in_meters)
        self.depth = int(depth_in_meters)
        self.total_area_of_parking_lot = self.width * self.depth
        self.area_of_each_series = self.total_area_of_parking_lot / 4
        self.area_required_for_car_in_meters = 24  # (4m*6m)
        self.area_required_for_bike_in_meters = 4  # (2m*2m)
        self.area_required_for_bus_in_meters = 50  # (5m*10m)
        self.no_of_vehicle_slots_in_each_series = {
            "car": (self.area_of_each_series * .6) // (self.area_required_for_car_in_meters),
            "bike": (self.area_of_each_series * .3) // (self.area_required_for_bike_in_meters),
            "bus": (self.area_of_each_series * .1) // (self.area_required_for_bus_in_meters)
        }

        self.a_series = {"car": {}, "bike": {}, "bus": {}}
        self.b_series = {"car": {}, "bike": {}, "bus": {}}
        self.c_series = {"car": {}, "bike": {}, "bus": {}}
        self.d_series = {"car": {}, "bike": {}, "bus": {}}


class Vehicle:
    """vehicle class"""
    def __init__(self, name, number, manufacturer, capacity, mileage, width, depth):
        self.name = name
        self.number = number
        self.manufacturer = manufacturer
        self.capacity = capacity
        self.mileage = mileage
        self.width = int(width)
        self.depth = int(depth)
        self.area = self.width * self.depth
        self.vehicle_type = vehicle_type(self.area)
        self.in_time = datetime.datetime.now().replace(microsecond=0)
        self.in_time=self.in_time.time()

    def lot_allocation(self):
        """lot allocation method"""
        if len(parking_lot_instance.a_series[self.vehicle_type]) < \
                parking_lot_instance.no_of_vehicle_slots_in_each_series[self.vehicle_type]:
            self.list_keys = parking_lot_instance.a_series[self.vehicle_type].keys()
            self.key = least_number(self.list_keys)
            parking_lot_instance.a_series[self.vehicle_type][self.key] = [self.number, self.in_time]
            print("Park your vehicle in a_series in slot no :", self.key, "\n your in_time is", self.in_time)
        elif len(parking_lot_instance.b_series[self.vehicle_type]) < \
                parking_lot_instance.no_of_vehicle_slots_in_each_series[self.vehicle_type]:
            self.list_keys = parking_lot_instance.b_series[self.vehicle_type].keys()
            self.key = least_number(self.list_keys)
            parking_lot_instance.b_series[self.vehicle_type][self.key] = self.number
            print("Park your vehicle in b_series in slot no :", self.key, "\n your_in time is", self.in_time)
        elif len(parking_lot_instance.c_series[self.vehicle_type]) < \
                parking_lot_instance.no_of_vehicle_slots_in_each_series[self.vehicle_type]:
            self.list_keys = parking_lot_instance.c_series[self.vehicle_type].keys()
            self.key = least_number(self.list_keys)
            parking_lot_instance.c_series[self.vehicle_type][self.key] = self.number
            print("Park your vehicle in c_series in slot no :", self.key, "\n your_in time is", self.in_time)
        elif len(parking_lot_instance.d_series[self.vehicle_type]) < \
                parking_lot_instance.no_of_vehicle_slots_in_each_series[self.vehicle_type]:
            self.list_keys = parking_lot_instance.d_series[self.vehicle_type].keys()
            self.key = least_number(self.list_keys)
            parking_lot_instance.d_series[self.vehicle_type][self.key] = self.number
            print("Park your vehicle in a_series in slot no :", self.key)
            print("your in_time is:", self.in_time)
        else:
            print("no parking space available")


def lot_deallocate(vehicle_number):
    """lot de_allocation function"""
    list_series = [parking_lot_instance.a_series, parking_lot_instance.b_series, parking_lot_instance.c_series,
                   parking_lot_instance.d_series]
    for series in list_series:
        # print(series)
        for vehicle_type1 in series:
            # print(vehicle_type1)
            if len(series[vehicle_type1]) > 0:
                # print(vehicle_type1)
                for vehicle_key in series[vehicle_type1]:
                    if series[vehicle_type1][vehicle_key][0] == vehicle_number:
                        in_time2 = series[vehicle_type1][vehicle_key][1]
                        out_time = datetime.datetime.now().replace(microsecond=0)
                        out_time=out_time.time()
                        time_diff = out_time - in_time2
                        # print(time_diff)
                        t_secs = time_diff.total_seconds()
                        # print(t_secs)
                        t_hrs = t_secs // 60 * 60
                        # print(t_hrs)
                        t_minutes = t_secs // 60 - 60 * t_hrs
                        if t_minutes > 30:
                            t_hrs = t_hrs + 1
                        cost = cost_function(t_hrs)
                        print("your in time:", in_time2)
                        print("your out time:", out_time)
                        print("parking cost:", cost, "₹")
                        del series[vehicle_type1][vehicle_key]
                        break
                else:
                    continue
                break
        else:
            continue
        break



def print_occupied_list():
    """printing occupied list function"""
    print("series_a:", parking_lot_instance.a_series)
    print("series_b:", parking_lot_instance.b_series)
    print("series_c:", parking_lot_instance.c_series)
    print("series_d:", parking_lot_instance.d_series)


def vehicle_type(area):
    """finding vehicle type function"""
    if 1 <= area <= 4:
        result = "bike"
    elif 4 < area <= 24:
        result = "car"
    else:
        result = "bus"
    return result


def least_number(list_key):
    """least number finding functions"""
    list_updated = [int(i) for i in list_key]
    if list_updated == []:
        key = "1"
    else:
        max_number = max(list_updated)
        for i in range(1, max_number + 2):
            if i not in list_updated:
                key = str(i)
                break
    return key


print("instance the parking lot class by providing width in meters,depth in meters)")
width = input("enter the width in meters :")
depth = input("enter the depth in meters: ")
parking_lot_instance = ParkingLot(width, depth)

while True:
    print("input 1 to allot lot,2 to deallocate,3 to display the parking layout")
    x = int(input("enter the value :"))
    if x == 1:
        name = input("enter the name : ")
        number = input("enter the vehicle number : ")
        manufacturer = input("enter the manufacturer : ")
        capacity = input("enter the capacity:")
        mileage = input("enter the mileage : ")
        width = input("enter the width in meters :")
        depth = input("enter the depth in meters : ")
        v_in = Vehicle(name, number, manufacturer, capacity, mileage, width, depth)
        v_in.lot_allocation()

    if x == 2:
        number = input("enter the vehicle number:")
        lot_deallocate(number)
    if x == 3:
        print_occupied_list()



