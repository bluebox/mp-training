class Dog:
    def __init__(self):
        self.__name="sslfjsldjf"  #__ private
        self._age=22         # _ protected
        self.work="sdlkfjsl" # public
    def getName(self):
        return self.__name
    def set_name(self,name):
        self.__name=name
    def get_age(self):
        return self._age
    def set_age(self,age):
        self._age=age
a=Dog()
print(a.getName())
# cann't access using a.name
# print(a.name)
print(a.get_age())
# print(a._age)  this works but not supposed in python
print(a.work) # directly accessible
# use setter and getter to access the __ and _ variables