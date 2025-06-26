ans={}
n=int(input("enter size of n"))
val=list(map(int,input().split()))
print(val)
for i in val:
    if i in ans:
        ans[i]+=1
    else:
        ans[i]=1
print(ans)