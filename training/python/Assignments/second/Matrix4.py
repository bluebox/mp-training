def process_matrix(matrix):
    running_total=0
    for row in matrix:
        for i in row:
            if i>10:
                break
            elif i%2==0:
                continue
            else:
                running_total+=i
    return running_total



matrix=[
    [10,2,3],
    [4,5,6],
    [7,8,9]
]
print(f"Matrix Total: {process_matrix(matrix)}")