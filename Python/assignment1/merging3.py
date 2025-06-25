def solve(a,b):
    mp={}
    for key,value in a.items():
        if key in b:
            mp[key]=value+b[key]
            b.pop(key)
        else:
            mp[key]=value
    for key,value in b.items():
        mp[key]=value
    return mp
dict1={'a':10,'b':20,'c':30}
dict2={'b':5,'c':15,'d':40}

dict3=solve(dict1,dict2)
print(dict3)