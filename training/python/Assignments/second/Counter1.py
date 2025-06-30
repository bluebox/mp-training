def create_counter(initial_count=0):
    temp=initial_count
    def counter_func():
        nonlocal initial_count
        initial_count+=1
        def reset_counter():
            nonlocal initial_count
            initial_count=temp
            return initial_count
        counter_func.reset=reset_counter
        return initial_count
    return counter_func

count=create_counter(-2)
print(count())
print(count())
print(count.reset())
print(count())
print(count())



