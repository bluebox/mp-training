from Bike import Bike
import constants1 as cf
from Bus import Bus
from Car import Car
from Lot import Lot
from vehicle import vehicle
from vehicle import *
bike_dict={}
car_dict={}
bus_dict={}
lot_list=[]
bike_area= 0.3*cf.lot_area
car_area=0.6*cf.lot_area
bus_area=0.1*cf.lot_area

def enter_lot():
   Width=int(cf.lot_width)
   Depth=int(cf.lot_depth)
   Number=cf.lot_number
   Series=cf.lot_series
   Status=cf.lot_status
   InTime=cf.lot_intime
   lt=Lot(Width,Depth,Number,Series,Status,InTime)
   lot_list.append(lt)

def canallocate(vehicle_type,vehicle_space):
    global bike_area,car_area,bus_area
    if (vehicle_type==1):
        if(vehicle_space <= bike_area):
            bike_area=bike_area-vehicle_space
            return True
    elif (vehicle_type==2):
        if(vehicle_space<=car_area):
            car_area=car_area-vehicle_space
            return True
    elif (vehicle_type==3):
        if(vehicle_space<=bus_area):
            bus_area=bus_area-vehicle_space
            return True
    return False
def calc_charge(vehicle_type,Allocation_time):
    if float(Allocation_time)-int(Allocation_time)>=0.5:
        Allocation_time=Allocation_time+1
    else:
        Allocation_time=int(Allocation_time)
    total_bill=20
    Allocation_time=Allocation_time-1
    if Allocation_time<=9:
      total_bill=total_bill+Allocation_time*10
    else:
        Allocation_time=Allocation_time-9
        total_bill=total_bill+Allocation_time*5+90
    return total_bill
def display_charges():
    print("1.first hour 20\n 2.next 1 -10 hour onwards  a charge of 10 rs\n 3.above 10 hr onwards a charge 5 rs per hour")
def enter_car():
   Milage=cf.car_mileage
   Name=input("enter name")
   Capacity=cf.car_capacity
   Width=int(cf.car_width)
   Depth=int(cf.car_depth)
   Number=input("enter Number")
   Manufacturer=input("Manufacturer")
   Allocation_time=input("Allocation Time ")
   cr=Car(Milage,Name,Capacity,Width,Depth,Number,Manufacturer)
   curr_vehicle_size=Width*Depth
   if canallocate(2,curr_vehicle_size):
    curr_charge=calc_charge(2,Allocation_time)
    print(curr_charge)
    car_dict[Number]=(cr,Allocation_time,curr_charge)
   else:
    print("Parking lot is full \n thanks for choosing us!!!")

def enter_bus():
   Milage=cf.bus_mileage
   Name=input("enter name ")
   Capacity=cf.bus_capacity
   Width=int(cf.bus_width)
   Depth=int(cf.bus_depth)
   Number=input("enter Number ")
   Manufacturer=input("Manufacturer ")
   Allocation_time=input("Allocation Time ")
   bs=Bus(Milage,Name,Capacity,Width,Depth,Number,Manufacturer)
   curr_vehicle_size=Width*Depth
   if canallocate(3,curr_vehicle_size):
    curr_charge=calc_charge(2,Allocation_time)
    print(curr_charge)
    bus_dict[Number]=(bs,Allocation_time,curr_charge)
   else:
    print("Parking lot is full \n thanks for choosing us!!!")

def enter_bike():
   Milage=cf.bike_mileage
   Name=input("enter name")
   Capacity=cf.bike_capacity
   Width=int(cf.bike_width)
   Depth=int(cf.bike_depth)
   Number=input("enter Number")
   Manufacturer=input("Manufacturer")
   Allocation_time=input("Allocation Time ")
   bk=Bike(Milage,Name,Capacity,Width,Depth,Number,Manufacturer)
   curr_vehicle_size=Width*Depth
   if canallocate(1,curr_vehicle_size):
    curr_charge=calc_charge(2,Allocation_time)
    print(curr_charge)
    bike_dict[Number]=(bk,Allocation_time,curr_charge)
   else:
    print("Parking lot is full \n thanks for choosing us!!!")
def enter_vehicles():
   while(True):
    vehicle_entered=int(input(" press 1 for Bike \n press 2 for Car \n press 3 for Bus\n press 4 for parking charges \n press 0 to exit\n"))
    if(vehicle_entered==0):
        print("Thanks for choosing us !!!")
        break
    elif vehicle_entered==1:
        enter_bike()
    elif vehicle_entered==2:
        enter_car()
    elif vehicle_entered==3:
        enter_bus()
    elif vehicle_entered==4:
        display_charges()
def exit_vehicles():
    vehicle_entered=int(input(" press 1 for Bike \n press 2 for Car \n press 3 for Bus\n"))
    
    if vehicle_entered==1:
        exit_bike()
    elif vehicle_entered==2:
        exit_car()
    elif vehicle_entered==3:
        exit_bus()
    pass
def exit_bike():
    inp=input("enter bike number")
    bike_dict.pop[inp]
    pass
def exit_car():
    inp=input("enter car number")
    car_dict.pop[inp]
    pass
def exit_bus():
    inp=input("enter bus number")
    bus_dict.pop[inp]
    pass
enter_lot()
num=int(input(" Press 1 to enter vehicles \n press any number to exit vehicles\n"))
if num==1:
  enter_vehicles()
else:
  exit_vehicles()