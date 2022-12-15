"""Program to print the array in spiral form"""


def spiral_print(array):
    """FUNCTION"""
    top = left = 0
    bottom = len(array) - 1
    right = len(array) - 1

    while True:
        if left > right:
            break

        for i in range(left, right + 1):
            print(array[top][i], end=' ')
        top = top + 1

        if top > bottom:
            break

        for i in range(top, bottom + 1):
            print(array[i][right], end=' ')
        right = right - 1

        if left > right:
            break

        for i in range(right, left - 1, -1):
            print(array[bottom][i], end=' ')
        bottom = bottom - 1

        if top > bottom:
            break

        for i in range(bottom, top - 1, -1):
            print(array[i][left], end=' ')
        left = left + 1


A = [[1, 2, 3, 4],
     [5, 6, 7, 8],
     [9, 10, 11, 12],
     [13, 14, 15, 16]]
spiral_print(A)
