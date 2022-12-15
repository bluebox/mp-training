"""
Program to check whether the string entered is palindrome or not

"""

print("Enter the string")
n = input()
d = len(n)
flag = 0
for i in range(d // 2):
    if n[i] != n[d-i-1]:
        flag = 1
        break
if flag ==0:
    print("The string is palindrome ")
else:
    print("The string is not palindrome")


