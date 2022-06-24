from vehicle import Vehicle
from series import series
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
            name=input()
            milage=int(input())
            capacity = int(input())
            width=int(input())
            depth = int(input())
            manufacturer=input()
            number=input()
            in_time=time()
            car=Vehicle(milage,name,capacity,width,depth,number,manufacturer,in_time)
            print(car)
            area=car.width*car.depth
            
            park(car,in_time,x,area)
        if x==2:
            print("enter the details of your bike (name,milage,capacity,width,depth,manufacturer,number")
            name = input()
            milage = int(input())
            capacity = int(input())
            width = int(input())
            depth = int(input())
            manufacturer = input()
            number = input()
            in_time = time()
            bike = Vehicle(milage, name, capacity, width, depth, number, manufacturer, in_time)
            print(car)
            area = car.width * car.depth
          
            park(bike, in_time, x, area)
        if x==3 :
            print("enter the details of your bus (name,milage,capacity,width,depth,manufacturer,number")
            name = input()
            milage = int(input())
            capacity = int(input())
            width = int(input())
            depth = int(input())
            manufacturer = input()
            number = input()
            in_time = time()
            bus = Vehicle(milage, name, capacity, width, depth, number, manufacturer, in_time)
            print(car)
            area = car.width * car.depth
           
            park(bus, in_time, x, area)
    if (x==2) :
        print("select 1 to unpark your car \n select 2 to unpark your bike \n select 3 to unpark your bus")
        y=int(input())
        num=input("enter the car number")
        unpark(y,num)
    if x==3 :
        break
