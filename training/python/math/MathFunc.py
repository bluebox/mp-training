import math
import random as r
s = set()
while(len(s) < 10):
    s.add(r.randint(1,100))
l = list(s)
total = sum(l)
mean = total/(len(l))

sd = 0
for i in l:
    sd+=math.pow((i-mean),2)
sd = sd/len(l)
sd = math.sqrt(sd)
print("Standard dev :: ",sd)

print(l)
gaussian = []
for i in l:
    val = 1/math.sqrt(2*math.pi*math.pow(sd,2))
    val2 = math.exp(-math.pow((i-mean), 2)/(2*math.pow(sd,2)))
    val = val2*val
    gaussian.append((val))
print(gaussian)


