


a=[]
b=[1,2,3,4]
def f(a,i,c,b):
    print(i)
    print(c)
    if i==len(b):
        if c not in a:
            a.append(c[:])
        print(c)
        return a

    print('hello')
    c.append(b[j])
    f(a,j+1,c,b)
    c.pop() 
    f(a,j+1,c,b)
c=[]
f(a,0,c,b)
print(a)

        
    