"""Main file of parking-lot functionality"""
import cbb_class
import exporting_csv
import parking_funcs

if __name__ == "__main__":
    car_1 = cbb_class.Car("HR51AN5098", 10, "alto800", 4, "maruti")
    bus_1 = cbb_class.Bus("TL10KL5302", 13, "Volvo", 30, "Eicher")
    bike_1 = cbb_class.Bike("ML98LN9988", 80, "Splendor", 2, "Honda")
    parking_funcs.parking_car(car_1)
    parking_funcs.exit_parking(car_1)
    parking_funcs.parking_car(bus_1)
    parking_funcs.parking_car(bike_1)
    parking_funcs.print_parking_lot()
    exporting_csv.export_csv()

# Extra information
# Functions

# exporting_csv
# exporting_csv.Export_Csv() for exporting csv file

# Parking_funcs
# print_parking_lot for printing parking lot
# parking_car for alloting parking
# exit_parking for billing and exiting parking
