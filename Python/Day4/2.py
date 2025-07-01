def solve():
    n=int(input())
    s=input()
    mp=dict()
    for i in range(n):
        if s[i] in mp and i!=n-1:
            print("YES")
            return
        else:
            mp[s[i]]=1
    s=s[::-1]
    up=dict()
    for i in range(n):
        if s[i] in up and i!=n-1:
            print("YES")
            return
        else:
            up[s[i]]=1
    print("NO")
t=int(input())
while t:
    t-=1
    solve()