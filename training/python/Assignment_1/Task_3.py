#  Merging Dictionaries with Summation :
#
# Problem Statement: Write a function merge_and_sum(dict1, dict2) that merges two dictionaries containing numerical values.
# The function should return a new dictionary that contains all keys from both input dictionaries.
# If a key exists in both dictionaries, the value in the new dictionary should be the sum of the values from dict1 and dict2.
#
# Requirements:
#
# The function must not modify the original dictionaries.
from collections import defaultdict
def merge_and_sum(d1,d2):
    output = defaultdict(int)
    output = d1
    for key,value in d2.items():
        if key in output:
            output[key] += value
        else:
            output[key] = value
    print(output)


dictionary_1={ 1: 10, 3: 30, 5: 50, 7: 70, 4: 40, 6: 60 }
dictionary_2={ 2: 20, 4: 40, 6: 60, 8: 80, 5: 50, 7: 70, 1:100 }
merge_and_sum(dictionary_1,dictionary_2)