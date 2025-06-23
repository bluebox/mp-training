class Animal:
    def __init__(self, name):
        self.name = name

    def speak(self):
        print(f"{self.name} makes a sound")

class Dog(Animal):
    def speak(self):
        print(f"{self.name} barks")

animal=Animal("wolf")
dog = Dog("Buggy")
dog2=Animal(dog)
dog2=Animal(dog)

# animal.speak()
dog2.speak()  
