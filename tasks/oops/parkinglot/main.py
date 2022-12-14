import Functionality
import Vehicle
from Functionality import Car_List

car1 = Vehicle.Car('Maruti-800', 'Jh-05_DR 8100', "Suzuki", '20', 4, 2)
bike1 = Vehicle.Bike('Bajaj-Platina', 'UP-31T-1956', "Bajaj", '100', 2, 1)
car2 = Vehicle.Car('Tata_Nano', 'Jh-05_DR 6456', "Tata-Troup", '15', 3, 2.3)
Functionality.parking_alloc(car1)
Functionality.parking_alloc(car2)


Functionality.parking_de_alloc(car1)
for obj in Car_List:
    print("Vehicles are present in series: ", obj.v_parking_no)
