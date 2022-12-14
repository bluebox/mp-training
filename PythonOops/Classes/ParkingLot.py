from Classes.vehicle import Car
from Classes.vehicle import Bus
from Classes.vehicle import Bike
from Classes.vehicle import Vehicle
from datetime import datetime
import lotFunctionality


class Lot:
    def __init__(self, depth, width, series, status):
        self.depth = depth
        self.width = width
        self.series = series
        self.status = status
        self.lot = lotFunctionality.init_lot(depth, width)
        self.car_in_lot = []
        self.bike_in_lot = []
        self.bus_in_lot = []

    def allocate_slot(self, vehicle: Vehicle):
        area = vehicle.depth * vehicle.width
        if isinstance(vehicle, Car):

            if self.lot['car'] >= area:
                self.car_in_lot.append(vehicle)
                self.lot['car'] -= area
                print('Parking Lot has been assigned.')
                return True
            else:
                return False
        if isinstance(vehicle, Bike):
            if self.lot['bike'] >= area:
                self.bike_in_lot.append(vehicle)
                self.lot['bike'] -= area
                print('Parking Lot has been assigned.')
                return True
            else:
                return False
        if isinstance(vehicle, Bus):
            if self.lot['bus'] >= area:
                self.bus_in_lot.append(vehicle)
                self.lot['bus'] -= area
                print('Parking Lot has been assigned.')
                return True
            else:
                return False

    def deallocate_slot(self, vehicle: Vehicle, out_time: datetime):
        area = vehicle.depth * vehicle.width
        if isinstance(vehicle, Car):
            b, i = lotFunctionality.match_vehicle(vehicle, self.car_in_lot)
            if b:
                self.car_in_lot.remove(i)
                self.lot['car'] += area
                print('Vehicle removed from parking')
                return lotFunctionality.calculate_charge(vehicle.in_time, out_time)
        if isinstance(vehicle, Bike):
            b, i = lotFunctionality.match_vehicle(vehicle, self.bike_in_lot)
            if b:
                self.bike_in_lot.remove(i)
                self.lot['bike'] += area
                print('Vehicle removed from parking')
                return lotFunctionality.calculate_charge(vehicle.in_time, out_time)
        if isinstance(vehicle, Bus):
            b, i = lotFunctionality.match_vehicle(vehicle, self.bus_in_lot)
            if b:
                self.bus_in_lot.remove(i)
                self.lot['bus'] += area
                print('Vehicle removed from parking')
                return lotFunctionality.calculate_charge(vehicle.in_time, out_time)
        return 0













