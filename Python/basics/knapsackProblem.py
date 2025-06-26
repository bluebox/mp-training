def solve(wei,val,ans,n,tar):
    if n<0:
        return 0
    if ans[n][tar]!=-1:
        return ans[n][tar]
    maxi=-(10**9)
    if tar>=wei[n]:
        maxi=max(maxi,val[n]+solve(wei,val,ans,n-1,tar-wei[n]))
    maxi=max(maxi,solve(wei,val,ans,n-1,tar))
    return maxi

n=int(input("enter size of W and val"))
Wei=list(map(int,input().split()))
Val=list(map(int,input().split()))
tar=int(input("enter target"))
ans=[[-1 for _ in range(tar+1)] for _ in range(n)]
print(solve(Wei,Val,ans,n-1,tar))
            