# reduce is like a accumulator in c++
from functools import reduce


def func(ini,val):
    return ini+val

ans=reduce(func,[1,2,3,4],0)
print(ans)

#___________________________________ using lambda

ans=[1,2,3,4,5,6,7,8,9,10]
val=reduce(lambda x,y:x+y,ans )
print(val)