val=[1,2,3,4,5]

for item in val:
    print(item)
for i in range(10):
    print(i,end=' ')

# range() is also works as slicing where it also have start,stop,stepOver
print()
for i in range(10,-1,-2):
    print(i,end=' ')
print()
print(val[-1::-1])