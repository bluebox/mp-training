def rotating_a_matrix_by_90_degree(matrix):
    rotated_matrix = []
    for col in range(0, len(matrix)):
        temp = []
        for row in range(len(matrix) - 1, -1, -1):
            temp.append(matrix[row][col])
        rotated_matrix.append(temp)
    return rotated_matrix


input_matrix = [[1, 2, 3],
                [4, 5, 6],
                [7, 8, 9]]

print(rotating_a_matrix_by_90_degree(input_matrix))
