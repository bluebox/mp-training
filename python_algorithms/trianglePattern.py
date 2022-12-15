"""Module to print rectangle"""


def print_triangle(len):
    for i in range(len):
        for j in range(len*2-1):
            if j < (len-1-i) or j > (len - 1 + i):
                print(' ', end="")
            else:
                print('*', end = "")
        print("")


height = int(input('Enter the height of Triangle:'))
if height % 2 == 0:
    height -= 1
print_triangle(height)
