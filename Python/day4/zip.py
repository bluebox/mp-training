li=[1,2,3,4]
ans=[10,20,30,40,50]

val=list(zip(li,ans))
print(val)
# zip take the input of iterables and group their element one by one as tuple

li=[[1,2],3,4,5]
val=list(zip(ans,li))
print(val)

val=list(zip(val,li,ans))
print(val)
# zip can take any no of iterables as parameter and it make group their element