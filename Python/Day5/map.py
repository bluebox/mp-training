#map(func,iterable)

def multiply_by(item):
    return item*2

li=[1,2,3,4,4]
ans=list(map(multiply_by,li))
print(ans)

ans=set(map(int,li))
print(ans)
ans = dict(map(lambda x: (x, x**2), li))
print(ans)

# we can we use lambda instead of function lambda is also a function but it del itself when the use is completed
ans=list(map(lambda x:x**2,li)) #where every element comes as x and we power it by 2 and then store
print(ans)

ans=set(map(lambda x:x,li))
print(ans)