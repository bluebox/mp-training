from copy import copy, deepcopy
from collections import defaultdict

def append_dict_values(d1,d2):
    d1_copy = copy(d1) #shallow copy
    # d1_copy = deepcopy(d1) #deep copy
    output = defaultdict( list,d1_copy )
    for key,value in d2.items():
            output[key].extend(value)
    print(d1)
    print(dict(output))
dictionary_1 = { 1 : [10, 100], 2 : [20, 200], 3 : [30, 300]}
dictionary_2 = { 1 : [11, 111], 2 : [22, 222], 4 : [40, 400]}
append_dict_values(dictionary_1,dictionary_2)