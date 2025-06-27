ans=list()
n=int(input("enter no of rows"))
for i in range(n):
    # print(f"enter {i} col value")
    ans.append(list(map(int,input().split())))

for i in range(n):
    for j in range(i+1):
       ans[i][j],ans[j][i]=ans[j][i],ans[i][j]
for i in ans:
    print(i)