def solve():
    n,j,k=list(map(int,input().split()))
    ans=list(map(int,input().split()))
    ele=ans[j-1]
    if k>1:
        print("YES")
        return
    maxi=max(ans)
    if maxi==ele:
        print("YES")
        return
    print('NO')
t=int(input())
while t:
    solve()
    t-=1