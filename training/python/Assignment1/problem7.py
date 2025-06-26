def flatten_list(nested_list):
    return  [x for i in nested_list for x in i]



li = [[1,2],[3,4],7,[5,6,7,8]]
returned_result = flatten_list(li)
print(returned_result)