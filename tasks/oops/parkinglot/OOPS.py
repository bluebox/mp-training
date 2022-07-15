# Vehicle class.
class Vehicle:
    def __init__(self, name, number, manufacturer):
        self.name = name
        self.number = number
        self.manufacturer = manufacturer
        self.lot = None


# Bus class inheriting Vehicle class.
class Bus(Vehicle):
    def __init__(self, milage, name, width, depth, number, manufacturer, capacity=45):
        self.milage = int(milage)
        self.width = int(width)
        self.depth = int(depth)
        self.capacity = int(capacity)
        Vehicle.__init__(self, name, number, manufacturer)


# Car class inheriting Vehicle class.
class Car(Vehicle):
    def __init__(self, milage, name, width, depth, number, manufacturer, capacity=4):
        self.milage = int(milage)
        self.width = int(width)
        self.depth = int(depth)
        self.capacity = int(capacity)
        Vehicle.__init__(self, name, number, manufacturer)


# Bike class inheriting Vehicle class.
class Bike(Vehicle):
    def __init__(self, milage, name, width, depth, number, manufacturer, capacity=2):
        self.milage = int(milage)
        self.width = int(width)
        self.depth = int(depth)
        self.capacity = int(capacity)
        Vehicle.__init__(self, name, number, manufacturer)


# Series class to create objects like series-A, series-B, series-C, series-D.
class Series:
    def __init__(self, width, depth, series, number=0):
        self.width = width
        self.depth = depth
        self.number = number
        self.series = series
        self.bikeArea = 0.3 * (self.width * self.depth)
        self.carArea = 0.6 * (self.width * self.depth)
        self.busArea = 0.1 * (self.width * self.depth)

    def getter_bikeArea(self):
        return self.bikeArea

    def setter_bikeArea(self, diffArea):
        self.bikeArea += diffArea

    def getter_carArea(self):
        return self.carArea

    def setter_carArea(self, diffArea):
        self.carArea += diffArea

    def getter_busArea(self):
        return self.busArea

    def setter_busArea(self, diffArea):
        self.busArea += diffArea


# Lot class to create lots like A1, A2, A3..., B1, B2..., C1...
class Lot:
    def __init__(self, width, depth, series, vehicle, in_time, status="Free", number=1):
        self.width = width
        self.depth = depth
        self.number = number
        self.series = series
        self.status = status
        self.in_time = in_time
        self.vehicle = vehicle


# Function for new series area allocation
def newSeries(current_series_index_number):
    global series, series_names, series_area
    print("-----------------------------------------------------------------------------------")
    print("Created a New Lot : ")
    print("The New Lot Created is : ", series_names[current_series_index_number])
    width, depth = series_area[series_names[current_series_index_number]][0], \
                   series_area[series_names[current_series_index_number]][1]
    print("Enter Width and Depth of the Slot", series_names[current_series_index_number], "with width", width,
          "and with breadth", depth)
    print('-------------------------------------------------------------------------------------')
    print()
    new_series = Series(width, depth, series_names[current_series_index_number])
    series[new_series] = []


# series dictionary storing the "series-objects" as "keys" and "respective series' lots' list" as "values".
series = {}
# series dictionary storing the "series-names" as "keys" and "respective series' width and depth vales in list" as "values".
series_area = {'A': [0, 0], 'B': [0, 0], 'C': [0, 0], 'D': [0, 0]}

# Initializing the series' width and breadth.
series_names = 'ABCD'
current_series_index_number = 0
for i in series_names:
    print("Enter width and breadth of", i)
    width, breadth = map(int, input().split())
    series_data = series_area[i]
    series_data[0] = width
    series_data[1] = breadth


# Function to create a new vehicle object.
def createVehicle(milage, vehicle, width, depth, capacity, vehicle_number, manufacturer):
    if vehicle == 'Bus':
        bus = Bus(milage, vehicle, width, depth, vehicle_number, manufacturer, capacity)
        return bus
    elif vehicle == 'Car':
        car = Car(milage, vehicle, width, depth, vehicle_number, manufacturer, capacity)
        return car
    elif vehicle == 'Bike':
        bike = Bike(milage, vehicle, width, depth, vehicle_number, manufacturer, capacity)
        return bike


# Function to get area of vehicle type in a series.
def areaGetterMethod(series, vehicle_name):
    return getattr(series, str("getter_" + vehicle_name + "Area"))


# Function to set area of vehicle type in a series.
def areaSetterMethod(series, vehicle_name):
    return getattr(series, str("setter_" + vehicle_name + "Area"))


# Function to park a vehicle.
def parkingVehicle(vehicle, in_time):
    global series, series_names, series_area
    for i in range(len(series)):
        individual_series = []
        for single_series in series:
            individual_series.append(single_series)
        single_series = individual_series[i]
        occupiedVehicleArea = areaGetterMethod(single_series, str(vehicle.name.lower()))
        if int(occupiedVehicleArea()) >= (vehicle.width * vehicle.depth):
            occupyingVehicleArea = areaSetterMethod(single_series, str(vehicle.name.lower()))
            vehicle_area = -1 * (vehicle.width * vehicle.depth)
            occupyingVehicleArea(vehicle_area)
            for lot in series[single_series]:
                if lot.status == "Free":
                    lot.status = "Occupied"
                    lot.vehicle = vehicle
                    vehicle.lot = lot
                    break
            else:
                series_lots_count = individual_series[i].number
                new_lot = Lot(vehicle.width, vehicle.depth, individual_series[i], vehicle, in_time, "Occupied",
                              series_lots_count + 1)
                individual_series[i].number += 1
                series[individual_series[i]].append(new_lot)
                vehicle.lot = new_lot
            return True
    return False


# Function to delocate a parking lot.
def delocatingLot(delocating_vehicle, out_time):
    print("Your Vehicle is parked at lot: ",
          str(delocating_vehicle.lot.series.series) + str(delocating_vehicle.lot.number))
    emptingVehicleArea = areaSetterMethod(delocating_vehicle.lot.series, str(delocating_vehicle.name).lower())
    vehicle_area = delocating_vehicle.width * delocating_vehicle.depth
    emptingVehicleArea(vehicle_area)
    parking_cost = cost(delocating_vehicle, out_time)
    print("The Parking cost is: ", parking_cost, "rupees.")
    delocating_vehicle.lot.status = "Free"
    delocating_vehicle.lot.vehicle = None
    del delocating_vehicle


# Function to display parked vehicles.
def displayParking():
    for individual_series in series:
        series_lots = series[individual_series]
        print("Series :", individual_series.series, "with width", individual_series.width, "and depth",
              individual_series.depth)
        print("has ", areaGetterMethod(individual_series, "car")(), "area left for cars.")
        print("has ", areaGetterMethod(individual_series, "bike")(), "area left for bikes.")
        print("has ", areaGetterMethod(individual_series, "bus")(), "area left for buses.")
        print()
        for lot in series_lots:
            print("Lot:", lot.series.series + str(lot.number), " status:", lot.status)
            if lot.status == "Occupied":
                vehicle = lot.vehicle
                print("Parked Vehicle Details are:")
                print("Vehicle Number:", vehicle.number, "Width", vehicle.width, "Depth", vehicle.depth)
        print("-------------------------------------------")


# Function to find a vehicle in parked vehicles if exists.
def findVehicle(vehicle_number):
    for single_series in series:
        lots = series[single_series]
        for lot in lots:
            if lot.vehicle.number == vehicle_number:
                return lot.vehicle
    return None


# Function to convert 24 hrs format time to minutes.
def tim_min(s):
    i = s.split(':')
    mins = int(i[0]) * 60 + int(i[1])
    return mins


# Funtion to calculate the parking fee.
def cost(vehicle, outTime):
    diff = tim_min(outTime) - tim_min(vehicle.lot.in_time)
    parking_cost = 0
    count = 0
    if diff > 30:
        parking_cost += 20
        diff -= 60
        while diff > 30 and count < 10:
            parking_cost += 10
            count += 1
            diff -= 60
        while diff > 30:
            diff -= 60
            parking_cost += 5
    return parking_cost


ch = input(
    "\n 1.Parking Lot \n 2.Deallocating Lot\n 3.Display Parking\n Press anyother key to terminate the process.\n Enter Your Choice : ")

if ch == '1':
    newSeries(current_series_index_number)
    current_series_index_number += 1

while ch in ["1", "2", "3"]:
    if ch == "1":
        print()
        print("-----------------------------------------------------------------------------------------")
        print()
        print("Enter your vehicle details are : ")
        in_time = input("Your inTime in 24hrs Format : ")
        milage = input("Milage of your Vehicle : ")
        vehicle = input("Your Vehicle Type (Bus/Car/Bike): ").capitalize()
        width = input("Width of your Vehicle : ")
        depth = input("Depth of your Vehicle : ")
        capacity = input("Capacity of your Vehicle : ")
        vehicle_number = input("Your vehicle number : ")
        manufacturer = input("Manufacturer Name of Your Vehicle : ")
        print()
        print("-----------------------------------------------------------------------------------------")
        print()
        new_vehicle = createVehicle(milage, vehicle, width, depth, capacity, vehicle_number, manufacturer)
        is_parked = parkingVehicle(new_vehicle, in_time)
        if not (is_parked):
            while not (is_parked):
                if current_series_index_number == 4:
                    print("The Lot Places are filled for ", new_vehicle.name, " and are currently unavailable.")
                    print()
                    break
                else:
                    newSeries(current_series_index_number)
                    is_parked = parkingVehicle(new_vehicle, in_time)
                    current_series_index_number += 1
        if is_parked:
            print("Your Vehicle is Parked at lot :", new_vehicle.lot.series.series + str(new_vehicle.lot.number))
    elif ch == "2":
        flg = True
        vehicle_number = input("Enter your Vehicle Number : ")
        vehicle = findVehicle(vehicle_number)
        if vehicle == None:
            print("Your Vehicle isn't Parked!!!")
        else:
            outTime = input("Enter the Present time in 24hrs Format : ")
            while (tim_min(outTime) < 0 or tim_min(outTime) >= 1440):
                outTime = input("Enter Valid Time in 24hrs Format!!! : ")
            delocatingLot(vehicle, outTime)
    elif ch == "3":
        displayParking()
    print()
    print("-----------------------------------------------------------------------------------------")
    print()
    ch = input(
        "\n 1.Parking Lot \n 2.Deallocating Lot\n 3.Display Parking\n Press anyother key to terminate the process.\n Enter Your Choice : ")
    print()
    print("-----------------------------------------------------------------------------------------")
    print()
