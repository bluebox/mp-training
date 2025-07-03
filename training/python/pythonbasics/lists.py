
print([x**2 for x in range(0,21)])
a = [1, 2, 3, 4, 5]

# b = a
# print(b)
# print(a.pop())
# print(a.pop(2)) #index

b = a[:]
print(b)


a = ["apples", "mangoes" , "orange"]
print([x for x in a if "es" in x])

c = a + b
print(sorted(c,key=str))
c.sort(key=str)
print(c)


# print(list([]))
# s = {1,2,3,4,}
# s.clear()
# print(s)
# c = b"hello"
# print(type(c))
# d = bytearray(c)
# d[0] = 102
# print(d)