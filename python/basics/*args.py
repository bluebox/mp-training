def solve(*a):
    print(a[2])
solve(1,2,3,4)


class calcuate:
    sum=0
    @staticmethod
    def solve():
        print("jai shree ram")
    @classmethod
    def change(cls, new_sum):
        cls.sum=new_sum
    def add(self,a,b):
        return a+b
a=calcuate()
print(a.sum)
a.solve()
a.change(20)
print(a.sum)
print(a.add(20,30))

class Dog:
    #class variable
    val=0
a=Dog()
print(a.val)