class vehicle:
    def __init__(self,milage,name,capacity,width,depth,number,manufacturer,intime):
        self.width=width
        self.depth=depth
        self.number=number
        self.manufacturer=manufacturer
        self.capacity=capacity
        self.name=name
        self.milage=milage
        self.intime=intime

class car(vehicle):
    def __init__(self,milage,name,capacity,width,depth,number,manufacturer,intime):
        super().__init__(milage,name,capacity,width,depth,number,manufacturer,intime)

class bikes(vehicle):
    def __init__(self,milage,name,capacity,width,depth,number,manufacturer,intime):
        super().__init__(milage,name,capacity,width,depth,number,manufacturer,intime)

class buses(vehicle):
    def __init__(self,milage,name,capacity,width,depth,number,manufacturer,intime):
        super().__init__(milage,name,capacity,width,depth,number,manufacturer,intime)


from datetime import datetime
class lot:
    #for  each series area is equal to 1000.
    total_area=1000
    helper_dic={"A":{"car":[[total_area*0.6]],"bikes":[[total_area*0.3]],"busses":[[total_area*0.1]]},"B":{"car":[[total_area*0.6]],"bikes":[[total_area*0.3]],"busses":[[total_area*0.1]]},"C":{"car":[[total_area*0.6]],"bikes":[[total_area*0.3]],"busses":[[total_area*0.1]]},"D":{"car":[[total_area*0.6]],"bikes":[[total_area*0.3]],"busses":[[total_area*0.1]]}}
    def __init__(self,width,depth,number,intime,status,series):
        self.width=width
        self.depth=depth
        self.number=number
        self.intime=intime
        self.status=status
        self.series=series

    def allocating_slot(self,object):
        i=1
        char="A"
        while i<4:
            if lot.helper_dic[char][type(object).__name__][0][0]-object.width*object.depth>=0:
                lot.helper_dic[char][type(object).__name__][0][0]-=object.width*object.depth
                lot.helper_dic[char][type(object).__name__].insert(self.number,[object.number,object.width*object.depth,object.intime])
                self.status="occupied"
                print("slot allocated to the vehicle at {}".format(self.number))
                print(lot.helper_dic)
                break
            else:
                char=chr(ord(char)+1)
                i+=1
        else:
            print("No free space in the parking")

    def randomVehicleDeallocation(self):
        slot=input("Enter the series from which the vehicle to be deallocated: ")
        vehicle_type=input("enter the vehicle type: ")
        lot.helper_dic[slot][vehicle_type].pop(self.number)
        print("charge to be paid : ",lot.chargeCalculation(self.intime))
        print("available vehicles: ",lot.helper_dic)


    @staticmethod
    def chargeCalculation(intime):
        outtime= str(datetime.now().time())[0:5]

        def conv(time):
            output = ""
            for i in time:
                if i != ":":
                    output += i
            return output

        time_spend = int(conv(outtime)) - int(conv(intime))
        if len(str(time_spend)) == 3:
            hours = int(str(time_spend)[0:1])
            minutes = int(str(time_spend)[1:])

        else:
            hours = int(str(time_spend)[0:2])
            minutes = int(str(time_spend)[2:])

        charge = 0
        if hours == 0 and minutes > 30:
            charge += 20
        elif hours == 10 and minutes > 30:
            hours -= 1
            charge += 20
            charge += hours * 10
            if minutes > 30:
                charge += 5
        elif hours > 0 and hours <= 10:
            hours -= 1
            charge += 20
            charge += hours * 10
            if minutes > 30:
                charge += 10
        elif hours > 10:
            hours -= 1
            charge += 20
            charge += 9 * 10
            hours -= 9
            charge += hours * 5
            if minutes > 30:
                charge += 5
        return charge



object2=car(10,"bmw",12345,10,1,"TS31F4775","tata","10:30")
object1=bikes(10,"pulsar",150,10,2,"TS11A1234","tvs","11:45")

slot1=lot(10,5,1,"10:00","free","A")
slot2=lot(10,4,2,"11:00","free","A")
slot1.allocating_slot(object2)
slot2.allocating_slot(object1)

slot1.randomVehicleDeallocation()








