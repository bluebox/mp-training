
class Lot():
    # lot_size=0
    # bus_area=0
    # car_area=0
    # bike_area=1
    # bike_list=[]
    def __init__(self,Width,Depth,Number,Series,Status,InTime):
        self.Width=Width
        self.Depth=Depth
        self.Number=Number
        self.Series=Series
        self.Status=Status
        self.InTime=InTime
        self.lot_size=Width*Depth
        # self.bike_area=self.lot_size*self.bike_area
        # self.bus_area=self.lot_size*self.bus_area
        # self.car_area=self.lot_size*self.car_area

        # curr_bike_area=Bike

        # self.AllocationTime=AllocationTime
   
    # a. 
    # b. Depth
    # c. Number
    # d. Series (A/B/C/D)
    # e. Status (Free/Occupied)
    # f. InTime
    '''    4. Define a Lot with 
    a. Width
    b. Depth
    c. Number
    d. Series (A/B/C/D)
    e. Status (Free/Occupied)
    f. InTime

    5. Parking Lot Functionality
    a. Define Series of parking Lots with where each series consists 
    (60% Cars, 30% Bikes, 10% Busses)
    b. One Can assign a lot depending upon the vehicle size and allocation Time
    c. Define functionality of random Vehicle exit and lot deallocation
    d. Define functionality of lot allocation based on availability of lots
    e. Define a charge calculation based on time parked first hour 20,
     next 1 -10 hour onwards  a charge of 10 rs,
      above 10 hr onwards a charge 5 rs per hour.
    f. If time exceeds more than 30  minutes of an hour, 
    a full charge will be collected of that hour. 

5. Wrtie few test cases to check the implimented functionality like creating series,
lots,allocation,deallocation, random allocation/de-allocation, fare calculations etc '''