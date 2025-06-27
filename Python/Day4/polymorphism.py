class school:
    def duties(self):
        print("have different duties")
    def staff(self):
        print("staff")


class NTS(school):
    def duties(self):
        print("NTS")
    def staff(self):
        print("NTS staff")

a=NTS()
b=school()
a.duties()
b.duties()

a.staff()
b.staff()

# here we have same function called duties and staff which does differnt works for different object
# this polymorphism, this is runtime polymorphism