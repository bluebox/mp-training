def group_by_key(ans):
    mp = {}
    for i in ans:
        key = i[0]
        val = i[1]
        if key in mp:
            mp[key].append(val)
        else:
            mp[key] = []
            mp[key].append(val)
    return mp


n = int(input("enter a no of tuple :"))
list_of_tuple = list()
for i in range(n):
    key = input("enter key : ")
    value = input("enter value : ")
    list_of_tuple.append((key, value))
dict_val = group_by_key(list_of_tuple)
print(dict_val)
