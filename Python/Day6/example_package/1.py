def solve():
    n=int(input())
    ans=[i for i in range(n)]
    vis=[True for i in range(n)]
    for i in range(n):
        if vis[i]:
            temp=False
            for j in range(n):
                if vis[j] and (i+j-3)%4==0:
                    vis[j]=False
                    temp=True
                    break
            if not temp:
                print("Alice")
                return
            vis[i]=False
    # print(vis)
    print("Bob")
t=int(input())
while t:
    solve()
    t-=1