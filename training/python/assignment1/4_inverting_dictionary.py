def invert_dictionary(d):
    a = list(d.items())
    new_dic = {}
    for key,value in a:
        if value not in new_dic:
            new_dic[value] = []
        new_dic[value].append(key)
    return new_dic
di = {'a': 1, 'b': 2, 'c': 1, 'd': 3}
dic = invert_dictionary(di)
print(dic)