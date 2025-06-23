 
class Parent(): 
	
	def __init__(self): 
		self.value = "Inside Parent"
		
	def show(self): 
		print(self.value) 
		 
class Child(Parent): 
	
	def __init__(self): 
		super().__init__()  
		self.value = "Inside Child"
		 
	def show(self): 
		print(self.value) 
		 
obj1 = Parent() 
obj2 = Child() 
obj3=Parent(obj2)
obj1.show()
obj2.show()  
obj3.show()