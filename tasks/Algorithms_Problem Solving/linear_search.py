"""Linear Search"""


def lin_ser(arr, ele):
    """Linear"""
    for i in arr:
        if i == ele:
            print("Element found")
            return
    print("Element not found")


ar = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
ELE = 10
lin_ser(ar, ELE)
