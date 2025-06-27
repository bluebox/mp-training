l = list((1,2,3,4,5,56))
#l = [] #both are same
print(l)

s = set((10,20,30,40))
#there is no default initialization for set
s = set()
s = {10,20,30,40}
print(type(s))
print(s)

t = tuple((10,20,30,40))
#default initialization t = ()
print(t)

d = dict()
d = {}
print(type(d))
d[10] = 20
print(type(d.keys()))
s = d.keys()
# print(s[1]) cannot be done as d.keys cannot be indexed
s = list(d.keys())
print((s[0]))
print(d.values())


