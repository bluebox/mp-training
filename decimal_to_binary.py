""" Program to take input numbers as decimal and convert it to binary """
print("Enter a decimal number")
n = int(input())
li = []
while n > 0:
    rem = n % 2
    li.append(rem)
    n = n // 2
li.reverse()
for i in li:
    print(i, end=(""))


