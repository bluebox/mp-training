
def custom_enumerate_filter(iterable, start=0, step=1, predicate=None):
    if not hasattr(iterable,'__iter__'):
        raise TypeError("Not provided iterable")

    if not isinstance(start,int):
        raise ValueError("start must be int")
    if not isinstance(step,int):
        raise ValueError("step must be int")
    index = start
    i = 0
    if isinstance(iterable,dict):
        items = iterable.items()
    else:
        items = iterable
    items_lst = list(items)
    while i < len(items_lst):
        item = items_lst[i]
        if predicate is None or predicate(item):
            yield (index, item)
        index += step
        i += step  

#with a predicate function
def is_a_infruit(item):
    return 'a' in str(item)


try:
    # data = ['apple', 'banana', 'orange', 'grape', 'melon']
    # data = 1234
    # data = {1, 2, 3, 4}
    data = {'apple': 1, 'banana': 2, 'orange': 3, 'grape':4, 'melon':5}
    tup = custom_enumerate_filter(data, predicate=is_a_infruit)
    print(tuple(tup))
except Exception as e:
    print(e)