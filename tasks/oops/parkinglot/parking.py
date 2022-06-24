# from turtle import clear
from vehicle import Vehicle
from series import series
from time import time

a=series()
b=series()
c=series()
d=series()
def bill(intime,outtime):
    total_time=intime-outtime
    if(total_time==1) :
        return 20
    elif(total_time<=10) :
        return (20*10)+10
    return 100+(total_time-10)*5

def park(obj,time,type,area):
    if(type==1):
        if a.car_area>area :
            print(a.car_area)
            a.car_area-=area

            a.list1.append(obj)
            print(a.list1)
        elif b.car_area>area :
            print(b.car_area)
            b.car_area-=area
            b.list1.append(obj)
        elif c.car_area>area :
            print(c.car_area)
            c.car_area-=area
            c.list1.append(obj)
        elif d.car_area>area :
            print(d.car_area)
            d.car_area-=area
            d.list1.append(obj)
        else :
            print("there is no space to park your car")
    if(type==2):
        if a.bike_area>area :
            a.bike_area-=area
            a.list1.append(obj)
        elif b.bike_area>area:
            b.bike_area-=area
            b.list1.append(obj)
        elif c.bike_area>area:
            c.bike_area-=area
            c.list1.append(obj)
        elif d.bike_area>area:
            d.bike_area-=area
            d.list1.append(obj)
        else:
            print("there is no space to park your bike")
    if type==3 :
        if a.bus_area>area:
            a.bus_area-=area
            a.list1.append(obj)
        elif b.bus_area>area:
            b.bus_area-=area
            b.list1.append(obj)
        elif c.bus_area>area:
             c.bus_area-=area
             c.list1.append(obj)
        elif d.bus_area>area:
            d.bus_area-=area
            d.list1.append(obj)
        else:
            print("there is no space to park your bus")


total_lot=[a,b,c,d]
def unpark(type,num):
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
