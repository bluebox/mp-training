ans = list(map(str, input("Enter strings: ").split()))

def solve(ans):
    mp = {}
    for i in ans:
        a = ''.join(sorted(i))
        if a not in mp:
            mp[a] = 1
        else:
            mp[a] += 1

    print(mp)

solve(ans)
