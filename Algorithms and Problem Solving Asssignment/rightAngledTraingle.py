def print_right_angled_triangle_pattern(number_of_rows):
    for i in range(number_of_rows):
        for j in range(i + 1):
            print('*', end=' ')
        print()


print_right_angled_triangle_pattern(int(input('Enter the number of rows in a right angled triangle : ')))
