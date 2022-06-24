from mimetypes import init

from typing_extensions import Self
import datetime




class vehicle:
    def __init__(self,name,number) :
      
        self.name=name
        self.number=number
        self.v_entry_time=None
        self.v_exit_time=None

    def entry_time(self):
        self.v_entry_time=datetime.datetime.now()
        
    def exit_time(self):
        self.v_exit_time=datetime.datetime.now() - self.v_entry_time   

class bus(vehicle):
    def __init__(self, name, number):
        super().__init__(name, number)

class car(vehicle):
   def __init__(self, name, number):
       super().__init__(name, number)

class bike(vehicle):
    def __init__(self, name, number):
        super().__init__(name, number)







class parkinglot:
    

    
    car=["A1","A2",]
    bike=[]
    truck=[]
    car_count=0
    bike_count=0
    truck_count=0
    free_space=40#-(car_count-tr)     
    
    parkdict=[]
    rows, cols = (4, 10)
    arr=[]
    for i in range(rows):
           col = []
           for j in range(cols):
              col.append(0)
           arr.append(col)  
    def assigning_lot(vechile):
        if parkinglot.free_space > 0:
           parkinglot.parkdict.append(vechile)
           vechile.entry_time()
           parkinglot.free_space=parkinglot.free_space-1 
           for i in range(0,4,1):
              print(i)
              for j in range(0,10,1):
                if parkinglot.arr[i][j]==0:
                    parkinglot.arr[i][j]=1
                    print("in",i,j)
                    i=8
                    break
              if i==8:
                break
                    
    def print_pa():
        print(parkinglot.parkdict[0].v_entry_time)
        print(parkinglot.free_space)
        print(parkinglot.arr)
c=car("saksham","AP 28 DB 6015")
parkinglot.assigning_lot(c)
c=car("saksham","AP 28 DB 6019")
parkinglot.assigning_lot(c)
parkinglot.print_pa()