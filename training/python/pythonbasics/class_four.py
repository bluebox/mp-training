
class Parent:

    def __init__(self,s):
        self.s = s

    def __add__(self, other):
        return self.s - other.s

a = Parent(10)
b = Parent(20)