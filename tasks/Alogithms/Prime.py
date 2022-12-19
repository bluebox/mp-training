""" To check the number entered is prime or not """

print("Enter the number ")
n = int(input())
i = 1
flag = 0
while i * i < n:
    i += 1
    if n % i == 0:
        flag = 1
        break
if flag == 0:
    print("it is a prime number")
else:
    print("it is not a prime number")

