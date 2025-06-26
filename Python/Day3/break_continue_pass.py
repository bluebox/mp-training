for i in range(10):
    print(i)
    break
#completelly break from loop

for i in range(10):
    if i==5:
        continue
    print(i)
# this print 0 to 10 except 5 because continue start the begin of the loop again

for i in range(10):
    pass
#pass is used when we don't want to execute the block for example

def solve():
    pass
# without any code it giving error to overcome we use pass
