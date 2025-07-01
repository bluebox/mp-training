def process_matrix(matrix):
    i = 0
    total = 0
    while i<len(matrix):
        j = 0
        while(j<len(matrix[i])):
            if matrix[i][j]%2 == 0:
                pass
            elif matrix[i][j] >10:
                break
            else:
                total += matrix[i][j]
            j+=1
        i+=1
    return total
matrix = [[1,2,3],[4,12,6],[7,8,9]]
print(f"Matrix Total:{process_matrix(matrix)}")