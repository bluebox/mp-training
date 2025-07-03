
'''
I've written this program to logg actions when a point of data is modified 
'''


import time
file_1=open('logging.txt','w')
class Logger:
    def __get__(self,obj,objtype=None):
        file_1.write(f"The value has been accessed at {time.strftime('%Y-%m-%d %H:%M:%S',time.localtime()) } \n")
        return obj.data
    def __set__(self,obj,value):
        file_1.write(f"The value was reset at {time.strftime('%Y-%m-%d %H:%M:%S',time.localtime())} \n")
        obj.data=value

class ValueManipulator:
    our_data=Logger()
    def __init__(self,our_data):
        self.our_data=our_data
    def set_value(self,new_val):
        self.our_data=new_val
    def get_value(self):
        return self.our_data


instance_1=ValueManipulator(25)
time.sleep(5)
print(instance_1.get_value())
time.sleep(5)
instance_1.set_value(36)
time.sleep(5)
print(instance_1.get_value())

file_1.close()