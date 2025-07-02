
def process_matrix(matrix):
    total = 0
    for row in range(len(matrix)):
        for col in range(len(matrix[row])):
            if matrix[row][col] % 2 == 0 and matrix[row][col] <= 10:
                print("Skip even number: "+ str(matrix[row][col]) + " at row "  + str(row) + " column "+  str(col))
                continue
            if matrix[row][col] > 10:
                print("Stop processing " + str(matrix[row][col]) + " at row " + str(row) + " column " + str(col))
                break
            else:
                print("Processing " + str(matrix[row][col]) + " at row " + str(row) + " column " + str(col))
            total += matrix[row][col]
    return total

matrix = [
    [-1, 2, 3 ],
    [14, 5, 6], 
    [17, 8, 11], 
    [19, 10, 12]
    ]
print(process_matrix(matrix))