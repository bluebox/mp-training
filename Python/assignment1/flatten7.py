def flatten_list(val):
    ans = [j for i in val for j in (i if isinstance(i, list) else [i])]
    return ans


list_val = [1, [1, 2, 3], [1]]
list_val = flatten_list(list_val)
print(list_val)
