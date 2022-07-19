'''main code '''
class Vehicle:
    '''a class vehicle '''
    def __init__(self,v_milage,v_name,v_capacity,v_width,v_depth,v_number,v_manufacturer,v_time):
        self.v_name=v_name
        self.v_milage=v_milage
        self.v_capacity=v_capacity
        self.v_width=v_width
        self.v_manufacturer=v_manufacturer
        self.v_depth=v_depth
        self.v_number=v_number
        self.v_time=v_time
    def __str__(self):
        '''a method '''
        return self.v_name

class series:
    '''series is aclass'''
    def __init__(self):
        self.total_area=500000
        self.car_area=self.total_area*0.6
        self.bike_area = self.total_area * 0.3
        self.bus_area = self.total_area * 0.1
        self.list1=[]


    def __str__(self) :
        '''it returns a string message of an object of class '''
        return "series"
    


a_series=series()
b_series=series()
c_series=series()
d_series=series()
def bill(intime,outtime):
    '''this function is used to generate bill '''
    total_time=intime-outtime
    if(total_time<=1) :
        return 20
    elif(total_time<=10) :
        return (20*10)+10
    return 100+(total_time-10)*5

def park(obj,time,type,area):
    '''this function is used to park '''
    if(type==1):
        if a_series.car_area>area :
            print(a_series.car_area)
            a_series.car_area-=area

            a_series.list1.append(obj)
        elif b_series.car_area>area :
            print(b_series.car_area)
            b_series.car_area-=area
            b_series.list1.append(obj)
        elif c_series.car_area>area :
            print(c_series.car_area)
            c_series.car_area-=area
            c_series.list1.append(obj)
        elif d_series.car_area>area :
            print(d_series.car_area)
            d_series.car_area-=area
            d_series.list1.append(obj)
        else :
            print("there is no space to park your car")
    elif(type==2):
        if a_series.bike_area>area :
            a_series.bike_area-=area
            a_series.list1.append(obj)
        elif b_series.bike_area>area:
            b_series.bike_area-=area
            b_series.list1.append(obj)
        elif c_series.bike_area>area:
            c_series.bike_area-=area
            c_series.list1.append(obj)
        elif d_series.bike_area>area:
            d_series.bike_area-=area
            d_series.list1.append(obj)
        else:
            print("there is no space to park your bike")
    elif type==3 :
        if a_series.bus_area>area:
            a_series.bus_area-=area
            a_series.list1.append(obj)
        elif b_series.bus_area>area:
            b_series.bus_area-=area
            b_series.list1.append(obj)
        elif c_series.bus_area>area:
             c_series.bus_area-=area
             c_series.list1.append(obj)
        elif d_series.bus_area>area:
            d_series.bus_area-=area
            d_series.list1.append(obj)
        else:
            print("there is no space to park your bus")


total_lot=[a_series,b_series,c_series,d_series]
def unpark(type,num):
    '''this function is used to unpark vehicle'''
    for ele in total_lot:
        for obj in ele.list1:
            if (obj.number==num):
                area=obj.depth*obj.width
                print("totla bill is {0} ".format(bill(10,33.5)))
                if(type==1):
                    ele.car_area-=area
                elif (type==2):
                    ele.bike_area-=area
                elif(type==3):
                    ele.bus_area-=area
                break




while True:
    print('select 1 to park\n select 2 to unpark \n enter 3 to exit ')
    x=int(input())
    if x==1:
        print("select 1 for car \n select 2 to park your bike \n select 3 to park your bus")
        x=int(input())
        if x==1:
            print("enter the details of your car (name,milage,capacity,width,depth,manufacturer,number")
            v_name=input()
            v_milage=int(input())
            v_capacity = int(input())
            v_width=int(input())
            v_depth = int(input())
            v_manufacturer=input()
            v_number=input()
            v_in_time=time()
            car=Vehicle(v_milage,v_name,v_capacity,v_width,v_depth,v_number,\
                v_manufacturer,v_in_time)
            print(car)
            v_area=car.v_width*car.v_depth
            
            park(car,v_in_time,x,v_area)
        if x==2:
            print("enter the details of your bike (name,milage,capacity,width,\
                depth,manufacturer,number")
            v_name=input()
            v_milage=int(input())
            v_capacity = int(input())
            v_width=int(input())
            v_depth = int(input())
            v_manufacturer=input()
            v_number=input()
            v_in_time=time()
            bike_1=Vehicle(v_milage,v_name,v_capacity,v_width,v_depth,v_number,\
                v_manufacturer,v_in_time)
            print(bike_1)
            v_area=car.v_width*car.v_depth
            
            park(bike_1,v_in_time,x,v_area)
        if x==3 :
            print("enter the details of your bus (name,milage,capacity,width,\
                depth,manufacturer,number")
            v_name=input()
            v_milage=int(input())
            v_capacity = int(input())
            v_width=int(input())
            v_depth = int(input())
            v_manufacturer=input()
            v_number=input()
            v_in_time=time()
            bus_1=Vehicle(v_milage,v_name,v_capacity,v_width,v_depth,v_number,v_manufacturer,\
                v_in_time)
            print(car)
            v_area=car.v_width*car.v_depth
            
            park(bus_1,v_in_time,x,v_area)
    if (x==2) :
        print("select 1 to unpark your car \n select 2 to unpark your bike \n \
            select 3 to unpark your bus")
        y=int(input())
        num=input("enter the car number")
        unpark(y,num)
    if x==3 :
        break
