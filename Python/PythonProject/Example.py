class Animal:
    def __init__(self):
        self.name=None;
        self.age=None;
    def setName(self,name):
        self.name=name;
    def setAge(self,age):
        self.age=age
    def getName(self):
        return self.name
    def getAge(self):
        return self.age

class Cat(Animal):
    def display(self):
        print(self.name)
        print(self.age)
cat=Cat()
cat.display()
s = "hello"
s+="anand"

print(s)
