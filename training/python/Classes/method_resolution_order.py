# class Base():
#     def __init__(self,var1,var2):
#         self.base_var1 = var1
#         self.base_var2 = var2
#     def redundant(self):
#         print("in base class, class has args :",self.base_var1,self.base_var2)
#
# class Middle(Base):
#     def __init__(self, var1, var2,var3):
#         super().__init__(var1, var2)
#         self.middle_var1 = var3
#     def redundant(self):
#         print("in middle class 1, class has args :",self.middle_var1)
#
# class Middle2(Base):
#     def __init__(self, var1, var2,var3):
#         super().__init__(var1, var2)
#         self.middle_var1 = var3
#     def redundant(self):
#         print("in middle class 2, class has args :",self.middle_var1)
#
# class End(Middle,Middle2):
#     def __init__(self, var1, var2, var3):
#         super().__init__(var1, var2,var3)
#         # Middle2.__init__(self,var1,var2,var3)
#     def redundant(self):
#         print("in end class")
# # m1 = Middle(10,20)
# e1 = End(10,20,30)
# e1.redundant()
# print(End.__mro__)

class Base:
    def __init__(self, var1, var2, **kwargs):
        super().__init__(**kwargs)
        self.base_var1 = var1
        self.base_var2 = var2

    def redundant(self):
        print("Base:", self.base_var1, self.base_var2)

class Middle(Base):
    def __init__(self, var1, var2, var3, **kwargs):
        super().__init__(var1, var2, **kwargs)
        self.middle1_var = var3

    def redundant(self):
        print("Middle1:", self.middle1_var)

class Middle2(Base):
    def __init__(self, var1, var2, var3, **kwargs):
        super().__init__(var1, var2, **kwargs)
        self.middle2_var = var3

    def redundant(self):
        print("Middle2:", self.middle2_var)

class End(Middle, Middle2):
    def __init__(self, var1, var2, var3):
        Middle.__init__(self,var1, var2, var3)  # Will call Middle → Middle2 → Base
        Middle2.__init__(self,var1,var2,var3)
    def redundant(self):
        print("End class")

e1 = End(10,20,30)
e1.redundant()
print(End.__mro__)