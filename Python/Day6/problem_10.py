def solve():
    a,b=list(map(int,input().split()))
    if a==0:
        print("NO")
    elif (b%a==0):
        print("YES")
    else :
        print("NO")
t=int(input())
while t:
    t-=1
    solve()