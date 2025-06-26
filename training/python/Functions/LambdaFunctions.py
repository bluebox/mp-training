def funct(a, b):
    if(a>b):
        return True
    else:
        return False

lamb1 = lambda a,b:funct(a,b)
print(lamb1(10,20))

has_vowel = lambda s:all(True if i.lower() in "aeiou" else False for i in s) or any(True if i.lower() in "aeiou" else False for i in s)
print(has_vowel("AEO"))
has_vowel1 = lambda s,c = True:c and any(True if i.lower() in "aeiou" else False for i in s)
print(has_vowel1("hello"))


lamb2 = lambda a,b:True if a>b else False