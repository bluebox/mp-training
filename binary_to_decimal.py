""" Program to input binary number and convert it to decimal """

print("Enter the binary number")
n = int(input())
sum = 0
i = 0
while n > 0:
    rem = n % 10
    if rem == 1:
       sum = sum + 2 ** i
    elif rem != 0:
        print("not valid binary number")
        i = -1
        break
    i = i+1
    n = n // 10
if i != -1:
    print(sum)
