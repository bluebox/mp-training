"""Main file of parking-lot functionality"""
import cbb_class
import parking_funcs
import parking_class

def test_case_normal_parking():
    """initialising car and parking it"""
    car_1 = cbb_class.Car("HR51AN5098", 10, "alto800", 4, "maruti")
    parking_funcs.parking_car(car_1)
    assert parking_class.parking.available_space == 239

def test_case_parking_bus():
    """Only 2 buses are allowed per section in an alot"""
    bus_1 = cbb_class.Bus("AN02KO5492", 4, "Eicher1", 20, "Marcopolo")
    parking_funcs.parking_car(bus_1)
    bus_2 = cbb_class.Bus("KL21OL5322", 3, "Eicher2", 20, "Marcopolo")
    parking_funcs.parking_car(bus_2)
    bus_3 = cbb_class.Bus("MN02LK5132", 6, "Eicher3", 20, "Marcopolo")
    parking_funcs.parking_car(bus_3)
    bus_4 = cbb_class.Bus("JK10KO3242", 6, "Eicher4", 20, "Marcopolo")
    parking_funcs.parking_car(bus_4)
    bus_5 = cbb_class.Bus("PO02KO5552", 4, "Eicher5", 20, "Marcopolo")
    parking_funcs.parking_car(bus_5)
    assert len(parking_class.parking.parking_dict["a",1])==3
    # 2 buses and 1 car of above test
    assert parking_class.parking.available_space == 234

def test_case_exit_parking():
    """Available space is increasing after bike getting exit"""
    bike_1 = cbb_class.Bike("ML98LN9988", 80, "Splendor", 2, "Honda")
    parking_funcs.parking_car(bike_1)
    assert parking_class.parking.available_space == 233
    parking_funcs.exit_parking(bike_1)
    assert parking_class.parking.available_space == 234

def test_case_parking_fees():
    """fee amount depending upon the difference of time"""
    bus_1 = cbb_class.Bus("AN02KO5492", 4, "Eicher1", 20, "Marcopolo")
    parking_funcs.parking_car(bus_1)
    fee_amount=parking_funcs.exit_parking(bus_1)
    assert fee_amount
    # dynamic fee amount depending upon time

def test_case_if_available_space_not_available():
    """if available space is less than 1 , what will happen"""
    parking_class.parking.available_space=1
    # manually changing the available space to 1 for testing
    # purpose
    bus_1 = cbb_class.Bus("AN02KO5492", 4, "Eicher1", 20, "Marcopolo")
    parking_funcs.parking_car(bus_1)
    bus_2 = cbb_class.Bus("AN02KO5492", 4, "Eicher1", 20, "Marcopolo")
    sorry_message=parking_funcs.parking_car(bus_2)
    assert sorry_message=="Sorry but no parking space available"
    # as there was only one parking space left and taken by bus 1
    # so at time of bus 2 it returned the message that parking is not available
