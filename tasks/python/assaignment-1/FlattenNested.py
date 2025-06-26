def flatten_list(nested)->list:
    flattened = []
    for i in nested:
        if isinstance(i,int):
            flattened.append(i)
        else:
            flattened+=i
    return flattened
print(flatten_list([[1,2],[1],2,[1,3,4,5,2,3,4,5]]))