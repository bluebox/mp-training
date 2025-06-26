#  Dictionary CRUD with None :
#
# Problem Statement: Write a function create_or_update_item(items_dict, key, value=None) that acts like a simplified
# Create/Read/Update/Delete (CRUD) operation on a dictionary.
#
# Logic:
# If a value is provided (i.e., it is not None), the function should add the key-value pair to the dictionary.
# If the key already exists, its value should be updated.
# If the value argument is not provided or is explicitly None, the function should ensure the key is removed from the dictionary.
# If the key doesn't exist, the function should do nothing and not raise an error.
# Requirements:
# The function must correctly differentiate between a provided value and the default None.
# It must modify the dictionary passed into it.
# It should return the modified dictionary.

def create_or_update_item(dictionary,key,value=None):
    # if already exists
    if value is not None and value != '':
        if key not in dictionary:
            print("Item not present in dictionary...Inserting item")
            dictionary[key]=value
            print("Value inserted")
        else:
            print("Item found in dictionary...Updating item value")
            dictionary[key]=value
            print("value updated")
    elif value == None:
        if key in dictionary :
            dictionary.pop(key)
            print("Got None condition , Existing Item removed")
        else:
            print("Key doesn't exist.")
    else:
        print("Can't perform any action")
    print(dictionary)
    print()
    return dictionary
dictionary={ 1: 10, 3: 30, 4: 40, 5: 50 , 6: 60, 7: 70}
key=int(input())
try:
    value = int(input())
except:
    value = None

print(create_or_update_item(dictionary,key,value))

