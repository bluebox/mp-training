def solve(n):
    i=1
    while i<=n:
        yield i
        i+=1
val=solve(5)
for i in val:
    print(i)