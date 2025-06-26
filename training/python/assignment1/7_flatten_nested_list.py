def flatten_list(nested_list):
    return [value for inner_list in nested_list for value in inner_list]

l = [
    [1,2,3,4,5],
    [2,3,4,5,6],
    ["one","two"]
]

f_list = flatten_list(l)
print(f_list)