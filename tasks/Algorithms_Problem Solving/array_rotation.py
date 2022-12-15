"""Program to rotate the array by 90"""


def right_rota(array):
    """FUNCTION"""
    n = len(array)
    for i in range(n // 2):
        for j in range(i, n - 1 - i):
            temp = array[i][j]
            array[i][j] = array[n - 1 - j][i]
            array[n - 1 - j][i] = array[n - 1 - i][n - 1 - j]
            array[n - 1 - i][n - 1 - j] = array[j][n - 1 - i]
            array[j][n - 1 - i] = temp

    for i in range(n):
        print(array[i])


A = [[1, 2, 3, 4],
     [5, 6, 7, 8],
     [9, 10, 11, 12],
     [13, 14, 15, 16]]
right_rota(A)
