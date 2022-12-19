""" Program to scramble the words given as input """
import random
def scramble(s2, n):
    if s2[n-1].isalnum():
        d = n-2
    else:
        d = n-3
    while n > 0:
        n -= 1
        a = random.randint(1, d)
        b = random.randint(1, d)
        c = s2[a]
        s2[a] = s2[b]
        s2[b] = c
    print(s2)
print("Enter the word to be scrambled ")
s1 = list(input())
n = len(s1)
if n > 3:
    scramble(s1, n)


