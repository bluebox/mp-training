n, m = map(int, input("Enter rows and cols: ").split())
dp=[[-1 for _ in range(m+2)] for _ in range(n+2)]
def solve(a,b):
    if a==n and b==m:
        return 1
    if a>n or b>m:
        return 0
    if dp[a][b]!=-1:
        return dp[a][b]
    maxi=0
    maxi+=solve(a,b+1)
    maxi+=solve(a+1,b)
    dp[a][b] = maxi
    return maxi

print(solve(0,0))
