class Flyer:
    def fly(self):
        print("Flying")

class Swimmer:
    def swim(self):
        print("Swimming")

class Duck(Flyer, Swimmer):
    def quack(self):
        print("Quack")

duck = Duck()
duck.fly()   
duck.swim()
duck.quack() 
