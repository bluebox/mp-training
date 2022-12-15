"""Check"""
import parking_lot
from vehicle import Car, Bike
Car_List = []
Bike_List = []
Bus_List = []


def parking_alloc(obj):
    if check_availability(obj):
        obj.entry_time()


def parking_de_alloc(obj):
    time = obj.exit_time()
    if isinstance(obj, Car):
        for key, value in parking_lot.CAR_AREA.items():
            if obj.v_parking_no == key:
                parking_lot.CAR_AREA[key] += obj.v_length * obj.v_width
                print(parking_lot.CAR_AREA[key])
    elif isinstance(obj, Bike):
        for key, value in parking_lot.BIKE_AREA.items():
            if obj.v_parking_no == key:
                parking_lot.BIKE_AREA[key] += obj.v_length * obj.v_width
    else:
        for key, value in parking_lot.BUS_AREA.items():
            if obj.v_parking_no == value:
                parking_lot.BUS_AREA[key] += obj.v_length * obj.v_width
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
    print(par_charge)


def check_availability(obj):
    """Check the availability of vehicle"""
    if isinstance(obj, Car):
        for key, value in parking_lot.CAR_AREA.items():
            if obj.v_width * obj.v_length <= value:
                parking_lot.CAR_AREA[key] -= obj.v_length * obj.v_width
                Car_List.append(obj)
                obj.v_parking_no = key
                return True
            elif key == 'A':
                parking_lot.lotA.l_status = "Occupied"
            elif key == 'B':
                parking_lot.lotB.l_status = "Occupied"
        print("Sorry!! There is no space for parking your car!")
    elif isinstance(obj, Bike):
        for key, value in parking_lot.BIKE_AREA.items():
            if obj.v_width * obj.v_length <= value:
                parking_lot.BIKE_AREA[key] -= obj.v_length * obj.v_width
                Bike_List.append(obj)
                obj.v_parking_no = key
                return True
        print("Sorry!! There is no space for parking your Bike!")
    else:
        for key, value in parking_lot.BUS_AREA.items():
            if obj.v_width * obj.v_length <= value:
                parking_lot.BUS_AREA[key] -= obj.v_length * obj.v_width
                Bus_List.append(obj)
                obj.v_parking_no = key
                return True
        print("Sorry!! There is no space for parking your Bus!")
