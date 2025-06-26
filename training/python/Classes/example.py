d = {'a':10,'b':30,'d':20,'c':40}
d_sort = dict(sorted(d.items(),key=lambda z:z[1]))
print(d_sort)

l = [[1,2,3,4],[5,6,7,8,9]]

l1 = [x for i in l for x in i if x != 5]
print(l1)

# l = lambda a:
