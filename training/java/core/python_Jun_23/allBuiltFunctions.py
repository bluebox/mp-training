num=-5
print("Absolute ",abs(num))

lst=[True,True]
print("all() function prints true if all are true otherwise prints false")
print(all(lst))
lst2=[True,False]
print(all(lst2))

print("any() function prints true if any one is true")
print(any(lst))
print(any(lst2))

print("binary version bin(-5): ",bin(num))

print("bool(lst) ;bool function return true unless object is empty false value 0 value")
print(bool(lst))

print("callable(lst);callable returns true if object is callable;list is not callable function is callable so -> :",callable(lst))


print("chr() retunr s char of ascii given chr(97):",chr(97))



x = dict(name = "ram", age = 6, country = "hyderabad")
print("dict() function returns dictionary array",x)
print("dir(x) returns list of methods of given object ")
print(dir(x))

rem=divmod(7,2)
print("divmod(7,2) returns tuple of quo. and rem",rem)

xx=('alice','bob','cat')
y=enumerate(xx)
print("enumarate function takes colllection and enumarates ,i.e we can print it as what we want like list dict")
print(list(y))
print(dict(y))
print(set(y))

print("eval(x) evaluates and expression")
x='print(8*8)'
print(eval(x))


a = ("india", "lanka", "Mexico")
b = ("delhi", "colombo", "Mexico")

x = zip(a, b)
print(tuple(x))
