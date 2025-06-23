class Animal:
    def __init__(self, name):
        print("parent constructor")
        self.name = name

class Dog(Animal):
    def __init__(self, name, breed):
        print("child constructor")
        super().__init__(name) 
        self.breed = breed

    def info(self):
        print(f"{self.name} is a {self.breed}")

dog = Dog("shepherd", "german")
dog.info()
