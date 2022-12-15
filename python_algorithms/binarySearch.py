def search(lis, num, low, high):
    if low < high:
        mid = (low + high)//2

        if lis[mid] == num:
            return mid
        elif num > lis[mid]:
            low = mid + 1
        elif num < lis[mid]:
            high = mid
        return search(lis, num, low, high)
    return -1


s = list(map(int, input('Enter a list: ').split(' ')))
x = int(input('Enter a number to be found: '))
s.sort()
print(s)
index = search(s, x, 0, len(s))
print(f'Index: {index}')
