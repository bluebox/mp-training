from collections import namedtuple

Person = namedtuple("Person", ["name", "age", "city"])

people = []

for i in range(3):
    name = input("Enter name: ")
    age = int(input("Enter age: "))
    city = input("Enter city: ")
    people.append(Person(name,age,city))
print(people)