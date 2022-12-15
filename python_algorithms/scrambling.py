import re
from random import shuffle


statement = input('Enter a phrase: ')
lis = statement.split(' ')
for i in range(len(lis)):
    s = re.findall("[,.?!]", lis[i][len(lis[i])-1])
    if len(s) == 1:
        temp = lis[i][:len(lis[i])-1]
    else:
        temp = lis[i]
    if len(temp) > 3:
        x = list(temp[1:len(temp) - 1])
        shuffle(x)
        temp = temp[0] + "".join(x) + temp[len(temp)-1]
    if len(s) == 1:
        lis[i] = temp + lis[i][len(lis[i])-1]
    else:
        lis[i] = temp

print(" ".join(lis))
