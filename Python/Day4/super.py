class human:
    def __init__(self,name,age):
        self.name=name
        self.age=age
class student(human):
    def __init__(self,name,age,section):
        super().__init__(name,age)
        # human.__init__(self,name,age) this is also valid instead we use super
        self.section=section
    def display(self):
        print(f"my name is {self.name} and i am {self.age} and studing in {self.section}")
a=student("anand",22,'CSE')
a.display()