class A:
    a = 20
    b = 30
    def __str__(self):
        return f"{self.a} {self.b} {self.c}"

a = A()
a.c = 60
print(a)

del a.c

a.c = 90
print(a)



# a = 40
# b = 60
#
# for i in enumerate(range(1,10)):
#     print(i)