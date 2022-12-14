"""Check"""
import Parking_lot
from Vehicle import Car, Bike, Bus
Car_List = []
Bike_List = []
Bus_List = []


def parking_alloc(obj):
    if check_availability(obj):
        obj.entry_time()


def parking_de_alloc(obj):
    time = obj.exit_time()
    if isinstance(obj, Car):
        for key, value in Parking_lot.CAR_AREA.items():
            if obj.v_parking_no == key:
                Parking_lot.CAR_AREA[key] += obj.v_length * obj.v_width
                print("Vehicle Exiting: ", obj.v_reg_no)
                Car_List.remove(obj)
                print("Area left: ",Parking_lot.CAR_AREA[key])
    elif isinstance(obj, Bike):
        for key, value in Parking_lot.BIKE_AREA.items():
            if obj.v_parking_no == key:
                Parking_lot.BIKE_AREA[key] += obj.v_length * obj.v_width
                print("Vehicle Exiting: ", obj.v_reg_no)
                Bike_List.remove(obj)
                print("Area left: ",Parking_lot.BIKE_AREA[key])
    else:
        for key, value in Parking_lot.BUS_AREA.items():
            if obj.v_parking_no == value:
                Parking_lot.BUS_AREA[key] += obj.v_length * obj.v_width
                print("Vehicle Exiting: ", obj.v_reg_no)
                Bus_List.remove(obj)
                print("Area left: ",Parking_lot.BUS_AREA[key])
    time = (time / 3600)
    tot_time = int(time)
    if time - tot_time > 0.5:
        tot_time += 1
    if tot_time <= 1:
        par_charge = 20
    if 1 < tot_time <= 10:
        par_charge = 20 + (tot_time - 1) * 10
    if tot_time > 10:
        par_charge = 20 + 100 + (tot_time - 11) * 5
    print("Parking Bill: ",par_charge)


def check_availability(obj):
    """Check the availability of vehicle"""
    if isinstance(obj, Car):
        for key, value in Parking_lot.CAR_AREA.items():
            if obj.v_width * obj.v_length <= value:
                Parking_lot.CAR_AREA[key] -= obj.v_length * obj.v_width
                Car_List.append(obj)
                obj.v_parking_no = key
                print("Please Park in: ", obj.v_parking_no)
                return True
        print("Here we go again!! There is no space for parking your car!")
    elif isinstance(obj, Bike):
        for key, value in Parking_lot.BIKE_AREA.items():
            if obj.v_width * obj.v_length <= value:
                Parking_lot.BIKE_AREA[key] -= obj.v_length * obj.v_width
                Bike_List.append(obj)
                obj.v_parking_no = key
                print("Please Park in: ", obj.v_parking_no)
                return True
        print("Bro!! There is no space for parking your Bike!")
    else:
        for key, value in Parking_lot.BUS_AREA.items():
            if obj.v_width * obj.v_length <= value:
                Parking_lot.BUS_AREA[key] -= obj.v_length * obj.v_width
                Bus_List.append(obj)
                obj.v_parking_no = key
                print("Please Park in: ", obj.v_parking_no)
                return True
        print("Oh man, Bus really!! There is no space for parking your Bus!")