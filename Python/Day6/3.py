def solve():
    n, m = list(map(int, input().split()))
    mat = []
    for i in range(n):
        mat.append(list(map(int, input().split())))
    maxi, cnt = 0, 0
    for row in mat:
        for col in row:
            maxi = max(maxi, col)
    for row in mat:
        for col in row:
            if col == maxi:
                cnt += 1
    mp = dict()
    up = dict()
    for i in range(n):
        a = 0
        for j in range(m):
            if mat[i][j] == maxi:
                a += 1
        mp[i] = a
    for i in range(m):
        a = 0
        for j in range(n):
            if mat[j][i] == maxi:
                a += 1
        up[i] = a
    for i in range(n):
        for j in range(m):
            val = mp[i] + up[j]
            if mat[i][j] == maxi:
                val -= 1
            if val == cnt:
                print(maxi - 1)
                return
    print(maxi)


t = int(input())
while t:
    t -= 1
    solve()
