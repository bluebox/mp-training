from typing import OrderedDict
from sortedcontainers import SortedDict


def solve(a, b):
    return a + b


print(solve(10, 20))

val = lambda a, b: a + b;
print(val(10, 30))

# lambda as key in dict

mp = {"anand": 1, "abhi": 2}
for key, value in mp.items():
    print(key, value)

mp = OrderedDict()
mp["anand"] = 2
mp["abhi"] = 3
for key, value in mp.items():
    print(key, value)

mp = SortedDict()
mp[3] = 30
mp[1] = 10
mp[2] = 20

print(mp)  # Output: SortedDict({1: 10, 2: 20, 3: 30})
