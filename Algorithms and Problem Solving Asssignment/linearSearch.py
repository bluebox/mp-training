def linear_search(elements, target_number):
    length = len(elements)
    for i in range(length):
        if elements[i] == target_number:
            return i
    return -1


numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
target = int(input('enter the target element to find its index : '))
print(f" The index of target element {target} is {linear_search(numbers, target)}")
