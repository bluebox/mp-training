
def is_even(n):
    if n%2==0:
        return True
    return False
    
def custom_enumerate_filter(iterable,start=0,step=1,predicate=None):
    for i in iterable:
        if predicate:
            if predicate(i):
                yield(start,i)
            # else:
            #     continue
        else:
            yield(start,i)
        start+=step
is_een=10
e=custom_enumerate_filter([1,2,3,4,5],5,2,is_een)
for i in e:
    print(i)


        

    
