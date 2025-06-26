import sys
a = 10
b = 20
c = 10
if a is c:
    print("a and c are same objects")#pyhton inherently interns -5 to 255 numbers
else:
    print("a and c are different objects")

a = 20
print(c)
if a is b:
    print("yes")
else:
    print("no")

num1 = 2**256
num2 = 2**256
if num1 is num2:
    print("num1 and num2 are same objects")
else:
    print("num1 and num2 are different objects")

#explictly interning by using sys.intern
sys.intern(str(num1))
sys.intern(str(num2))

if num1 is num2:
    print("num1 and num2 are same objects")
else:
    print("num1 and num2 are different objects")