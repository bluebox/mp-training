class Student:
    def __init__(self,name="",age=0,Class="X"):
        self.__name=name # __ to private variable
        self.age=age     # without __ is a public variable
        self._Class=Class
    def display(self):
        print(self.__name)
anand=Student("anand",22)
# try to use the name attribute
# print(anand.name)
print(anand.age)
# print(anand.Class) Class is also not accesible because Class is protected
class Abhi(Student):
    def printVal(self):
        print(self._Class)
        # print(self.__name) cannot this because private variables are not inherited
a=Abhi()
a.printVal()