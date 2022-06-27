from vehicle import Vehicle
from time import time
from parking import park,unpark


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
