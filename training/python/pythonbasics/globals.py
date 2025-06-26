# b = globals().copy()
# x = 10
# y = x
# def outer():
#     # x = 20
#     # print(x)
#     def inner():
#         print(x)
#     inner()
#     return x
#
# # print(x)
# # print(outer())
# a = globals().copy()
# for item in a.items():
#     print(item)
# print(globals())
# print(len(globals()))

c = 0

def inc():
    global c
    globals()['c'] += 1
    print(c)
    return globals()['c']
print(inc(),inc(),inc(),inc())