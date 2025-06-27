class Animal:
    def __init__(self,name):
        self.name=name
    def inherited_method(self):
        print("in the Animal class")
class Dog(Animal):
    def display(self):
        print(f"{self.name}")
    def inherited_method(self):
        print("in Dog class")
        Animal.inherited_method(self)
        super().inherited_method()
a=Dog("lsdjfsljfs")
a.display()
a.inherited_method()

# python run on hierarchy it check if the child has init if not check the parent if not check the object