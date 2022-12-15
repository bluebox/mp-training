""" Searching the number with the help of binary search """
# taking a sample list of numbers
li = [2, 5, 8, 9, 11, 14, 17, 18, 22, 25, 26, 29, 30, 31, 35, 36, 38, 44, 48, 55, 59]
print("Enter the number to be searched ")
n = int(input())
start = 0
end = len(li)
flag = 0
while(start <= end):
    mid = (start + end) // 2
    if li[mid] == n:
        print(f"Element found at {mid+1} position")
        flag = 1
        break
    elif n > li[mid]:
        start = mid+1
    else:
        end = mid-1
if flag == 0:
    print("Element not found in the list")

