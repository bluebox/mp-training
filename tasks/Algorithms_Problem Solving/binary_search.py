"""Binary search"""


def bin_search(arr, ele):
    """Using loop"""
    start = 0
    end = len(arr) - 1
    while end >= start:
        mid = (start + end) // 2
        if arr[mid] == ele:
            print("Value is present in the list")
            break
        if arr[mid] < ele:
            start = mid + 1
        else:
            end = mid - 1
    if end < start:
        print("Value not present in the list")


def rec_bin_search(arr, ele, start, end):
    """Using recursion"""
    if end >= start:

        mid = (end + start) // 2

        if arr[mid] == ele:
            return mid

        if arr[mid] > ele:
            return rec_bin_search(arr, start, mid - 1, ele)
        return rec_bin_search(arr, mid + 1, end, ele)
    return -1


ar = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
ELE = 5
bin_search(ar, ELE)
result = rec_bin_search(ar, ELE, 0, len(ar) - 1)
if result != -1:
    print("Element is present at index", str(result))
else:
    print("Element is not present in array")
