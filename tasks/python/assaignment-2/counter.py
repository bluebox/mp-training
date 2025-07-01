# f= None
def create_counter(initial_count = 0):
    counter = [initial_count]
    def counter_func():
        def func():
            counter[0] = initial_count
            print("counter reset to ",counter[0])
        counter_func.reset = func
        counter[0]+=1
        return counter[0]
    counter_func()
    counter = [initial_count]
    return counter_func
a = create_counter(10)
print(a())
print(a())
a.reset()
print(a())
b = create_counter()
print(b())
b.reset()
print(b())