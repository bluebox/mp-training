def fun(s):
    if not s:
        return True
    if len(s)==1:
        return False
    stack=[]
    dict1={')':'(','}':'{',']':'['}

    i=0
    while i<len(s):
        if s[i] in dict1.values():
            stack.append(s[i])
            i+=1
        elif s[i] in dict1.keys():
            if not stack or stack[-1]!=dict1[s[i]]:
                return False
            
            stack.pop()
            i+=1
        elif s[i]=="<":
            new_one=""
            i+=1
            while i<len(s) and s[i]!=">":
                new_one+=s[i]
                i+=1
            if i==len(s):
                return False
            if new_one[0]=="/":
                a1=new_one[1:]
                if not stack or stack[-1]!=a1:
                    return False 
                stack.pop()
            else:
                stack.append(new_one)
                i+=1
        else:
            i+=1
    return not stack
s=input("enter a string:")
print(fun(s))