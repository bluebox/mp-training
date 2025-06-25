def solve(ans):
    mp= {}
    for i in ans:
        key=i[0]
        val=i[1]
        if key in mp:
            mp[key].append(val)
        else:
            mp[key]=[]
            mp[key].append(val)
    return mp
n=int(input("enter a no of tuple :"))
ans=list()
for i in range(n):
    key=int(input("enter key : "))
    value=int(input("enter value : "))
    ans.append((key,value))
mp=solve(ans)
print(mp)