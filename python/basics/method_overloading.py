class Student:
    def __init__(self,name="Unknow",age=0):
        self.name=name
        self.age=age
    def get_name(self):
        return self.name
    def get_age(self):
        return self.age
#basically python doesn't supports methodOverloading instead we use this
a=Student()
b=Student("anand")
c=Student("abhi",22)
print(a.get_age(),a.get_name())
print(b.get_age(),b.get_name())
print(c.get_age(),c.get_name())

#calling a single function with different parameter 