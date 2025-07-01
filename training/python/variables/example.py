a = range(1,10)
for i in a:
    print(i)
    i+=1
    print(i)
# next(a) error
a = iter(a)

while True:
    try:
        print(next(a))
    except StopIteration:
        print("end reached")
        break
    # finally:
    #     print("reached finally")
print("out of while")

li = list("hello")
print(" ".join(li))
s = "".join(li)
n = len(li)
for i in " ".join(li):
    print(n*" "+i)
    n-=1
print(s)
print((4//4))
