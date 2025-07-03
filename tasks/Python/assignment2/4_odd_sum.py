def process_matrix(matrix):
    total = 0
    for row in matrix:
        for value in row:
            if value > 10:
                break
            elif value % 2 == 0:
                continue
            else:
                total += value
    return total

matrix1 = [[1,11,2],[3,4,5],[7,6,7]]
print(f"Matrix 1 Total: {process_matrix(matrix1)}")