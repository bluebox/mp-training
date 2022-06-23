"""This file contains some important callable function necessary for this project"""
import parking_class

parking_class.parking.init_parking()

def print_parking_lot():
    """function for printing parking lot"""
    print("\n------Parking Lot------\n")
    for i in parking_class.parking.parking_dict:
        for j in parking_class.parking.parking_dict[i]:
            print(i, "->", j.vech_number, "(", j.__class__.__name__, ")")
    print("\n")

def parking_car(vechile):
    """This function checks availablity
    and allot parking to the vechile"""
    if parking_class.parking.available_space != 0:
        parking_class.parking.park_allot(vechile)
    else:
        print("Sorry but no parking space available")
        return "Sorry but no parking space available"

def exit_parking(vechile):
    """This Function returns the parking fees
    and reallocate the parking space"""
    diff = vechile.exit_time()
    diff = (diff / 3600)

    # code for if time is greater than 30 minutes
    # it will charge for the whole hour
    dec_val=diff
    diff=int(diff)
    dec_val=dec_val-diff
    if dec_val>0.5:
        diff=diff+1
    parking_fees = 20
    for park in parking_class.parking.parking_dict:
        for parked_vechile in parking_class.parking.parking_dict[park]:
            if parked_vechile == vechile:
                parking_class.parking.parking_dict[park].remove(vechile)
    print("\n------Parking Fees-------")
    print("\nVechile Number :", vechile.vech_number)
    print("Total hours is", diff)
    if 10 > diff > 1:
        parking_fees = parking_fees+(diff-1)*10
    if diff > 10:
        parking_fees = parking_fees+(10)*10
        parking_fees = parking_fees+(diff-11)*5
    print("Your total charge is Rs:", parking_fees, "\n")
    return parking_fees
