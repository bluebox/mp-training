def merge_and_sum(dict1:dict,dict2:dict)->dict:
    concat_dict = dict()
    for i in dict1:
        concat_dict[i] = dict1[i]
    for i in dict2:
        if i in concat_dict:
            concat_dict[i]+=dict2[i]
        else:
            concat_dict[i]= dict2[i]
    return concat_dict

dict_1 = {"a":21,"b":40,"c":22,"d":50}
dict_2 = {"e":50,"a":49,"f":80,"c":50}

print(merge_and_sum(dict_1,dict_2))