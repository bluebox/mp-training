def invert_dictionary(norm_dict:dict):
    inv_dict = dict()
    for k,v in norm_dict.items():
        if v in inv_dict:
            inv_dict[v].append(k)
        else:
            inv_dict[v] = [k]
    return inv_dict

dictionary = {"a":1,"b":2,"c":1,"d":3}
print(invert_dictionary(dictionary))