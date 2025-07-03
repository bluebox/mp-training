# def func(a = 20,b = 30):
#     print(a,b)
#
# func(b=50,a=50)
# a = 20
# b = 20 + 0j
# print(a is 20)

# def func(a=20,*args,q=30,**kwargs):
#     print(a,args,q,kwargs)
#
# func(10,1,2,6,3,4,q=20,b=40)
def fn(k):
    s = "a"
    while len(s) < k:
        t = ""
        for i in s:
            if "a" <= i < "z":
                t += (i + chr(ord(i)+1))
            elif i == "z":
                t += (i + "a")
        s = t
        # print(s)
    return s[k]

print(fn(17))