What your project does?
This is a command line based parking lot project which assign's the lot to car, buses, bikes
according to the requirement
-Calculate the price according to the time taken
-Allots and deallots the park lots and assign the empty space again
-Park with the given requirements like one allot can have 70% car 20% Bike and 10% bus
-Prints the parking lot designed by the functions
-Tells the available space, and check whether space is available and returns if space is unavailable
-and much more

How to install it
Just extract the file, cd to parking_lot 
go to the main.py file and run the main file by inputing the requirment inside the __name__=__main__ file

in vechile_class.py function def exit_time(self)
the timings are given hard coded because of testing purpose, you can change that accordingly

examples of usability

car_1 = cbb_class.Car("HR51AN5098", 10, "alto800", 4, "maruti")
car_1 is the object of Car class, it can be bike/bus as per the requirement which is inherting the properties
of vechile class 
it takes 5 mandatory arguments

1st is vechile number
2nd is mileage
3rd name of the phone
4th capacity of the vechile
5th company of the vechile

functions:

parking_funcs.parking_car(car_1)
passing car_1 object in this function will going to check if there is a available space
and if there is a available space it will alot parking space to the object 
this function is valid for all car/bus/bike

---------------------------------------------------------------------------

parking_funcs.exit_parking(car_1)
passing car_1 object in this function will going to print the requirement parking amount
as per the instruction

parking prices as follow
20rs base price
for 1-10 hours: 10rs per hours
after 10 hours: 5per per hour additional
if the time has more then 30mins in that hour
full hour charge will be implement.
and pop the object from the parking dictionary and increasing the availability space required

---------------------------------------------------------------------------

parking_funcs.print_parking_lot()

This function will print the parking lot in an organised order , with the vechile number , and their 
entry timing.

---------------------------------------------------------------------------

exporting_csv.export_csv()

This function will create/write a csv file in the csv_files folder with the details of parking lot and its 
objects.

---------------------------------------------------------------------------

Testing Purpose:
you need to have pylint installed and can go to the test_case.py file and in the terminal run "py.test test_case.py" make sure, you are in the right directory.
It contains some test cases which can help you in testing up the project and its usage.


This project is made by "Yatin vohra" See LICENSE.txt for more information about the usage and more details.