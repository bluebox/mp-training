nums=[1,2,3]
a,b,c=nums
print(a,b,c)
nums=(1,2,3,4)
a,*b,c=nums
print(a,b,c)
b[0]=1
print(b)
## difference between *a ,**b is *a is list and ** b is a dict