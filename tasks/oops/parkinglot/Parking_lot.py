class Lot:
    def __init__(self, width, depth, series, status, number=0):
        self.l_width = width
        self.l_depth = depth
        self.l_series = series
        self.l_status = status
        self.l_number = number


lotA = Lot(50, 50, 'A', "Free")
lotA.l_number = lotA.l_depth * lotA.l_width
lotB = Lot(20, 50, "B", 'Free')
lotB.l_number = lotB.l_depth * lotB.l_width
lotC = Lot(100, 50, "C", 'Free')
lotC.l_number = lotC.l_depth * lotC.l_width
lotD = Lot(60, 100, "D", 'Free')
lotD.l_number = lotD.l_depth * lotD.l_width

CAR_AREA = {'A': 0, 'B': 0, 'C': 0, 'D': 0}
BIKE_AREA = {'A': 0, 'B': 0, 'C': 0, 'D': 0}
BUS_AREA = {'A': 0, 'B': 0, 'C': 0, 'D': 0}
CAR_AREA["A"] = lotA.l_number * 0.6
CAR_AREA["B"] = lotB.l_number * 0.6
CAR_AREA["C"] = lotC.l_number * 0.6
CAR_AREA["D"] = lotD.l_number * 0.6
BIKE_AREA['A'] = lotA.l_number * 0.3
BIKE_AREA['B'] = lotB.l_number * 0.3
BIKE_AREA['C'] = lotC.l_number * 0.3
BIKE_AREA['D'] = lotD.l_number * 0.3
BUS_AREA['A'] = lotA.l_number * 0.1
BUS_AREA['B'] = lotB.l_number * 0.1
BUS_AREA['C'] = lotB.l_number * 0.1
BUS_AREA['D'] = lotB.l_number * 0.1

