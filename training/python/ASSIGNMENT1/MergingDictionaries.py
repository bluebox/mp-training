def merge_and_sum(dict1, dict2):
    new_dict={}
    for i in dict1:
        if i not in new_dict:
            new_dict[i]=dict1[i]
        else:
            new_dict[i]+=dict1[i]
    for j in dict2:
        if j not in new_dict:
            new_dict[j]=dict2[j]
        else:
            new_dict[j]+=dict2[j]
    return new_dict

dict1={'a': 10, 'b': 20, 'c': 55}
dict2 = {'b': 5, 'c': 15, 'd': 40}
final_dict=merge_and_sum(dict1, dict2)
print(final_dict)