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


