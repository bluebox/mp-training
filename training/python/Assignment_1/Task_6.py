#   Common Elements with List Comprehension :
#
# Problem Statement:
# Write a function find_common_elements(list1, list2) that takes two lists as input.
# The function should return a new list containing only the elements that are present in both lists,
# without any duplicates.
#
# Requirements:
# You must use a single list comprehension to solve this.

def find_common_elements(basket1, basket2):
    check=set()
    common_items=[ item for item in basket1 if item in basket2 and not (item in check or check.add(item)) ]
    print(common_items)

basket1 = [ "cookies" , "chocolates" , "chips" , "fruits" , "vegetables", "milk", "chocolates" ]
basket2 = [ "cookies" , "chocolates" , "milk" , "vegetables" , "coconut", "milk" ]
find_common_elements(basket1,basket2)

