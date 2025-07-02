def solve():
    n = int(input())
    ans = list(map(int, input().split()))

    if n == 2:
        if abs(ans[0] - ans[1]) <= 1:
            print(0)
            return
        print(-1)
        return

    for i in range(n - 1):
        if abs(ans[i] - ans[i + 1]) <= 1:
            print(0)
            return

    for i in range(1, n - 1):
        if ans[i] < min(ans[i - 1], ans[i + 1]) or ans[i] > max(ans[i - 1], ans[i + 1]):
            print(1)
            return

    print(-1)

t = int(input())
for _ in range(t):
    solve()
