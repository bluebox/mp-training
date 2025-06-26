def find_common_elements(list1:list,list2:list)->list:
    # concat_list = list1+list2
    list3 = [i for i in list2 if i in list1]
    return list3

print(find_common_elements([1,2,3,5,7,8],[2,1,4,9,10]))