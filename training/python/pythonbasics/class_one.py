# class Parent:
#     def __init__(self,s):
#         self.s = s
#     def __str__(self):
#         return str(self.s) + " hello"
#
# p = Parent(25)
# print(str(p))
# print(p.__str__())

# class A:
#     @classmethod
#     def greet(cls):
#         print("Hello a")
#
# class B(A):
    # @staticmethod
    # def greet():
    #     print("Hello b")
#     pass
#
# class C(B):
#     def greet(self):
#         print("Hello c")
#     pass
# c = C()
# c.greet()
#
# B.greet()
# C.greet(c)



class A:
    def __init__(self):
        # self.a = a
        self.greet()

    @classmethod
    def greet(cls):
        print("hello")
        print(cls)



class B(A):
    pass

# a = A(45)
# a.b = 20

a = A()