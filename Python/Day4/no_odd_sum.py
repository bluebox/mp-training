def solve():
    n=int(input())
    ans=list(map(int,input().split()))
    a,b=0,0
    for i in ans:
        if i%2==0:
            a+=1
        else :
            b+=1
    if a==0 or b==0:
        print(0)
        return
    if a%2!=0:
        print(b//2)
    elif b%2!=0:
        print(a)
    else:
        if a*2<=b:
            print(a)
        else:
            print(b//2)
t=int(input())
while t:
    solve()
    t-=1