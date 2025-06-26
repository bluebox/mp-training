def solve(dict1, key, value=None):
    if value == None:
        if key in dict1:
            dict1.pop(key)
    else:
        dict1[key] = value
    return dict1


dict1 = {'a': 2}
dict1 = solve(dict1, 5, 0)
dict1 = solve(dict1, 'a')
dict1 = solve(dict1, 5, 10)
print(dict1)
