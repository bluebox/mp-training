import parking_functionality
import vehicle
from parking_functionality import Car_List

car1 = vehicle.Car('Maruti-800', 'Jh-05_DR 8100', "Suzuki", '20', 4, 2)
bike1 = vehicle.Bike('Bajaj-Platina', 'UP-31T-1956', "Bajaj", '100', 2, 1)
car2 = vehicle.Car('Tata_Nano', 'Jh-05_DR 6456', "Tata-Troup", '15', 3, 2.3)
parking_functionality.parking_alloc(car1)
parking_functionality.parking_alloc(car2)
print(car1.v_parking_no)
for obj in Car_List:
    print(obj.v_parking_no)
parking_functionality.parking_de_alloc(car1)
