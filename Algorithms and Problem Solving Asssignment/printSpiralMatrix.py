def print_spiral_matrix(matrix, end_row_index, end_column_index):
    start_row_index, start_column_index = 0, 0
    while start_row_index < end_row_index and start_column_index < end_column_index:
        for i in range(start_column_index, end_column_index):
            print(matrix[start_row_index][i], end=' ')
        start_row_index += 1

        for i in range(start_row_index, end_row_index):
            print(matrix[i][end_column_index - 1], end=' ')
        end_column_index -= 1

        if start_row_index < end_row_index:
            for i in range(end_column_index - 1, start_column_index - 1, -1):
                print(matrix[end_row_index - 1][i], end=' ')

            end_row_index -= 1

        if start_column_index < end_column_index:
            for i in range(end_row_index - 1, start_row_index - 1, -1):
                print(matrix[i][start_column_index], end=' ')

            start_column_index += 1


input_matrix = [[1, 2, 3, 4, 5],
                [5, 6, 7, 8, 6],
                [9, 10, 11, 12, 7],
                [13, 14, 15, 16, 8],
                [9, 10, 11, 12, 13]]

print_spiral_matrix(input_matrix, len(input_matrix), len(input_matrix))
