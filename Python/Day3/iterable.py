# list,tuple,set,str,dict all are iterable

ans = [1, 2, 3, 4, 5]
for i in ans:
    print(i, end=' ')
print()
ans = (1, 2, 3, 4, 5)
for i in ans:
    print(i, end=' ')
print()
ans = {1, 2, 3, 3, 4, 4, 5, 100}
for i in ans:
    print(i, end=' ')
print()
ans = {'anand': 1, 'abhi': 2, 'sai': 3}
# for  dict we have .items(),.values() and .keys() and inbuild they use .keys() and .keys() is for convinction

for i in ans:
    print(i, end=' ')
print()

for key, value in ans.items():  # ans.items() give key and value as tuple and we unpack it as key,value
    print(key, value)

for value in ans.values():
    print(value)


# for str

for i in 'jai shree ram':
    print(i,end='')