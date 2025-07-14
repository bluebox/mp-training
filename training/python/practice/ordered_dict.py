from collections import OrderedDict

d1 = OrderedDict({1: [10], 2: [20]})
d2 = OrderedDict({3: [30], 1: [11]})

dd1 = {1: [10], 2: [20]}
dd2 = {3: [30], 1: [11]}

for k, v in d2.items():
    if k in d1:
        d1[k].extend(v)
    else:
        d1[k] = v


for k1, v1 in dd2.items():
    if k1 in dd1:
        dd1[k1].extend(v1)
    else:
        dd1[k1] = v1

print(d1)
print(dd1)
