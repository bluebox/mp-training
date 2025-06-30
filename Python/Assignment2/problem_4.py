def process_matrix(matrix):
    total=0
    for mat in matrix:
        for val in mat:
            if val%2==0:
                continue
            elif val<=10:
                total+=val
    return total

rows = int(input("enter no of rows:"))
matrix = []
for row in range(rows):
    matrix.append(list(map(int, input().split())))
total=process_matrix(matrix)
print(total)