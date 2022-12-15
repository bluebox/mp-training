""" Printing the triangle pattern """

print("Enter the lines of triangle to be printed ")
n = int(input())
for i in range(n):
    d = n-i-1
    s_1 = " " * d
    print(s_1, end="")
    d = i+1
    s_1 = " ".join("*" * d)
    print(s_1, end=" ")
    print()
