

string=str(input())
d={'{':'}','[':']','(':')'
}
st=[]
t=True
i=0
c=''
while(i<len(string)):
    
    print(i)
    if string[i]=='<':
        k=0
        while(string[i]!='>'):
            
            if string[i]=='/':
                k=1
            else:
                c+=string[i]
            
            i+=1
        print(i,c)
        if k==1:
            c+='>'
            if len(st)>0 and st[-1]==c:
                st.pop()
                c=''
                
                
            else:
                t=False
                 
        
        if c!='':
            c+='>'
            st.append(c)
            c=''
        
    
        print(st,i)
    else:
        if string[i] in d.keys():
            st.append(string[i])
        
        else:
            if len(st)>0 and string[i]==d[st[-1]]:
                st.pop() 
            else:
                t=False
                
    i+=1
print(st,c)
if len(st)!=0:
    
    t=False
if t==True:
    print('valid')
else:
    print('invalid') 

