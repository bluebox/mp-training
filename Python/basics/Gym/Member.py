class Member:
    def __init__(self,name,age):
        self.__name=name
        self.__age=age
    def getName(self):
        return self.__name
    def getAge(self):
        return self.__age
    def setName(self,name=None):
        self.__name=name
    def setAge(self,age=None):
        self.__age=age
    def display(self):
        print(self.__name,self.__age,end=" ")