def flatten_list(nested_list):
    return [j for i in nested_list for j in i]

l=[[1,2,[0],4],[3,4,5,6,7],[12,3,4],[34]]
print(flatten_list(l))