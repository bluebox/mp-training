def find_common_elements(list1,list2):
    new_list=[i for i in set(list1) if i in list2]
    # for i in list1:
    #     if i in list2 and i not in new_list:     #without list comprehension
    #         new_list.append(i)

    return new_list

list1=[1,2,3,4,4,5]
list2=[4,5,5,6,7,8]
print("new list : ",find_common_elements(list1,list2))