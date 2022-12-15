def search(lst, low, high, target):
    if high > low:
        mid = (high+low)//2
        if lst[mid] == target:
            return mid
        elif lst[mid] > target:
            high = mid
        else:
            low = mid + 1
        return search(lst, low, high, target)
    return "object not found"


lst = [1, 2, 5, 10, 17, 20]
print(search(lst, 0, len(lst)-1, 18))