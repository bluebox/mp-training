def solve():
    n,s=list(map(int,input().split()))
    ans=list(map(int,input().split()))
    mini=min(ans)
    maxi=max(ans)
    if mini<=maxi<=s:
        print(abs(s-mini))
    elif s<=mini<=maxi:
        print(abs(maxi-s))
    else:
        val = abs(s - maxi)
        vals = abs(s - mini)
        val = val + vals + min(val, vals)
        print(val)
t=int(input())
while t:
    t-=1
    solve()