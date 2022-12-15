""" Program to check whether a function is right angled triangle or not """

print("Enter the three sides of a triangle ")
a = int(input())
b = int(input())
c = int(input())
flag = 0
if a > b and a > c:
    if a * a == b*b + c*c:
        flag = 1
if b > a and b > c:
    if b*b == a*a + c*c:
        flag = 1
if c > a and c > b:
    if c*c == a*a + b*b:
        flag = 1
if flag == 1:
    print("It is right angled triangle")
else:
    print("It is not a right angled triangle")