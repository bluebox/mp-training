def search(lis, num):
    for i in lis:
        if i == num:
            return i


s = map(int, input('Enter a list of integers: ').split(' '))
x = int(input('Enter a number to be found: '))
print(f'Index: {search(s, x)}')