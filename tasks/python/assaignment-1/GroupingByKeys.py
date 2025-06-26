def group_by_key(tuples_list)->dict:
    tuple_dict = dict()
    for key,val in tuples_list:
        if key in tuple_dict:
            tuple_dict[key].append(val)
        else:
            tuple_dict[key] = [val]

    return tuple_dict
tup_list = [("a",10),("b",10),("c",10),("a",11),("c",400),("d",11),("b",55)]
print(group_by_key(tup_list))

# setdefault