# Task 2:
# Create a list of 10 numbers. Replace all numbers at even indexes with 0.
numbers=[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,28,19,20]
print("replace all even indexes with 0.")
for number in range(len(numbers)):
    if(number % 2 == 0):
        numbers[number]=0


print(numbers)