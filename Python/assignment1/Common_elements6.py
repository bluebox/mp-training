def solve(a, b):
    ans = []
    for i in a:
        if i in b and i not in ans:
            ans.append(i)
    return ans


a = [1, 2, 2, 3, 4, 5, 6]
b = [1, 2, 2, 3, 5, 6, 7, 8, 9]
ans = solve(a, b)
print(ans)


# ------------------------------------#

def find(a, b):
    ans = [x for x in a if x in b]
    st = set(ans)
    ans = list(st)
    return ans


a = [1, 2, 2, 3, 4, 5, 6]
b = [1, 2, 2, 3, 5, 6, 7, 8, 9]
ans = find(a, b)
print(ans)
