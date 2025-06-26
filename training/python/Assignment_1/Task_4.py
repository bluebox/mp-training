#  Inverting a Dictionary with List Values :
#
# Problem Statement: Write a function invert_dictionary(d) that inverts a dictionary.
# The original dictionary's values become the new keys, and the original keys become the new values.
# Because multiple keys in the original dictionary might have the same value, the values in the new dictionary
# must be lists of the original keys.
#
# Requirements:
# Iterate through the items of the input dictionary.
# For each item, use the value as the key for the new dictionary.
# Append the original key to a list at that new key. If the new key doesn't exist yet, you must create it with a new list.

# Input: d = {'a': 1, 'b': 2, 'c': 1, 'd': 3}
# Output: {1: ['a', 'c'], 2: ['b'], 3: ['d']}

from collections import defaultdict
def invert_dictionary(input):
    output={}
    for key,value in input.items():
        if(value in output.keys()):
            output[value].append(key)
        else:
            output[value]=list(key)

    print(output)
input = {'a': 1, 'b': 2, 'c': 1, 'd': 3, 'x':1, 'y':2 }
invert_dictionary(input)
