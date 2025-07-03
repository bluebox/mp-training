# import dis

def dual_role():
    data = yield "start"
    # yield data
    while True:
        data2 = 2 * data
        data = yield data2


x = dual_role()
print(next(x))
print(x.send(20))
print(x.send(10))
print(x.send(0))

# dis.dis(dual_role())