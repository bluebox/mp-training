class student():
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def getName(self):
        return self.name

    def __str__(self):
        return f"my name is {self.name} and {self.age} years old"


student1 = student("abhi", 22)
print(str(student1))

# student1 is a object of student and getName is a method and name and age is objects attributs
