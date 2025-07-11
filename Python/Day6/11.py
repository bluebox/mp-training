def solve():
    a,b,x,y=list(map(int,input().split()))
    cnt=0
    if a>b:
        if a-b==1 and a%2==1:
            print(y)
            return
        else:
            print(-1)
            return
    while a!=b:
        if a%2==0:
            cnt+=min(x,y)
        else:
            cnt+=x
        a+=1
    print(cnt)
t=int(input())
while t:
    solve()
    t-=1