class Student:
    def __init__(self,name,age):
        self.name=name
        self.age=age
    def display(self):
        print(self.name,self.age)
anand=Student("anand",22)
anand.display()