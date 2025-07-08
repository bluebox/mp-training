  

arr=[-1,2,-2,-1,1,4,6,3,5,-3,0,8]
a=set(arr)
mini=float('inf')
maxi=float('-inf')
s=0
for i in a:
    if i<mini:
        mini=i 
    if i>maxi:
        maxi=i
    s+=i 
print(mini,maxi)
mis=(maxi-mini+1)//2*((maxi+mini))

print(mis-s)