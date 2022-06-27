'''this module contains a series class '''
class series:
    '''series is aclass'''
    def __init__(self):
        self.total_area=500000
        self.car_area=self.total_area*0.6
        self.bike_area = self.total_area * 0.3
        self.bus_area = self.total_area * 0.1
        self.list1=[]


    def __str__(self) :
        '''it returns a string message of an object of class '''
        return "series"
    