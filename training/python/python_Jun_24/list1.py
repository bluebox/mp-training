
# Task 1:
# Write a program that takes a list of integers and returns a new list with only the even numbers.

numbers=[]
evenNumbers=[]
print("Enter 10 numbers: ")
for i in range(10):
    number=int(input())
    numbers.append(number)
    if(number%2==0):
        evenNumbers.append(number)

print(numbers)
print(evenNumbers)

print("input sapearated by space")
# evenNumbers=[]
evennumbers=[int(i) for i in input().split() if(int(i)%2==0)]
print(evennumbers)
