def merge_and_sum(dict1,dict2):
    new_dict={}
    for i in dict1:
        if i in dict2:
            new_dict[i]=dict1[i]+dict2[i]
        else:
            new_dict[i]=dict1[i]
    for i in dict2:
        if i not in new_dict:
            new_dict[i]=dict2[i] 
    return new_dict



dict1={}
dict1['a'],dict1['b'],dict1['c']=10,20,30

dict2={}
dict2['b'],dict2['c'],dict2['d']=5,15,40

new_dict=merge_and_sum(dict1,dict2)
print("dict1 : ",dict1)
print("dict2 : ",dict2)
print("new dict : ",new_dict)


print(set(dict1.items()))
print(set(dict2))






