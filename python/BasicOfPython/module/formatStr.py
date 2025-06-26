ans=22
print(f"i am anand and iam {ans} years old")

i=0
while i<=10:
    print(i,end=" ")
    i+=2
print()
ans=[1,2,3,4,5,6,7,8,9,10]
for i in range(len(ans)-1,-1,-1):
    print(ans[i],end=" ")
print()
thislist = ["apple", "banana", "cherry"]
thislist.insert(0, "orange")
print(thislist)