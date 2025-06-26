ans=[1,2,3,4,5]
for i,val in enumerate(ans):
    print(i,val)
# this gives iterable with index and as well as value
mp={"anand":1,"ahbi":2}
for key,val in enumerate(mp):
    print(key,val)
#iterating through a map or dict
#using key
for key in mp.keys():
    print(key,mp[key])
for val in mp.values():
    print(val)
for key,val in mp.items():
    print(key,val)