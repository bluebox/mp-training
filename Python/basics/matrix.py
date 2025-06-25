ans=[]
n=int(input("enter value"))
for i in range(n):
    ans.append(list(map(int,input().split())))
ans.sort()
for i in ans:
    for j in i:
        print(j,end=" ")
    print()
"""
for fixed size matrix
"""
n,m=3,3
ans=[[0 for _ in range(n)] for _ in range(m)]
ans[0][0]=20
print(ans)