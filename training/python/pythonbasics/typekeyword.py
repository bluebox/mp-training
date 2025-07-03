import math

print(type(math.nan))

print(type(math))

a =  complex(3,4)
print(a)
print(bool(a))

print(bool(3))
print(bool(None))

print(sum((True,False,True)))

print(abs(a)) #if it is complex, returns magnitude
print(type(abs(a)))
print(a)

print(type(math.nan))
print(type(None))

print(math.trunc(3.0))
print(round(128.87654,4))

n=7
print(n.bit_length())

print('''
alpha
numeric
values
''')




a = {1:"one", True : "True" , False : "False", 0 : "Zero"}
print(a[1],a[0])