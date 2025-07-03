a = [1, 2, 3, 4, 5, 6, 7, 8]

iterator = iter(a)
for i in iterator:
    print(i)
    print(next(iterator))

# t = iterator
# print(str(t))
# print(repr(t))