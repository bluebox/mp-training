ans="jai shree ram"
print(ans[:])
print(ans[1:])
"""
[start:stop:step]
"""
print(ans[1:5:2])
print(ans[::-1])
print(ans[len(ans)-1::-1])

ans=[1,2,2,3,3,4,4,5,6]
print(ans[0:])
print(ans[:20])# this doesn't raise error slice take the min(val,len(ans))
try:
    print(ans[20])
except Exception as e:
    print(e)