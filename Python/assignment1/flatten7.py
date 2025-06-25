def flatten_list(val):
    ans=[]
    for i in val:
        try:
            a=len(i)
            for j in range(a):
                ans.append(i[j])
        except Exception as e:
            ans.append(i)
    return ans
val=[1,[1,2,3]]
val=flatten_list(val)
print(val)

#-----------------------------------using isinstance

def solve(val):
    ans=[]
    for i in val:
       if isinstance(i,list):
           for j in i:
               ans.append(j)
       else :
           ans.append(i)
    return ans
val=[1,[1,2,3]]
val=solve(val)
print(val)
