mat = [[1, 2, 3, 4, 5],
       [6, 7, 8, 9, 10],
       [11, 12, 13, 14, 15],
       [16, 17, 18, 19, 20],
       [21, 22, 23, 24, 25]]
print(mat)
m = 5
n = 5
count = 0
i = 0
j = 0

while count < 25:
    while j < m and mat[i][j] != 0:
        print(mat[i][j], end=' ')
        mat[i][j] = 0
        j += 1
        count += 1
    j -= 1
    i += 1
    while i < n and mat[i][j] != 0:
        print(mat[i][j], end=' ')
        mat[i][j] = 0
        i += 1
        count += 1
    i -= 1
    j -= 1
    while j >= 0 and mat[i][j] != 0:
        print(mat[i][j], end=' ')
        mat[i][j] = 0
        j -= 1
        count += 1
    j += 1
    i -= 1
    while i >= 0 and mat[i][j] != 0:
        print(mat[i][j], end=' ')
        mat[i][j] = 0
        i -= 1
        count += 1
    i += 1
    j += 1

