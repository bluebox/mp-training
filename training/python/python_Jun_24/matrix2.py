# print("Task 2: Flatten a 2D matrix into a 1D list using list comprehension.")
# mat=[
#     [1,2,3],
#     [4,5,6],
#     [7,8,9]
# ]
# flatList=[x for x in mat for x in x]
# print(flatList)
nested_tuple = ((2,4), (5, 6))
tpl=((12, 'ravi', (56,76,89)) , (12, 'bhargav', (55,65,78)))
for a , b , c in tpl:
    print("First", a, "then", b,"then ,")
    s1,s2,s3=c
    avg=(s1+s2+s3)/3
    print(s1)
    print(s2)
    print(s3)
    print(avg)