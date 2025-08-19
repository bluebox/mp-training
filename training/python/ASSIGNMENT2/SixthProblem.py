def custom_enumerate_filter(iterable, start=0, step=1, predicate=None):
    print("---------------------------------------------------------")
    print(iterable)
    if predicate:
        for j in range(start,len(iterable),step):
            if predicate(iterable[j]):
                yield (start, iterable[start])
            start += step
    else:
        for j in range(start,len(iterable),step):

                yield (start, iterable[start])
                start+=step


for i in custom_enumerate_filter([1,2,0,4,5,6,7,0,0,False,7,'d'],2,4,lambda x:bool(x)):
    print(i)

for i in custom_enumerate_filter([1,2,0,4,5,6,7,0,0,False,7,'d']):
    print(i)

for i in custom_enumerate_filter("hiuitriurggtuyt0tjrh",predicate=lambda x:bool(x)):
    print(i)