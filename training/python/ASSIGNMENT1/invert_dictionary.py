def invert_dictionary(d):
    dictonary={}
    for i in d:
        if d[i] not in dictonary:
            dictonary[d[i]]=[i]
        else:
            dictonary[d[i]].append(i)
    return dictonary
d = {'a': 1, 'b': 2, 'y': 1, 'd': 3}
print(invert_dictionary(d))