class Animal:
    def __init__(self,name):
        self.name=name
    def jump(self):
        print("Animal is jumping")
class Dog(Animal):
    def __init__(self,name="anand"):
        super().__init__(name)
    def jump(self):
        print("Dog is jumping")
a=Dog("abhi")
a.jump()
print(a.name)
# this is method overridding we can achieve method overloading using python attributes value in name="anand"

def add(a=0,b=0):
    return a+b
a=add()
b=add(10)
c=add(10,20)
print(a,b,c)
# this is method overloading