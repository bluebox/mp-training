def fun(arr):
   
    
    arr1=[]

    sum1=sum([i for i in range(min(arr),max(arr)+1) ])
    for i in arr:
        if i not in arr1:
            arr1.append(i)
    if sum1==sum(arr1):
        return "already given correct one"


    return sum1-sum(arr1)
n=int(input("enter no of numbers u want yo enter"))
arr=[]
for i in range(n):
    k=int(input())
    arr.append(k)
print("output is :", fun(arr))

