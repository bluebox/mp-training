deci = int(input("enter decimal no. :"))
quo = 0
bin = []
while True:
    if deci<=0:
        break
    quo = int(deci/2)
    rem = str(deci%2)
    bin.insert(0,rem)
    deci = int(deci/2)

print(''.join(bin))

