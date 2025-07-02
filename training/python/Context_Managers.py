# def outer():
#   def inner():
#     print("hello")
#   outer.last=inner()
#   return outer 
# a=outer()
# outer.naam="kanishka"
# print(outer.naam)
# import math

# a=lambda x: math.pow(x,2)
# print("{:.6f}".format(a(10)))
import pdb

# for i in range(10):
#     pdb.set_trace()
#     print(i*2)

class FileManager:
    def __init__(self,name,mode):
        self.name=name
        self.file=None
        self.mode=mode
    def __enter__(self):
        try:
            self.file=open(self.name,self.mode)
            return self.file
        except FileNotFoundError as err:
            print("The requested file is not found in the given path")
    def __exit__(self,exc_type, exc_value, exc_traceback):
        try:
            self.file.close()
        except:
            print("Unable to close the file")
with FileManager("example.txt","w") as f:
    f.write("hello guys blah blah")
    
    



# with open("example.txt","r") as f:
#     content=f.readlines()
#     content_2=f.readline()

# print(content)
# print('-------------------------------')
# print(content_2)