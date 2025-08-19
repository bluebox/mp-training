def process_matrix(matrix):
    total=0
    for i in matrix:
        for j in i:
            if j>10:
                break
            if j%2==0:
                continue
            total+=j
    return  total

matrix1 = [ [1, 2, 3],[77, 5, 6],[7, 11, 9] ]
print(f"Matrix 1 Total: {process_matrix(matrix1)}")

