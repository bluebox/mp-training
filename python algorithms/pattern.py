size = int(input('Enter size: '))
for i in range(2*size-1):
    for j in range(4*size-3):
        if i%2 == 0:
            print('-',end='')
