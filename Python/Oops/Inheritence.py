class Animal:
    def __init__(self):
        self.name="Sai mama"
        print("this is Animal class")
        self.is_wild=None
    def set_is_wild(self,wild):
        self.is_wild=wild
class Dog(Animal):#this is example of inheritence
    def __init__(self):
        super().__init__()
        print("Dog class")
    def display(self):
        print(self.name)
a=Dog()
a.display()

class puppy(Dog):
    def display(self):
        print("puppy")

a=puppy()
a.display()
print(a.name)

