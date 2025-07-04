def solve():
    n,k=list(map(int,input().split()))
    val=n-k+1
    for i in range(val,0,-1):
        print(i,end=' ')
    for i in range(val+1,n+1):
        print(i,end=' ')
    print()
t = int(input())
while t:
    solve()
    t -= 1
