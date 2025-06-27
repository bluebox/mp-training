from collections import defaultdict
ans=list(map(str,input().split()))
mp=defaultdict(list)

for i in ans:
    val=[0]*26
    for j in i:
        val[ord(j)-ord('a')]+=1
    mp[tuple(val)].append(i)
for val in mp.values():
    print(val)