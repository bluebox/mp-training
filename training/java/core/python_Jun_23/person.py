class Person:
    
    def __init__(self,name,age):
        self.name=name
        self.age=age
    def __str__(self):
        return f"{self.name}({self.age})"
    
class Child:

    def __init__(self,name,age,place):
        super().__init__(name,age)
        self.place=place
    def __str__(self):
        return f"{self.place}"

p1=Person("vijay",20)
print(p1)
p2=Child("arjun","23","ooty")
print(p2)        

