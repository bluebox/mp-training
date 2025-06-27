import bisect
a = [1,2,3,4,5,6,7,8]
print(bisect.bisect_right(a,4))
bisect.insort(a,8)
bisect.insort(a,4)
print(bisect.bisect_left(a,4))

