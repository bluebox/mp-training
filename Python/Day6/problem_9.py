def solve():
    n=int(input())
    a,b=0,0
    for i in range(1,n+1):
        if n%i==0:
            if i%2==0:
                b+=1
            else:
                a+=1
    print(a,b)
t=int(input())
while t:
    solve()
    t-=1