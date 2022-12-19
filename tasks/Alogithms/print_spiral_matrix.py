# Program to print the spiral matrix

print("Enter the number of rows and columns in the matrix ")
rows = int(input())
cols = int(input())
matrix = [[0 for _ in range(cols)] for _ in range(rows)]
print("Enter the values of the matrix ")
for i in range(rows):
    for j in range(cols):
        matrix[i][j] = int(input())
print("The Matrix is :")
for i in range(rows):
    print(matrix[i])
direction = 0
top = 0
down = rows -1
right = cols-1
left = 0
res = []
while top <= down and left <= right:
    if direction == 0:
        for i in range(left, right+1):
            res.append(matrix[top][i])
        top += 1
    elif direction == 1:
        for i in range(top, down+1):
            res.append(matrix[i][right])
        right -= 1
    elif direction == 2:
        for i in range(right, left-1, -1):
            res.append(matrix[down][i])
        down -= 1
    elif direction == 3:
        for i in range(down, top-1, -1):
            res.append(matrix[i][left])
        left += 1
    direction = (direction + 1) % 4
print(res)


