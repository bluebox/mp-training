
class Parent:
    def __init__(self):
        self.public_var = "I'm Public"
        self._protected_var = "I'm Protected"
        self.__private_var = "I'm Private"

    def display(self):
        print(self.public_var)
        print(self._protected_var)
        print(self.__private_var)

class Child(Parent):
    def display(self):
        print(self.public_var)
        print(self._protected_var)

child = Child()
child.display()
# child.display1()
# print(child.public_var)
# print(child._protected_var)
# print(child._Parent_private_var)