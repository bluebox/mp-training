def group_by_key(tuples_list):
    dictonary={}
    for i in tuples_list:
        if i[0] not in dictonary:
            dictonary[i[0]]=[i[1]]
        else:
            dictonary[i[0]].append(i[1])
    return dictonary

final_dict=group_by_key([("a",1),("b",2),("b",0)])
print(final_dict)