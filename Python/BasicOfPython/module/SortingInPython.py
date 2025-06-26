ans=list(map(int,input().split()))
ans.sort(reverse=True)
print(ans)

ans=[1,"anand","abhi",2]
ans.sort(key=lambda x:str(x),reverse=True)
print(ans)
ans="anand"
val=ans.capitalize()
print(val)