# 3. Define a Lot with 
    # a. Width
    # b. Depth
    # c. Number
    # d. Series (A/B/C/D)
    # e. Status (Free/Occupied)
    # f. InTime

import random


class lot:
    def __init__(self, Width, Depth, Number):
        if Width < 100:
            self.Width = 100
        self.Width = Width
        self.Depth = Depth
        self.area = self.Depth * self.Width
        self.Number = Number
        self.series = {'bus' : {}, 'car' : {}, 'bike' : {}}
        self.busLeft = ((self.area * 60) // 100) // 30
        self.carLeft = ((self.area * 30) // 100) // 20
        self.bikeLeft = ((self.area * 10) // 100) // 10
        self.Status = "Free" if self.busLeft != 0 or self.carLeft != 0 or self.bikeLeft != 0 else "Occupied"

    # This thing here adds vehicles to the lot
    def addVehicleToLot(self, vehicle, allocationTime, inTime):
        if vehicle.size == 'large':
            if self.busLeft == 0:
                return "no spots left for bus"
            self.busLeft -= 1
            self.series['bus'][vehicle.Number] = [vehicle, inTime, allocationTime]
        if vehicle.size == 'medium':
            if self.carLeft == 0:
                return "no spots left for bus"
            self.carLeft -= 1
            self.series['car'][vehicle.Number] = [vehicle, inTime, allocationTime]
        if vehicle.size == 'small':
            if self.bikeLeft == 0:
                return "no spots left for bus"
            self.bikeLeft -= 1
            self.series['bike'][vehicle.Number] = [vehicle, inTime, allocationTime]
        return type(vehicle).__name__ + " added to lot"

    # charge calculation based on time alloted to a vehicle
    def calculateCharge(self, inTime, allocationTime):
        total = 0
        if allocationTime > 0:
            total = 20
            allocationTime -= 1
        if allocationTime > 0:
            if allocationTime > 9:
                total += 10 * 10
                allocationTime -= 10
            else:
                total += allocationTime * 10
                allocationTime = 0
        total += allocationTime * 5
        return total

    # deallocation of random vehicle from a lot
    def randomExit(self, type):
        if len(self.series[type]) == 0:
            return "No lots allocated to deallocate"
        values = random.choice(list(self.series[type].items()))
        del self.series[type][values[0]]
        a = str(type) + " has just deallocated one occupied space, the Number that got deallocated is " + str(values[0])
        b = " the allocation charge is " + str(self.calculateCharge(values[1][1], values[1][2]))
        return a + b

    # returns current lot details
    def getLotDetails(self, type = ""):
        if type == "":
            return self.series
        return self.series[type]