def func(val):
    return val
print(func(10))

def var_args_func(*a):
    for i in a:
        print(i, end=" ")

def named_args_func(**a):
    for k in a:
        print(k,":",a[k])
def func1(a1,b1,*a,**b):
    print("a1:",a1)
    print("b1:",b1)
    print("a:",a)
    print("b:",b)
    print(type(b))

var_args_func(1,2,3,4,54,6)
print()
named_args_func(val1=10,val2=20,val3=[10,20,30])
func1(10, 20, 1, 2, 3, 4, 5, 6, 76, arg1="hello", arg2=10, arg3={1, 2, 3, 4, 5})