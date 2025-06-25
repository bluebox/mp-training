def solve(a):
    mp={}
    for key,value in a.items():
        if value in mp:
            mp[value].append(key)
        else:
            mp[value]=[key]
    return mp
d={'a':1,'b':2,'c':1,'d':3}
ans=solve(d)
print(ans)