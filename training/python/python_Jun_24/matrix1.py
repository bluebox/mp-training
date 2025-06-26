print("Task 1: Print the sum of each row in a 3×3 matrix.")
mat=[
    [1,2,3],
    [4,5,6],
    [7,8,9]
]
for i in mat:
    print(f"Row sum {i} is {sum(i)}")