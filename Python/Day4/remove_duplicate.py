from collections import defaultdict

ans = list(map(int, input().split()))

ans_without_dup = list()
for i in ans:
    if i not in ans_without_dup:
        ans_without_dup.append(i)
print(ans_without_dup)

# this takes O(n^2)
# we can use map for O(n)
ans_without_dup = list()

mp = defaultdict(int)
for i in ans:
    mp[i] += 1
for key in mp:
    ans_without_dup.append(key)
print(ans_without_dup)
