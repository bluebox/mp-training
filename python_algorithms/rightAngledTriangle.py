def print_triangle(len):
    for i in range(len):
        for j in range(len):
            if j <= i:
                print("*", end='')
            else:
                print(' ', end="")
        print("")


x = int(input('Enter length of the triangle: '))
print_triangle(x)