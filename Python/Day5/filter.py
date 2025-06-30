# this is same as map but it filter the valid element and its function return boolean

def is_even(item):
    return item%2==0
li=[1,2,3,4,5,6]

ans=list(filter(is_even,li))
print(ans)

def is_odd(item):
    return item%2!=0
ans=list(filter(is_odd,li))
print(ans)
# filter using lambda

ans=list(filter(lambda x:x%2==0,li))
print(ans)