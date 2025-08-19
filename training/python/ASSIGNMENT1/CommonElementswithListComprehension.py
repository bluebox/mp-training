def find_common_elements(list1, list2):
    return list(set([i for i in list1 if i in list2]))

list1=[1,2,3,4,5,6,7,8,60]
list2=[2,3,5,7,9,12,44]
print(find_common_elements(list1, list2))