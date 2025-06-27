class school:
    def __init__(self, name, address, upto):
        self.name = name
        self.address = address
        self.upto = upto
    def display(self):
        print(f"school name is {self.name} is in {self.address} upto {self.upto} standard")

nts = school("nts", "marikal", 7)
nts.display()
#__init__ is a constructer which sets the objects attributes when initializing the object 