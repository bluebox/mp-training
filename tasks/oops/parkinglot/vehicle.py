'''this module contains a vehicle class'''
class Vehicle:
    '''a class vehicle '''
    def __init__(self,v_milage,v_name,v_capacity,v_width,v_depth,v_number,v_manufacturer,v_time):
        self.v_name=v_name
        self.v_milage=v_milage
        self.v_capacity=v_capacity
        self.v_width=v_width
        self.v_manufacturer=v_manufacturer
        self.v_depth=v_depth
        self.v_number=v_number
        self.v_time=v_time
    def __str__(self):
        '''a method '''
        return self.v_name
        