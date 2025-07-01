def custom_enumerate_filter(iterable,start=0,step = 1,predicate = None):
        i = start
        while i<len(iterable):
            if predicate is None or predicate(iterable[i]):
                yield i,iterable[i]
            i+=step

def predicate(a):
    return a%2 == 0

for i in custom_enumerate_filter([1,2,3,4,5],start=1,step = 2,predicate=predicate):
    print(i)