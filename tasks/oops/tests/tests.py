from vehicle import *
from datetime import datetime as dt

car_1 = Car(18, "Audi R8", 2, 3, 5, "AP05AR1111", "Audi", dt.now().strftime("%H:%M:%S"))
bike_1 = Bike(35, "Y2K", 1, 2, 3, "AP05AR0001", "MTT", dt.now().strftime("%H:%M:%S"))
bus_1 = Bus(15, "Tata", 50, 10, 12, "AP05AR0007", "Tata Motors", dt.now().strftime("%H:%M:%S"))

print(car_1.display())
print(bike_1.display())
print(bus_1.display())