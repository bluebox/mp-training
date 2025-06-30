def flatten_list(nested_list):
    flat_list=[j for i in nested_list for j in i]
    return flat_list

nested_list=[[1,2,3],[4,5],[7,8,9],[10]]
print("flattened list : ",flatten_list(nested_list))