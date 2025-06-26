def group_by_key(tuples_list):
    new_dic = {}
    for pair in tuples_list:
        key, value = pair
        if key not in new_dic:
            new_dic[key] = []
        new_dic[key].append(value)
    return new_dic


tuples = [("a",1.2),("b",3),("c",2),("a",9) ,("c",True), ("a","hello"),("b","a") ]
dic = group_by_key(tuples)
print(dic)