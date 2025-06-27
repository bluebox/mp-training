from collections import defaultdict

nums=[1,2,3,4,5,6]
tar=int(input("enter a tar"))
mp=defaultdict(int)

for i in nums:
    rem=tar-i
    if rem in mp:
        print(i)
        break
    else :mp[i]+=1
