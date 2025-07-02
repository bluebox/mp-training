from my_package.my_package_file import MyFilter
import random
def predicate1(val):
    return val<100
def predicate2(val):
    return val%2==0

vals = []
for i in range(100):
    vals.append(random.randint(0,200))

print(vals)
recv_val = []
def catch():
    try:
        while(True):
            val = yield
            recv_val.append(val)
    except GeneratorExit:
        pass
sinc = catch()
second = MyFilter(sinc,predicate1).my_coroutine_filter()
first = MyFilter(second,predicate2).my_coroutine_filter()

next(first)



for i in vals:
    first.send(i)
first.close()
print("filtered vals :: ",recv_val)
print(len(recv_val))
