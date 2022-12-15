def transpose(x):
    for i in range(len(x)):
        for j in range(i):
            x[i][j] = x[j][i]


def reverse(x):
    i = 0
    j = len(x)-1
    while i < j:
        temp = x[i]
        x[i] = x[j]
        x[j] = temp
        i += 1
        j -= 1


mat = [
    [1, 2, 3, 4, 5],
    [6, 7, 8, 9, 10],
    [11, 12, 13, 14, 15],
    [16, 17, 18, 19, 20],
    [21, 22, 23, 24, 25]
]
reverse(mat)
transpose(mat)
print(mat)
