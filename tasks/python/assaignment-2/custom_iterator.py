def custom_enumerate_filter(iterable,start=0,predicate = None):
    if predicate == None:
        i = start
        while(i<len(iterable)):
            yield (i,iterable[i])
            i+=1
    else:
        i = start
        while i<len(iterable):
            if predicate(iterable[i]):
                yield (i,iterable[i])
            i+=1

def predicate(a):
    if(a%2 == 0):
        return True
    return False

for i in custom_enumerate_filter([1,2,3,4,5],predicate=predicate):
    print(i)