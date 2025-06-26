def find_common_elements(list1, list2):
    return  list(set([x for x in list2 if x in list1]))
li1 = [1, 2, 3]
li2 = [3, 4, 5]
print(find_common_elements(li1, li2))