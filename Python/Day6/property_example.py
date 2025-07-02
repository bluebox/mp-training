class Student():
    def __init__(self,name,age):
        self.name=name
        self.age=age
    @property
    def age(self):
        return self._age
    @age.setter
    def age(self,val):
        self._age=val
    @property
    def get_name(self):
        return self.name
    @get_name.setter
    def set_name(self,val):
        self.name=val

a=Student('anand',22)
print(a.age)
a.set_name="abhi"
print(a.get_name)

# to use the same name as variable as the attributes we should use _attribute_name to avoid recursion