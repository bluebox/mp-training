"""defines parking lot"""

import datetime
import vehicle_type


class ParkingLot:
    """class parking lot"""

    def __init__(self, width, depth, number, series, status, lot_dict={}):
        self.width = width
        self.depth = depth
        self.number = number
        self.series = series
        self.status = status
        self.lot_dict = lot_dict

    # car_parking_area = {'depth': 15, 'width': 5}
    # bus_parking_area = {'depth': 15, 'width': 5}
    # bike_parking_area = {'depth': 15, 'width': 2}

    def check_availability(self, obj):
        diction = obj.lot_dict
        if self.__class__.__name__ == "Bike":
            if self.depth * self.width <= diction['bike_area']:
                return True
        if self.__class__.__name__ == "Car":
            if self.depth * self.width <= diction['car_area']:
                return True
        if self.__class__.__name__ == "Bus":
            if self.depth * self.width <= diction['bus_area']:
                return True
        return False

    def allocate(self, obj):
        """calculates in time"""
        diction = obj.lot_dict
        if isinstance(self, vehicle_type.Bike):
            diction['bike_area'] -= self.depth * self.width
        elif isinstance(self, vehicle_type.Car):
            diction['car_area'] -= self.depth * self.width
        elif isinstance(self, vehicle_type.Bus):
            diction['bus_area'] -= self.depth * self.width
        self.intime = datetime.datetime.now()

    def deallocate(self, obj):
        """calculates out time"""
        diction = obj.lot_dict
        if isinstance(self, vehicle_type.Bike):
            diction['bike_area'] += self.depth * self.width
        elif isinstance(self, vehicle_type.Car):
            diction['car_area'] += self.depth * self.width
        elif isinstance(self, vehicle_type.Bus):
            diction['bus_area'] += self.depth * self.width
        now = datetime.datetime.now()
        return now


seriesA = ParkingLot(10, 10, 1, 'A', 'Free')
total_area = seriesA.width * seriesA.depth
seriesA.lot_dict = {'car_area': int(0.6 * total_area),
                    'bike_area': int(0.3 * total_area),
                    'bus_area': int(0.1 * total_area)}
seriesB = ParkingLot(20, 25, 2, 'A', 'Free')
total_area = seriesB.width * seriesB.depth
seriesB.lot_dict = {'car_area': int(0.6 * total_area),
                    'bike_area': int(0.3 * total_area),
                    'bus_area': int(0.1 * total_area)}
seriesC = ParkingLot(40, 45, 3, 'A', 'Free')
total_area = seriesC.width * seriesC.depth
seriesC.lot_dict = {'car_area': int(0.6 * total_area),
                    'bike_area': int(0.3 * total_area),
                    'bus_area': int(0.1 * total_area)}
seriesD = ParkingLot(110, 100, 4, 'A', 'Free')
total_area = seriesD.width * seriesD.depth
seriesD.lot_dict = {'car_area': int(0.6 * total_area),
                    'bike_area': int(0.3 * total_area),
                    'bus_area': int(0.1 * total_area)}
