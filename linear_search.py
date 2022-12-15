""" Program to search a number by linear search"""

# taking a random set of number as a list
li = [3, 7, 9, 5, 21, 45, 34, 76, 56, 46, 98, 72, 15, 19, 38]
print("Enter the number to be searched")
n = int(input())
flag = 0
for i in range(len(li)):
    if li[i] == n:
        print(f"The element is found at {i+1} position")
        flag = 1
        break
if flag == 0:
    print("The element is not found")

