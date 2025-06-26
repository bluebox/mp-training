ans=[1,2,3,4]
for item in enumerate(ans):
    print(item)

for ind,val in enumerate(ans):
    print(ind,val)

# enumerate gives index and values of a iterable

ans={'anand':1}

for ind,val in enumerate(ans):
    print(ind,val)