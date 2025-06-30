def group_by_key(tuples_list):
    d={}
    for tuple in tuples_list:
        if tuple[0] not in d:
            d[tuple[0]]=[tuple[1]]
        else:
            d[tuple[0]].append(tuple[1])
    return d



tuples_list=[(1,'a'),(2,'b'),(3,'c'),(1,'d')]
print(group_by_key(tuples_list))