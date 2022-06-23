"""Class containing Parking lot properties and functions"""

class parking(object):
    """Parking lot class with required functionalities"""
    total_parking = 240
    available_space = total_parking
    parking_per_Section = 20

    # 70% car, 20% bike 10% bus ratio's - 20 total parking
    # per section of one series
    # Total parking 4*3*20=240 spaces
    CAR_COUNT = int((70/100)*parking_per_Section)
    BIKE_COUNT = int((20/100)*parking_per_Section)
    BUS_COUNT = int((10/100)*parking_per_Section)

    parking_dict = {}
    time_dict = {}
    lot_series = ["a", "b", "c", "d"]
    series_sections = [1, 2, 3]

    def init_parking():
        """creating dict with index series and section combined"""
        for i in parking.lot_series:
            for j in parking.series_sections:
                parking.parking_dict[i, j] = []

    def allotment_func(vechile,i):
        """This functions updates parking lot and available space"""
        parking.parking_dict[i].append(vechile)
        parking.available_space = parking.available_space-1
        vechile.entry_time()

    def park_allot(vechile):
        vech_name = vechile.__class__.__name__
        for park_series in parking.parking_dict:
            vech_count = 0
            for park_sec in parking.parking_dict[park_series]:
                if park_sec.__class__.__name__ == vech_name:
                    vech_count = vech_count+1
            if vech_name == 'Car':
                if vech_count < parking.CAR_COUNT:
                    parking.allotment_func(vechile,park_series)
                    break

            if vech_name == 'Bike':
                if vech_count < parking.BIKE_COUNT:
                    parking.allotment_func(vechile,park_series)
                    break

            if vech_name == 'Bus':
                if vech_count < parking.BUS_COUNT:
                    parking.allotment_func(vechile,park_series)
                    break
