def find_common_elements(list1,list2):
    list1 = set(list1)
    list2 = set(list2)
    if len(list1) <= len(list2):
        return [x for x in list1 if x in list2]
    return [x for x in list2 if x in list1]

l1 = [1,2,3,4,5,5,4]
l2 = [5,6,7,1,4,7]
common_elements = find_common_elements(l1,l2)
print(common_elements)