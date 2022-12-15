"""Scramble words problem"""

import random

str_inp = input("Enter a word\n")
PUNCTUATIONS = '''? ,.;!'''
COUNT = 0

if len(str_inp) > 3:
    for char in str_inp:
        if char in PUNCTUATIONS:
            COUNT += 1
    COUNT += 1
    new_str = str_inp[1:-COUNT]
    new_str = random.sample(new_str, len(new_str))
    new_str.insert(0, str_inp[0])
    new_str.append(str_inp[-COUNT:len(str_inp)])
    print(''.join(new_str))
else:
    print("invalid Input")
