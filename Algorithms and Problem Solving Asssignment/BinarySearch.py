def binary_search(elements, target):
    ei, si = len(elements), 0
    while si <= ei:
        mid = (si + ei) // 2
        if elements[mid] == target:
            return mid
        elif elements[mid] > target:
            ei = mid - 1
        else:
            si = mid + 1
    return -1


numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
target = int(input('enter the target element to find its index : '))
print(f" The index of target element {target} is {binary_search(numbers, target)}")
