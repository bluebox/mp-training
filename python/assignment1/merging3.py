def merge_and_sum(a, b):
    mp = {}
    for key, value in a.items():
        if key in b:
            mp[key] = value + b[key]
        else:
            mp[key] = value
    for key, value in b.items():
        if key not in a:
            mp[key] = value
    return mp


dict1 = {'a': 10, 'b': 20, 'c': 30}
dict2 = {'b': 5, 'c': 15, 'd': 40}

dict3 = merge_and_sum(dict1, dict2)
print(dict1)
print(dict2)
print(dict3)
