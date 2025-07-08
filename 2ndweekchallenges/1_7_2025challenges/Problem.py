
num=3
t=0
c={}
for i in range(1,num+1):
    a=i 
    n=0
    while(a>0):
        if a in c:
            t+=n 
            t+=c[a] 
            break
        a=a&a-1
        n+=1  
    print('n',n,'t',t,'i',i)
    print(a)
    if i not in c:
        print('hi',i)
        c[i]=n 
    t+=n 

print(t)

