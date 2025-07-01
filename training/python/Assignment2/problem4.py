def process_matrix(matrix):
    summation = 0
    for i in matrix:
        for x in i:
            if x > 10:
                break
            elif x%2 == 0:
                continue
            else:
                summation += x
    return summation

matrix1 = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9]
]
print(f'Matrx 1 Total: {process_matrix(matrix1)}')