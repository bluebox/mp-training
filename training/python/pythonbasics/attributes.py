#
# class Parent:
#
#     a = 20
#     def __init__(self):
#         self.a = 90
#         # Parent.a = 100
#
# p = Parent()
# print(p.a)
# print(Parent.a)


class Counter:
    count = 0
    def __init__(self):
        self.count+=1

Counter(),Counter(),Counter(),Counter(),Counter()
c = Counter()
print(Counter.count)
print(c.count)

class Counter2:
    count = 0
    def __init__(self):
        Counter2.count += 1

Counter2(),Counter2(),Counter2(),Counter2(),Counter2()
c2 = Counter2()
print(Counter2.count)
print(c2.count)