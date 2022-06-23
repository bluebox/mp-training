"""This file has a vechile class and its methods"""
import datetime
import parking_class
class Vechile:
    """Class with vechiles main properties"""
    def __init__( self, numberplate, Milage, Name, Capacity, Manufacturer ) :
        """Constructor"""
        self.vech_number=numberplate
        self.vech_milage=Milage
        self.vech_name=Name
        self.vech_capacity=Capacity
        self.vech_manufacturer=Manufacturer
        self.vech_timediff=None
        self.vech_entrytiming=None

    def entry_time(self):
        """Sets the entry time of the vechile in parking"""
        self.vech_entrytiming=datetime.datetime.now()

    def exit_time(self):
        """Returns the diff between the entry and exit point"""
        # self.diff=datetime.datetime.now()-self.timing
        self.vech_timediff=datetime.datetime(2022, 6, 24)-self.vech_entrytiming
        parking_class.parking.available_space=parking_class.parking.available_space+1
        return self.vech_timediff.total_seconds()
