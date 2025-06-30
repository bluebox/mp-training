def custom_enumerate_filter(iterable, start=0, step=1, predicate=None):
    index = start
    for item in iterable:
        if predicate is None or predicate(item):
            yield (index, item)
        index += step
for idx, val in custom_enumerate_filter(['a', 'b', 'c'], start=10, step=5):
    print(idx, val)
nums = [1, 2, 3, 4, 5, 6]
is_even = lambda x: x % 2 == 0
for idx, val in custom_enumerate_filter(nums, start=100, step=10, predicate=is_even):
    print(idx, val)