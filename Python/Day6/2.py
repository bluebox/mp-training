def solve():
    a, b, c = list(map(int, input().split()))
    d=min(b,c)
    e=max(b,c)
    if d<=a<=e:
        print("NO")
        return
    print("YES")
t = int(input())
while t:
    solve()
    t -= 1
