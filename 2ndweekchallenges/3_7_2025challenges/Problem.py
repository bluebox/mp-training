

arr=[[5,10],[7,15],[15,20],[12,14],[25,33],[2,4]]
b=[True]
c=[arr[0]]

for i in range(1,len(arr)):
    flag=1
    n=len(c)
    while(n>0):
        
        last=c.pop() 
        c.insert(0,last)
        if arr[i][0]<last[0] and arr[i][1]<last[0]:
            n-=1 
            continue
            
            
        if last[1]>arr[i][0]:
            flag=0
            b.append(False)
            break
        n-=1 
    if flag==1:
        b.append(True)
    while(n>0):
        c.insert(0,c.pop())
        n-=1
    
    c.append(arr[i])
            
    
    
    
    
    
    
  

print(arr)
print(b)


