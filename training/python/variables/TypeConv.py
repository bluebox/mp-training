c = 'c '
print(ord(c.strip(" ")[0]))
print(chr(ord(c[0])))
i = 10
f = float(i)
print(i.__sizeof__(),f.__sizeof__())
s = '23'
j = int(s)

tup = set((1,2,3,4,4)).union(set((2,3,4,5,56)))
s = tup.intersection((1,2,5,6))
print(tuple(tup))
print(s)
a = (1,2,3,4,5)+(1,3,4,5,6)
print(id(a),a.__sizeof__())