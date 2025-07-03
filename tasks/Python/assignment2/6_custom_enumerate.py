def custom_enumerate_filter(iterable, start=0, step=1, predicate=None):
    for index in range(start,len(iterable),step):
        if predicate is None or predicate(iterable[index]):
            yield  index, iterable[index]
            index += step
nums = [3, 4, 7, 8, 9, 13]
for idx, val in custom_enumerate_filter(nums, start=1, step=3, predicate=lambda x: x % 2 == 0):
    print(idx, val)

