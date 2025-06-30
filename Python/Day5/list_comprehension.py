#list comprehension is the simple way to assign value of list
ans=[-1 for _ in range(10)]
print(ans)

ans={i for i in range(20)}#set
print(ans)

ans={i:20 for i in range(10)}
print(ans)

#2d list with all values equal to -1 like that we use for dp problem
# vector<vector<int>>dp(n,vector<int>(m,-1)) this is equivalent to this

dp=[[-1 for _ in range(10)] for i in range(10)]
for i in dp:
    print(i)