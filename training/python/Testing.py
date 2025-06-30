# class Test: 
#     def __init__(self,name):
#         self.name=name
#     def return_self(self):
#         print(f'blablablah {self.name}')
#     @classmethod
#     def call_return(cls):
#         print(f"hello guys")
# new_obj=Test("Kanishka")
# print(new_obj.call_return())

# class Animal:
#     def __init__(self,cost=20,name="rabbit"):
#         self.name=name 
#         self.cost=cost 
#     def run(self):
#         print("Animal")

# class Mouse(Animal):
#     def __init__(self,cost,name,size):
#         super().__init__(cost,name)
#         self.size=size
#     def run(self):
#         print("Calling from Mouse")

# a=Mouse(30,"pewpew",19)
# print(a.name,a.cost,a.size)

# class Hamster(Animal):
#     def __init__(self,size_2):
#         self.size=size_2
#     def run(self):
#         print("Calling from Hamster")

# class Rodent(Mouse,Hamster):
#     def __init__(self,size_2):
#         self.size=size_2

# new_an=Rodent(5)
# print(Rodent.mro())

# a=10
# def incre():
#     b=20
#     print(id(b))
#     def decre():
#         nonlocal b
#         print(id(b))
#         b-=10
#     decre()
#     return b
# incre()


# def my_func():
#     '''
#     This function is the best 
    
#     '''

#     print("I'm the best!!!")
# print("hello world")