class Employee:
    def __init__(self,dept="math"):
        self.dept=dept
    def greet(self):
        print("hello Employee")
class Abhi(Employee):
    def __init__(self,dept):
        super().__init__(dept)
    def greet(self):
        super().greet()

a=Abhi("science")
a.greet()
