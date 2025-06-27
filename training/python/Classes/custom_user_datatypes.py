from collections import UserDict
from decimal import InvalidOperation


class MyDict(UserDict):
    def __delitem__(self,key):
        raise InvalidOperation
    def __setitem__(self, key, value):
        if key in self.keys():
            raise InvalidOperation
        super().__setitem__(key,value)
d = {'a':1,'b':2,'c':3}
custom_dict = MyDict(d)
print(custom_dict.data)
try:
    custom_dict['b'] = 10
except InvalidOperation:
    print("Operation not allowed ")


