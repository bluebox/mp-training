class Animal:
    __val=10 # this is class attributs like static in java
    # to access this we need to have @classmethod
    @classmethod
    def setVal(cls,val):
        cls.__val=val
    @classmethod
    def display(cls):
        return cls.__val
print(Animal.display())
Animal.setVal(100)
print(Animal.display())

class Dog:
    @staticmethod
    def display():
        print("val")

a=Dog()
a.display()
Dog.display()
#static method can accesed by bog class and objects as well
