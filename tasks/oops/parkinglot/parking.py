'''this module contains data of parking lot and functions for parking and unparking '''
from series import series

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
