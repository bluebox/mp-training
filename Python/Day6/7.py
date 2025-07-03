def solve():
    n = int(input())
    ans = list(map(int, input().split()))

    max_ans = ans[:]
    min_ans = ans[:]

    maxi = ans[-1]
    max_ans[-1] = maxi

    for i in range(n - 2, -1, -1):
        maxi = max(maxi, ans[i])
        max_ans[i] = maxi

    mini=ans[0]
    for i in range(n):
        mini=min(mini,ans[i])
        min_ans[i]=mini

    s=''
    for i in range(n):
        if min_ans[i]==ans[i]:
            s+='1'
        elif max_ans[i]==ans[i]:
            s+='1'
        else:
            s+='0'
    print(s)
t = int(input())
for _ in range(t):
    solve()
