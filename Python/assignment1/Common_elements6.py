def solve(a, b):
    seen = set()
    common_list = [i for i in a if i in b and (i not in seen and not seen.add(i))]
    return common_list


a = [1, 2, 2, 3, 4, 5, 6]
b = [1, 2, 2, 3, 5, 6, 7, 8, 9]
common_list = solve(a, b)
print(common_list)


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
