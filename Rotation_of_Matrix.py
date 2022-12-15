# Program to rotate a 2-d matrix by 90 degree

print("Enter the number of rows and columns in the matrix")
rows = int(input())
cols = int(input())
mat = [[0 for _ in range(cols)] for _ in range(rows)]
trans = [[0 for _ in range(rows)] for _ in range(cols)]
print("Enter the elements of matrix ")
for i in range(rows):
    for j in range(cols):
        mat[i][j] = int(input())
# print(mat)
# print(trans)
for i in range(rows):
     print(mat[i])
for i in range(rows):
    for j in range(cols):
        # print(mat[i][j])
        trans[j][i] = mat[i][j]
for i in range(cols):
    print(trans[i])